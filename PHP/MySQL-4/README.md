# 2025.8.23 - MySQLの基礎 4 データのUPDATE（更新）・DELETE（削除）処理

## 目的

登録されたデータを編集・更新（UPDATE）や削除（DELETE）できるようにする  
これにより、Webアプリに「管理性」が追加され、`CRUD操作 (Create,Read,Update,Delete)` の一連の流れを習得できる  

## 目次

- [編集画面の作成](#1)
- [更新（UPDATE）処理の実装](#2)
- [削除（DELETE）処理の実装](#3)
- [バリデーションの再利用](#4)
- [トークンチェックの追加（CSRF対策）](#5)

## 学習内容

<a id="1"></a>

### 編集画面の作成

- 一覧ページから「編集」リンクをクリックすると、対象データの編集画面に遷移する
- フォームには既存データを初期値として表示する

---
<a id="2"></a>

### 更新（UPDATE）処理の実装

- 編集フォーム送信後、DBの内容を `UPDATE文` で更新する
- 更新成功後は一覧画面にリダイレクトする

---
<a id="3"></a>

### 削除（DELETE）処理の実装

- 一覧ページから「削除」リンクをクリックすると対象データを削除する
- 処理後は一覧画面に戻る

---
<a id="4"></a>

### バリデーションの再利用

- `Day22` で作成したバリデーション関数を再利用し、入力値の確認を行う

---
<a id="5"></a>

### トークンチェックの追加（CSRF対策）

- フォーム送信後には `CSRFトークン` を埋め込み、改ざん防止を行う

---
---
## セキュリティ面での注意

- `GET` で削除処理を行うと誤操作の危険があるため、できれば `POST` にする
- `CSRF対策` としてトークンを利用する
- ユーザー入力値は必ずエスケープ処理（htmlspecialchars）を行う
- IDを直接指定する方法は不正アクセスの可能性があるため、認可チェックも検討する

## 練習問題 

`members` テーブルのデータを「編集」「削除」できるページを作成

1. 一覧ページ（`index.php`）に「編集」「削除」リンクを表示
2. 編集リンクから `edit.php?id=〇〇` に遷移し、対象データをフォームに表示
3. 更新ボタン押下で `update.php` にPOST送信し、DBをUPDATE
4. 削除リンクから `delete.php?id=〇〇` に遷移し、DBをDELETE
5. 更新・削除後は `index.php` へリダイレクト
6. 入力チェックやトークンチェックを実装

## 作業チェックリスト

- 編集フォームに既存データを表示できたか
- UPDATE文で正しくデータを更新できたか
- DELETE文で指定のデータを削除できたか
- バリデーション・トークンチェックを実装できたか

## MEMO

- `edit.php` 解説  
    「データベースに登録された会員情報を編集するための画面」を作るコード  

    PHP部分：データを取得する準備  
    ```php
    require `db_connect.php`;
    ```

    - `db_connect.php` というファイルを読み込んで、データベース接続の設定を使えるようにする
    - このファイルの中には `$pdo = new PDO(...)` のような接続コードがあるはず

    ```php
    $id = $_GET['id'];
    ```

    - URLの中にある `?id=1` のような値を取得
    - つまり「どの会員の情報を編集するか」を指定している部分

    ```php
    $stmt = $pdo->prepare("SELECT * FROM members WHERE id=?");
    $stmt->execute([$id]);
    $member = $stmt->fetch();
    ```

    - `members` テーブルから、指定された `id` の会員情報を取り出す
    - `prepare()` と `execute()` を使って、安全にSQLを実行（SQLインジェクション対策）
    - `fetch()` で1件のデータを `$member` に取り出す

    HTML部分：編集フォームの表示
    ```html
    <form action="./update.php" method="post">
    ```

    - このフォームは、入力された内容を `update.php` に送って、データベースを更新するためのもの
    - `method="post"` は安全な送信方法


    隠しフィールド（ID）  
    ```html
    <input type="hidden" name="id" value="<?= htmlspecialchars($member['id']) ?>">
    ```

    - 編集対象の `id` をフォームに含めて送信
    - `hidden` なので画面には表示されないが、重要な情報

    名前とメールの入力欄
    ```html
    名前: <input type="text" name="name" value="<?= htmlspecialchars($member['name']) ?>"><br>
    メール: <input type="email" name="email" value="<?= htmlspecialchars($member['email']) ?>"><br>
    ```

    - `value="..."` の部分に、現在の会員情報が表示される
    - ユーザーはここを編集できる
    - `htmlspecialchars()` は安全に表示するための関数（HTMLタグなどを無効化）


    送信ボタン
    ```html
    <input type="submit" value="更新">
    ```

    - このボタンを押すと、入力された内容が `update.php` に送られて、データベースが更新される


- `update.php` 解説
    「フォームから送られてきた会員情報をデータベースで更新する処理」をするコード

    1．データベース接続
    ```php
    require 'db_connect.php';
    ```

    - データベースに接続するための設定ファイルを読み込んでいる
    - この中には `$pdo = new PDO(...)` のようなコードがあるはず

    2．フォームから送られてきたデータを受け取る
    ```php
    $id = $_POST['id'];
    $name = $_POST['name'];
    $email = $_POST['email'];
    ```

    - `POST` で送られてきたデータ（フォームの入力内容）を受け取っている
    - `id` はどの会員を更新するかを示す番号
    - `name` と `email` は新しく入力された名前とメールアドレス

    3．データベースの情報を更新する
    ```php
    $stmt = $pdo->prepare("UPDATE members SET name=?, email=? WHERE id=?");
    $stmt->execute([$name, $email, $id]);
    ```

    この部分で、指定された `id` の会員情報を新しい `name` と `email` に更新  

    4．更新後に画面を移動する
    ```php
    header("Location: index.php");
    exit;
    ```

    - 更新が終わったら、`index.php` に自動で移動
    - `exit;` はそれ以降の処理を止めるための命令


- `delete.php` 解説
    「指定された会員情報をデータベースから削除する処理」をするコード  

    1．データベース接続
    ```php
    require 'db_connect.php';
    ```

    - データベースに接続するための設定ファイルを読み込んでいる
    - このファイルには `$pdo = new PDO(...)` のような接続コードが書かれているはず

    2．URLから削除対象のIDを取得
    ```php
    $id = $_GET['id'];
    ```

    - URLに含まれる `?id=3` のような情報を取得
    - つまり「どの会員を削除するか」を指定するためのID

    3．データベースから削除する
    ```php
    $stmt = $pdo->prepare("DELETE FROM members WHERE id=?");
    $stmt->execute([$id]);
    ```

    - `members` テーブルから、指定された id の会員情報を削除
    - `prepare()` と `execute()` を使うことで、安全にSQLを実行（SQLインジェクション対策）

    4．削除後に一覧ページへ移動
    ```php
    header("Location: index.php");
    exit;
    ```

    - 削除が終わったら、自動的に `index.php`（一覧ページ）に移動
    - `exit;` はそれ以降の処理を止める命令

---

参考: []()
