### アプリケーション  
パソコンやスマホで動作するプログラム  

### モバイルアプリケーション  
スマホにインストールして使うアプリ  
インターネットに繋がなくても利用できるアプリがある  

### webアプリケーション  
ブラウザを使ってアクセスするアプリ  
基本的にはインターネットが使えることが条件になる  

### ブラウザ  
インターネット上のページを見るためのツール  
検索機能、URLを使うことでwebアプリケーションを開くことができる  

### URL  
webアプリケーションの住所（人間がサービスのURLを覚えやすいように名前をつけたもの）  
サーバーのある場所  

### ドメイン  
URLの最も重要な部分  
サーバーの住所自体はこのドメインで決まる  
URLは実際には「192.168.0.0」このような数字の羅列になっている  

### IPアドレス  
ドメインの実際の数字の羅列  
サーバーの本当の住所  
サーバーだけでなくデバイスにもIPアドレスが設定されている  

### ドメインネームシステム（DNS）  
ドメイン名をIPアドレスに変換するシステム  
→購入したドメイン名（お名前.comなどで購入できる）と自分のサーバーのIPアドレスを紐づける  

### サーバー  
webアプリケーションの情報を置いておくコンピュータ  
webアプリを表示するために必要な情報を提供している  
サーバーはアプリごとに用意されている  

### リクエスト  
ブラウザがサーバーに情報をもらいに行くこと  

### レスポンス  
サーバーがブラウザに情報を提供すること  
インターネットを通じたページの表示や情報のやり取りはリクエストとレスポンスの往復で成り立っている  

### webアプリケーションを作るために必要なもの  
1. サーバーの用意  
2. webアプリケーションのプログラムの作成  
3. ドメイン名の設定

### 開発  
webアプリケーションを作る作業  

### プログラミング言語  
コンピュータに命令を与えるための言語  
プログラミング言語ごとに得意な領域が違う  
デバイスとwebアプリのサーバー、この２つのコンピュータでプログラミング言語が活躍する  

### フロントエンド  
ユーザーが直接触れたり見える部分  
主に見た目を作る（アプリの画面）  

### バックエンド/サーバーサイド  
サーバーの中で動いている処理  
（サーバーサイドはバックエンドの別称）  

・ユーザーの認証  

・データの保存  

・ファイルのアップロード  

などの、見た目以外の機能をほとんど作成する  

### フロントエンドのプログラミング言語  
以下の言語を使う場合がほとんど  

#### HTML（マークアップ言語）  
画面に表示する情報  
どんな情報を画面に表示するかを決めるための言語  

#### CSS（スタイルシート言語）  
見た目を装飾する情報  
ページを装飾することができる  

#### JavaScript  
画面に動きをつける情報  
アニメーションをつけたり、ボタンを押した時にサーバーと通信する機能をつけたりすることができる  

### バックエンドのプログラミング言語  
会社やプロジェクトによって使う言語はバラバラ  

#### Ruby  
webアプリケーション開発が得意な言語  
シンプルな書き方が特徴  

#### PHP  
webアプリケーション開発が得意な言語  
多くの開発現場で使われている  

#### Java  
幅広い用途に使われる言語  
特に安全性の高いシステムを作れる  
大規模なシステム開発でもよく使われる言語  

#### Python  
シンプルで書きやすい言語  
AI分野でも使われることが多い  

### フレームワーク  
webアプリ開発のための枠組み  
中でもwebアプリを作るための枠組みが「webアプリケーションフレームワーク」  
フレームワークを使うことで、webアプリを作るための大枠を用意した状態でアプリを作成することができる  
よりwebアプリを簡単に作れるようにしてくれるソフト  
このソフトはプログラミング言語で作られている  

#### Ruby on Rails  
Rubyで作られたフレームワーク  
Rubyを使って開発する際に使用する  

#### Laravel  
PHPで作られたフレームワーク  

#### Spring Framework  
Javaで作られたフレームワーク  

#### Django  
Pythonで作られたフレームワーク  

### ライブラリ  
プログラムを作るうえでよく使う機能などをまとめたもの  
例えば、ログイン機能や検索機能などはライブラリとして誰かが提供してくれている  
こうしたライブラリを使うことでコードを書く手間を省くことができる  
このように、プログラミングでは自分で何もかもゼロから作るというよりは  
すでに誰かが作ってくれた枠組みや機能などを活用して組み合わせる事が多い  

### プログラミング言語が動く流れ  
1. Amazonで商品一覧ページにアクセスする  
2. ブラウザがサーバーにリクエストを送る  
3. サーバーでバックエンドのプログラムが動く  
4. 商品一覧ページを表示したいので、商品情報を集めなければいけない  
  →商品の情報はどこから取ってくるのか  

---

### データベース  
データを貯めるための保管庫  
Amazonのようなショッピングサイトであれば  

・お客様情報  

・商品情報  

・購入履歴  

などが保管されている  

### SQL（クエリ言語）  
データベースとのやりとりをする言語  
データベースに情報を保管したり、保存した情報を取り出すために使う  

---

