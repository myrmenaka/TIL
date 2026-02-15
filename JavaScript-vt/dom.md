# DOM (Document Object Model)　タグの操作

■ HTMLやXMLで記述された各要素を取り扱うためのインターフェースを規定したもの（W3C）  
■ HTMLの内容（タグなど）をツリー構造で表現し、管理する仕組み  
■ JavaScriptからHTML文書にアクセスし、その構造を書き換えることができる  
　・プログラムによって、タグの追加や削除などが一時的に行える  
　 → 元々のHTMLファイルは変更しない  
　・HTML内の一般的なタグもオブジェクトとして操作できる  

## ドキュメントツリー

■ HTML内のタグはツリー構造で管理されている  
　・各タグや属性、値は `ノード` と呼ばれる  

### ノードの分類

| 種類 | 内容 | 例 |  
|:--:|:--:|:--:|  
| 要素ノード | HTMLの要素（タグ） | `<h1>`,`<p>` |  
| 属性ノード | 要素の属性 | `<p name="sample">` |  
| テキストノード | 要素内のテキスト | テキスト |  
| 空白ノード | 改行や空白文字など |  |  

※ 空白/改行/タブ もテキストノードとして扱われる場合がある  
（webブラウザによって扱いが異なる）  

### ノードの関係

| 関係 | 内容 |  
|:--:|:--:|  
| ルートノード | ツリーの最上位に位置するノード |    
| 親子ノード | 上下関係があるノード<br>直接つながっているノードで、ある要素の上にあるものを`親ノード` 、下にあるものを`子ノード` と呼ぶ |    
| 兄弟ノード | 同じ親ノードを持つノード<br>先に記述されているものを`兄ノード` 、後に記述されているものを`弟ノード` と呼ぶ |  

## ノード取得
HTML内のタグなどを操作する場合、対象となるノードを取得（特定）しなければならない

### documentオブジェクトのメソッドを利用

| メソッド名 | 内容 |  
|:--:|:--:|  
| document.getElementById("id名") | 指定したid値を持つ要素をElementとして返す |  
| document.getElementByTagName("タグ名") | 指定したタグ要素を持つ要素をHTMLCollectionとして返す |  
| document.getElementByName("name値") | 指定したname値を持つ要素をNodeListとして返す |  
| document.getElementByClassName("class値") | 指定したclass値を持つ要素をHTMLCollectionとして返す |  
| document.querySelector("セレクタ") | 指定したセレクタと一致する要素をElementとして返す |  
| document.querySelectorAll("セレクタ") | 指定したセレクタと一致する要素をNodeListとして返す |  

### ※ HTMLCollection / NodeList
・複数のノードを一つにまとめたもの（配列のようなもの）  
・HTMLCollectionは名前によって値の参照ができる  
・HTMLCollection → 動的  
・NodeList → 静的

#### 以下のプロパティ/メソッドが利用可能
| プロパティ/メソッド | 内容 |  
|:--:|:--:|  
| length | ノードの総数 |  
| item(n) | n番目の要素を取得する |  

## webページの動的変更
・動的変更する際に利用される主なキーワード  
　→ DOMツリー内で対象のノードを特定したら、以下のキーワードを利用して各種データを変更するとwebページの内容を動的に変更させることができる  

| キーワード | 機能 |  
|:--:|:--:|  
| element.textContent | 表示されている文字列を変更する（タグは無効） |  
| element.innerHTML | 表示されている文字列を変更する（タグは有効）<br> → HTMLの構造ごと入れ替えるときはこっち |  
| element.属性名 | タグの属性を変更する<br> → 画像だけ変えるなどの時はこっち<br>他のタグも一緒に差し替えるときは、innerHTML() |  
| element.style.属性名 | タグに適用されているスタイルシートの内容を変更する |  

### 例１：`.`でつなげる  
```js
// textContent
document.getElementById("title").textContent = "こんにちは";
// innerHTML
document.getElementById("title").innerHTML = "文字";
// 属性名
document.getElementById("title").src = "画像URL";
// style.属性名
document.getElementById("title").color = "red";
```
### 例２：変数にまとめる  
```js
const box = document.getElementById("box");

box.textContent = "こんにちは";
box.innerHTML = "<b>こんにちは</b>";
box.id = "new-id";
box.style.color = "blue";
box.style.backgroundColor = "yellow";
```

・新規にタグを作成したり、既存のタグを削除する場合は別途用意されているメソッドを利用  

| メソッド名 | 機能 |  
|:--:|:--:|  
| createElement() | タグを新規に作成する |  
| appendChild() | タグをDOMツリーに追加する |  
| removeChild() | DOMツリーからタグを削除する |  
| replaceChild() | DOMツリーのタグを変更する |  

## ノードの追加/置換/削除

■ DOMを利用し、表示後の各要素を操作することができる  
　・webブラウザの中だけで処理され、元々のHTMLファイルを書き換えているわけではない  
　・ノードを追加する場合、新規作成しただけではDOMツリーに反映されない  

※ 別途、DOMツリーに関連付ける処理を行う必要がある  
　→ 基準となる要素を指定する  

### 手順
■ 子ノードの新規作成  
1. ノードを新規に作成  
```js
let newTag = document.createElement('作成するタグ名')
```
2. 必要な情報を設定（表示する文字列や各種属性）  
```js
newTag.id = "new";
newTag.textContent = "new node";
```
3. DOMツリーに追加（登録）  
```js
既にDOMにある親ノード.appendChild(newTag);
```
（例）
```js
// body に追加したい場合
document.body.appendChild(newTag);

// id="target" の要素に追加したい場合
document.getElementById("target").appendChild(newTag);
```

■ 親ノードの新規作成  
1. ノードを新規に作成  
```js
let parent = document.createElement("div");

```
2. 子ノードの作成  
```js
let child = document.createElement("p");
child.textContent = "子ノードです";

```
3. 親ノードに子ノードを追加  
```js
parent.appendChild(child);

```
4. 親ノードをDOMに追加  
```js
// 例：bodyに追加
document.body.appendChild(parent);
```

■ ノードの変更  
1. 変更後の新しいノードを作成  
```js
let newNode = document.createElement("p");
```
2. 必要な情報を設定  
```js
newNode.textContent = "新しいノードです";
newNode.className = "updated";
```
3. DOMツリー内の他の既存ノードと変更
```js
let oldNode = document.getElementById("target");
oldNode.replaceWith(newNode);
```

■ ノードの削除  
1. 削除したいノードを特定  
```js
let target = document.getElementById("target");
```
2. DOMツリーから削除  
```js
target.remove();
```
