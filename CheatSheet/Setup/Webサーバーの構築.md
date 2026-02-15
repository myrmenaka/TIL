# Webサーバーの構築

## 構築の流れ
①パッケージのインストール  
②設定ファイルの編集  
③ファイアウォールの設定  
④サービスの起動・自動起動の設定  
※②～④は順不同  

## dnf installコマンドによるパッケージのインストール

### ■ インストール済みパッケージの確認
```
$ dnf list installed | grep http
```
※他に競合する Web サーバー（nginx など）が無いか確認する（バッティングする可能性があるため）

※以降root権限での作業

### ■ ApacheHTTPサーバーのインストール
```
# dnf install httpd
```
→ 全て `y`

### ■ インストール確認
```
# dnf list installed | grep httpd
```

### Web サーバーの起動確認（まだ外部からはアクセス不可）
```
# systemctl start httpd
```
### 起動確認
```
# systemctl status httpd
```
・緑の● → active(running)  
・白の○ → inactive(dead)  
### webサーバーの停止
```
# systemctl stop httpd
```
※この段階では 外部からアクセス不可  
→ファイアウォールがhttpをブロックしているため（デフォルトでは必要最低限のネットワーク通信しか受け付けない）


## ※ここから先は一時設定なので省略可
## ファイアウォールの設定
### 現在のファイアウォールの設定を確認
```
# firewall-cmd --list-all
```
※この-allを-項目で指定可能（例：firewall-cmd --list-services など）
この中の services: cockpit dhcpv6-client ssh を確認する
→デフォルトではこの3つのみが許可されている
（
`cockpit` → Web 管理ツール Cockpit（TCP 9090）
`dhcpv6-client` → DHCPv6 のクライアント通信（IPv6 のアドレス取得に必要）
`ssh` → SSH（TCP 22）
）
httpが無いことが確認できる
→ファイアウォールによってブロックされている

### httpサービスの接続を一時的に許可する
```
# firewall-cmd --add-service=http --zone=public
```
※ゾーンは初期ではpublicのみ（最も制限が強い標準ゾーン）  
成功時は success が表示される

### もう一度ファイアウォールの設定確認
```
# firewall-cmd --list-services
```
→httpが追加されていることを確認

※ httpを許可したことでリモートから接続可能になる（Apacheのテストページが表示される）


### システム起動時のwebサーバーの自動起動とファイアウォールの設定

これまでの設定はOSを再起動すると無効になってしまう
```
# systemctl status httpd
```
→サーバーは停止している
```
# firewall-cmd --list-all
```
→servicesの中から追加していたhttpは無くなってる


## Web サーバーとファイアウォールの永続化設定
※ここから設定を永続的なものにしていく  
### ■ 再起動すると設定が消える理由
・httpd は自動起動が無効  
・firewall の一時設定は permanent ではない  
→ 再起動後は httpd が停止し、http が許可されていない状態に戻る

### ■ webサーバーの自動起動設定
OS起動時に自動的に起動するようにする
```
# systemctl enable httpd
```
成功時には `Created symlink ～` と表示される  
※ enable は「自動起動を有効にする」だけで、今すぐ起動はしない

### ■ 起動確認
```
# systemctl status httpd
```
`enabled;` が追加されていることを確認
※ もともとは `disabled;` となっていた
※ 自動起動を有効にしただけなので起動はしていない → 再起動もしくはコマンドで起動する
### ■ 再起動の場合
```
# systemctl restart httpd
```

## ファイアウォールの永続設定
```
# firewall-cmd --add-service=http --zone=public --permanent
# firewall-cmd --reload
```
※permanentオプションを付けて実行した場合、即座には反映されないので、`reload` で変更を反映させる必要がある  

いずれも成功時には `success` と表示される

### ■ ファイアウォールの設定確認
```
# firewall-cmd --list-all
```
### ■ 再起動して、webサーバーとファイアウォールが自動起動・永続的な設定になっているか確認
```
# systemctl restart httpd
```
→サーバーが起動しているか
```
# firewall-cmd --list-all
```
→httpがあるか  
リモートで接続できるか

### ※ 状態だけ確認したい場合
サービスの稼働状態の確認
```
# systemctl is-active httpd
```
サービスの自動起動の状態確認
```
# systemctl is-enabled httpd
```

# ログファイル確認
webサーバー（Apache）のログは `/var/log/httpd/` ディレクトリに記録されている

・access_log : アクセスログ  
・error_log : エラーログ  

### ■ リアルタイムでログを見る
```
# tail -f /var/log/httpd/access_log
```

# index.htmlの配置

・`/var/www/html/` ドキュメントルート（Apache の公開ディレクトリ）  
・`index.html`: ウェルカムページ  

※ コマンド全体をsudoコマンドに適応させるには、sh -c コマンドで実行する  

### ■ index.htmlファイルにHello,Worldを記述する
```
$sudo sh -c "echo 'Hello,World' > /var/www/html/index.html"
```
ブラウザで表示すると、画面上に「Hello,World」と表示される  

※ SELinux により、index.html の所有者が root でないとブロックされる場合がある


### ※ ドキュメントルートの変更（必要な場合）
#### ・Apacheの設定ファイル  
`/etc/httpd/conf/httpd.conf` 

#### 変更箇所
```
DocumentRoot "/var/www/html"
Listen 80
```
→ DocumentRoot を変更すると公開ディレクトリが変わる  

※このファイルの、Listen 80 ←Apacheのサーバーが待ち受けるポート番号  


## /var/www/html のパーミッション設定（一般ユーザーが編集する場合）

### ■ /var/www/html のグループをapacheに変更
```
# chown -R root:apache /var/www/html
```
※ `/var/www/html/` の最後に `/` を付けると挙動が変わるので注意
### ■ /var/www/html/ のパーミッションを775(rwxrwxr-x)に変更
```
# chmod 775 -R /var/www/html
```
### ■ /var/www/html/ のパーミッション確認
```
# ls -ld /var/www/html
```
### ■ 一般ユーザーをグループapacheに追加
```
# gpasswd -a user322 apache
```
### ■ グループapacheの確認
```
# cat /etc/group | grep apache
```
※ 設定後は再ログイン or 再起動が必要  

### ■ 一般ユーザーから/var/www/html/にテキストファイルが作成できるか確認
```
$ touch /var/www/html/test.txt
```


