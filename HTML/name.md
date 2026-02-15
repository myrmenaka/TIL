# name 属性の本質（フォーム送信の仕組み）
フォーム理解の“心臓部”  
ここを押さえると HTML → サーバー → データ の流れが一気につながる

## name属性の本質

・name は「サーバーに送るときの変数名」  
・送信されるデータは name=値 の形になる  

→ name がない input は、サーバーに値が届かない。

### 1. name があると送信される（基本）
```html
<input type="text" name="username" value="akane">
```
送信されるデータ：
```
username=akane
```
これが HTTPリクエストの中身

### 2. name がないと送信されない（重要）
```html
<input type="text" id="username" value="akane">
```
→ 見た目は同じでも、  
→ サーバーには何も送られない  

`id は UI のため`  
`name は データのため`  
という役割の違いがここでハッキリする

### 3. radio が「1つだけ選べる」理由は name にある
```html
<input type="radio" name="gender" value="male">
<input type="radio" name="gender" value="female">
```
・同じ name の中で1つだけ選べる  
→ ブラウザが自動で排他的にしてくれる  

送信されるデータ：
```
gender=male
```
### 4. checkbox は「複数選べる」理由も name にある
```html
<input type="checkbox" name="hobby" value="music">
<input type="checkbox" name="hobby" value="game">
```
複数チェックすると：
```
hobby=music
hobby=game
```
サーバー側では配列として受け取ることが多い

### 5. 配列送信（hobby[] の意味）
よく見るこれ：
```html
<input type="checkbox" name="hobby[]" value="music">
<input type="checkbox" name="hobby[]" value="game">
```
送信されるデータ：
```
hobby[]=music
hobby[]=game
```
→ サーバー側で 配列として受け取れる  
→ PHP/Laravel だと `$request->hobby` が配列になる  

※ Laravel 学習にも直結する部分

### 6. hidden と name の関係
hidden は「画面に見えないけど送信したい値」  
```html
<input type="hidden" name="token" value="abc123">
```
送信される値：
```
token=abc123
```
→ CSRFトークン  
→ 商品ID  
→ 内部的な値  
などに使う。

### 7. name のない input は“存在しない”扱い
```html
<input type="text" id="email">
```
見た目はあるが、サーバーには 何も送られない  

→ 初心者が一番やりがちなミス  
→ name を強調しないと事故る理由  

## まとめ
【name】＝サーバーに送るときのキー名  
【id】＝label と結びつけるための固有名  

・name がない → データは送られない  
・id がない → label が機能しない  
・radio は name が同じだから1つだけ選べる  
・checkbox は name が同じでも複数送れる  
・hobby[] は配列送信  
