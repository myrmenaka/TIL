# ディレクトリ・ファイルを作成  

1. フォルダを作成（Document）  
2. VSCodeでフォルダを開く  
3. フォルダ横の新しいファイルから名前をつけて作成（index.htmlなど）  

## タグについて  

```html
<h1>This is a tag</h1>
```

開始タグ＝`<h1>`  

閉じタグ＝`</h1>`　閉じるときは「/」が必要  

## Emmet（VSCode標準搭載機能)  

タグを簡単に保管して入力してくれる  

タグ名→tabキーを押すと勝手に出てくる  

## 保存ショートカット  

Mac：`Command＋S`  

Windows：`Ctrl＋S`  

## ブラウザで参照するには  

ブラウザ（Googleなど）に作成したHTMLファイルをドラック＆ドロップ  

# タグの種類  

```html
<h1>テキスト</h1>～<h6>テキスト</h6>
```

→　ヘッドライン、見出し。数が大きくなるにつれ見出しが小さくなる  

```html
<p>テキスト</p>
```

→　パラグラフ、段落を表示する  

```html
<a 属性=”URL”>テキスト</a>
```

→　アンカー、設定した別のページに飛ぶ  

→　属性を付与することでより色んな機能が使えるようになる  

```html
<section>
</section>
```

→　章を作る  

### なぜタグには色んな種類があるのか  

- タグによって色んな役割がある

- 機械に理解してもらうための仕様

  セマンティック：データの持つ意味をコンピュータに理解させ処理する技術  
    
- 情報によって優先度をつけることができる  

# ネスト（入れ子）  

HTMLタグは入れ子にすることができる  

```html
<p>テキスト <a href="URL">テキスト</a></p>
```

→pタグの中にaタグを入れ子にした状態  

```html
<section>
    <h1>テキスト</h1>
    <p>テキスト</p>
    <p>テキスト<a href="URL">テキスト</a></p>
</section>
```

このように特に制限なく入れ子にし続けることができる  

情報のまとまりを作ってあげると機械的に優しくなる  

CSSを当てる際にもグルーピングすることでよりCSSでデザインしやすくなる  

# 最重要タグの雛形  

→絶対に書くタグのこと  

### <!DOCTYPE html>  

このファイルがHTML文書であることを示す目印  

HTMLファイルの1行目に必ず書く（閉じタグ無し）  

### <html></html>  

全てのコンテンツをこのタグで囲う。2行目に必ず書く  

### <head></head>  

頭、head内に書いたコードはユーザーからは見えない  

ブラウザにwebページを表示させる上で重要な情報をここに書く  

### ↓head内に書くコード  

```html
<meta charset="utf-8">  

<meta name="viewport" content="width=device-width, initial-scale=1">  

<title>タイトル</title>  

<meta name="description" content="sample text text">
```

**補足**  

`metaタグ`：webページを存在させる上で重要な情報を記すためのタグ  

`charset="utf-8”`：文字コード　→　この解釈をブラウザが誤ると文字化けする  

`name=”viewport”`：異なるデバイスで見た時にどう映るかの設定  

`content=”width=device-width`：それぞれのデバイスの大きさに合わせてWebサイトを表示  

`initial-scale=1”`：最初のズームの倍率は1  

`<title>タイトル</title>`：ブラウザで検索した際のリンク部分になる（ページタイトル）  

`content="sample text text”`：ブラウザで検索した際の説明文（ページタイトルの下の文）になる、ただしGoogleのアルゴリズムで変わる場合もある  

`<body></body>`  
bodyタグの中に書くとユーザーから見える  

## VSCodeでは。。。  

html→tab  

でほとんど自動入力してくれる  

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    
</body>
</html>
```

ただし、`<meta name="description" content="sample text text">`  

は入力されないので自分で入力する  

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <meta name="description" content="sample text text">
</head>
<body>
    
</body>
</html>
```

**補足**  

`<html lang="en">`：言語設定（ja=日本語）、多言語対応してないページであれば必要ない  

---
参考：[youtube](https://youtu.be/LXUlkEBLayU)
