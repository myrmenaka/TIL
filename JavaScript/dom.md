# DOM（ドム）とイベント

✔ JavaScript は「HTML を操作するための言語」  
✔ DOM は「HTML を JS が触れるようにした仕組み」  
✔ イベントは「クリックされた」「入力された」などの“きっかけ”  

この3つを理解すると、  
ボタンを押すと動く UI が作れるようになる  

## 1. DOM（Document Object Model）とは？
HTML を JavaScript が触れるようにした“木構造” のこと
```
document
 ├─ html
 │   ├─ head
 │   └─ body
 │       ├─ h1
 │       ├─ p
 │       └─ button
```
JavaScript はこの DOM を操作して画面を変える

## 2. DOM を取得する（超重要）
### ✔ 最もよく使うのはこれ
```js
const btn = document.querySelector('.button');
const title = document.querySelector('#title');
```
・`.class`  
・`#id`  
・`tag`  

全部取得できる

## 3. DOM を書き換える
### ✔ テキストを書き換える
```js
title.textContent = 'こんにちは';
```
### ✔ クラスを付け替える
```js
btn.classList.add('active');
btn.classList.remove('active');
btn.classList.toggle('active');
```
### ✔ HTML を差し込む
```js
content.innerHTML = '<p>追加されたよ</p>';
```
## 4. イベント（クリック・入力など）
JavaScript の“動き”は `イベント` で始まる
### ✔ クリックイベント
```js
btn.addEventListener('click', () => {
    console.log('クリックされた！');
});
```
### ✔ 入力イベント
```js
input.addEventListener('input', () => {
    console.log(input.value);
});
```
### ✔ フォーム送信
```js
form.addEventListener('submit', (e) => {
    e.preventDefault(); // ページ遷移を止める
    console.log('送信された');
});
```
## 5. DOM × イベントの最小構成（テンプレ）
```html
<h1 id="title">タイトル</h1>
<button class="btn">押してね</button>

<script>
  const title = document.querySelector('#title');
  const btn = document.querySelector('.btn');

  btn.addEventListener('click', () => {
    title.textContent = '変わったよ！';
  });
</script>
```
これで ボタンを押すとタイトルが変わる  

## 6. DOM とイベントを理解するとできること
・ボタンで UI を切り替える  
・モーダルを開く・閉じる  
・タブ切り替え  
・アコーディオン  
・入力チェック  
・Ajax（fetch）でデータ取得  
・SPA の基礎  

→ Web アプリの基礎全部  
