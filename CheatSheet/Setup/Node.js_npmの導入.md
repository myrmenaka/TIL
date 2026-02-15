# Node.jsとnpmの導入

## ■ Node.jsのインストール（自動でnpmもインストール）

### Node.jsの現在有効になっているインストールのバージョン確認

```
# dnf module list nodejs
```

### Node.js のバージョン選択状態をリセットして、どのバージョンも選ばれていない状態に戻す

```
# dnf module reset nodejs
```

### Node.js 18 のストリームを有効化し、Node.js 18 本体をインストール

```
# dnf -y module install nodejs:18
```

※ -yオプション＝自動で「yes」にする自動承認

### ※ インストールせずに、バージョン切り替えだけの場合

```
# dnf module reset nodejs
# dnf module enable nodejs:18
```

### Node.jsの現在有効になっているインストールのバージョン確認

```
# dnf module list nodejs
```

### バージョン確認

```
$ node -v
$ npm -v
```

## ■ プロキシ設定（必要な環境のみ）
※ 一般ユーザーで設定する
```
$ npm config set proxy http://~~~:~~~
$ npm config set https-proxy http://~~~:~~~
```
※ 設定後再起動

### 設定の確認
```
$ npm config get proxy
$ npm config get https-proxy
```

## ■ 環境変数の設定（設定方法のみ参考）
※ 学習環境で、ホームディレクトリに node-module を置いて環境変数を参照する実習内容だったため設定している  
本来は、プロジェクト毎に node-module を置くため不要  
（プロジェクト毎にモジュールインストールする）  

### viで編集
```
# vi .bashrc
```
※ 環境変数として「`NODE_PATH`」、ユーザのホームディレクトリにある「`.bashrc`」へ定義

【変更箇所】
```
～
13．export PATH
14．export NODE\_PATH=~/node\_modules　←
15．
～
```

### ファイルの再読込
```
$ source .bashrc
```
### 環境変数の設定の確認
```
$ echo $NODE\_PATH
```

## ※ 環境変数（NODE_PATH）は不要
Node.js は プロジェクト内の node_modules を自動で参照するため、NODE_PATH の設定は不要  
（むしろ設定すると壊れることがあるため非推奨）  

## ■ パッケージ・モジュールのインストール

ここまでの設定に不備が無ければ  
```
$ npm install パッケージ・モジュール名
```
でインストール可能  
※ 「,」で区切って列挙可能  

## ※ 注意
npm install は必ず「そのプロジェクトのディレクトリ」で実行する  
→ `package.json` があるプロジェクトフォルダで実行する必要がある  

## ■ プロジェクトごとのパッケージ管理（正しい方式）
Node.js の世界では プロジェクトごとに node_modules を持つのが標準  

### プロジェクトの作成
```
$ mkdir myapp
$ cd myapp
```
### package.json の作成
```
$ npm init -y
```
### パッケージのインストール（プロジェクト内で実行）
```
$ npm install パッケージ名
```
### 例：Express のインストール
```
$ npm install express
```
※ Express は、JavaScriptのフレームワークの一つ  
### Express のバージョン確認
```
$ npm list express
```

## ■ 実行と動作確認、終了

### 単体ファイルの実行
※ JavaScriptファイルの実行
```
$ node プロジェクト名/ファイル名
```
### 終了
`ctrl + C`

## ■ Express Generator を使ったひな形作成（推奨）

### Express Generator のインストール（グローバル）
```
$ npm install -g express-generator
```
※ 「express」 という CLI コマンドが使えるようになる

### プロジェクト作成
```
$ express myapp
```
※ myapp/ というディレクトリに Express アプリのひな形一式が自動生成

### ■ 作成される一式
```
myapp/
├── app.js            ← アプリのエントリポイント
├── bin/
│   └── www           ← サーバ起動スクリプト
├── routes/
│   ├── index.js      ← ルーティング
│   └── users.js
├── views/            ← テンプレート（pug/ejs など）
├── public/           ← 静的ファイル
├── package.json
└── ...
```
※ Express は「最低限だけ作る」思想だから、Laravel ほど重厚ではない  

### 依存パッケージをインストール
```
cd myapp
npm install
```
※ `package.json` に書かれた依存（express など）が node_modules/ に入る

### サーバの起動
```
npm start
```
※ bin/www がサーバ起動スクリプトとして動く  
※ デフォルトでは http://localhost:3000 でアクセス可能  

※ 単体ファイルの実行と違う点は、こちらは  
「Express アプリとしての正しい構造を持ったプロジェクトを起動する」  
→ PHP/Laravelの、`laravel new myapp`, `php artisan serve` と同じ世界  



