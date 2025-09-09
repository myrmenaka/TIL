# 2025.7.8 - 確認画面付きのフォーム処理（POSTとセッションの活用）

## 目的

POST送信とセッションを使って、「入力 → 確認 → 完了」の3画面構成フォームを作成し、実務でもよく使われる「確認画面付き送信処理」の流れを理解する

## 目次

- [フォーム処理の3ステップ構成](#1)
- [セッションを使う理由](#2)
- [基本的な流れ](#3)

## 学習内容

<a id="1"></a>

### フォーム処理の3ステップ構成

- 入力画面  
    - フォームをPOSTで送信すると `確認画面` に移動する
- 確認画面  
    - POSTで受け取り、セッションに保存  
    - 「修正する」ボタンと「送信する」ボタンを設置
    - `入力画面` から送信されてきた内容を取得し、表示する
- 完了画面  
    - 送信された内容を表示する  
    → セッションから表示、POSTの再送信を防ぐ

[参考: 入力画面、確認画面、完了画面を実装しよう](https://hackmd.io/@GH-wNvm-QfG5Y89-RmIY8g/HyEACrfK_)  

---
<a id="2"></a>

### セッションを使う理由

- POSTされたデータを、確認画面 → 完了画面 に引き継ぐ
- ブラウザの「戻る」や「リロード（再読み込み）」でもデータが保持される
- POST → リダイレクト → GET（PRGパターン）を使うと、二重送信防止にもなる

[参考: リダイレクトとは？初心者でも絶対分かる徹底解説｜設定方法も紹介](https://tms-partners.com/12216/)  
[参考: 新人Webエンジニア必須？の知識「PRGパターン」について](https://zenn.dev/imah/articles/3d186a6462ecc8)  
[参考: POST メソッドの多重送信を防ぐ PRG パターンとは](https://poco-tech.com/posts/spring-boot-introduction/post-redirect-get-pattern/)  

---
<a id="3"></a>

### 基本的な流れ

`入力画面`  
　↓  
`POST`  
　↓  
`確認画面`  
　↓  
`POST`  
　↓  
`処理`  
　↓  
`リダイレクト`  
　↓  
`完了画面`  

---
---
## セキュリティ面での注意

- フォームの値はサニタイズ（`htmlspecalchars()`）して表示
- `process.php` ではセッションの存在を必ず確認（直接アクセスの防止）
- セッションデータは `完了後に削除` し、`再送信やなりすましを防止`
- 入力値の検証（バリデーション）やトークンによるCSRF対策は、より実務的にする場合は必須


## 練習問題 

名前とメールアドレスを入力するフォームを作成し、`確認画面 → 完了画面` の流れを実装  

- input.php  
    → 入力画面
- confirm.php  
    → 確認画面
- process.php  
    → 処理・リダイレクト
- thanks.php  
    → 完了画面

## 作業チェックリスト

- 入力画面・確認画面・完了画面の3構成を作成したか
- セッションでデータを保持・受け渡しできたか
- `PRGパターン（POST → リダイレクト → GET）` で再送信防止できたか
- 出力時はすべて `htmlspecalchars()` を使ったか


## MEMO

- `input.php` 解説  
    ```html
    <form action="./confirm.php" method="post">
        <p>名前: <input type="text" name="name" required></p>
        <p>メール: <input type="email" name="email" required></p>
        <input type="submit" value="確認する">
    </form>
    ```
    - `required`  
    → 入力が必須になり、空欄のままでは送信すると警告が出る  
    ※翻訳: required ＝ 必須  
    [参考: mdn web docs : HTML属性:required](https://developer.mozilla.org/ja/docs/Web/HTML/Reference/Attributes/required)  

- `confirm.php` 解説  

    1．POSTデータのチェックとセッション保存  
    ```php
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        $_SESSION['form'] = [
            'name' => $_POST['name'],
            'email' => $_POST['email']
        ];
    } else {
        header('Location: input.php');
        exit;
    }
    ```
    - `$_SERVER['REQUEST_METHOD'] === 'POST'`  
    → このページがPOST（フォーム送信）で呼び出されたか確認  
    - `$_SESSION['form'] = [...]`  
    → フォームで送られた `name` と `email` をセッション変数 `form` に保存
    - `else { ... }`  
    → POST送信でなかった場合（直接アクセスされた等）は `input.php（入力画面）` に強制リダイレクト  

    2．セッションの中身を変数に代入  
    ```php
    $form = $_SESSION['form'];
    ```
    - セッション変数の内容を `$form` に代入することで、コードを見やすくする  

    3．入力確認の表示（HTML部分）  
    ```html
    <h2>確認画面</h2>
    <p>名前: <?php echo htmlspecialchars($form['name'], ENT_QUOTES, 'UTF-8'); ?></p>
    <p>メール: <?php echo htmlspecialchars($form['email'], ENT_QUOTES, 'UTF-8'); ?></p>
    ```
    - ユーザーが入力した内容を画面に表示  
    - `htmlspecialchars()` を使って、HTMLタグなどの危険な文字列をエスケープして安全に表示  

    4．送信・修正ボタン  
    ```html
    <form action="./process.php" method="post">
        <input type="submit" value="送信する">
    </form>
    <form action="./input.php" method="get">
        <input type="submit" value="修正する">
    </form>
    ```
    - 上のフォーム  
    → 「送信する」ボタンが押されたら、次の処理 `process.php（処理・リダイレクト）` へ進む  
    - 下のフォーム
    → 「修正する」ボタンを押すと、`input.php（入力画面）` に戻る  

    全体の流れまとめ  
    - `input.php（入力画面）` からPOSTされた名前・メールをセッションに保存
    - セッションの情報を使って確認画面を表示
    - 「送信」 or 「修正」ボタンで次の処理へ分岐

- `process.php` 解説  

    1．セッションにformデータがあるか確認  
    ```php
    if (!isset($_SESSION['form'])) {
        header('Location: input.php');
        exit;
    }
    ```
    - `!isset(...)` は「存在していない場合」を意味  
    - form データがセッションにない場合（＝正しい手順で来ていない）なら、入力画面に戻す  
    - `header('Location: input.php');`  
    → 「リダイレクト命令」  
    - `exit;`  
    → 処理を即終了  

    2．実際の処理（今回は省略）  
    ```php
    // ここでDB登録処理やメール送信など行う（今回は省略）
    ```
    - 本来なら、ここでDBに保存したり、メール送信したりする処理を入れる場所  
    - 例）  
    ```php
    // データベースへ挿入
    $stmt = $pdo->prepare('INSERT INTO users (name, email) VALUES (?, ?)');
    $stmt->execute([$_SESSION['form']['name'], $_SESSION['form']['email']]);
    ```

    3．完了フラグを設定  
        [※完了フラグについてもう少し解説](#a)  
    ```php
    $_SESSION['complete'] = true;
    ```
    - 「処理が完了した」ことを記録しておくためのフラグ  
    - これを `thanks.php（完了画面）` で見て「一度だけありがとう画面を表示する」などに使える  

    4．二重送信防止  
    ```php
    unset($_SESSION['form']);
    ```
    - セッション変数 `form` を削除
    - これで再度ページを開いても処理は繰り返されなくなる（＝二重送信を防止）  

    5．完了画面へリダイレクト  
    ```php
    header('Location: thanks.php');
    exit;
    ```
    - ユーザーが直接このファイルを見ることはなく、`thanks.php（完了画面）` へ移動  
    - 完了したらすぐ画面遷移することで、「スムーズなUX」をつくる  
        ※UXとは、User Experience（ユーザーエクスペリエンス）の略  
        ユーザー体験のこと、使いやすさや満足度を含めた体験全体  
        ちなみに、UI（ユーザーインターフェース）は、ユーザーが製品やサービスと接する際の画面や操作部分を指す  
        UXは、UIを含めたユーザー体験全体を指す  

    全体の流れまとめ  
    - セッションに form データがあるか確認
    - 登録や送信などの処理（省略）
    - 完了フラグで一度だけの表示を管理
    - 二重送信防止のためセッションから削除
    - `thanks.php（完了画面）` に移動

- `thanks.php` 解説  

    1．完了フラグの確認（`empty()` でチェック）  
    ```php
    if (empty($_SESSION['complete'])) {
        header('Location: input.php');
        exit;
    }
    ```
    - `$_SESSION['complete']` が `空（null, false, 未定義など）` なら、入力画面に戻す  
    - つまり、「`process.php` を通っていない人は見ちゃダメ！」という安全対策  
    - `header('Location: input.php');`
    → リダイレクト命令  
    - `exit;`  
    → 処理を終了  

    2．完了フラグの削除（1回だけ表示させる）  
    ```php
    unset($_SESSION['complete']);
    ```
    - 一度完了画面を見たら、フラグを削除して次回は見せないようにしている  
    - これにより「ページのリロードで再表示されない」「URL直打ち防止」などの効果がある  

    3．画面の表示（HTML）  
    ```html
    <h2>送信が完了しました！</h2>
    <p>ご協力ありがとうございました。</p>
    ```
    - ユーザーに「処理が完了しました」と伝えるメッセージ  
    - サーバー側で完了したことをちゃんと確認したうえで表示される、安心な仕組み  

    全体の流れまとめ  
    - `process.php` で `$_SESSION['complete'] = true;` をセットする
    - `thanks.php` でそれをチェックし、表示許可
    - 表示後は `unset()` で削除して、二度と表示されないようにする

<a id="a"></a>
- 完了フラグについてもう少し解説  

    → どうして、`$_SESSION['complete'] = true;` この記述がフラグになるのか  

    `thanks.php` では `$_SESSION['complete']` の存在をチェックすることで、「`process.php` をちゃんと通ったか？」を判断する  

    そもそも、なぜ `$_SESSION['complete'] = true` という記述なのか  
    - `complete` は英語で「完了」の意味  
        → フラグ名（キー）として直感的でわかりやすい（他にも`submitted` や `done` などでも良いが、目的が伝わりやすい意味で `complete` がよく使われる）  
        ※つまり極論なんでもいい  
    - では`= true` は何か  
        → こちらも極論、値はなんでもいい（`yes` とか `1` とか）  
        ここで必要なのは、`$_SESSION['complete']` が `定義されている＝存在している` こと  
        `true` は単に、「完了していること」を確認するための印  
        `true` にしているのは、人が見て意味がわかりやすいため（PHPで `true` は真（「はい」や「正しい」）という意味で、フラグ用途に合致している…というだけ）  

    セッションのやりとりをイメージで整理すると  

    `process.php` 内でやっていること  
    ```php
    $_SESSION['complete'] = true;
    ```
    → これは 「処理完了した」という目印をセッションに登録  

    `thanks.php` で確認する流れ  
    ```php
    if (!isset($_SESSION['complete'])) {
        header('Location: input.php');
        exit;
    }
    ```
    → 「その目印（`$_SESSION['complete'] = true;`）があるか？」を見て、  
    あれば表示OK（＝完了済み）  
    なければ拒否（＝不正アクセスやリロード）  

    なぜ“分かる”のか？  
    セッションは、ユーザーごとのブラウザとPHPの間で保持される一時的な保存領域  
    だから、`process.php` で設定されたセッション変数は、そのまま `thanks.php` でも読み取れる  
    → 前のページで置いてきたメモを「次のページで拾う」ようなイメージ  

    そして、使い終わったら削除  
    ```php
    unset($_SESSION['complete']);
    ```
    - これを `thanks.php` の最後に書いておけば、「一度だけ」完了画面を表示できる  
    → 2回目にアクセスしても、フラグがないので元に戻される  

---

参考: []()
