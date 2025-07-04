# 2025.7.4 - ファイルアップロード処理 1 画像対応

## 目的

画像ファイルをHTMLフォームからアップロードし、PHPで受け取ってサーバー上に保存・表示できるようになる

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

## 作業チェックリスト

- `<form>` に `enctype="multipart/form-data"` を付けたか
- `$_FILES` から適切にファイル情報を取得できたか
- `move_uploaded_file()` でファイルを保存できたか
- 保存した画像を表示できたか

## MEMO

- 

---

参考: []()
