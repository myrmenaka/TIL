# CSS の本質（基礎の基礎）

CSS とは、  
✔ HTML に“見た目”を与えるための言語  

HTML が「意味」  
CSS が「見た目」  
JavaScript が「動き」  

→ この三位一体の理解が大事

## CSS の3つの基本構造
CSS は  
`セレクタ → プロパティ → 値`    
の3つでできている。

```css
セレクタ {
    プロパティ: 値;
}
```
例：
```css
p {
    color: red;
    font-size: 16px;
}
```
・p → どの要素に  
・color → 何を  
・red → どうするか  

## CSS の最重要概念：ボックスモデル
CSS のすべてのレイアウトは 箱（box） の考え方で動く
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
・margin：外側の余白  
・border：枠線  
・padding：内側の余白  
・content：中身  

### ✔ これを理解すると：
・余白が思った通りに動かない  
・要素がズレる  
・横並びにならない  

こういう CSS の“あるある事故”が全部解決する

## CSS の3大レイアウト
CSS はこの3つが中心

### ■ Flexbox（横並び・縦並びの最強ツール）
・横並び  
・中央寄せ  
・均等配置  
・スマホ対応  
```css
.container {
    display: flex;
    gap: 20px;
}
```

### ■ Grid（2次元レイアウトの王様）
・カード一覧  
・複雑なレイアウト  
・行と列を同時に制御  
```css
.grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 20px;
}
```

### ■ Position（絶対配置・固定配置）
・固定ヘッダー  
・モーダル  
・バッジ表示  
```css
.box {
    position: absolute;
    top: 10px;
    left: 20px;
}
```

## CSS の書き方は3種類（使い分け）
### ■ 外部CSS（最も推奨）
```html
<link rel="stylesheet" href="style.css">
```
### ■ head 内に書く（小規模）
```html
<style>
p { color: red; }
</style>
```
### ■ インライン（非推奨）
```html
<p style="color:red;">NG</p>
```

## CSS の優先順位（超重要）
CSS は どれが勝つか が決まっている。

1. インライン（最強）
2. id セレクタ
3. class セレクタ
4. タグセレクタ
5. 継承

これを `カスケード（Cascade）` と呼ぶ

## CSS の基本テンプレ
```css
/* 1. リセット（初期化） */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

/* 2. ベーススタイル */
body {
    font-family: sans-serif;
    line-height: 1.6;
}

/* 3. レイアウト */
.container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
}

/* 4. コンポーネント */
.button {
    background: #333;
    color: #fff;
    padding: 10px 20px;
    border-radius: 4px;
}
```
これが“CSS の基本形”。