# CSS の最重要概念：Flexbox（フレックスボックス）
現代 CSS レイアウトの中心  
`「横並び」「縦並び」「中央寄せ」「均等配置」「スマホ対応」`  
全部これひとつでできる  

## Flexbox（フレックスボックス）
✔ Flexbox は「並べ方」をコントロールする仕組み  
✔ 横並び・縦並び・中央寄せが一瞬でできる  
✔ レスポンシブ（スマホ対応）に強い  
✔ float や position の代わりに使うのが現代の実務  

### ■ Flexbox を使うには display:flex を書くだけ
```css
.container {
    display: flex;
}
```
これだけで `子要素が横並び` になる
```
[ A ][ B ][ C ]
```

### ■ flex-direction（並べる方向）
```css
.container {
    display: flex;
    flex-direction: row;      /* 横並び（デフォルト） */
    /* flex-direction: column;  縦並び */
}
```
✔ row（横）
```
A B C
```
✔ column（縦）
```
A
B
C
```

### ■ justify-content（主軸方向の並び方）
横並びなら「横方向」  
縦並びなら「縦方向」  
```css
.container {
    display: flex;
    justify-content: center;
}
```

#### 代表的な値

| 値 | 意味 |  
|:--:|:--:|  
| flex-start | 左寄せ |  
| center | 中央寄せ |  
| flex-end | 右寄せ |  
| space-between | 両端に寄せて均等配置 |  
| space-around | 均等＋左右に余白 |  
| space-evenly | 完全均等配置 |  

### ■ align-items（交差軸方向の揃え方）
```css
.container {
    display: flex;
    align-items: center;
}
```
| 値 | 意味 |  
|:--:|:--:|  
| flex-start | 上揃え |  
| center | 中央揃え |  
| flex-end | 下揃え |  
| stretch | 高さを揃える（デフォルト） |  

### ■ gap（要素間のすき間）
```css
.container {
    display: flex;
    gap: 20px;
}
```
・margin 調整より圧倒的に楽  
・Flexbox と Grid のための“公式の余白”  
・実務では 必須レベル  

### ■ flex-wrap（折り返し）
```css
.container {
    display: flex;
    flex-wrap: wrap;
}
```
→ カード一覧などで便利  
```
A B C D E F
↓ 画面が狭いと折り返す
A B C
D E F
```

### ■ 子要素側の指定：flex-grow / flex-shrink / flex-basis
#### ✔ flex: 1; が最強のショートカット
```css
.item {
    flex: 1;
}
```
意味：  
・grow（広がる）＝1  
・shrink（縮む）＝1  
・basis（初期幅）＝0  

→ 均等に広がる

## Flexbox のテンプレ
```css
.container {
    display: flex;
    flex-direction: row;     /* row / column */
    justify-content: center; /* 主軸方向の揃え方 */
    align-items: center;     /* 交差軸方向の揃え方 */
    gap: 20px;               /* 要素間のすき間 */
    flex-wrap: wrap;         /* 折り返し */
}

.item {
    flex: 1;                 /* 均等に広がる */
}
```

## Flexbox を理解すると何ができる？
・横並びが一瞬  
・ボタンを中央に置くのが一瞬  
・カード一覧が簡単  
・スマホ対応が自然にできる  
・float や position の“無理やりレイアウト”が不要になる  

→ `Flexbox を使えない＝レイアウトが組めない` に近い  
