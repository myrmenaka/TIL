# CSS の最重要概念：ボックスモデル（レイアウトの基礎の基礎）
ボックスモデルの深堀
```
+------------------------------+
|            margin            |
|  +------------------------+  |
|  |         border         |  |
|  |  +------------------+  |  |
|  |  |     padding      |  |  |
|  |  |  +------------+  |  |  |
|  |  |  |  content   |  |  |  |
|  |  |  +------------+  |  |  |
|  |  +------------------+  |  |
|  +------------------------+  |
+------------------------------+
```

## ボックスモデルの4層構造
CSS の要素はすべて 4つの層 でできている  

| 層 | 説明 |  
|:--:|:--:|  
| content | テキスト・画像など“中身” |  
| padding | 中身と枠線の間の余白 |  
| border | 枠線 |  
| margin | 要素の外側の余白（他の要素との距離） |  

### 1. content（中身）
```css
width: 200px;
height: 100px;
```
で指定されるのは content の大きさだけ

### 2. padding（内側の余白）
```css
padding: 20px;
```
・content の周りに余白を作る  
・背景色は padding まで広がる  
・クリック範囲も広がる（ボタンで重要）  

### 3. border（枠線）
```css
border: 1px solid #333;
```
・padding の外側に付く  
・枠線の太さも 要素のサイズに加算される  

### 4. margin（外側の余白）
```css
margin: 20px;
```
・他の要素との距離  
・背景色はつかない  
・`margin 同士は重なる（margin collapse）` ← 重要  

## ボックスモデルの“事故”の原因：合計幅の計算
CSS の width は `content の幅だけ` を指す  
```
実際の横幅 = content + padding + border + margin
```
例：
```css
width: 200px;
padding: 20px;
border: 2px solid;
margin: 10px;
```
実際の幅：
```
200 + 20 + 20 + 2 + 2 + 10 + 10 = 264px
```
→ 「なんで 200px にしたのに 200px じゃないの？」  
という初心者の混乱はここから来る

## 必ず使う：box-sizing
```css
* {
  box-sizing: border-box;
}
```
これを使うと、  
`width の中に padding と border を含める`  

つまり、  
`実際の幅 = width の値そのまま`  
これでレイアウトが一気に安定する

## margin が重なる（margin collapse）
CSS の罠のひとつ  
```
要素A（margin-bottom: 20px）
要素B（margin-top: 30px）
```
このとき、間の余白は 20 + 30 = 50px ではなく、  
30px（大きい方） になる

理由：  
margin は「外側の余白」なので、上下の margin は“合体”する。

## ボックスモデルの理解
```
content：中身
padding：中身の周りの余白（背景色あり）
border：枠線
margin：外側の余白（背景色なし）
```
そして、
```
width は content の幅
→ border-box を使うと width に全部含まれる
```
これが CSS レイアウトの基礎の基礎
