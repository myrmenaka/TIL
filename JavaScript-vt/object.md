# 時刻・文字列・数値操作

## ビルドインオブジェクト

## String（文字列）
### length
文字数を取得  
【返り値】number

```js
"hello".length; // 5
```
### includes()
部分一致を判定  
【返り値】boolean

```js
"javascript".includes("script"); // true
```
### slice()
部分文字列を取得（非破壊）  
【返り値】string

```js
"abcdef".slice(1, 4); // "bcd"
```
### replace() / replaceAll()
文字列の置換  
【返り値】string

```js
"aaa".replace("a", "b");     // "baa"
"aaa".replaceAll("a", "b");  // "bbb"
```
### split()
区切って配列にする  
【返り値】string[]

```js
"apple,banana".split(","); // ["apple", "banana"]
```
## Number（数値）
### toFixed()
小数点以下を丸めて文字列にする  
【返り値】string

```js
(3.14159).toFixed(2); // "3.14"
```
### parseInt() / parseFloat()
文字列 → 数値  
【返り値】number

```js
parseInt("42");   // 42
parseFloat("3.14"); // 3.14
```
## Date（日付）
### new Date()
現在日時を取得  
【返り値】Date

```js
const now = new Date();

// 指定した日時で作成
const now = new Date(年, 月, 日, [時, 分, 秒]);
```
### getFullYear() / getMonth() / getDate()
日付の各要素（年・月・日）を取得  
【返り値】number

```js
now.getFullYear(); // 2026
now.getMonth();    // 0〜11（注意）
now.getDate();     // 1〜31
```
### getTime()
UNIXタイム（ミリ秒）を取得  
→ 日付差の計算でよく使う  
【返り値】number

```js
const diff = date2.getTime() - date1.getTime();
```
## Math（数学系ユーティリティ）
### Math.random()
0〜1のランダム数  
【返り値】number

```js
Math.random(); // 0.123...

// 乱数の種類
Math.random() * 乱数の種類;
// （例）Math.random() * 5;
// → 乱数の種類だけ作成する乱数に掛ける
// 例えば、0～4なら種類は5となる
```
### Math.floor() / Math.ceil() / Math.round()
切り捨て / 切り上げ / 四捨五入  
【返り値】number

```js
Math.floor(3.9); // 3
Math.ceil(3.1);  // 4
Math.round(3.4); // 3
```
### Math.max() / Math.min()
最大値・最小値  
【返り値】number

```js
Math.max(1, 5, 3); // 5
```
## Array（配列）
（すでに触れてるので、要点だけ）  
### map()
変換（非破壊）  
【返り値】新しい配列
### filter()
条件に合う要素を抽出（非破壊）  
【返り値】新しい配列
### reduce()
集計・合計  
【返り値】任意の値
### find()
条件に合う1件  
【返り値】要素 or undefined
### push() / pop()
末尾の追加・削除（破壊的）  
【返り値】push → 新しい長さ / pop → 削除した要素
## Object（オブジェクト）
### Object.keys()
キー一覧を配列で取得  
【返り値】string[]

```js
Object.keys({ a: 1, b: 2 }); // ["a", "b"]
```
### Object.values()
値一覧を取得  
【返り値】any[]

```js
Object.values({ a: 1, b: 2 }); // [1, 2]
```
### Object.entries()
キーと値のペアを取得  
【返り値】[key, value][]

```js
Object.entries({ a: 1, b: 2 });
// [["a", 1], ["b", 2]]
```
### Object.assign()
オブジェクトを結合（破壊的）

```js
Object.assign({}, {a:1}, {b:2}); // {a:1, b:2}
```
### structuredClone()（新しめ）
オブジェクトを深くコピー（非破壊）

```js
const copy = structuredClone(obj);
```
## JSON（データ形式）
### JSON.stringify()
オブジェクト → JSON文字列

```js
JSON.stringify({ a: 1 }); // '{"a":1}'
```
### JSON.parse()
JSON文字列 → オブジェクト

```js
JSON.parse('{"a":1}'); // {a:1}
```

## 使い分け

| やりたいこと | 使うビルトイン |  
|:--:|:--:|  
| 文字列操作 | String |  
| 数値の丸め・計算 | Number / Math |  
| 日付処理 | Date |  
| 配列の加工 | Array |  
| オブジェクトの変換・コピー | Object |  
| APIのデータ変換 | JSON |  
