# 2025.7.21 - MySQLの基礎 2 PHPからのDB接続と基本的なSELECT文

## 目的

PHPからMySQLデータベースに接続し、基本的な `SELECT文` でデータを取得する方法を学ぶ  
データベースのテーブル構造を理解し、データ取得の基本的な流れを把握する  

## 目次

- [`PDO` を使ったDB接続の復習](#1)
- [テーブルの作成](#2)
- [基本的な `SELECT文` の構造](#3)
- [PHPでのデータ取得（例）](#4)

## 学習内容

<a id="1"></a>

### `PDO` を使ったDB接続の復習

- `PDO（PHP Data Objects）` は、安全で柔軟なデータベース接続方法  
- 接続には `new PDO()` を使い、`try/catch` ブロックでエラーハンドリングを行う  

---
<a id="2"></a>

### テーブルの作成

- データベース : `portfolio_db` を選択
- テーブル名 : `learning_logs`  
- カラム例 : ↓  
    | カラム名 | 型 | 説明 |  
    |:--:|:--:|:--:|  
    | id | INT | 主キー・自動連番 |  
    | title | VARCHAR(100) | 学習タイトル |  
    | description | TEXT | 学習内容の詳細説明 |  
    | created_at | DATETIME | 登録日時 |  

---
<a id="3"></a>

### 基本的な `SELECT文` の構造

```sql
SELECT [取得したい要素] FROM [使用テーブル];
```

[参考: 【SQL】3分でSELECT文を完全マスター!データ取得の基礎から応用まで!](https://www.sejuku.net/blog/72964)  

---
<a id="4"></a>

### PHPでのデータ取得（例）

[select_test.php](./select_test.php)  

#### 1．データベースの接続情報の設定  
```php
$dsn = 'mysql:host=localhost;dbname=portfolio_db;charset=utf8mb4';
$user = 'root';
$password = '';
```

- `$dsn`（接続先の情報）

    - `mysql:` → MySQLに接続するという意味
    - `host=localhost` → 自分のPCにあるデータベースを使う
    - `dbname=portfolio_db` → 使用するデータベースの名前
    - `charset=utf8mb4` → 日本語や絵文字も正しく扱える文字コード
- `$user` / `$password` → データベースにログインするためのユーザー情報
    - `root` → 管理者権限を持つデフォルトのユーザー
    - `''` → パスワード未設定（ローカル環境ではこのままが多い）

#### 2．データベースに接続する
```php
$pdo = new PDO($dsn, $user, $password);
```

- `PDO`（PHP Data Objects）という仕組みを使って、DBに接続
- この `$pdo` を使ってSQL文を送ったり、結果を取得したりできる

#### 3．データを取得するためのSQL文を実行
```php
$sql = "SELECT * FROM learning_logs";
$stmt = $pdo->query($sql);
```

- `"SELECT * FROM learning_logs"` → テーブル `learning_logs` の全データを取得する命令  
    ※`*` → 全て
- `$stmt` に結果が入る（この中に複数のデータがある状態）  

[参考: phpのqueryがわかる!データ取得の基本を学ぼう](https://www.sejuku.net/blog/72118)  

#### 4．取得したデータを1件ずつ表示
```php
foreach ($stmt as $row) {
    echo "タイトル: " . htmlspecialchars($row['title']) . "<br>";
    echo "内容: " . nl2br(htmlspecialchars($row['description'])) . "<br><hr>";
}
```

- `foreach` でデータを1つずつ取り出して表示
- `$row['title']` → タイトル
- `$row['description']` → 内容（改行あり）  

※[参考: mdn web docs : `<hr>`: 主題区切り（水平線）要素](https://developer.mozilla.org/ja/docs/Web/HTML/Reference/Elements/hr)  

#### 安全に表示するための工夫

- `htmlspecialchars(...)`  
→ HTMLタグなどの危険な文字を安全に処理するための関数（XSS対策）
- `nl2br(...)`  
→ 内容の中にある改行（ `\n` ）を `<br>` に変えて表示  
→ 実際に改行されたように見える  

#### 5．エラーが起きたときの対処（try / catch）
```php
} catch (PDOException $e) {
    echo "エラー: " . $e->getMessage();
}
```

- `try` の中で接続が失敗したりすると `catch` に飛ぶ
- `$e->getMessage()` → 何が原因で失敗したのか表示

#### 全体の流れまとめ

1. DB接続情報を設定  
2. PDOでデータベースに接続  
3. SQLを実行してデータ取得  
4. データを安全に画面に表示（改行も整形）  
5. もし失敗したらエラーメッセージ表示  

---
---
## セキュリティ面での注意

- `htmlspecialchars()` や `nl2br()` を使ってXSS対策を行う
- データベース接続情報は外部ファイル化
- ユーザー入力によるSQL構造は、必ず `プレースホルダ` や `プリペアドステートメント` を使う（今回は静的クエリなので省略）  

※`プレースホルダ` 、 `プリペアドステートメント` とは…  
「プリペアドステートメント」と「プレースホルダ」は、PHPで安全にデータベース操作をするためのとても大事な仕組み  

#### まず、なぜ必要なのか
通常のSQLにユーザー入力を直接入れると、こんな危険がある  
```php
$sql = "SELECT * FROM users WHERE name = '$input'";
```

- ここに悪意のある文字が入ると → `SQLインジェクション` というセキュリティの脅威になる  
なので、データを安全に渡す手段として「プリペアドステートメント」と「プレースホルダ」を使う  

#### プリペアドステートメントとは
一言で言えば「SQL文を事前に準備しておく」こと

- SQLの型をあらかじめ書いておいて `後からデータだけ別で渡す` という方法  
この分離により、データは `単なる値` として扱われるので、安全になる  

#### プレースホルダとは
SQLの中で「あとから値を入れる部分」を `穴` のように記述する記号のこと  

- たとえば `?` や `:name` などが `プレースホルダ`  

#### 実例で比較  

NG例（危険）  
```php
$name = $_POST['name'];
$sql = "SELECT * FROM users WHERE name = '$name'";
$stmt = $pdo->query($sql);
```

→ 入力された文字がそのままSQLに混ざる危険  

OK例（安全）  
```php
$name = $_POST['name'];
$sql = "SELECT * FROM users WHERE name = ?";
$stmt = $pdo->prepare($sql);
$stmt->execute([$name]);
```

- `?` がプレースホルダ
- `prepare()` がプリペアドステートメント  
    [参考: PHPマニュアル : PDO::prepare](https://www.php.net/manual/ja/pdo.prepare.php)  
- `execute()` でデータを安全に渡してる！  
    [参考: PHPマニュアル : PDOStatement::execute](https://www.php.net/manual/ja/pdostatement.execute.php)  

    ※PHPマニュアルについて、`MySQL版` と `PDO版` があるので注意  
    → 現在やってるのは、`PDO版`  

#### たとえるなら…
🍰 ケーキの型（SQL文）を先に用意しておいて  
🍓 あとから安全にトッピング（データ）するイメージ  

→ 型に入れてるからこぼれないし、異物が入らないという感じ  

#### 補足：名前付きプレースホルダもある
```php
$sql = "SELECT * FROM users WHERE name = :name";
$stmt = $pdo->prepare($sql);
$stmt->execute(['name' => $name]);
```

- `:name` ← 名前付きでわかりやすく管理できる  

## 練習問題 

`phpMyAdmin` で `learning_logs` テーブルを作成し、ダミーデータを3件登録して、PHPで全件取得・表示する  

1．`portfolio_db` 内に `learning_logs` テーブルを作成  
2．`phpMyAdmin` からダミーデータを3件登録（なんでもいい）  
3．`select_test.php` に[上記PHPコード](#4)を記述して `htdocs` に保存  
4．ブラウザから `http://localhost/select_test.php` を開いて表示を確認  

→ 3件分のタイトルと内容がブラウザに表示されれば成功  

## 作業チェックリスト

- `learning_logs` テーブルを作成できたか
- ダミーデータを `phpMyAdmin` で追加したか
- PHPから `SELECT文` で全件取得し、表示できたか

## MEMO

- `nl2br` をずっと `n12br` と勘違いしてました  
    → 気づいた箇所のみ修正  

- テーブル作成時に入力する `カラム` は、そのテーブルにどんな情報を保存したいか＝ `項目を定義` する作業  
    → つまり、`カラムを決めるだけでは中身は空`  
    そのあとに「挿入」などで、`実際のデータ（レコード）` を登録していくことで中身が入る  

    テーブル ＝ 収納棚  
    カラム ＝ 棚のラベル  
    レコード ＝ 実際に入れた書類やメモ  

---

参考: []()
