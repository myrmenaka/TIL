# 2025.7.21 - MySQLの基礎 1 XAMPPでのDB接続準備

## 目的

MySQLの基本的な概念（テーブル・レコードなど）と、XAMPPを用いたローカル環境でのデータベース接続準備を理解し、実際に開発用DBを作成する

## 目次

- [MySQL とは](#1)
- [データベースの基本用語](#2)
- [XAMPPでMySQL(DB)操作 CUI/GUI](#3)
- [接続に必要な情報（phpMyAdmin での初期状態）](#4)
- [接続用PHPコードの雛形（PDO）](#5)

## 学習内容

<a id="1"></a>

### MySQL とは

- データベース管理システム（データベースマネジメントシステム（DBMS）もしくは、リレーショナルデータベースマネジメントシステム（RDBMS））の一種
- PHPと組み合わせて動的なWebアプリケーションを構築可能  

[参考: 「分かりそう」で「分からない」でも「分かった」気になれるIT用語辞典 : MySQLとは](https://wa3.i-3-i.info/word11774.html)  
[参考: MySQLとは？特徴や使い方、インストール方法を初心者向けにカンタン解説](https://www.rstone-jp.com/column/109980/)  

---
<a id="2"></a>

### データベースの基本用語

| 用語 | 説明 |  
|:--:|:--:|  
| データベース | 複数のテーブルをまとめたもの |  
| テーブル | データを格納する構造 |  
| カラム | テーブルの `列`、データの項目名 |  
| レコード | テーブルの `行`、1件のデータのかたまり |  

[参考: 【IT初心者向け】データベースとは？ 種類や基礎的な用語をわかりやすく解説](https://tech-camp.in/note/technology/94198/)  

---
<a id="3"></a>

### XAMPPでMySQL(DB)操作 CUI/GUI

データベースとテーブルの準備  

#### GUI(phpMyAdminで操作)  

1．phpMyAdminにアクセス
1. XAMPPを起動（`Apache` と `MySQL` を「Start」）
2. ブラウザで http://localhost/phpmyadmin にアクセス

2．データベースを作成
1. 左側メニューの「新規作成」をクリック
2. データベース名に `portfolio_db` と入力  
(データベース名、適宜変更)
3. 照合順序（Collation）は `utf8mb4_general_ci` を選択
4. 「作成」ボタンをクリック

→ これで `空のデータベース` ができる  

3．テーブルを作成（ユーザー情報）
1. 作成した portfolio_db（データベース）をクリック
2. 「テーブルを作成」欄に `users` と入力  
（テーブル名、適宜変更）
3. 必要なカラム数に設定（） → 「実行」  
カラム設定（1行ずつ入力）  

    例）  
    | 名前 | 型 | 長さ/値 | その他設定 |  
    |:--:|:--:|:--:|:--:|  
    | id | INT |  | A_I（自動増加）＋主キー |  
    | username | VARCHAR | 255 | NOT NULL＋UNIQUE |  
    | password | VARCHAR | 255 | NOT NULL |  
    | created_at | DATETIME |  | デフォルト値 → CURRENT_TIMESTAMP |  


4. 入力が終わったら「保存」ボタンをクリック  

→ これで `users テーブル` が完成  

4．データの確認・追加
- 左メニューで users テーブルを選択
- 上部タブの「挿入」から手動でデータ追加も可能（テスト用に便利）
- 「表示」タブで登録されたデータを一覧表示できる



#### CUI(ターミナル操作)  

※ データベース名:portfolio_db、テーブル名:users、カラム数:4  

データベース作成
```sql
CREATE DATABASE portfolio_db CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
```

ユーザーテーブル作成
```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);
```

[参考: MySQLをXAMPPを使ってWindows環境にインストールする方法](https://blog.proglus.jp/1595/)  

---
<a id="4"></a>

### 接続に必要な情報（phpMyAdmin での初期状態）

`ホスト名` : localhost  
`ユーザー名` : root  
`パスワード` : なし（初期は空欄）

---
<a id="5"></a>

### 接続用PHPコードの雛形（PDO）

[connect_test.php](./connect_test.php)  

※ 上記の雛形は、随時「接続成功」と表示されるコード  
 → 本番環境では表示しないほうが好ましいが、テスト環境では表示することで接続を目視できる意図がある

- `PDO（PHP Data Objects）` は、安全で柔軟なデータベース接続方法  
- 接続には `new PDO()` を使い、`try/catch` ブロックでエラーハンドリングを行う  

#### <全体の構造>  
```php
$dsn = 'mysql:host=localhost;dbname=portfolio_db;charset=utf8mb4';
$user = 'root';
$password = '';
```

#### <`$dsn`（接続文字列）>
- `Data Source Name（データソース名）` の略
- データベースの種類や接続先をひとまとめに指定

    - `mysql:` → 使用するデータベースの種類
    - `host=localhost` → 接続先のホスト（ここでは自分のPCを指す）
    - `dbname=portfolio_db` → 使用するデータベース名
    - `charset=utf8mb4` → 文字コード（絵文字なども扱える `UTF-8` 拡張）  


    ※`charset（キャラセット）` は、ざっくり言うと「文字の種類とその表し方（エンコード方式）」を示す指定  
    `utf8mb4` は文字コードの一種で、日本語や絵文字も含めて幅広い文字に対応  
    `utf8` よりも強化されていて、例えば「🌸」や「😃」などの絵文字も正しく保存・表示できる  


#### <`$user`、 `$password`>
- データベースにログインするための「ユーザー名」と「パスワード」
- 今回はローカル環境なので `root` ユーザー・パスワード空欄になっている  

#### <データベース接続の実行>
```php
$pdo = new PDO($dsn, $user, $password);
```

- `PDO` は `PHP Data Objects` の略で、PHPからDBを扱うためのクラス  
    [参考: PHPマニュアル : PHP Data Objects](https://www.php.net/manual/ja/book.pdo.php)  
- `new PDO(...)` で接続を試む  
    [参考: PHPマニュアル : 接続、および接続の管理 ](https://www.php.net/manual/ja/pdo.connections.php)  
- 成功すると `$pdo` という変数に接続オブジェクトが入る
- この `$pdo` を使って SQL を送ったりデータ取得したりできる  

#### <例外（エラー）への対応：try / catch>
```php
try {
    $pdo = new PDO($dsn, $user, $password);
    echo "接続成功";
} catch (PDOException $e) {
    echo "接続失敗: " . htmlspecialchars($e->getMessage(), ENT_QUOTES, 'UTF-8');
}
```

#### <`try ～ catch` とは>
- 「エラーが出そうな処理」を `try` に入れ、
- 失敗したら `catch` で対処する、という仕組み  

※`Day13 基本文法 8-3 エラーハンドリング（例外処理）` で扱っている  

#### <`PDOException $e`>
- `PDO` 関係でエラーが起きたときはこの「例外オブジェクト」に情報が入る
- `$e->getMessage()` で「何が原因で失敗したか」を取得  

[参考: PHPマニュアル : PDOException クラス](https://www.php.net/manual/ja/class.pdoexception.php)  

#### <まとめ>  
| 処理 | 役割 |   
|:--:|:--:|  
| `$dsn` | データベース接続情報（ホスト、DB名、文字コード） |   
| `$user`, `$password` | ログイン認証情報 |   
| `new PDO(...)` | 実際に接続を試みる |   
| `try { ... } catch { ... }` | エラーに備えて安全に処理する |   
| `echo ...` | 成否の表示（ユーザーや開発者向け） |   

このコードで、PHPからMySQLに接続できる準備が整う  

※`$pdo` を使って「SELECT文でデータを取得する」や「INSERT文でデータを登録する」などができるようになる（次回以降）  

---
---
## セキュリティ面での注意

- 接続情報（DB名、ユーザー名、パスワード）は、`.env` などで外部管理するのが望ましい（次回以降）
- XAMPPの `rootユーザー` にパスワードがない場合、開発以外での使用はNG（本番環境ではユーザー制御が必須）
- 

## 練習問題 

XAMPP環境で `portfolio_db` というデータベースを作成し、上記のPDO接続スクリプトを使って `connect_test.php` を作成し、接続確認を行う  

1．XAMPPを起動し、MySQLとApacheを開始  
2．`http://localhost/phpmyadmin` にアクセスし、データベースを作成  
3．[上記PHPコード](#5)を `htdocs` に `connect_test.php` として保存  
4．`http://localhost/connect_test.php` にアクセスして表示を確認  

→ 上記のPDO接続コードで「接続成功」と表示されればOK  

## 作業チェックリスト

- XAMPPを使ってMySQLのデータベースを作成できたか
- `portfolio_db` を `phpMyAdmin` 上に作成したか
- PHPでDB接続テストが成功したか

## MEMO

- XAMPP環境なので、本番環境の際は再度学習が必要  
- あくまでデータベースの操作に慣れることが当面の学習目的

- 条件分岐（if/elseif）とエラー処理（try/catch）違い  

    `if / elseif：条件で分けたいとき`  

    → これは「こうなってたらこうする」「それ以外ならこうする」と自分で状況を調べて、行動を決めるもの  

    たとえば  
    ```php
    $score = 75;

    if ($score >= 90) {
        echo "すごく良い成績！";
    } elseif ($score >= 70) {
        echo "まあまあ良い成績！";
    } else {
        echo "がんばろう！";
    }
    ```

    - 自分で `$score` をチェックして、分岐
    - 予測できるパターンに対して行動を分けるときに使う  

    `try / catch：予期しないエラーへの対策`  

    → これは「何か危なそうな処理をまずやってみる → 失敗したらどうするかも考えておく」ような危険を先読みする仕組み  

    たとえば  
    ```php
    try {
        $pdo = new PDO($dsn, $user, $password);
        echo "接続成功";
    } catch (PDOException $e) {
        echo "接続失敗: " . $e->getMessage();
    }
    ```

    - `PDO` は失敗することもある（DBが落ちてるなど）
    - `PHPが自動で異常を検出` し、catchに渡す
    - `予測できないトラブルに備えておく` のが try/catch の役割  

    `たとえるなら…`  
    | 役割 | たとえ話 |   
    |:--:|:--:|  
    | if / elseif | 信号の色を見て自分で判断して渡る  |   
    | try / catch | 橋を渡ったら崩れた！その時にどう避難するか用意してる |   

    `まとめ表`  
    | 比較項目 | if / elseif | try / catch |   
    |:--:|:--:|:--:|  
    | 目的 | 条件に応じた分岐 | エラーの発生に備えて安全に処理する |   
    | 自分で調べるか | ✔️（自分で値をチェックする） | ❌（PHPがエラーを検知してくれる） |   
    | 使う場面 | 通常の処理分岐 | DB接続・ファイル操作など「失敗しやすい処理」 |   

---

参考: []()
