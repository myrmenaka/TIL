# 2025.6.20 - 基本文法 5 配列

## 目的

複数の値をひとつの変数にまとめて扱える `配列` の基礎を理解する  

## 目次

- [インデックス配列](#1)
- [配列の要素の追加・変更](#2)
- [`foreach` インデックス配列](#3)
- [連想配列](#4)
- [`foreach` 連想配列](#5)

## MEMO

<a id="1"></a>

### インデックス配列

- 配列の値を、`要素` という  
- 配列の要素の数を、`要素数` という  
- 自動的に `0` から番号（インデックス、添字）が振られる  
- インデックスで値にアクセスする

```php
$変数 = [値, 値, 値];
```
#### 出力方法

```php
echo $変数[出力するインデックス];
```

#### デバック用に配列の全ての要素をまとめて出力する方法  
`var_dump` や `gettype` と異なる点は、改行して出力してくれる  
ただし、`NULL` や `false` は出力できない
```php
print_r($変数);
```
[indexArray.php](indexArray.php)

---
<a id="2"></a>

### 配列の要素の追加・変更

#### 要素の追加  
→　最大インデックスの次に追加される
```php
$変数[] = 値;
```
#### 要素の変更
```php
$変数[変更するインデックス] = 変更後の値;
```
[array-change.php](array-change.php)

---
<a id="3"></a>

### `foreach` インデックス配列

- 配列の `要素` を1つずつ取り出して処理する  
※`$要素名` は新しく命名する  
※配列の中身を順に取り出して、新しい箱に入れるイメージ  

```php
foreach ($配列の変数名 as $要素名) {
    処理;
};
```
---
<a id="4"></a>

### 連想配列

- インデックス配列のインデックスを文字列で管理する  
- この文字列を、`キー` という  
- 文字列で管理するため、意味のある管理が可能になる  
- `連結配列 ＝ ミニデータベース` のようなイメージ  

```php
$変数 = [
    キー => 値,
    キー => 値,
    キー => 値
    ];
```

#### 出力方法

```php
echo $変数[出力するキー];
```

#### 連想配列の要素の追加・変更について

[配列の要素の追加・変更](#2)  
- 上記の `インデックス` を `キー` に差し替えて行う
- 追加の際には新しいキー名を記述する  

[associativeArray.php](associativeArray.php)

---
<a id="5"></a>

### `foreach` 連想配列

- 配列の`キー` と `要素` をそれぞれ1つずつ取り出して処理する  
※`$キー名` と `$要素名` を新しく命名する

```php
foreach ($配列の変数名 as $キー名 => $要素名) {
    処理;
};
```
[associativeArray-foreach.php](associativeArray-foreach.php)

---
## セキュリティ面での注意

- ユーザー入力でキーや値を扱う場合、配列の存在チェック（`isset()`）をしないと `Noticeエラー` になる

---
## 作業チェックリスト

- インデックス配列の作成・アクセスを理解した
- 連想配列の構文と使い方を理解した
- `foreach` で配列をループ処理できるようになった
- 配列の練習問題を手で打って確認した

---
## 疑問・課題

- 要素を追加する際に、インデックスを指定して割り込み追加することはできるのか
- ネスト構造で途中でわけが分からなくなる

---

参考: []()
