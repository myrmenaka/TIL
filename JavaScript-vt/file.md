# ファイルの分離

■ 分離することによるメリット  
・メンテナンスしやすい  
・再利用性が高まる  

■ イベント処理を行うJavaScript を記述する場合、注意  
・HTMLの読込が完了してから、イベント処理を実行しなければならない  
　→ ページのロードが完了するまでDOM操作が行えないため  
　※ HTMLファイルを上から順に読み込んでDOMツリーを作成するため、最後の行まで読み込まないとDOMによる操作が行えない  
・ページのロードが完了したときに実行されるイベントがあるので、そのイベントを利用  

### onloadイベント
・画像ファイルのダウンロードを含めて、すべてのページのロードが完了したときに呼び出される  
・サイズの大きな画像ファイルがある場合、ロードが完了するまで時間がかかる  
・Windowオブジェクトのプロパティとして記述
```js
window.onload = function() {
    // DOM も画像もすべて読み込み完了後に実行
};
```

### DOMContentLoadedイベント
・タグの読込が完了（DOMツリーの完成）したときに呼び出される  
・画像ファイルのダウンロードが行われる前に呼び出されるので、すぐ実行できる  
・`addEventListener()`を利用して記述
```js
document.addEventListener("DOMContentLoaded", function() {
    // DOM が完成した時点で実行
});
```

## JavaScript ファイル分離の注意点

### 1. `<script src="xxx.js">` は 必ず HTML の最後（`</body>` の直前）に置く
・JS が HTML 要素を操作する前に、DOM が読み込まれている必要がある  
・head に置くと、DOM がまだ存在しないため null エラーが起きやすい  

```html
<body>
    ...
    <script src="main.js"></script>
</body>
```

### 2. head に置く場合は defer を必ず付ける
```html
<script src="main.js" defer></script>
```
■ defer の効果：  
　・HTML の解析が終わるまで実行を遅らせる  
　・DOM が存在する状態で JS が動く  
　・実務では defer が標準  

※ async は読み込み完了後すぐ実行され、HTML の解析をブロックする可能性があるため、DOM 操作には不向き  
　→ DOM 操作を行う JS は defer を使う  

### 3. 外部 JS では `<script>` タグを書かない
#### NG：
```js
<script>
console.log("hello");
</script>
```
#### OK：
```js
console.log("hello");
```
### 4. 外部 JS から HTML 要素を取得するときは id / name の一致が必須
```html
<input id="username">
```
#### JS：
```js
document.getElementById("username");
```
1文字違うだけで null になる。

### 5. 外部 JS では イベントリスナーを使うのが基本
・HTML に onclick を書くのは非推奨  
#### NG（HTML と JS が混ざる）：
```html
<button onclick="calc()">計算</button>
```
#### OK（分離できる）：
```js
document.getElementById("btn").addEventListener("click", calc);
```
### 6. 外部 JS は 相対パスの基準が HTML ファイルになる
・○○.js から見た相対パスではなく、
`HTML から見た相対パスになる点`が重要  

### 7. 外部 JS の読み込み順に注意
複数ファイルがある場合：

```html
<script src="lib.js" defer></script>
<script src="main.js" defer></script>
```
main.js で lib.js  の関数を使うなら、
lib.js  を先に読み込む必要がある

### 8. 外部 JS では「グローバル変数の衝突」に注意
複数ファイルで同じ名前の変数を使うと上書きされる  

#### 対策：
・関数内で let / const を使う  
・即時関数（IIFE）でスコープを閉じる  
・モジュール（type="module"）を使う  

### 9. 外部 JS をキャッシュするブラウザの挙動に注意
修正したのに反映されないときは：  
・Ctrl + F5（強制リロード）  
・ファイル名を変える（versioning）  

```html
<script src="main.js?v=1.0"></script>
```
## まとめ
・DOM が読み込まれてから JS を実行することが最重要  
・HTML と JS を完全に分離するのが基本  
・id / name / パスの一致が命  
・イベントリスナーで処理を書くのが現代の標準  
・外部 JS の読み込み順とキャッシュに注意  

## サンプル

### イベント処理を行うJavaScript ファイル
#### ■ onload_sample.js
<流れ>
1. ページ内のすべての読み込みが完了するまで待つ  
・DOM  
・CSS  
・画像  
・外部 JS  
→ 全部読み終わるまで待つ  
2. その後にボタンを取得してイベント登録する  
3. だから script を head に置いても絶対にエラーにならない
```js
// webページのロードが完了したら、自動的に関数initを実行
window.onload = init;

// ボタンにイベントリスナーを登録する関数
function init() {
    document.getElementById("btn").addEventListener('click', showName, false);
}

// イベントハンドラ
function showName() {
    // form内の入力データの取得
    let userName = document.getElementById("form").userName.value;

    // 入力済みかチェック
    if (userName === "") {
        userName = "ゲスト";
    }

    // 結果をwebページ上の目的のタグに文字列として表示
    document.getElementById("result").textContent = "ようこそ" + userName + "さん";
}

```
#### DOMContentLoaded.js
※ 無名関数使用  
<流れ>  
1. DOM が完成するまで待つ  
2. その後にボタンを取得してイベント登録する  
3. だから script を head に置いてもエラーにならない  
```js
// DOMツリーの構築が完了したら、自動的に無名関数を実行
document.addEventListener('DOMContentLoaded', function() {

    // ボタンにイベントリスナーを登録する関数を実行
    document.getElementById("btn").addEventListener('click', function() {
        // form内の入力データの取得
        let userName = document.getElementById("form").userName.value;

        // 入力済みかチェック
        if (userName === "") {
        userName = "ゲスト";
        }

        // 結果をwebページ上の目的のタグに文字列として表示
        document.getElementById("result").textContent = "ようこそ" + userName + "さん";
    }, false);
    
}, false);
```

#### HTMLでは、`<title>`の下あたりに、JavaScript ファイルのソースを記述
```html
<head>
    ...
    <title></title>
    <!-- onload_sample.jsの場合 -->
    <script src="sample/onload_sample.js" defer></script>

    <!-- DOMContentLoaded.jsの場合  -->
    <script src="sample/DOMContentLoaded.js" defer></script>
</head>
<body>
    ...
```
※ head に置く場合は defer を付けることで、HTML の解析をブロックせず、DOM 完成後に安全に実行される

## ※ `<head>` に`<script>` を置かないほうがいいケースは “DOMContentLoaded や onload を使わない場合”
・script 内で DOM を直接触る  
・イベント登録を即時に書いている  
・DOMContentLoaded / onload / defer を使っていない  

→ こういう場合は head に置くと確実にエラーになる。
