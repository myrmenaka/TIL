# 2025.7.7 - Cookieの基本と使い方

## 目的

- `Cookie（クッキー）` の仕組みと使い方を理解し、セッションとの違いを区別できるようになる  
- `Cookie` を使って簡単な「前回入力値の保存」処理を作れるようになる

## 目次

- [`Cookie` とは](#1)
- [`Cookie` と `セッション` の違い](#2)
- [`Cookie` の使い方（基本文法）](#3)

## 学習内容

<a id="1"></a>

### `Cookie` とは

- クライアント（ユーザーのブラウザ）に一時的なデータを保存する仕組み
- 保存されたデータは、次回以降のアクセス時にも読み取れる  

例）  
ログイン状態の保持、前回の入力内容保持、アクセス回数のカウント…etc

[参考: 「分かりそう」で「分からない」でも「分かった」気になれるIT用語辞典 : クッキー（cookie）](https://wa3.i-3-i.info/word1725.html)  
[参考: PHPマニュアル : クッキー（cookie）](https://www.php.net/manual/ja/features.cookies.php)  

---
<a id="2"></a>

### `Cookie` と `セッション` の違い

|比較項目|Cookie|セッション|  
|:--:|:--:|:--:|  
|保存場所|クライアント側（ブラウザ）|サーバー側|  
|有効期限|任意に設定可|ブラウザを閉じるまで（またはサーバー設定）|  
|容量制限|約 4KB|制限なし（サーバー依存）|  
|セキュリティ|ユーザーが編集可能|比較的安全（サーバー管理）|  

[参考: クッキーとセッションを雰囲気で使っているエンジニアが、違いを説明できるようになる記事](https://zenn.dev/collabostyle/articles/8949e8db686263)  
[参考: 神田ITschool : クッキーの仕組みを理解しよう](https://kanda-it-school-kensyu.com/php-basic-contents/pb_ch11/pb_1102/)  

---
<a id="3"></a>

### `Cookie` の使い方（基本文法）

- 保存  
    ```php
    setcookie('キー名', '保存したい値', 有効期限のタイムスタンプ);
    ```
    ※有効期限を記述しない場合は、ブラウザを閉じると消える  

    - 第一引数には `保存する Cookie の名前`、第二引数には `保存する値`、第三引数は `値を保存する有効期間`
    - 有効期限には、`time()` + `秒数` を使う  
    例）  
    1日 → `time()+60*60*24`  
    1週間 → `time()+60*60*24*7`

    ※既存のキー名に値だけ変えて記述すると更新（上書き）される  

    [参考: PHPマニュアル : setcookie()](https://www.php.net/manual/ja/function.setcookie.php)  
    
- 取得  
    ```php
    $_COOKIE['キー名']
    ```
    [参考: PHPマニュアル : $_COOKIE](https://www.php.net/manual/ja/reserved.variables.cookies.php)  

- 削除  
    ```php
    setcookie('キー名', '', - 有効期限)
    ```
    → 削除する `Coookie` を指定して、有効期限の値をマイナスにする  

[参考: PHPでクッキーを取得・送信する方法：$_COOKIE, setcookie()](https://uxmilk.jp/15008)  
[参考: PHP Cookieの使い方](https://zenn.dev/k1822m/articles/535cc6c12e6686)  

---
---
## セキュリティ面での注意

- `Cookie` の内容は`ユーザーに見られる・改ざんされる可能性がある` ため、`パスワード` や `機密情報` は絶対に保存しない
- 出力する値には必ず  `htmlspecialchars()` を使ってXSS対策を行う
- `Cookie` を信頼せず、`検証処理` を必ず行う（特に `認証系` では使用に注意）

## 練習問題 

名前を入力したら `Cookie` に保存し、`次回アクセス時に自動で入力欄に表示` されるフォームの作成

## 作業チェックリスト

- `setcookie()` で名前を `Cookie` に保存できたか
- `$_COOKIE` で値を取得してフォームに再表示できたか
- `htmlspecialchars()` でXSS対策を行ったか
- 有効期限を正しく設定できたか


## MEMO

- `name_form.php` 解説  

    // フォームが送信されたらCookieを保存  
    1．Cookie で名前を保存する処理（PHP）  
    ```php
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $name = $_POST['name'];
    setcookie('saved_name', $name, time() + 60 * 60 * 24); // 1日（86400秒）
    }
    ```
    - `$_SERVE['REQUEST_METHOD']=== 'POST'`  
        → フォームが「送信（POST）」されたときだけ、この中の処理を実行  
    - `$_POST['name']`  
        → フォームの `name="name"` に入力された内容を取得  
    - `setcookie('saved_name', $name, time() + 60 * 60 * 24)`
        → 取得した名前を「saved_name」という名前でCookieに保存  
        ※`time() + 60 * 60 * 24` は現在時刻＋24時間（1日）を意味する  

    2．入力フォーム部分（HTML）  
    ```php
    <form method="post" action="">
        <p>名前: <input type="text" name="name"
            value="<?php echo isset($_COOKIE['saved_name']) ? htmlspecialchars($_COOKIE['saved_name'], ENT_QUOTES, 'UTF-8') : ''; ?>">
        </p>
        <input type="submit" value="送信">
    </form>
    ```
    - `<form method="post" action="">`  
        → `method="post"` で送信方法をPOSTに  
        `action=""` なので自分自身のページに送信される  
    - `value="<?php echo isset($_COOKIE['saved_name']) ? ... ?>"`  
        → Cookieの値があれば、その名前をあらかじめ入力欄に表示  
    - `htmlspecialchars(..., ENT_QUOTES, 'UTF-8')`  
        → 名前にHTMLタグなどが含まれていても安全に表示できるように変換  

    3．送信後のあいさつメッセージ（PHP）  
    ```php
    if (isset($_POST['name'])) {
        echo "こんにちは、" . htmlspecialchars($_POST['name'], ENT_QUOTES, 'UTF-8') . "さん！";
    }
    ```
    - `isset($_POST['name'])`  
        → フォームが送信されたかチェック（名前が送られてきたか）  
    - `echo "こんにちは、..."`  
        → 入力された名前を使ってメッセージを表示  

    <全体の流れまとめ>
    - 最初にページを開いたとき、Cookieがあればフォームに名前が表示される
    - フォーム送信時に、入力された名前がCookieとして1日間保存される
    - 同時に「こんにちは、○○さん！」と表示される

- PHPのスクリプトでは、Cookieの処理は「出力より前」に行う必要があるというルールがあるため、自然と「フォームのHTMLより前」に書かれることが多い  
    → PHPでは `setcookie()` を使うと ヘッダー情報（＝ブラウザへの指示）を送るが、これは HTMLを出力する前に行わないとエラーになる  

    → なぜ `setcookie()` は前に書くのか  
    - CookieはHTTPレスポンスヘッダーの一部として送られる
    - PHPでは、画面に何かを出力（echoやHTML）した後に `setcookie()` を使おうとすると、 「ヘッダーはすでに送られたので、もうCookieは設定できません」というエラーになる  
    
    ※もし「PHPの出力がCookie設定より先にきちゃった！」という場合、`ob_start()（出力バッファリング）` を使えばエラー回避も可能

---

参考: []()
