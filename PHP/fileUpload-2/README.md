# 2025.7.5 - ファイルアップロード処理 2 セキュリティ対策

## 目的

ファイルアップロード処理における `セキュリティリスク` を理解し、`拡張子制限` ・ `MIMEタイプ検証` ・ `ファイル名の安全な処理` などの対策ができるようになる

## 目次

- [なぜファイルアップロードは危険か](#1)
- [許可する拡張子を限定する](#2)
- [MIMEタイプ（実際のファイル形式）を検証する](#3)
- [ファイル名の処理（安全なリネーム）](#4)

## 学習内容

<a id="1"></a>

### なぜファイルアップロードは危険か

- ユーザーが任意のファイルをサーバーに送ることができる  
    → `悪意あるファイル` の可能性がある  
    例）  
    `.phpファイル` をアップロード → サーバー上で実行される危険性  

- 拡張子を偽装された画像（`.jpg` に見せかけたスクリプト）なども注意  

[参考: ファイルアップロードの保護 - サイバー攻撃を防ぐための10のベストプラクティス](https://japanese.opswat.com/blog/file-upload-protection-best-practices)  
[参考: ファイルアップロードの脆弱性はなぜ生まれるのか？その原因と見落としがちな落とし穴を解説！](https://cysec148.hatenablog.com/entry/2025/05/05/170624)  

---
<a id="2"></a>

### 許可する拡張子を限定する

- `pathinfo()関数` でファイルの拡張子を取得  

    ```php
    pathinfo($ファイルパス, PATHINFO_EXTENSION)
    ```
    [参考: PHPマニュアル : pathinfo()](https://www.php.net/manual/ja/function.pathinfo.php)  
    [参考: PHPのpathinfo()で拡張子やファイル名を取得する方法](https://dev-lib.com/php-pathinfo-extension-filename/)  
- 許可された拡張子のみ通す  
    例）`jpg` `png` `gif` …etc

---
<a id="3"></a>

### MIMEタイプ（実際のファイル形式）を検証する

- `finfo_file()` でファイルの実際のタイプをチェック  

    ```php
    finfo_file(resource $finfo, string $filename, int $options = FILEINFO_NONE, resource $context = null): string|false
    ```
    - `$info`  
    → `finfo_open()` で取得したファイル情報リソース  
    MIMEタイプ判定の設定を含む  
    - `$filename`  
    → 判定したいファイルのパス（文字列）  
    例）`image.jpg` や `uploads/test.pdf`  

    以下省略可能
    - `$options`  
    → オプションのフラグ  
    通常は `FILEINFO_NONE`  
    `FILEINFO_MIME_TYPE` などを指定して結果を制御可能
    - `$context`  
    → ストリームコンテキスト  
    HTTPなどのリモートファイルに使う場合に利用  
    通常は `null` でOK  

    [参考: PHPマニュアル : finfo_file()](https://www.php.net/manual/ja/function.finfo-file.php)  
    [参考: ファイル内容からMIMEタイプを確認する方法・finfo_file](https://web.just4fun.biz/?PHP/%E3%83%95%E3%82%A1%E3%82%A4%E3%83%AB%E3%81%AEMIME%E3%82%BF%E3%82%A4%E3%83%97%E3%82%92%E7%A2%BA%E8%AA%8D%E3%81%99%E3%82%8B%E6%96%B9%E6%B3%95%E3%83%BBfinfo_file)  
- MIMEタイプだけでなく、`FILEINFO_MIME` を指定するとエンコーディングも含めた情報を取得可能  
- `finfo_file()` はファイルの中身を少し読み込んで判定するので、拡張子だけに頼らない安全な型チェックが可能
- 拡張子だけで判断せず、実際の内容を検査

---
<a id="4"></a>

### ファイル名の処理（安全なリネーム）

- 元のファイル名を使うと、同名ファイルの上書きや、危険な命名になる可能性がある  
- `uniqid()` などでファイル名を変更して保存する  
    → 現在時刻にもとづいたユニークなIDを生成する関数  
    ※注意  
    >警告
    この関数が生成する値は、暗号学的にセキュアではありません。そのため、これを暗号や、戻り値を推測できないことが必須の値として使っては いけません。
    >

    [参考: PHPマニュアル : uniqid()](https://www.php.net/manual/ja/function.uniqid.php)  
    [参考: ユニークなIDを生成！PHPでuniqidを使う方法【初心者向け】](https://magazine.techacademy.jp/magazine/18802)  

---
---
## セキュリティ面での注意

- 拡張子とMIMEタイプの両方をチェックすることで偽装を防止
- アップロードファイルの保存先に、`実行権限（.php など）` を与えない
- ファイル名は元のまま使わず、`必ず生成して保存` （上書きやパス予測のリスク回避）
- サーバーによっては、 `.htaccess` で `uplpads/ フォルダ内` のPHP実行を禁止するのも有効


## 練習問題 

前回（`Day17`）の画像アップロード処理に、以下のセキュリティ対策を追加  
- 拡張子制限（jpg/png/gif のみ）
- MIMEタイプの検証
- ファイル名の自動生成（ユニークに）


## 作業チェックリスト

- 拡張子のチェックを正しく実装できたか
- `finfo_file()` を使ってMIMEタイプの検証を行ったか
- `uniqid()` で安全なファイル名を生成して保存できたか
- 出力時にHTMLに画像タグを埋め込めたか


## MEMO

- 

---

参考: []()
