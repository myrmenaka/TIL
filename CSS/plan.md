# CSS 設計（BEM / Utility / コンポーネント化）
ここを理解すると CSS がぐちゃぐちゃになる問題が一気に消える  

CSS は「書くこと」よりも  
`“どう構造化するか”` が9割  

HTML と同じで、CSS も設計が命  

## 1. BEM（Block / Element / Modifier）
BEM は 世界で最も使われている CSS 設計手法  
```
.block {}
.block__element {}
.block--modifier {}
```

### ✔ Block（独立した部品）
```
.card
.nav
.button
```
### ✔ Element（部品の中のパーツ）
```
.card__title
.card__body
.nav__item
```
### ✔ Modifier（状態・バリエーション）
```
.button--primary
.button--large
.card--active
```

## 2. Utility-first（小さなクラスを組み合わせる）
Tailwind のような思想  
```
.mt-4  （margin-top: 16px）
.text-center
.flex
.items-center
```
### メリット：
・早い  
・一貫性が出る  
・CSS ファイルが膨らまない  

### デメリット：
・HTML がクラスだらけになる  
・デザイン変更がしづらい  

## 3. コンポーネント化（最も実務的）
React / Vue / Laravel Blade でも使われる考え方
```
.card { ... }
.card__title { ... }
.card__body { ... }
```
1つの UI を「部品」としてまとめる

## 4. CSS 設計の黄金ルール

### ■ class だけでスタイルを書く（id は使わない）
id は JS 用  
CSS は class が基本  

### ■ ネストしすぎない（2階層まで）
```
.card__title {}     ← OK
.card .title {}      ← OK
.card .title span {} ← NG（深すぎる）
```
### ■ セレクタは短く・明確に
```
.header nav ul li a {} ← NG
.nav__link {}          ← OK
```
### ■ コンポーネントごとにファイルを分ける
```
components/
  card.css
  button.css
  form.css
```

## CSS 設計のテンプレ
```css
/* Block */
.card {
    background: #fff;
    padding: 16px;
    border-radius: 8px;
}

/* Element */
.card__title {
    font-size: 20px;
    margin-bottom: 8px;
}

.card__body {
    font-size: 14px;
}

/* Modifier */
.card--highlight {
    border: 2px solid #4da3ff;
}
```