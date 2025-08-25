# 2025.8.25 - ログイン機能の実装 1 会員登録とログイン処理

## 目的

Webアプリにログイン機能を実装し、ユーザーごとに利用できる仕組みを学ぶ  

## 目次

- [会員登録フォームの作成](#1)
- [会員登録処理](#2)
- [ログインフォームの作成](#3)
- [ログイン処理](#4)
- [ログイン状態の保持](#5)

## 学習内容

<a id="1"></a>

### 会員登録フォームの作成

- ユーザー名、メールアドレス、パスポートを入力できるフォームを作成
- パスワードは `ハッシュ化（password_hash()）` してDBに保存する  
    ※ハッシュ化とは  
    → データを特定のアルゴリズムで変換し、固定長の文字列を生成する処理  
    → この処理は不可逆的であり、一度ハッシュ化されたデータを元のデータに戻すことはできない  
    [参考: PHPマニュアル : password_hash](https://www.php.net/manual/ja/function.password-hash.php)  
    [参考: PHPマニュアル : 安全に、かつセキュアにパスワードをハッシュする](https://www.php.net/manual/ja/faq.passwords.php)  
    [参考: パスワードハッシュとは暗号の基礎](https://tuta.com/ja/blog/what-is-a-password-hash)  

---
<a id="2"></a>

### 会員登録処理

- DBにユーザー情報を `INSERT`  
    ※ `INSERT処理` は、`Day27`
- メールアドレスの重複チェックを行う

---
<a id="3"></a>

### ログインフォームの作成

- メールアドレスとパスワードを入力する

---
<a id="4"></a>

### ログイン処理

- メールアドレスでユーザーを検索
- 入力パスワードとDBのハッシュを、`password_verify()` で照合  
    [参考: PHPマニュアル : password_verify](https://www.php.net/manual/ja/function.password-verify.php)  
    [参考: PHPのpassword_verifyメソッドの使い方を現役エンジニアが解説【初心者向け】](https://magazine.techacademy.jp/magazine/21722)  
- 成功したら `セッションにユーザーIDを保存`

---
<a id="5"></a>

### ログイン状態の保持

- `$_SESSION['user_id']` をセットし、ログイン中かどうかを判定できるようにする

---
---
## セキュリティ面での注意

- パスワードは必ずハッシュ化して保存（平文保存は厳禁）
- SQLインジェクション対策のためプレースホルダを必ず使用
- セッションハイジャック対策として、ログイン後に `session_regenerate_id(true)` を行うのが望ましい
- メールアドレスの重複チェックを行うことで不正登録を防ぐ

## 練習問題 

会員登録・ログイン機能を実装する  

1. `register.php` で会員登録フォームを作成
2. DBにINSERTする際、パスワードをハッシュ化
3. `login.php` でログインフォームを作成
4. ログイン成功時にセッションを開始し、`$_SESSION['user_id']` に保存
5. ログイン中かどうかを判定できるようにする

## 作業チェックリスト

- 会員登録フォームが作成できたか
- パスワードをハッシュ化して保存できたか
- ログインフォームが作成できたか
- `password_verify()` で認証できたか
- セッションにユーザーIDを保存できたか

## MEMO

- `register.php` 解説  
「会員登録フォーム」と「登録処理」

    HTML部分：会員登録フォーム  
    ```html
    <form method="post">
        名前: <input type="text" name="name" required><br>
        メール: <input type="email" name="email" required><br>
        パスワード: <input type="password" name="password" required><br>
        <input type="submit" value="登録">
    </form>
    ```

    - ユーザーが「名前」「メールアドレス」「パスワード」を入力する画面を表示
    - `method="post"` によって、入力内容がPHPに送られる
    - `required 属性` で、空欄のまま送信できないようにしている

    PHP部分：登録処理  
    ```php
    require 'db_connect.php';
    session_start();
    ```

    - `db_connect.php` を読み込んで、データベースに接続
    - `session_start()` はログイン機能などで使う準備（今回は未使用でもOK）

    1．POST送信されたか確認  
    ```php
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    ```

    - フォームが送信されたときだけ処理を実行するようにしている

    2．入力データの取得  
    ```php
    $name = $_POST['name'];
    $email = $_POST['email'];
    $pass = $_POST['password'];
    ```

    - フォームから送られてきた「名前」「メール」「パスワード」を受け取る

    3．パスワードのハッシュ化  
    ```php
    $hash = password_hash($pass, PASSWORD_DEFAULT);
    ```

    - パスワードを暗号化して保存する（セキュリティ対策）
    - `PASSWORD_DEFAULT` は安全な方式を自動で選んでくれる

    4．メールアドレスの重複チェック  
    ```php
    $stmt = $pdo->prepare("SELECT * FROM users WHERE email=?");
    $stmt->execute([$email]);
    if ($stmt->fetch()) {
        echo "このメールアドレスは既に登録されています";
    }
    ```

    - すでに同じメールアドレスが登録されていないか確認
    - `fetch()` で1件でも見つかれば「重複あり」と判断

    5．新規登録処理  
    ```php
    $stmt = $pdo->prepare("INSERT INTO users(name, email, password) VALUES(?,?,?)");
    $stmt->execute([$name, $email, $hash]);
    echo "登録が完了しました";
    ```
    
    - 重複がなければ、users テーブルに新しいユーザー情報を追加
    - パスワードはハッシュ化されたものを保存


- `login.php` 解説  
「ログイン機能」

    HTML部分：ログインフォーム  
    ```html
    <form method="post">
        メール: <input type="email" name="email" required><br>
        パスワード: <input type="password" name="password" required><br>
        <input type="submit" value="ログイン">
    </form>
    ```

    - ユーザーが「メールアドレス」と「パスワード」を入力する画面を表示
    - `method="post"` によって、入力内容がPHPに送られる
    - `required 属性` で、空欄のまま送信できないようにしている

    PHP部分：ログイン処理  
    ```php
    require 'db_connect.php';
    session_start();
    ```

    - `db_connect.php` を読み込んで、データベースに接続
    - `session_start()` はログイン状態を保存するための準備（セッション開始）

    1．POST送信されたか確認  
    ```php
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    ```

    - フォームが送信されたときだけ処理を実行するようにしている

    2．入力データの取得  
    ```php
    $email = $_POST['email'];
    $pass = $_POST['password'];
    ```

    - フォームから送られてきた「メールアドレス」と「パスワード」を受け取る

    3．データベースからユーザー情報を取得  
    ```php
    $stmt = $pdo->prepare("SELECT * FROM users WHERE email=?");
    $stmt->execute([$email]);
    $user = $stmt->fetch();
    ```

    - 入力されたメールアドレスに一致するユーザー情報をデータベースから取得
    - `$user` に該当する行（ユーザー情報）が入る

    4．パスワードの照合  
    ```php
    if ($user && password_verify($pass, $user['password'])) {
    ```

    - `password_verify()` を使って、入力されたパスワードと保存されたハッシュを照合
    - 一致すればログイン成功

    5．ログイン成功時の処理  
    ```php
    $_SESSION['user_id'] = $user['id'];
    echo "ログイン成功";
    ```

    - セッションにユーザーIDを保存（ログイン状態を記録）
    - 本来は「マイページ」などにリダイレクトするのが一般的

    6．ログイン失敗時の処理  
    ```php
    echo "メールアドレスまたはパスワードが違います";
    ```

    - メールアドレスが存在しない、またはパスワードが違う場合にエラーメッセージを表示


---

参考: []()
