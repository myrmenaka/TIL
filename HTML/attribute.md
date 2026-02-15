# HTML 属性の体系的理解（グローバル属性 / 特定属性）
HTML を“意味で書く”ための最後の大きなピース  
`タグの理解 → 構造 → アウトライン → フォーム → 属性体系`    
という流れの最終段階  

## HTML 属性の体系（全体像）
HTML の属性は大きく分けて 2種類  

### 1. グローバル属性（どの要素でも使える）
例：  
・class  
・id  
・style  
・title  
・lang  
・hidden  
・tabindex  
・aria-*  

### 2. 特定要素専用属性（特定のタグだけが持つ）
例：  
・a の href  
・img の src / alt  
・input の type / name / value  
・form の action / method  
・script の src / defer  
・link の rel / href  

## グローバル属性（どのタグでも使える属性）
HTML のほぼすべての要素で使える“共通の属性”  

### ■ class（CSS 用のグループ化）
```html
<div class="card highlight"></div>
```
### ■ id（固有識別子）
```html
<section id="about"></section>
```
### ■ style（インラインCSS）
※ 実務ではほぼ使わない（CSSに書くため）
```html
<p style="color:red;">赤文字</p>
```
### ■ title（ツールチップ）
```html
<span title="説明文">アイコン</span>
```
### ■ lang（言語）
```html
<p lang="en">Hello</p>
```
### ■ hidden（非表示）
```html
<div hidden>見えない</div>
```
### ■ tabindex（フォーカス順）
```html
<button tabindex="1">OK</button>
```
### ■ aria-*（アクセシビリティ）
```html
<button aria-label="閉じる">×</button>
```
## 特定要素専用属性（タグごとに違う）
### ■ a 要素（リンク）
・href  
・target  
・rel  
```html
<a href="/about" target="_blank" rel="noopener">会社情報</a>
```
### ■ img 要素（画像）
・src  
・alt  
・width / height  
```html
<img src="cat.jpg" alt="猫の写真">
```
### ■ input 要素（フォーム）
・type  
・name  
・value  
・placeholder  
・required  
・checked  
・min / max / step  
```html
<input type="email" name="email" required>
```
### ■ form 要素
・action  
・method  
・enctype  
```html
<form action="/login" method="post">
```
### ■ script 要素
・src  
・defer  
・async  
```html
<script src="app.js" defer></script>
```
### ■ link 要素（CSS読み込み）
・rel  
・href  
```html
<link rel="stylesheet" href="style.css">
```

## 属性体系を理解するコツ
### ✔ グローバル属性
→ どのタグでも使える“共通機能”  

### ✔ 特定属性
→ そのタグの“役割に必要なもの”  

### ✔ 属性は「タグの意味を補強するもの」
→ HTML は意味  
→ CSS は見た目  
→ JS は動き  

この原則に沿って属性を選ぶと、HTML が一気に綺麗になる。

## 属性体系のテンプレ
```html
<tag 
    グローバル属性（class, id, style, title, lang...）
    専用属性（href, src, type, name, alt...）
>
    内容
</tag>
```
## よくある間違い
### ❌ class と id を混同する
→ 役割が違う（グループ化 vs 固有識別）  

### ❌ alt を書かない img
→ アクセシビリティ的にNG  

### ❌ a に href がない
→ リンクとして機能しない  

### ❌ input に name がない
→ サーバーに送られない  
