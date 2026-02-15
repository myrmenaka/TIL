# fetch（API 通信）と async/await（非同期処理）
「外部データを取ってきて画面に表示する」＝Web アプリの本質  

Laravel の API と連携する時も、  
外部サービス（天気・地図・AI API）を叩く時も、  
全部この仕組み  

✔ fetch は「サーバーと通信するための関数」  
✔ async/await は「非同期処理を読みやすく書くための仕組み」  
✔ JSON を扱えると API が読めるようになる  

## 1. fetch の最小構成（超シンプル）
```js
fetch('https://example.com/data.json')
  .then(res => res.json())
  .then(data => {
    console.log(data);
  });
```
これで URL からデータを取ってくる  

## 2. async/await を使うともっと読みやすい
```js
async function loadData() {
  const res = await fetch('https://example.com/data.json');
  const data = await res.json();
  console.log(data);
}

loadData();
```
### ✔ await の意味
「この処理が終わるまで待つ」

## 3. JSON（ジェイソン）とは？
API が返すデータのほぼすべては JSON
```json
{
  "name": "Tarou",
  "age": 25
}
```
JavaScript では オブジェクトとして扱える
```js
console.log(data.name);
```

## 4. fetch で取得したデータを画面に表示する
```html
<div id="result"></div>

<script>
async function loadUser() {
  const res = await fetch('https://jsonplaceholder.typicode.com/users/1');
  const user = await res.json();

  document.querySelector('#result').textContent = user.name;
}

loadUser();
</script>
```
→ 画面にユーザー名が表示される

## 5. POST（データを送る）
```js
const res = await fetch('/api/save', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify({
    name: 'Akane',
    age: 25
  })
});
```
Laravel の API と通信するときはこの形

## 6. エラー処理（実務で必須）
```js
try {
  const res = await fetch('/api/data');
  if (!res.ok) throw new Error('通信エラー');
  const data = await res.json();
} catch (e) {
  console.error(e);
}
```

## fetch + async/await のテンプレ
```
async function run() {
    const res = await fetch('URL');
    const data = await res.json();
    console.log(data);
}

run();
```
POST のテンプレ：
```
async function send() {
    const res = await fetch('URL', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ key: 'value' })
    });
    const data = await res.json();
}
```

## ここまで理解するとできること
・API からデータを取得  
・Laravel の API と通信  
・フォーム送信を Ajax 化  
・SPA の基礎  
・外部サービス（天気・地図・AI API）と連携  

→ Web アプリの本質部分が作れるようになる  
