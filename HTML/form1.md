# フォーム構造（label / input / name / id の完全理解）
HTML の基礎を固める上で、フォームは“タグの意味・属性・構造”が全部詰まっている最重要分野  

ここを押さえると、  
・id の本当の役割  
・name の本質  
・label の意味  
・input の種類  
・アクセシビリティ  
実務でのフォーム構造が一気に理解できる

## フォーム構造の基礎
### ■ フォームの最小構造
```html
<form action="/submit" method="post">
    <label for="email">メールアドレス</label>
    <input type="email" id="email" name="email">
</form>
```
### 1. form 要素
フォーム全体を包む“枠”  
・`action=""` → 送信先  
・`method=""` → GET / POST  

※ 今は構造理解が目的なので、細かい HTTP の話は省略  

### 2. label 要素
入力欄の名前札  
クリックすると input にフォーカスが移る  
```html
<label for="email">メールアドレス</label>
```
・`for="email"` → input の id と一致させる必要がある  
・アクセシビリティ上も必須

### 3. input 要素
ユーザーが入力する欄  
```html
<input type="email" id="email" name="email">
```
#### ✔ 属性の意味
・`type="email"` → 入力の種類  
・`id="email"` → label と結びつけるための“固有名”  
・`name="email"` → サーバーに送るときの“変数名”  
ここが超重要。

### 4. id と name の違い　※大事なポイント
| 属性 | 役割 | 必要性 |  
|:--:|:--:|:--:|  
| id | label と結びつけるための固有名 | UI/UX・アクセシビリティ上 必須 |  
| name | サーバーに送るときのキー名 | 送信するなら絶対必須 |  

つまり：
・id → 人間とブラウザのため  
・name → サーバーのため  
この違いを理解すると、HTML の理解が一段上がる

### 5. よくある間違い
#### ❌ pタグでラベルを代用する    
　
→ 入力欄と結びつかない  
→ アクセシビリティ的にアウト  
→ クリックしてもフォーカスされない  

#### ❌ id を付けずに label を書く
→ label が機能しない  

#### ❌ name を付けずに input を送信
→ サーバーに値が届かない  

## input の種類（type属性）
HTML の `<input>` は type 属性で性質が変わる“変身型タグ”  

### ■ よく使う input の種類
### 1. `type="text"`（最も基本）
・1行のテキスト入力  
・名前、住所、タイトルなど  
```html
<input type="text" id="name" name="name">
```
### 2. `type="email"`（メール形式チェック）
・メールアドレス専用  
・スマホで「@」が出やすくなる  
・ブラウザが形式チェックしてくれる  
```html
<input type="email" id="email" name="email">
```
### 3. `type="password"`（非表示入力）
・入力内容が●で隠れる  
・実際の暗号化はサーバー側  
```html
<input type="password" id="pass" name="password">
```
### 4. `type="number"`（数値）
・数値だけ入力  
・スピンボタン（▲▼）が出る  
・min / max / step と組み合わせる  
```html
<input type="number" id="age" name="age" min="0" max="120">
```
### 5. `type="checkbox"`（複数選択）
・チェックボックス  
・name を配列にすることが多い  
```html
<input type="checkbox" id="news" name="subscribe" value="news">
```
### 6. `type="radio"`（単一選択）
・同じ name の中で1つだけ選べる  
・id はそれぞれ別  
```html
<input type="radio" id="male" name="gender" value="male">
<input type="radio" id="female" name="gender" value="female">
```
### 7. `type="file"`（ファイルアップロード）
・画像やPDFなどを選択  
```html
<input type="file" id="avatar" name="avatar">
```
### 8. `type="hidden"`（画面に表示しない値）
・CSRFトークン  
・商品ID  
・内部的な値を送るときに使う  
```html
<input type="hidden" name="token" value="abc123">
```
### 9. `type="submit"`（送信ボタン）
```html
<input type="submit" value="送信">
```
### 10. `type="button"`（ただのボタン）
・JS と組み合わせて使う  
```html
<input type="button" value="クリック">
```

## よく使う属性との組み合わせ
### ■ required（必須入力）
```html
<input type="email" required>
```
### ■ placeholder（入力例）
```html
<input type="text" placeholder="山田太郎">
```
### ■ value（初期値）
```html
<input type="text" value="初期値">
```

## input の種類を理解するコツ
```
<input 
    type="種類" 
    id="固有名" 
    name="送信名" 
    value="初期値" 
    placeholder="入力例" 
    required
>
```
・type → 性質  
・id → label と結びつける  
・name → サーバーに送るキー  
・value → 初期値  
・placeholder → 入力例  
・required → 必須  
