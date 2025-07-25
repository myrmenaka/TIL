# HTML
タグのリファレンス

## `<form>` 

フォーム要素
[参考: mdn web docs](https://developer.mozilla.org/ja/docs/Web/HTML/Reference/Elements/form)

- `<form>`
- action
- method

## `<label>` 

ラベル要素
[参考: mdn web docs](https://developer.mozilla.org/ja/docs/Web/HTML/Reference/Elements/label)

## `<input>`

入力要素
[参考: mdn web docs](https://developer.mozilla.org/ja/docs/Web/HTML/Reference/Elements/input)

- type
- value

---

# 用語

## サニタイズ

[参考: 「分かりそう」で「分からない」でも「分かった」気になれるIT用語辞典](https://wa3.i-3-i.info/word16265.html)

## XSS（クロスサイトスクリプティング）

[参考: 「分かりそう」で「分からない」でも「分かった」気になれるIT用語辞典](https://wa3.i-3-i.info/word11764.html)

## POST送信

POST送信（POST submission）  
- Webブラウザなどのクライアントが、POSTメソッドを使ってサーバーにデータを送信する行為を指します。
- 例えば、Webフォームに情報を入力して送信ボタンをクリックすると、ブラウザはPOSTメソッドを使ってそのデータをサーバーに送信します。
- この行為によって、サーバーは送信されたデータを受け取り、適切な処理を行います。
- POST送信は、GET送信とは異なり、ブラウザのアドレスバーに送信データが表示されないため、より安全にデータを送信できます。

POSTメソッド（HTTP POST method）  
- HTTPプロトコルで定義されたリクエストメソッドの一つで、サーバーにデータを送信する際に使用されます。
- 具体的には、Webブラウザなどのクライアントが、サーバーに対して「このデータを受け取って処理してください」という指示を出す際に使われます。
- 主にフォームデータの送信や、ファイルのアップロードなどに利用されます。
- GETメソッドとは異なり、送信するデータはリクエストのボディ部分に格納されます。
- URLにデータが直接表示されないため、機密性の高い情報を送信する際に安全です。

具体例  
例えば、Webフォームでユーザー名とパスワードを入力してログインする場合、ブラウザはPOSTメソッドを使って、これらの情報をサーバーに送信します。この行為がPOST送信です。

まとめ  
- POSTメソッドは、データをサーバーに送信するためのHTTPリクエストの方式を指します。
- POST送信は、POSTメソッドを使って実際にデータをサーバーに送信する行為を指します。
- POSTメソッドは手段、POST送信はその手段を用いた結果の行為を表します。

[参考: 「分かりそう」で「分からない」でも「分かった」気になれるIT用語辞典](https://wa3.i-3-i.info/word1496.html)

## リクエストボディ

リクエストボディとは、HTTPリクエストの本体部分のことで、クライアントからサーバーに送信したいデータを格納する領域です。  
主にPOSTやPUTなどのリクエストメソッドで使用され、フォームデータやJSONデータなど、様々な形式のデータを送信するために利用されます。  

HTTPリクエストの構成:  
HTTPリクエストは、リクエストライン（メソッド、URI、HTTPバージョン）、ヘッダー、ボディの3つの主要な部分で構成されます。  

リクエストボディの役割:  
リクエストボディは、リクエストの目的に合わせて、サーバーに送信したいデータを格納します。  

使用例:  
- フォームデータの送信:HTMLフォームで入力されたデータを送信する際に、リクエストボディに格納して送信します。
- JSONデータの送信:APIでデータをやり取りする際に、JSON形式でデータを送信するためにリクエストボディを使用します。
- ファイルのアップロード:ファイルをサーバーにアップロードする際に、リクエストボディにファイルの内容を格納して送信します。

まとめ:  
リクエストボディは、HTTPリクエストにおいて、クライアントからサーバーへデータを送信するための重要な要素です。  
様々な形式のデータを送信するために使用され、特にPOSTやPUTなどのリクエストメソッドでよく利用されます。

