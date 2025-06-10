
## CSSがバッティングしたらどうなるのか  

### 詳細度  
どのスタイルを最も優先するか  
- 後に書かれたCSSほど強い  
- 詳細に書かれたCSSほど強い  
- 「`!important`」が付与されたら最も強くなる  
```CSS
p {
    color: aqua !important;　←これが書かれた場合どこにあっても最強
}
```
あまり推奨しない（よほどの理由がない場合には使わない）  

### Q.　どっちが強いか  
```css
/* 1 */
p {
    color: red;
}

/* 2 */
.text {
    color: blue;
}
```
A.　`2` pタグ全部よりtextのクラスに当てるほうが詳細度が高い  
```css
/* 1 */
list > li {
    color: red;
}

/* 2 */
.list .list-item {
    color: blue;
}
```
A.　`2` 子要素、子孫要素よりもクラスで指定したほうが強い  
```css
/* 1 */
.perent > a {
    color: red;
}

/* 2 */
a {
    color: blue !important;
}
```
A.　`2` 「!important」が付いてるので問答無用で強い  

## 実務でもやる時におすすめの方法  

1. なるべくクラスを指定する  
2. なるべく子孫セレクタより子セレクタを使う  
3. なるべくセレクタは3つを超えないようにする（2つに押さえる）  
  →なるべく影響範囲を小さくする、セレクタを多く使いすぎない、CSSが複雑化しないで済む  

※このように、詳細度や順番によって適用される優先度が変わるCSSの仕様のことを「カスケード（cascade=滝）」と呼ぶ  

## ＠規則  
`@import"CSSファイル名";`  
他のCSSファイルをインポートして読み込むことができる  
CSSを複数に分けて1個のCSSに合わせることができる  
※非推奨、重くなってCSSの読み込み速度が遅くなる  

## メディアクエリ  
@media screen  
```css
.sample {
    background-color: black;
}
@media screen  and (max-width: 768px){
    .sample {
        background-color: red;
    }
}
```
→画面が768pxまではこのスタイルを適用させますという指示  
ウィンドウサイズによってスタイルを変えることができる  
デバイスが異なる場合にユーザーにどう見せるかをこのスタイルで操作することができる  
デバイスの大きさに応じて最適なレイアウトを作る  
```css
/* PC */
.sample {
    background-color: black;
}
/* SP */
@media screen  and (max-width: 768px){
    .sample {
        background-color: red;
    }
}
/* Tablet */
@media screen  and (max-width: 768px) and (min-width: 690px){
    .sample {
        background-color: red;
    }
}
```
​こんな感じで使う  
※実際は、PC用とスマホ用の2パターンでやることが多い  

## デフォルトのスタイル  
全てのタグにはデフォルト値というものがあり、ブラウザによって勝手にスタイルが当たっている  
最低限のスタイルをデフォルトで与えておくことでユーザーにとって最低限の見た目を担保できる  
ブラウザによってやや違うので厄介  
ブラウザ間のスタイルの差異をなくす作業が必要になる  

### 統一するためには  
1.「reset.css」→すべての値を0にする  
[https://meyerweb.com/eric/tools/css/reset/](https://meyerweb.com/eric/tools/css/reset/)  
2.「normalize.css」→差異があるところだけスタイルを統一する  
[https://necolas.github.io/normalize.css/](https://necolas.github.io/normalize.css/)  
3.「sanitize.css」→normalize.cssの上位互換  
[https://csstools.github.io/sanitize.css/](https://csstools.github.io/sanitize.css/)  
`sanitize.css`を使うのが無難  

CSS書くときは上記のスタイルシートを最初に敷く  
（HTMLの雛形に使用するCSSのlinkタグの上に貼る←優先順位は低いため）  

## ボックスモデルとブロック要素・インライン要素について  
```html
/* html */
<div class="box">text</div>
​
```
```css
/* css */
.box {
    text-align: center;　←テキスト中央揃え
    padding: 20px;
    border: 2px solid red;
    margin: 20px;
}
```
### padding（パディング）  
[https://developer.mozilla.org/ja/docs/Web/CSS/padding](https://developer.mozilla.org/ja/docs/Web/CSS/padding)  
要素の内側に間隔をあけるためのプロパティ  
### margin（マージン）  
[https://developer.mozilla.org/ja/docs/Web/CSS/margin](https://developer.mozilla.org/ja/docs/Web/CSS/margin)  
要素と要素の間隔  
### border（ボーダー）  
[https://developer.mozilla.org/ja/docs/Web/CSS/border](https://developer.mozilla.org/ja/docs/Web/CSS/border)  
paddingとmarginの間  

### 特徴  
```css
.block {　←duv.block
    text-align: center;
    padding: 20px;
    border: 2px solid red;
    margin: 20px;
}
.line { ←span.line
    text-align: center;
    padding: 20px;
    border: 2px solid red;
    margin: 20px;
}
```
divタグはブロックレベル要素、spanタグはインライン要素  
どちらにも同じ値のCSSを当てた時  

- ブロック要素は上下左右にmarginを当てることができる  

ただし、要素を横並びに配置することができない  

- インライン要素は左右にのみmarginが付く  

更に、要素は横並びになる  

上下左右にmarginを当てたい場合はブロック要素を使用  
横並びにしたい場合はインライン要素を使用する  

初期値でdivタグ（ブロック要素）は`display:block;`、spanタグ（インライン要素）は`display:inline;`というプロパティが最初から当たっている  

### CSSではブロック要素・インライン要素は操作することができる  
```css
.block {
    text-align: center;
    padding: 20px;
    border: 2px solid red;
    margin: 20px;
    display: inline;　←ブロック要素にインライン要素をつける
}
.line {
    text-align: center;
    padding: 20px;
    border: 2px solid red;
    margin: 20px;
    display: block;　←インライン要素にブロック要素をつける
}
```

## part１・２・３まとめ  
- HTMLはwebページの骨組みを作るマークアップ言語であったのに対し、CSSはHTMLを装飾するスタイルシート言語  
- これによりwebページの要素に色をつけたり文字の大きさを変えたりレイアウトを揃えたりできる  
- CSSの基本はクラスやidといったHTMLのセレクタに対してプロパティやプロパティ値を設定することで実装する  
- CSSのスタイルがもしバッティングしたら優先度は次のように決まる  
  >- セレクタは細かく詳細に書かれたものほど優先される  
  >- 後ろに書いたコードほど優先される  
  >- 「!import」を使うと問答無用で優先される  

- ＠規則というものがあり特殊な設定を行える  
  特に重要なのは「＠media」これを使ってレスポンシブコーディングができる  
- ブラウザごとにデフォルトのスタイルというものがある  
  意図せずこれが適用されるとCSSが当てづらいのでwebページにCSSを設定する際にはresrt，normalize，sanitize.cssなどを用いてまず平地化するのが一般的なやりかた  
- CSSにはボックスモデル、ブロック要素・インライン要素という使用がある  
  これによって適応できるCSSのプロパティとそうでないプロパティがある  

---

参考：[youtube](https://youtu.be/LXUlkEBLayU)
リンク：[MDN Web Docs](https://developer.mozilla.org/ja/docs/Web/CSS)
