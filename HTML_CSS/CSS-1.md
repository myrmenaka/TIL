
## CSSの書き方（３種）  
### 1.　styleタグを使う  
```css
<body>
    <style>
　　　　この中にCSSを書く
    </style>
</body>
</html>
```
※あまり推奨されないがフレームワークにより似たような書き方をする  

### 2.　インラインCSS  
```css
<p style="color: red;">text</p>
　　　　　　↑複数つけることも可能↓
<p style="color: red; font-weight:bold;">text</p>
　　　　　　→文字色　　　→太文字
```
スタイル属性をタグに付けてその中にCSSプロパティを書く  
※これもあんまり使わない  

### 3.　リンクタグを使ってCSSファイルを読み込む  
```css
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <meta name="description" content="sample text text">
    <link rel="stylesheet" href="./style.css">
</head>
<body>
    
</body>
</html>
```
​HTML雛形のheadの中にlinkタグを書く  
`rel="stylesheet"`：スタイルシートを読み込むためのタグとして示す  
`href="style.css"`：hrefの中にCSSのパスを入力する  

★CSS入力のためのファイルを作成する  

### CSSのパスの設定方法  
作業中のHTMLファイルと同じ場所を示す（ `./` ）  
もう一個上の階層にある場合（ `../` ）  
更にもう一個上の階層の場合（ `../../` ）  
他のディレクトリにある場合はそのディレクトリ名も入る（ `./ディレクトリ名/` ）  
CSSファイル（style.css）がindex.htmlから見てどこに置いてあるかでパスを変える必要がある  
※VSCodeは補完してくれる  
※「絶対パス」「相対パス」  

## CSSを書く前の準備  
```css
@charset "utf-8";
```
この文章がUTF-8に従って書いていることを表す  
※あってもなくてもどっちでもいい説がある  

## 基本ルール  
基本的にCSSは`セレクター`、`プロパティ`、`プロパティ値`で構成  
セレクター（どのHTMLに対して、どんな）プロパティ（の、どの）プロパティ値（を当てるか）  
```css
セレクター {
    プロパティ : プロパティ値;
}

p {
    color: red;
    background-color: red;　　　　複数追記できる
    font-weight: bold;
}
```
​セレクター：どのHTMLに対してCSSを当てるか  

### コメントについて  
```css
/* この中にコメントを記載できる　*/

/*p {
    color: red;
    background-color: red;
    font-weight: bold;
}*/
　→コメントアウトした状態
```
​メモを残しておきたい時や、一時的に一部のCSSを無効化（コメントアウト）したい時に使う  
コメントは複数行囲うこともできる  
※ショートカット  
Windows→Ctrl＋/  
Mac→Command＋/  

## プロパティ・プロパティ値  
たくさんの種類があるため、やりながら徐々に引き出しを増やしていくのがおすすめ  

### プロパティの一例  
`margin-場所: 値;`  
margin=間隔  
間隔を開ける場所により、left、right、top、bottom、とある  

`color: 値;`  
テキストの色を変える  

`background-color: 値;`  
背景の色を変える  

`font-weight: 値;`  
フォントの太さを変える  

`font-size: 値;`  
フォントのサイズを変える  

`width:値;`  
横幅  

`height:値;`  
縦幅  

`line-height:値;`  
行間  

他、「CSS　プロパティ　一覧」で検索すると出てくる  

### webサイトの特定の表現をどうやっていいるのか見る方法  
※GoogleChrome推奨  
対象に合わせて「右クリック→要素の検証」  
※GoogleChromeデベロッパーツールWindows起動方法  
Google Chromeの右上の3点リーダーアイコン（⋮）から、[その他のツール] > [デベロッパーツール]をクリックします。  
ショートカット：Control＋Shift＋i  
このツールを使うことでCSSを見たりテストをすることもできる  

### 値について  
`ピクセル（px）`：画面上のドットの数を指定、固定  
`パーセント（％）`：画面サイズに合わせて柔軟に動く  
`em`：テキストの冒頭にインデントを付ける、全角1文字分に相当する  

### 色について・16進数  
red、green、blue、yellowなどでも反応はするが推奨しない  
- rgb（赤、緑、青）  
- rgba（赤、緑、青、透明度）  
- ＃0000＝16進数で色を表現する方法  
  「16進数　カラーコード」で検索
  
ここはお好みで、透明度をつけたい場合はrgbaでしか表記できない  

## 関数  
プロパティには数字だけでなく関数も入れることができる  

### calc()関数  
パーセントとピクセルのように値が違うもの同士で演算ができる  
```css
width: calc(100% - 50px);​
```

### url()関数  
関数の中に画像ファイル名を入れると画像が表示される  
```css
background-image: url("画像フォルダ");​
```
background-image→背景画像  

### translate()関数  
X軸とY軸を指定して要素を動かす  
```css
transform: translate(-100px, 30px) rotate(90deg);
```
​transform→関数を設定して要素を変化させるプロパティ  
rotate→回転のプロパティ（deg＝度）  

### skew()関数  
要素を歪める  
```css
transform: translate(-100px, 30px) rotate(90deg) skew(70deg);
```
​
## ショートハンド  
このmargin4個をこのように1個にまとめることができる  
```css
    margin-bottom: 200px;
    margin-left: 200px;
    margin-right: 200px;
    margin-top: 200px;
    
    ↓
    
    margin: 100px 200px 300px 200px;
    　　　　　→　上、右、下、左
```
​このように似たプロパティは1個にまとめることができる  
※上下左右同じ場合は、margin: 100px など一つで良い  
「CSS　プロパティ　ショートハンド」で検索  

---
参考：[youtube](https://youtu.be/LXUlkEBLayU)
