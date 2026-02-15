# タイマー機能

「一定時間毎」、「一定時間経過後」に特定の処理を実行する  

## 一定時間毎
時間：ミリ秒で指定
```js
let 変数名 = window.setInterval(処理, 時間);
```
### setInterval の“ズレ”について
JavaScript の setInterval は、処理が重いと少しずつズレるという仕様がある  

例：1秒ごとに実行しても、実際には 1.01秒、1.02秒… と誤差が積み重なる  
#### ※ ただし今回の時計サンプルは `new Date()` を使っているのでズレない
理由は、「現在時刻を毎回取り出す」ため  
　・setInterval は”正確な1秒”ではない  
　　→ 「1秒後に実行してね」とお願いするだけ  
　・JavaScript はシングルスレッド  
　・`new Date()` は「本物（OS）の現在時刻」を返す  
　　→ setInterval の”タイミング”のズレがあっても、`new Date()` が返す”時計表示”はズレない

## 一定時間経過後
```js
let 変数名 = window.setTimeout(処理, 時間);
```
サンプル
```js
setTimeout(function() {
    alert("3秒経過しました");
}, 3000);
```
## タイマーの取り消し
タイマーを設定したときの変数を引数として指定
```js
clearInterval(変数);
clearTimeout(変数);
```

※ このままだと「停止しても時計表示は残る」ので、仕様によっては表示のリセットも入れる
```js
clearInterval(timerId);
document.getElementById("clock").textContent = "--時--分--秒";
```

## サンプル

### ■ 表示ボタンを押したら、デジタル時計を表示
```html
    <script src="sample/sample.js"></script>
</head>
<body>
    <h1>デジタル時計</h1>

    <!-- スタートボタン・ストップボタン -->
    <button id="btnStart">表示</button>
    <button id="btnStop">停止</button>
    <hr>

    <!-- 現在時刻を表示 -->
    <p id="clock">--時--分--秒</p>
</body>
```
### ■ イベント処理を行うJavaScript ファイル
```js
document.addEventListener('DOMContentLoaded', function() {
    // 開始したタイマー情報を格納する変数
    let timerId;

    // 表示ボタンにイベントリスナーを登録する関数を実行
    document.getElementById("btnStart").addEventListener('click', function() {
        // 二重起動防止
        if (timerId) return;
        
        // イベントハンドラ（1秒ごとに無名関数を実行）
        timerId = window.setInterval(function() {
            // 現在時刻の取得
            let now = new Date();

            // 時刻の値の取得 ※表示は1桁
            let hh = now.getHours();
            let mm = now.getMinutes();
            let ss = now.getSeconds();

            // 表示する文字列の作成
            let time = hh + "時" + mm + "分" + ss + "秒";
            // webページ上に現在の時刻を表示
            document.getElementById("clock").textContent = time;
        // setInterval()関数の第二引数はインターバル時間（ミリ秒）
        }, 1000);
    }, false);

    // 停止ボタンにイベントリスナーを登録する関数を実行
    document.getElementById("btnStop").addEventListener('click', function() {
        // 実行中のタイマーの停止
        clearInterval(timerId);
        // 再スタート可能にする
        timerId = null;
    }, false);

}, false);
```

#### ※ 時刻の値の取得時に、2桁表示に変更可能
```js
let hh = String(now.getHours()).padStart(2, "0");
let mm = String(now.getMinutes()).padStart(2, "0");
let ss = String(now.getSeconds()).padStart(2, "0");
```
#### ※ padStart()
```js
String(値).padStart(桁数, 埋める文字);
```
・第一引数：  最終的に何桁にしたいか
・第二引数：  足りない分を何で埋めるか  

