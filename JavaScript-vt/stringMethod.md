# Stringオブジェクトの主なメソッド

## ■ length
【目的】文字数を取得  
【返り値】number  

```js
"hello".length; // 5
```
## ■ includes()
【目的】部分一致の判定  
【返り値】boolean  
【用途】バリデーション、検索  

```js
"javascript".includes("script"); // true
```
## ■ startsWith() / endsWith()
【目的】前方一致 / 後方一致  
【返り値】boolean  
【用途】ファイル拡張子チェック、URL判定

```js
"hello.js".endsWith(".js"); // true
```
## ■ slice()
【目的】部分文字列を取得（非破壊）  
【返り値】string  

```js
"abcdef".slice(1, 4); // "bcd"
```
## ■ substring()
【目的】部分文字列を取得（sliceに似てる）  
【返り値】string  
【特徴】負の値を0として扱う  

```js
"abcdef".substring(1, 4); // "bcd"
```
## ■ replace() / replaceAll()
【目的】文字列の置換  
【返り値】string  
【用途】サニタイズ、整形  

```js
"aaa".replace("a", "b");     // "baa"
"aaa".replaceAll("a", "b");  // "bbb"
```
## ■ toUpperCase() / toLowerCase()
【目的】大文字化 / 小文字化  
【返り値】string  
【用途】比較、正規化  

```js
"Hello".toLowerCase(); // "hello"
```
## ■ trim() / trimStart() / trimEnd()
【目的】空白の除去  
【返り値】string  
【用途】フォーム入力の前処理  

```js
"  hello  ".trim(); // "hello"
```
## ■ split()
【目的】区切って配列にする  
【返り値】string[]  
【用途】CSV、タグ分割  

```js
"apple,banana".split(","); // ["apple", "banana"]
```
## ■ repeat()
【目的】文字列の繰り返し  
【返り値】string  

```js
"ha".repeat(3); // "hahaha"
```
## ■ padStart() / padEnd()
【目的】指定長さになるまで埋める  
【返り値】string  
【用途】ゼロ埋め、整形  

```js
"5".padStart(3, "0"); // "005"
```
## ■ charAt() / charCodeAt()
【目的】指定位置の文字・文字コード取得  
【返り値】string / number  

```js
"abc".charAt(1); // "b"
```
## ■ indexOf() / lastIndexOf()
【目的】部分文字列の位置を取得  
【返り値】number（見つからなければ -1）  

```js
"banana".indexOf("a"); // 1
```

## よく使うTOP5
1.includes()  
2.slice()  
3.replace() / replaceAll()  
4.split()  
5.trim()  

この5つを押さえておけば、文字列処理の8割は対応できる

## ポイント
・Stringは不変（immutable）  
→ どのメソッドも「元の文字列は変わらない」

・replaceは1つだけ、replaceAllは全部  
→ 実務では replaceAll の方が使いやすい

・splitは配列に変換する入口  
→ map/filter と組み合わせると強力