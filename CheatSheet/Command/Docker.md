# Docker コマンド

## Dockerfile 変更したときのコマンド
### ■ コンテナ停止
```
docker compose down
```
### ■ イメージをキャッシュ無しで再ビルドする
```
docker compose build --no-cache
```
※ 特にタイムゾーン設定はOSレイヤーなので、キャッシュが残ると反映されないことがある
### ■ コンテナ起動
```
docker compose up -d
```

## ??

### ■ OSのタイムゾーン確認
```
docker compose exec app date
```
