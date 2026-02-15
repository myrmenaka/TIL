# イベント処理

■ イベントリスナー/イベントハンドラ  
・イベント処理では捕捉時に何を実行するか、通常は関数名を記述  
・チェックしたいイベントがあれば、事前に捕捉するようにイベントリスナー（イベントの種類）を記述  
・イベントハンドラ＝関数  

## イベント処理の登録

### ■ タグ内の属性として宣言　：非推奨  
・HTML文書とJavaScriptの`コードを分離することができない`  
```js
<タグ名 onイベント名 = '関数名()'>

// 例
<button onclick="myFunc()">押す</button>
```
### ■ 要素オブジェクトのプロパティとして宣言  
・HTML文書とJavaScriptの`コードを別々のファイルに分離することができる`  
　→ ページが読み込まれた時にイベント処理を実行する  
　※ ページの読み込みが完了していないと、要素オブジェクトが利用できないため  
・DOMが読み込まれてから実行する必要がある  
・1つの要素オブジェクトに`1種類のイベント処理しか設定できない` （上書き）  
```js
要素オブジェクト.onイベント名 = 関数名;
```  
### ■ addEventListenerメソッドを使って宣言　：最も推奨  
・HTML文書とJavaScriptの`コードを別々のファイルに完全に分離することができる`  
　→ ページが読み込まれた時にイベント処理を実行する  
　※ ページの読み込みが完了していないと、要素オブジェクトが利用できないため  
・イベントの細かい制御（キャプチャ/バブリング）が可能  
・1つの要素オブジェクトに`複数のイベント処理を同時に設定することができる`  
```js
要素オブジェクト.addEventListener('イベントの種類', 関数名, false);
```
※ 第三引数の`false`は「キャプチャリングではなく、バブリングで処理する」という指定  
　キャプチャリング：親要素から子要素へ伝播していく  
　→ デフォルトで`false`なのはバブリングで処理することが多いため  

## 各種イベント

### 読込
| イベント | イベントが発生するタイミング | 設定可能な要素 |  
|:--:|:--:|:--:|  
| load | ページ内の画像・CSS・JS など全て読み込み完了 | window, body, img, script, link |  
| unload | ページから離れるとき | window, body |  
| DOMContentLoaded | HTMLの解析が終わった時点 | document |  
### マウス
| イベント | イベントが発生するタイミング | 設定可能な要素 |  
|:--:|:--:|:--:|  
| click | 対象をクリックしたとき | ほぼ全ての要素 |  
| dblclick | 対象をダブルクリックしたとき | ほぼ全ての要素 |  
| mousedown | 対象上でマウスボタンを押したとき | ほぼ全ての要素 |  
| mouseup | 対象上でマウスボタンを離したとき | ほぼ全ての要素 |  
| mouseover | 対象上にマウスポインタが重なったとき | ほぼ全ての要素 |  
| mouseout | 対象上からマウスポインタが離れたとき | ほぼ全ての要素 |  
### フォーム
| イベント | イベントが発生するタイミング | 設定可能な要素 |  
|:--:|:--:|:--:|  
| submit | フォーム上のsubmitボタンを押したとき | form |  
| reset | フォーム上のresetボタンを押したとき | form |  
| change | 対象の値が変化したとき<br>入力（値）が確定したとき | input(text), select, textarea, checkbox, radio |  
| input | 入力欄の内容が変化したとき<br>（キーボード入力・ペースト・変換確定など） | input, textarea, contenteditable要素 |  
### フォーカス
| イベント | イベントが発生するタイミング | 設定可能な要素 |  
|:--:|:--:|:--:|  
| focus | 対象にフォーカスが当たったとき | a, area, label, input, select, <br>textarea, button |  
| blur | 対象からフォーカスが外れたとき | a, area, label, input, select, <br>textarea, button |  
※ `focus` と `blur` はバブリングしない  
　→ バブリング：対象の要素だけでなく、親要素へ伝播していく  
### キーボード
| イベント | イベントが発生するタイミング | 設定可能な要素 |  
|:--:|:--:|:--:|  
| keydown | キーを押した瞬間 | window, document, input, textarea |  
| keyup | キーを離した瞬間 | window, document, input, textarea |  
| keypress | 文字が入力されたとき（非推奨） | window, document, input, textarea |  
| input | （※キーボードイベントではなくフォームイベント）<br>入力欄の内容が変化したとき | input, textarea, contenteditable要素 |  

※ `input` イベントは、フォームイベント  
ただし、「キーボード入力によって発火することが多い」ので、分類はフォーム、挙動はキーボード寄り  

## ■ フォーム内のデータをプログラムで利用する場合の手順
・フォームに「id属性値」を設定  
・プログラムで利用したいコントロールに「name属性値」を設定  
　→ 「id属性値」を指定してフォームを特定し、そのフォーム内の各種コントロールはそれぞれの「name属性値」を利用して特定する  
　→ 各種コントロールで入力/選択されたデータは「value属性」を参照すれば取得できる  
```js
    <script>
        function showName() {
            /* 
            form内のテキストボックスに入力されているデータを取得
                1.id="form"が設定されているタグを特定
                2.特定したフォーム内で
                name="username"が設定されているタグを参照
                3.その値（value）を取得
            */
            let userName = document.getElementById("form").username.value;

            // 入力済みかチェック
            if(userName === "") {
                userName = "未入力です";
            }

            // 結果をwebページ上の目的のタグに文字列として表示
            document.getElementById("result").textContent = userName;
        }
    </script>
</head>
<body>
    // データ入力用フォーム
    <form id="form">
        氏名：
        <input type="text" name="username" placeholder="氏名を入力してください">
        <input type="button" id="btnShow" value="表示">
    </form>

    // 結果を表示するためのpタグ
    <p id="result"></p>

    // イベントリスナー登録
    <script>
        document.getElementById("btnShow").addEventListener('click', showName, false);
    </script>
</body>
```