5. バックエンドのプログラムでこのSQLを使ってデータベースから情報を取ってくる  
6. 取ってきた商品情報を元に商品一覧ページの情報を用意する  
7. HTML/CSS、JavaScriptのプログラムが動く  
8. これらの情報をレスポンスしてブラウザに返す

→ここまでがバックエンドのプログラムの役割  

9. ブラウザがレスポンスを受け取る  
10. ブラウザが受け取った情報の中にあるHTML/CSS、JavaScriptの内容を解釈する  
11. 商品一覧ページが表示される  

### webアプリケーションを作る工程  
#### 1. 要件定義  
  何を作るのかを決めること  
エンジニアが何かを作る場合には必ず要件定義からスタートする  
どんなアプリケーションを作るのかという方向性を決める重要なステップ  
アプリ＝特定の目的を達成するためのもの

#### 要件定義のゴール  
作るアプリの目的や機能を決める  

#### 2. 設計  
  どのようにプログラムを作っていくのかを考えること  

・どんな見た目にするのか  

・データベースではどんなデータを管理するのか  

・プログラミング言語は何を選択するのか  

などを考えて、次の工程に入れる状態にする  

→要件定義と設計をしっかり決めておかないと後で作り直しになったり、将来的なアップデートに対応できなくなる  

#### 3. 開発  
  webアプリケーションを作る作業  
設計に基づいてプログラミングをしてwebアプリを作る段階  

#### 4. テスト  
プログラムが正しく動くか確認すること  
デバックをするだけでなく、設計した通りに正しく動くかも確認する  

---

### バグ  
プログラムの問題や誤り  
### デバック  
バグを探し出して修正すること  

---

#### 5.リリース  
世の中に公開して使ってもらえる状態にすること  

---

### デプロイ  
作ったwebアプリを一般の人が使えるようにする作業  
一般の人がアクセスできるサーバーにwebアプリのプログラムが書かれたファイルや画像をアップデートする  

---

#### 6. 運用・保守  
公開後のメンテナンスをすること  
問題なくwebアプリを使い続けられるようにメンテナンスをする  
リリース後もアップデートをしたり  
使い勝手が良くなるように改善を繰り返す  
サービスが長期的に維持できるようにする工程  

### コンピューターを構成する要素  

### ハードウェア（ハード）  
コンピュータを構成する物理的な部品  
例：モニター、キーボード、マウス…  

### ソフトウェア（ソフト）  
コンピュータ上で動くプログラムやデータ  
例：webアプリケーション…  

### OS  
オペレーティングシステムの略  
コンピュータの人格を決める重要な要素  
ハードウェア（パソコン）の中にOSを入れることで、操作やアプリを動かせるようになる  
パソコンを動かすうえで必須のソフト  

#### Windows  
Microsoft社提供するOS  

#### Mac  
Apple社が提供するOS  
Apple社が製造するパソコン、MacでないとMacOSを搭載することはできない  

→パソコンを２種類に分けると、「Macかそれ以外」となる  

### スマホのOS  

#### iOS  
iPhoneに搭載されているOS  
Macと同じく、iOSもiPhoneでないと動かない  

#### Android  
Google社が提供するOS  

### エンジニアがwebアプリを作る場合に使うOS  
主にwebアプリを動かすためのサーバーに使われることが多い  
サーバーもコンピュータなのでOSがないと動かない  
サーバーを用意してOSを入れる際に選択肢として多いのがUNIXとLinux  

#### UNIX  
50年以上前に誕生  
商用利用の制限が厳しい  

#### Linux  
UNIXを参考にして作られたOS、UNIXと似ている点が多い  
オープンソースのOS  
無料でも使えるOSがあるので広くサーバーに使われている  

#### オープンソース  
プログラムを一般に公開し誰でも変更したり利用できる状態にしていること  
そのためエンジニアがプログラムをアップデートしより高い品質に進化させ続けることができる  

### プログラミングにおける重要な用語  

### テキストエディタ  
プログラミングを書くためのツール  
例えると、プログラミングに特化したメモ帳やWord  
エンジニアが書くプログラムというのはあくまでテキストファイル  
→メモ帳でプログラムを書くこともできる  
プログラミングをより書きやすくし、作業の効率を上げるツール  

### ソースコード（コード）  
プログラムを作るために書くテキスト  
プログラミング言語は人間が書きやすいように英語ベースで作られている  

### 機械語  
「０」と「１」だけで構成された、コンピュータが理解できる言語  
プログラミングで書いたコードはそのままだと機械は読めない  
書いたコードを機械語に翻訳する作業（コンパイル）が必要  

### コンパイル  
プログラムを機械語に変換すること  
書いたプログラムを動かす時にこのコンパイルという作業が行われる  

### Git（必須ツール）  
プログラムのコードの変更履歴を保存できるソフト  
Gitを使えば間違えてしまったときでもコードの状態を戻すことができるようになる  

### GitHub  
Gitを利用したwebサービス  
エンジニアのほとんどが登録しているサービス  
GitHubを利用することでより便利にGitを使ったり、チームでの開発がしやすくなる  
オープンソースとして公開されているプログラムの多くがGitHub上でコードを公開している  

---
参考：[youtube](https://youtu.be/oU2sisdcztQ)
