# 2025.6.24 - 基本文法 7-2 フォーム入力の受け取りと表示（POST）

## 目的

- フォームから送られた `POSTデータ` をPHPで受け取り、処理する方法を理解する
- ユーザーからの入力を安全に受け取る方法を学ぶ
- 初歩的な `XSS（クロスサイトスクリプティング）対策` を学ぶ

## 目次

- [HTMLの `<form>` でPOST送信する](#1)
- [`$_POST` で入力データの受け取る](#2)
- [入力値を画面に表示する](#3)
- [`htmlspecialchars()` によるサニタイズ（XSS対策）](#4)

## MEMO

<a id="1"></a>

### HTMLの `<form>` でPOST送信する

HTMLでは、ユーザーから入力を受け取るために `<form>` というタグを使う  
```html
<form action="PHPファイル名" method="post">
```

- `action=PHP"ファイル名"` : 入力内容を送る先の指定
- `method="post"` : 送信方法  
    POST はデータを「見えない形（リクエストボディ）」で送る  
    → URLに表示されない  

※入力欄には `name属性` が必須  
    PHPで受け取るキーになる
```html
<input type="text" name="username">
```

---
<a id="2"></a>

### `$_POST` で入力データの受け取る

フォームの `method="post"` で送られたデータは、PHP側で `$_POST` という配列に入っている  
```php
$username = $_POST['username'];
```

- `$_POST['usrname']` の `username` は、HTMLの `name="username"` に対応している
- このように、ユーザーがフォームに入力した内容をPHPで受け取るようになる


---
<a id="3"></a>

### 入力値を画面に表示する（echo）

受け取った値は、`echo` を使ってHTMLに表示できる  
```php
echo "こんにちは、{$username}さん！";
```
ここで、例えば`username` に「田中」と入力されていれば  
>こんにちは、田中さん！  

と、画面に表示される

---
<a id="4"></a>

### `htmlspecialchars()` によるサニタイズ（XSS対策）

PHPでは、ユーザーから受け取った入力をそのまま `echo` すると危険なコードが実行されてしまうことがある  

例）
>`$_POST['username']` に  
`<script>alert("攻撃")</script>` と入力された場合  
>
>→ JavaScriptが実行されてしまう（XSS攻撃）

これを防ぐために使うのが  
`htmlspecialchars()` : 悪意のあるコードを文字列として表示し、実行されないようにする関数  

セキュリティ対策の基本中の基本！

```php
$代入する変数 = htmlspecialchars($_POST['対象にするキー'], ENT_QUOTES, 'UTF-8');
```
- `<` → `&lt;`
- `>` → `&gt;`
- `"` → `&quot;`  

などに変換され、画面には文字として表示されるだけで、実行さない  

>`ENT_QUOTES` : シングルクオートとダブルクオートを共に変換  
`UTF-8`      : ASCII 互換のマルチバイト 8 ビット Unicode  
>[参考: PHPマニュアル](https://www.php.net/manual/ja/function.htmlspecialchars.php)

#### <簡単な流れ>

1. ユーザーが form に入力
2. POST送信
3. PHPで `$_POST['キー']` として受け取る
4. `htmlspecialchars()` でサニタイズ
5. echo で安全に表示

---

## セキュリティ面での注意

- ユーザー入力をそのまま表示する  
    → `htmlspecialchars()` でサニタイズする
- `$_POST` で存在しないキーを使う  
    → `isset()` で存在チェックする

---
## 作業チェックリスト

- form.html を作成した
- result.php を作成して動作確認した（XAMPP環境使用）
- XAMPP で http://localhost/form.html にアクセスできた  
    ※作業環境やパスでURLは異なるよ
- `$_POST` と `htmlsecialchars()` の使い方が理解できた

---
## 疑問・課題

- HTML の復習
- セキュリティ対策を意識した開発

---

[リファレンス: references.md](references.md)

参考: []()
