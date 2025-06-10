
## セレクタ（主要なもの）  
セレクタには色んな指定の方法がある  
★VSCodeでHTMLの作業ページで「タグ＃id名→tab」「タグ.class名→tab」でclassがついた状態でタグが生成されるショートカット  
### 1.　id　→　＃  
```html
//HTML
<p id="text">text</p>
```
```css
//CSS
#text {
    color: red;
}
```
​
### 2.　class　→　.  
```html
//HTML
<p class="text">text</p>
```
```css
//CSS
.text {
    color: green;
}
```

## 要素セレクタ  
タグセレクタ、idセレクタ、classセレクタ  
復習↓  
classは1つのページに同じものがいくつ存在してもOK  
idはHTMLページに1個しか同じものは存在してはいけない  

## 疑似クラスセレクタ  
`:hover`：マウスカーソルをのせた時だけスタイルをあてる  
```css
//CSS
a:hover {
    color: red;　　　→リンク
}
```
​
## 小セレクタ・子孫セレクタ  
`子孫セレクタ`：親セレクタの中に入っているタグに設定する  
```html
//HTML
    <div class="parent">
        <p>text</p>　←ここのpタグも反映される
        <div>
            <div>
                <p>text text</p>　←ここのpタグも反映される
            </div>
        </div>
    </div>
```
```css​
//CSS
.parent p {
    color: red;
}
```
→parentのクラスがついてるタグの中にネスト（入れ子）されているpタグだけに当たっている状態  
階層がいくつ下であっても反映される  

### 小セレクタ：親要素の直下にある  
親セレクタの真下にないと反映されない  
```html
//HTML
    <div class="parent">
        <p>text</p>　←ここのpタグは反映される
        <div>
            <div>
                <p>text text</p>　←ここのpタグは反映されない
            </div>
        </div>
    </div>
```
```css
//CSS
.parent > p {
    color: green;
}
```
※小セレクタと子孫セレクタ同時に指定する際は、子孫セレクタ、小セレクタの順で当てる  

## 複数セレクタの組み合わせができる  

### 複数のセレクタに同時に同じCSSを当てたい時  
```html
//HTML
    <div class="abc">text</div>
    <div class="def">text</div>
```
```css
//CSS
.abc,
.def {
    
}
```
セレクタとセレクタの間にカンマが入っていればOK  

### 両クラスが揃った時にこのスタイルを当てる時
```html
//HTML
<div class="def xyz">text</div>
```
```css
//CSS
.def.xyz {
    
}
```
複数クラスの場合は両方の条件が揃ってないとダメ  
→`<div class="xyz">text</div>`など単独には反映されない  

### セレクタとクラスの組み合わせもできる  
```css
//CSS
div.def {
    　　　　　→divタグかつ、defというクラスに反映
    　　　　　　Pタグのdefには反映されない
}
```

### セレクタの隣にclassを当てる
```html
//HTML
    <div class="def">text</div>
    <a href="">link</a>
```
```css
//CSS
.def + a {
    
}
```
この場合、defタグの次にaタグがある時だけ機能する  

★基本的に親要素に対して付けたスタイルは中の子要素全てに反映される  
より詳細に書かれているCSSの方が優先されるが、そういう指定が全く無い場合は親要素に書かれたCSSがそのまま子要素にも継承する  
※CSSプロパティには子要素に継承するものとしないものがある。`colorやfont-sizeなどのテキスト`に纏わるものは継承され、`widthやheightなど大枠`に纏わるものは継承されない傾向にある。「MDN　web　docs　カスケードと継承」  

## 練習問題  

### Q.1　「.abc」直下の子要素の隣のｐタグのみに｛color: red;｝  
```html
//HTML
		    <div class="abc">  →ここの
        text
        <ul>　→直下の子要素
            <li>item</li>
        </ul>
        <p>text</p>　→このｐタグを赤にしたい
        <div>text</div>
    </div>
```
A.  
```css
//CSS
.abc > ul + p {
    color: red;
}
```
​
### Q.2　「.abc」直下の<a>タグのみにカーソルを乗せた時だけ｛color: red;｝  
```html
//HTML
    <div class="abc">
        text
        <ul>
            <li>item</li>
        </ul>
        <p>text</p>
        <div>text</div>
        <a href="">link</a>　←「.abc」直下の<a>タグ
    </div>
    <h1 class="headline">text</h1>
	    <a href="">link</a> ←こっちは「.abc」直下じゃないヨ
```
A.  
```css
//CSS
.abc > a:hover {　←カーソルを当てたときの色は:hover
    color: red;
}
```
`直下→　＞`　　`隣→　＋`  
※aと:hoverの間は半角あけないで繋げる  

---

参考：[youtube](https://youtu.be/LXUlkEBLayU)
