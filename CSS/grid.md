# CSS の最重要概念：Grid（グリッドレイアウト）
Flexbox が「1次元（横か縦）」のレイアウトだったのに対し、  
`Grid は 「2次元（行 × 列）」を同時に扱える最強レイアウト`  

カード一覧、複雑なページ構成、ダッシュボード、  
全部 Grid で“美しく・簡単に”作れるようになる  

## CSS Grid の本質
✔ Grid は「行と列を同時にデザインするレイアウトシステム」  
✔ Flexbox が苦手な“複雑な配置”が得意  
✔ スマホ対応（レスポンシブ）が圧倒的に楽  
✔ 現代 CSS のレイアウトは「Flex + Grid」が基本  

### ■ Grid を使うには display:grid を書くだけ
```css
.container {
    display: grid;
}
```
これで 子要素がグリッドのセルに入るようになる  

### ■ 列を作る：grid-template-columns
```css
.container {
    display: grid;
    grid-template-columns: 200px 200px 200px;
}
```
→ 3列の固定幅グリッド
```
[200][200][200]
```

### ■ repeat() で簡潔に書ける
```css
grid-template-columns: repeat(3, 200px);
```

### ■ 自動伸縮：fr（fraction）
最も使う単位
```css
grid-template-columns: 1fr 1fr 1fr;
```
→ 均等3列
```
[ 1fr ][ 1fr ][ 1fr ]
```

### ■ gap（要素間のすき間）
Flexbox と同じく、Grid でも gap が最強  
```css
gap: 20px;
```

### ■ 行の高さ：grid-template-rows
```css
grid-template-rows: 100px auto 100px;
```

### ■ 自動で列数を調整する（レスポンシブ最強）
最も使われる書き方  
```css
grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
```
意味：  
・最低 200px を確保  
・それ以上は自動で広がる  
・画面が狭くなったら列数が減る  

→ スマホ対応が勝手にできる

### ■ 子要素を広げる：grid-column / grid-row
```css
.item {
    grid-column: span 2; /* 横に2マス分広がる */
}
```

## Grid のテンプレ
```css
.container {
    display: grid;
    grid-template-columns: repeat(3, 1fr); /* 列 */
    grid-template-rows: auto;              /* 行 */
    gap: 20px;                              /* すき間 */
}

.item {
    grid-column: span 1; /* 必要なら広げる */
}
```

## Flexbox と Grid の違い（本質）
| 概念 | Flexbox | Grid |  
|:--:|:--:|:--:|  
| レイアウト | 1次元（横 or 縦） | 2次元（行 × 列） |  
| 得意なもの | ナビ、ボタン、横並び | カード一覧、複雑なレイアウト |  
| 並べ方 | コンテンツ優先 | レイアウト優先 |  
| スマホ対応 | まあまあ | めちゃ楽 |  
