# Docker環境構築手順

## 1. プロジェクトフォルダを作る
```
mkdir myapp
cd myapp
```
・`Dockerfile` や `docker-compose.yml` を置く“ルート”になる場所  
・Laravel ならここにアプリ本体も入る（srcディレクトリの中に入れる）  

※ Windowsのエクスプローラーで作成するので問題ない  

ディレクトリツリー（例）
```
project-folder/
├─ docker/
│   ├─ Dockerfile
│   ├─ php.ini
│   └─ apache/
│       └─ httpd.conf（必要なら）
├─ docker-compose.yml
├─ src/   ← Laravel プロジェクトをここに作るパターン
│   ├─ app/
│   ├─ bootstrap/
│   ├─ config/
│   ├─ public/
│   ├─ routes/
│   └─ ...
└─ README.md
```

## 2. Dockerfile を作成する
```
touch Dockerfile
```
※ エクスプローラーでもOK  

### ■ Dockerfile の役割
・イメージを作るための設計図  
・RUN（ビルド時）と CMD（起動時）を分けるのが本質  

## 3. Dockerfile に必要最低限を書く
#### 例：Apache + PHP の最小構成  
```Dockerfile
FROM almalinux:9

RUN dnf install -y httpd php php-cli php-fpm

CMD ["/usr/sbin/httpd", "-D", "FOREGROUND"]
```

### ■ ポイント：
・RUN は“イメージを作るための処理”  
・CMD は“コンテナ起動時のメインプロセス”  
・USER は書かなくて OK（デフォルト root）  

## 4. docker-compose.yml を作る（複数サービスを扱う場合）
```
touch docker-compose.yml
```
※ エクスプローラーでもOK  

### 例：Apache + PHP + MySQL
```
yaml
services:
  web:
    build: .
    ports:
      - "8080:80"
    volumes:
      - .:/var/www/html
```

### ■ ポイント：
・Dockerfile は“中身の作り方”  
・docker-compose は“どう起動するか”  

## 5. イメージをビルドする
```
docker compose build
```
・RUN が実行されるのはこのタイミング  
・インストール結果がイメージに保存される  

## 6. コンテナを起動する
```
docker compose up -d
```
・CMD が実行されるのはこのタイミング  
・Apache なら httpd -D FOREGROUND が動く  

## 7. コンテナに入る（必要なら）
```
docker compose exec web bash
```
・`composer install`  
・`php artisan migrate`  
・`chmod/chown`  

などを行う  

→ そのコンテナに対しての命令  

## 8. アプリを配置する（Laravel など）
### ■ 新規作成
```
composer create-project laravel/laravel .
```
### ■ 既存プロジェクトをマウント
```
docker-compose.yml の volumes で OK。
```

## 9. 動作確認

・http://localhost:8080  
（Apache + Laravelの場合、ドキュメントルートに注意）  
・Apache のログ  
・phpinfo  
・Laravel のトップページ  

## 重要なポイント
### ✔ RUN と CMD の使い分け
RUN：インストール・設定  
CMD：サーバー起動（フォアグラウンド）  

### ✔ ENTRYPOINT を使うかどうか
固定したいコマンドがあるなら ENTRYPOINT  
引数だけ変えたいなら CMD  

### ✔ USER の扱い
デフォルト root  
必要なら最後に一般ユーザーに切り替える  

## まとめ
Dockerfile で“中身”を作り、  
docker-compose で“起動方法”を決め、  
RUN はビルド、CMD は起動  
