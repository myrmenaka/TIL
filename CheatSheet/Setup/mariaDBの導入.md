# mariaDBの導入
## ①パッケージのインストール

### ■ MariaDB サーバーのインストール
```
# dnf install mariadb-server
```
### ■ インストール確認
```
# dnf list installed | grep mariadb
```
## ②MariaDB の起動・自動起動設定
### ■ mariaDBの自動起動設定（永続）
```
# systemctl enable mariadb
```
※起動後、startもしくは再起動
### ■ 起動
```
# systemctl start mariadb
または
# systemctl restart mariadb
```
### ■ 状態確認
```
# systemctl status mariadb
```

## ③初期設定
```
# mysql_secure_installation
```
（Enter→n→y→DBのパスワード入力→y→n→y→y）
```
// 例
Enter current password for root (enter for none): Enter
Switch to unix_socket authentication? n
Change the root password? y
New password: （DB root のパスワード）
Re-enter new password: （再入力）
Remove anonymous users? y
Disallow root login remotely? n
Remove test database and access to it? y
Reload privilege tables now? y
```

## ④文字コード設定（UTF-8 / utf8mb4）
MariaDB はデフォルトで utf8 ではないため、utf8mb4 に統一するのが実務標準

### ■ サーバ側設定（mysqld）
/etc/my.cnf.d/mariadb-server.cnf
#### 追記
```
[mysqld]
character-set-server=utf8mb4 ←
```
### ■ クライアント側（client）
/etc/my.cnf.d/client.cnf
#### 追記
```
[client-mariadb]
default-character-set=utf8mb4 ←
```
### ■ 設定反映（MariaDB 再起動）
```
systemctl restart mariadb
```
### ■ 文字コード設定確認
※ MariaDB にログインして確認
```
MariaDB> show variables like 'chara%';
```
## ⑤ファイアウォールの設定（他ホストからの接続を許可）
MariaDB のデフォルトポートは 3306/tcp
```
# firewall-cmd --add-port=3306/tcp --permanent
# firewall-cmd --reload
# firewall-cmd --list-ports
```
## ⑥他のホストのDBに接続
```
$ mariadb -h <DBサーバーのIP> -u root -p
```
※ root でリモート接続するには設定が必要  
（通常は root のリモート接続は推奨されない）  

## ⑦MariaDB 側でユーザー作成（リモート接続許可）
※ MariaDB にログイン
### ■ 現在のユーザー確認
```
MariaDB> select user, host from mysql.user;
```
### ■ 新しいホストからの接続を許可するユーザを作成
```
MariaDB> CREATE USER 'ユーザー名'@'IPアドレス' IDENTIFIED BY 'パスワード';
```
#### ホスト指定の例
※ワイルドカード
| 記述 | 意味 |  
|:--:|:--:|  
| '%' | どこからでも接続可（危険） |  
| '192.168.1.%' | 同一サブネットから接続可 |  
| 'localhost' | ローカルのみ |  
※パスワードはそのユーザーとして接続する人（相手）が使うパスワード

### ■ 権限を付与
```
MariaDB> GRANT ALL PRIVILEGES ON mydb.* TO 'ユーザー名'@'IPアドレス';
```
### ■ 権限を反映
```
MariaDB> FLUSH PRIVILEGES;
```
