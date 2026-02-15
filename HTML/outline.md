# HTML のアウトライン（文書構造）
HTML を“文章として設計する力” に直結する超重要ポイント  
セマンティックタグ・見出し階層・section/article の理解が全部つながる

## HTML のアウトラインとは？

✔ HTML のアウトライン＝ページの“自動目次”  
✔ 見出し（h1〜h6）＋ セクション要素（section/article/nav/aside）で決まる  
✔ 正しいアウトラインは SEO・アクセシビリティ・構造理解に必須  

→ `HTML は“文章の構造”をブラウザに伝えるための言語`  
であり、アウトラインはその“構造の地図”  

## アウトラインを作る要素（重要）
HTML5 では 「セクションを作る要素」 が決まっている  

### ■ セクションを作る要素（sectioning content）
・`<section>`  
・`<article>`  
・`<nav>`  
・`<aside>`  

これらが 新しいアウトラインの階層を作る。

### ■ セクションを作らない要素（sectioning root）
・`<main>`  
・`<body>`  
・`<blockquote>`  
・`<fieldset>`  
・`<figure>`  

これらは アウトラインの“根”にはなるが、新しい階層は作らない

### ■ アウトラインの具体例
#### 例1：基本構造
```html
<h1>サイトタイトル</h1>

<section>
  <h2>商品一覧</h2>
  <p>説明文</p>
</section>

<section>
  <h2>会社情報</h2>
  <p>説明文</p>
</section>
```
アウトラインはこうなる：
```
1. サイトタイトル（h1）
   1.1 商品一覧（h2）
   1.2 会社情報（h2）
```
#### 例2：section の中に section がある場合
```html
<section>
  <h2>商品一覧</h2>

  <section>
    <h3>新着商品</h3>
  </section>

  <section>
    <h3>人気商品</h3>
  </section>
</section>
```
アウトライン：
```
1. 商品一覧（h2）
   1.1 新着商品（h3）
   1.2 人気商品（h3）
```
→ 「見出しが付けられるなら section」がここで効いてくる

#### 例3：article は“独立したアウトライン”を持つ
```html
<article>
  <h1>記事タイトル</h1>
  <p>本文</p>
</article>
```
アウトライン：
```
1. 記事タイトル（article の h1）
```
✔ ページ全体の h1 とは別  
→ article は“独立したコンテンツ”

## よくある誤解
### ❌ h1 は複数あってもいい（HTML5仕様上はOK）
→ でも実務では ページの主題は1つ  
→ SEO 的にも混乱を招く  
→ なので h1 は1つだけ が鉄則  

### ❌ 見た目の大きさで h2/h3 を選ぶ
→ CSS で調整すべき  
→ HTML は意味で選ぶ  

### ❌ section を乱用する
→ 見出しがない section は意味が弱い  
→ 「見出しが付けられないなら div」が正しい

## アウトラインのテンプレ
```html
<h1>ページの主題</h1>

<section>
  <h2>大きな章</h2>

  <section>
    <h3>小見出し</h3>
  </section>

  <section>
    <h3>小見出し</h3>
  </section>
</section>

<article>
  <h2>独立したコンテンツ</h2>
</article>
```