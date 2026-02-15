# フォーム全体の正しい構造（form / fieldset / legend）

## フォームの正しい構造
フォームは 3つの階層 で考える  
・form：フォーム全体  
・fieldset：入力グループ（まとまり）  
・legend：そのグループのタイトル  

→ “正しいフォームの骨組み”  

### 1. `<form>`：フォーム全体を包む枠
```html
<form action="/submit" method="post">
    ...
</form>
```
#### ■ 役割
・入力内容をサーバーに送る  
・method（GET/POST）を決める  
・action（送信先URL）を決める  

#### ■ ポイント
・フォームは 1つの目的につき1つ  
・入れ子にしてはいけない（form の中に form はNG）  

### 2. `<fieldset>`：入力項目の“まとまり”を作る
フォームの中で、  
・個人情報  
・住所  
・支払い情報  
・アカウント情報  
などを ”意味のあるグループに分ける” ときに使う
```html
<fieldset>
    <legend>ユーザー情報</legend>

    <label for="name">名前</label>
    <input type="text" id="name" name="name">

    <label for="email">メール</label>
    <input type="email" id="email" name="email">
</fieldset>
```
### 3. `<legend>`：fieldset のタイトル
fieldset の“見出し”として機能  

#### ■ legend の役割
・グループの意味を説明する  
・スクリーンリーダーが「ここから先は○○の入力です」と理解できる  
・アクセシビリティ的に超重要  

## フォームの正しい構造（全体例）
```html
<form action="/register" method="post">

    <fieldset>
        <legend>ユーザー情報</legend>

        <label for="name">名前</label>
        <input type="text" id="name" name="name">

        <label for="email">メールアドレス</label>
        <input type="email" id="email" name="email">
    </fieldset>

    <fieldset>
        <legend>ログイン情報</legend>

        <label for="pass">パスワード</label>
        <input type="password" id="pass" name="password">
    </fieldset>

    <input type="submit" value="登録">
</form>
```

## 「id / name / label」との関係
| 要素 | 役割 |  
|:--:|:--:|  
| form | フォーム全体 |  
| fieldset | 入力項目のまとまり |  
| legend | そのまとまりのタイトル |  
| label | 入力欄の名前札 |  
| id | label と input を結びつける |  
| name | サーバーに送るキー名 |  
| input | 実際の入力欄 |  
→ 全部が意味を持って連動してる  

## よくある間違い
### ❌ fieldset を使わずに div で全部まとめる
→ 意味が伝わらない  
→ アクセシビリティ的に弱い  

### ❌ legend を書かない
→ グループの意味が分からない  
→ スクリーンリーダーが困る  

### ❌ form の中に form を入れる
→ HTML仕様違反  

## テンプレ
```html
<form action="送信先" method="post">

    <fieldset>
        <legend>グループ名</legend>

        <label for="id名">ラベル名</label>
        <input type="種類" id="id名" name="送信名">
    </fieldset>

    <input type="submit" value="送信">
</form>
```

## label の2つの書き方
HTML の `<label>` は input と結びつける方法が2種類ある

### ■ for属性で結びつける方法（もっとも一般的）
```html
<label for="email">メールアドレス</label>
<input type="email" id="email" name="email">
```

#### 特徴
・label と input が 離れていてもOK  
・id と for が一致していれば結びつく  
・実務で最も使われる  
・CSSでレイアウトしやすい  
・アクセシビリティ的にも正しい  

#### 使う場面
・label と input を別々に配置したいとき  
・グリッドレイアウト  
・横並びフォーム  
・大規模フォーム  

### ■ input を label の中に入れる方法（内包方式）
```html
<label>
    <input type="checkbox" name="agree">
    利用規約に同意する
</label>
```
#### 特徴
・for と id が 不要  
・label をクリックすると input が反応する  
・チェックボックスやラジオボタンでよく使う  
・コードが短くなる  
・スマホで押しやすい  

#### 使う場面
・checkbox  
・radio  
・ボタンのようにまとめてクリックさせたいとき  
・小さなUIでクリック範囲を広げたいとき  

### ■ 2つの書き方の違い（比較表）
| 項目 | for方式 | 内包方式 |  
|:--:|:--:|:--:|  
| id が必要 | ✔ 必要 | ✖ 不要 |  
| label と input の位置 | 離れていてOK | 必ず近く |  
| よく使う場面 | テキスト入力、メール、パスワード | チェックボックス、ラジオ |  
| コードの長さ | やや長い | 短い |  
| アクセシビリティ | ◎ | ◎ |  
| レイアウトの自由度 | 高い | 中くらい |  

## 使い分け
### ■ テキスト入力系
→ for方式
```html
<label for="name">名前</label>
<input id="name" type="text">
```
### ■ チェックボックス・ラジオ
→ 内包方式
```html
<label>
  <input type="checkbox"> 同意する
</label>
```
### ■ 例外
・UIデザインの都合で for方式を使うこともある  
・逆に、テキスト入力でも内包方式は“技術的には可能”だが、ほぼ使わない（レイアウトが崩れやすい）  

## label のテンプレ
### ■ for方式（基本）
```html
<label for="ID名">ラベル名</label>
<input id="ID名" type="種類" name="送信名">
```
### ■ 内包方式（checkbox / radio）
```html
<label>
    <input type="種類" name="送信名" value="値">
    ラベル名
</label>
```
