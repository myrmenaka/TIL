# 2025.7.4 - ファイルアップロード処理 1 画像対応

## 目的

画像ファイルをHTMLフォームからアップロードし、PHPで受け取ってサーバー上に保存・表示できるようになる  

※セキュリティ対策は次回 `Day18`

## 目次

- [ファイルアップロードの基本](#1)
- [`$_FILES` で得られる情報](#2)
- [ファイルの保存処理](#3)

## 学習内容

<a id="1"></a>

### ファイルアップロードの基本

- `<form>タグ` に、  
    `enctype="multipart/form-data"` を指定する  
    [参考: mdn web docs : 「フォーム送信のための属性」より「enctype」](https://developer.mozilla.org/ja/docs/Web/HTML/Reference/Elements/form)  

- ファイル選択を行う  
    ```html
    <input type="file" name="アップロード名">
    ```

- PHPで `$_FILES['name']` からファイル情報を取得する  
    [参考: PHPマニュアル : $_FILES](https://www.php.net/manual/ja/reserved.variables.files.php)  

[参考: HTMLのフォームからファイルをアップロードし保存する方法（PHP）](https://www.otsuka-bs.co.jp/web-creation/blog/archive/20250109-01.html)  

---
<a id="2"></a>

### `$_FILES` で得られる情報

#### $_FILES['file']`['name']`  

→ アップロードされた元のファイル名

#### $_FILES['file']`['tmp_name']`  

→ 一時ファイルの保存パス

#### $_FILES['file']`['size']`  

→ ファイルサイズ（バイト）

#### $_FILES['file']`['type']`  

→ MIMEタイプ  
    例）image/jpeg

#### $_FILES['file']`['error']`  

→ ファイルアップロードに関するエラーコード  
    ※「0」 が成功

[参考: PHPマニュアル : POST メソッドによるアップロード](https://www.php.net/manual/ja/features.file-upload.post-method.php)  

---
<a id="3"></a>

### ファイルの保存処理

- `move_uploaded_file()関数` を使ってサーバーへ保存する  

    ```php
    move_uploaded_file(一時パス, 保存先パス)
    ```
    [参考: PHPマニュアル : move_uploaded_file()](https://www.php.net/manual/ja/function.move-uploaded-file.php)  
    ※注意 : コピー先のファイルが既に存在する場合、上書きされる  
- 保存先フォルダには、`書き込み権限` が必要  
    >PHPでファイルアップロードを成功させるには、アップロード先のディレクトリにPHPスクリプトが書き込み権限を持っている必要があります。  
    具体的には、`move_uploaded_file()`関数がファイルを移動する際に、そのディレクトリに書き込み権限がないとエラーになります。
    >
    [参考: Qiita : 【講義11.php_ファイルアップロード】詰んだことでパーミッションの理解が深まった話](https://qiita.com/broccolibroccolibroccoli/items/db3d5315729ced8837ce)  
---
---

## セキュリティ面での注意

- この段階ではまだセキュリティ対策が不十分  
    ※次回 `Day 18` で扱う  
- 特に以下注意点  

    - アップロード可能なファイルの種類を制限していない
    - ファイル名に危険な文字列が含まれる可能性
    - `MIME` タイプの検証をしていない


## 練習問題 

画像ファイルをアップロードして、サーバーに保存＆その場で表示  

- upload_form.php  
    → HTMLフォーム
- upload.php  
    → PHPでアップロード処理

## 作業チェックリスト

- `<form>` に `enctype="multipart/form-data"` を付けたか
- `$_FILES` から適切にファイル情報を取得できたか
- `move_uploaded_file()` でファイルを保存できたか
- 保存した画像を表示できたか

## MEMO

- 今更だけど、フォーム側の `.phpファイル` であっても `<!DOCTYPE html>` の記述は必要  
    → - `.phpファイル` の拡張子に関係なく、表示される内容がHTMLならHTMLの書き方に従うのが基本  
    ※今日までの分、記述忘れてる  

- `upload.php`  
    解説  

    // ファイルがアップロードされているか確認  

    1．画像がちゃんと送られてきたか確認
    ```php
    if (isset($_FILES['image']) && $_FILES['image']['error'] === 0)
    ```
    - `$_FILES['image']` は、フォームから送られてきた画像ファイルの情報が入っているところ
    - `isset(...)` は「その情報がちゃんと存在するか」を確認
    - `error === 0` は「エラーがない＝問題なく画像が送られてきた」という意味  
        → 「画像がちゃんと送られてきたか」チェックしている  

    2．画像が一時的に保存されている場所を取得
    ```php
    $tmpPath = $_FILES['image']['tmp_name'];
    ```
    - 画像は一度サーバーの一時フォルダに保存されるので、その場所（パス）をここでゲット  

    3．元のファイル名を取り出す
    ```php
    $originalName = basename($_FILES['image']['name']);
    ```
    - ユーザーが選んだファイル名（例：cat.jpg）を取得
    - `basename(...)`でファイル名だけを取り出す  
        [参考: PHPマニュアル : basename()](https://www.php.net/manual/ja/function.basename.php)

    4．保存したいフォルダを決める
    ```php
    $saveDir = 'uploads/';
    ```
    - 画像を置いておく `保存場所（フォルダ）` を指定
    - `uploads/` は、プロジェクト内の「アップロード専用フォルダ」といったイメージ  

    // 保存先のディレクトリがなければ作成  
    5．そのフォルダがなければ作る
    ```php
    if (!is_dir($saveDir)) {
    mkdir($saveDir, 0755, true);
    }
    ```
    - `is_dir(...)` → 指定されたパスがディレクトリか確認  
    [参考: PHPマニュアル : is_dir()](https://www.php.net/manual/ja/function.is-dir.php)
    - なければ `mkdir(...)` で新しく作る  
    [参考: PHPマニュアル : mkdir()](https://www.php.net/manual/ja/function.mkdir.php)
        - `0755` は「アクセス許可（安全でよく使われる設定）」、（所有者: 読み書き、他: 読み取り）
        - `true` は「親フォルダも一緒に作ってOK」という意味  

    // 保存パスを指定  
    6．保存先のパスを作る
    ```php
    $savePath = $saveDir . $originalName;
    ```
    - フォルダ名 + ファイル名 をくっつけて、保存する場所（例：uploads/cat.jpg）を作る  

    // ファイルを保存  
    7．ファイルをその場所へ移動（＝保存）
    ```php
    if (move_uploaded_file($tmpPath, $savePath))
    ```
    - `move_uploaded_file()関数` → PHP専用関数
    - 一時的な場所 `$tmpPath` から、本当の保存場所 `$savePath` に移動させる処理
    - これが成功すると、画像がサーバー（upload/）に保存される  

    8．成功したら画面に表示
    ```php
    echo "アップロード成功 <br>";
    echo "<img src='{$savePath}' alt='アップロード画像' style='max-width:300px;'>";
    ```
    - 文字で「アップロード成功！」と表示
    - `<img>タグ` を使って、画面に画像を表示（サイズ調整も記述されている）  
        [参考: mdn web docs : 画像埋め込み要素](https://developer.mozilla.org/ja/docs/Web/HTML/Reference/Elements/img)  

- そもそも `パス` とは…  
[参考: 「分かりそう」で「分からない」でも「分かった」気になれるIT用語辞典 : パス](https://wa3.i-3-i.info/word1166.html)

---

参考: []()
