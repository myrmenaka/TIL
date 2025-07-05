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

- `upload.php`  
    追記箇所解説  

    // 拡張子チェック  
    1．ファイル名から拡張子を取り出す  
    ```php
    $ext = strtolower(pathinfo($originalName, PATHINFO_EXTENSION));
    ```
    - `pathinfo(..., PATHINFO_EXTENSION)`  
        → ファイル名から拡張子部分だけ（例: png）を抽出
    - `strtolower()`  
        → すべて小文字に変えて比較しやすくする  
        [参考: PHPマニュアル : strtolower()](https://www.php.net/manual/ja/function.strtolower.php)  

    2．拡張子が「使っていい種類」かチェック  
    ```php
    $allowed_ext = ['jpg', 'jpeg', 'png', 'gif'];
    if (!in_array($ext, $allowed_ext)) {
    echo "許可されていない拡張子です。";
    exit;
    }
    ```
    - `$allowed_ext = ['jpg', 'jpeg', 'png', 'gif'];`  
        → 使ってもいいリスト  
    - `in_array($ext, $allowed_ext)`  
        → 抽出した拡張子が許可されたリストに含まれているかどうか  
    - もし入っていなければエラーを出して処理を止める

    // MIMEタイプ検証  
    3．本当に画像かどうか中身もチェック
    ```php
    $finfo = finfo_open(FILEINFO_MIME_TYPE);
    $mimeType = finfo_file($finfo, $tmpPath);
    finfo_close($finfo);
    ```
    - `finfo_open(FILEINFO_MIME_TYPE)`  
        → ファイルのMIMEタイプを調べるためのリソースを作成（中身の検査を準備）  
        [参考: PHPマニュアル : finfo_open()](https://www.php.net/manual/ja/function.finfo-open.php)  
    - `finfo_file(..., $tmpPath)`  
        → 一時ファイルの内容を調べて、実際のファイル形式（例: image/jpeg）を判定  
    - `finfo_close()`  
        → リソースを開放（検査が終わったら閉じる）  
        [参考: PHPマニュアル : finfo_close()](https://www.php.net/manual/ja/function.finfo-close.php)  

        ※拡張子が `.jpg` でも、中身が怪しい場合、ウイルス入りの偽装ファイルなどを防ぐ  

    4．MIMEタイプが画像として問題ないかチェック  
    ```php
    $allowed_mime = ['image/jpeg', 'image/png', 'image/gif'];
    f (!in_array($mimeType, $allowed_mime)) {
    echo "不正なファイル形式です。";
    exit;
    }
    ```
    - `in_array($mimeType, $allowed_mime)`  
        → 実際の中身の形式が許可されたタイプかどうかをチェック  

    // ファイル名をユニーク（かぶらない）にして、保存パスを指定  
    5．ファイル名を一意にして保存先を決定  
    ```php
    $newFileName = uniqid('img_', true) . '.' . $ext;
    $savePath = $saveDir . $newFileName;
    ```
    - `uniqid('img_', true)`  
        → 一意のIDを生成（例: img_64e2f9ab8b0a90.12345678）  
        第二引数の `true` でより高精度なIDにする  

        ※同じファイル名の衝突を防ぎながら、安全に保存しつつ表示できる構成  

- 「何のための処理なのか」をはっきりさせることを意識する  
- 追加するとしたら、ファイルサイズの上限設定、エラーハンドリング

---

参考: []()
