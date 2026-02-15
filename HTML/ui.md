# フォームUIのベストプラクティス
「良いフォーム」と「悪いフォーム」を分ける決定的なポイント

## フォームUIベストプラクティス（HTML＋UX＋アクセシビリティの総合）

### ■ label は必ず使う（placeholder だけにしない）
#### ❌ 悪い例
```html
<input type="text" placeholder="名前">
```
・placeholder は入力中に消える  
・何を入力する欄か分からなくなる  
・アクセシビリティ的にもNG  

#### ✔ 良い例
```html
<label for="name">名前</label>
<input type="text" id="name" name="name" placeholder="山田太郎">
```
label＝必須、placeholder＝補助  

### ■ エラー表示は「入力欄の近く」に置く
#### ❌ 悪い例
・ページの上に「エラーがあります」とだけ表示する

#### ✔ 良い例
```html
<label for="email">メールアドレス</label>
<input type="email" id="email" name="email">
<p class="error">メールアドレスの形式が正しくありません</p>
```
・どこが間違っているか一目で分かる  
・スクリーンリーダーにも伝わる  

### ■ 必須項目は「*」ではなく text で示す
#### ❌ 悪い例
```
名前 *
```
#### ✔ 良い例
```html
<label for="name">名前 <span class="required">必須</span></label>
```
・「*」は意味が曖昧  
・「必須」と書く方がアクセシブル  

### ■ 入力欄の幅は「入力内容に合わせる」
#### ❌ 悪い例
・メールアドレス欄が 100px とか極端に短い  

#### ✔ 良い例
・メール → 長め  
・郵便番号 → 短め  
・電話番号 → 中くらい  

→ ユーザーが「どれくらい入力するか」を予測しやすい。

### ■ スマホ最適化（type の選び方が重要）
#### ✔ email
→ スマホで @ が出やすい

#### ✔ tel
→ 数字キーボードが出る

#### ✔ number
→ スピンボタンが出る（ただし UI 的に微妙なことも）

#### ✔ date
→ カレンダー UI が出る（ブラウザ依存）

type の選び方で UX が大きく変わる。

### ■ チェックボックス・ラジオは「押しやすく」
#### ✔ 内包方式でクリック範囲を広げる
```html
<label>
  <input type="checkbox" name="agree">
  利用規約に同意する
</label>
```
・スマホで押しやすい  
・UI 的にも自然  

### ■ グループ化は fieldset / legend を使う
```html
<fieldset>
  <legend>配送先情報</legend>
  ...
</fieldset>
```
・「ここから先は住所入力です」と伝わる  
・スクリーンリーダーにも優しい  
・大規模フォームでは必須  

### ■ ボタンは input より button を使う（実務）
#### ❌ 古い書き方
```html
<input type="submit" value="送信">
```
#### ✔ button
```html
<button type="submit">送信</button>
```
理由：  
・中にアイコンを入れられる  
・HTML を入れられる  
・カスタマイズしやすい  

### ■ エラーメッセージは赤、成功は緑（色だけに頼らない）
#### ✔ 色＋テキストで伝える
・色覚多様性にも配慮

### ■ 入力補助（autocomplete）を積極的に使う
```html
<input type="email" autocomplete="email">
<input type="name" autocomplete="name">
<input type="postal-code" autocomplete="postal-code">
```
・スマホで自動入力が出る  
・UX が劇的に良くなる  

→ 必須レベル

## フォームUIのテンプレ
```html
<form>
  <fieldset>
    <legend>グループ名</legend>

    <label for="id名">ラベル名 <span class="required">必須</span></label>
    <input 
      type="種類" 
      id="id名" 
      name="送信名" 
      placeholder="入力例" 
      autocomplete="種類"
      required
    >
    <p class="error">エラーメッセージ</p>
  </fieldset>

  <button type="submit">送信</button>
</form>
```