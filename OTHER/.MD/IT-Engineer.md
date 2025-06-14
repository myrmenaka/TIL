# エンジニアの仕事内容

基本的には何かシステムやアプリを作ることに関わる仕事

## エンジニアが作るもの

### 1. webアプリ

Google ChromeやSafariなどのブラウザからアクセスして使うアプリ

### 2. ネイティブアプリ

スマホやパソコンの端末に直接インストールするアプリのこと

それぞれの端末に最適化しているため、webアプリに比べてより使いやすサービスを提供できる

有名なサービスだとwebアプリとネイティブアプリの両方を提供している場合がある

例：Amazon、YouTube、X…

### 3. 業務システム

主に企業向けのシステム

給与管理や在庫管理、予約システムなど

銀行への導入や、全国の店舗に導入されるなど、規模の大きいシステムも多い

### 4. 組み込みシステム

日常生活の中であちこちに入っているシステムのこと

洗濯機やエアコン、電子レンジなどに組み込まれたプログラム

## エンジニアの業務工程

### 1. 要件定義 ：「何を作るのか」

システムの方向性を決める段階

「どんな目的のためにシステムを作るのか」、

「目的を達成するためにどんな機能が必要なのか」を考える

目的や必要な機能はできればドキュメントにしてクライアントや、社内システムであれば社内の関係者と合意を取る必要がある

### 2. 設計：「どう作るのか」

要件定義で決めた目的や機能を具体的にどうシステムにしていくかを決める段階

プログラムの構造やデータの管理方法、見た目のデザインなどを決めていき実際にプログラミングができる状態にしていく

サービス開発は事前の設計が重要

ここがしっかり決まっていないと無駄な仕事が発生したり、後から大きな修正や作り直しが起きる

### 3. 開発

プログラムを書く工程

設計に基づいてシステムを作り上げていく

### 4. テスト

作成したシステムをテストする工程

- 要件通りの機能が作られているか
- 問題なくシステムが動作するか

をチェックする

実際は、開発しながらテストもしていく

テストは人が行うものもあればプログラムによってテストを自動化することができる

テストに漏れがあったり、チェックが甘くなると後で重大な問題を起こす可能性がある

非常に重大な工程

### 5. リリース

作成したシステムを公開する工程

作ったシステムを世界中の人や会社の人に使ってもらうためにサーバーと呼ばれる公開用のコンピュータを用意して設定を行う

### 6. 運用・保守

公開したシステムを長期的に維持する工程

システムのバグ修正や、アップデートといった継続的なメンテナンス、システムが正常に動いているか監視するための仕組み作りなどを行う

システムというのはリリース後も動き続け、新しいニーズや問題への対応が続いていく

システムには完成はなく、エンジニアはそのシステムを長期的に維持することが求められる

## エンジニアの開発手法

会社やプロジェクトで異なる

### ウォータフォール開発

業務工程を上から順番に行っていく開発手法

最初にシステムの全体をしっかりと設計して詳細まで決めた段階で開発に移っていく

ウォータフォール＝滝

滝のように上から下に工程が流れるイメージ

「業務システム」や「組み込みシステム」でよく使われる手法

特に企業向けのシステムを開発するときには最初にしっかり計画を立てる必要があるのでウォータフォール開発になるケースが多い

<メリット>

最初から計画をしっかり立てることで必要な人員や時間、要件などを把握しやすい

失敗が許されないような大規模なプロジェクトや金融系のシステムにおいてウォータフォール開発が向いている

<デメリット>

最初の「２．設計」の難易度が高い

また、途中で変更をしにくいので市場やユーザーのフィードバックを元に改善をしていくようなサービスとは相性が良くない

### アジャイル開発

もっと素早く改善を繰り返してサービスを良くしていきたい場合に向いている

アジャイル＝素早い

業務工程を機能ごとに繰り返していくのがアジャイル開発のイメージ

最初に、必要な機能に絞って設計・開発してリリースして、

次の機能を設計・開発してリリース…というように機能ごとに工程を繰り返していく開発手法

「webアプリ」や「ネイティブアプリ」でよく使われる手法

ベンチャー企業や小規模なプロジェクト、自社のサービスを開発する企業でも用いられるケースが多い

<メリット>

機能ごとに開発を行っていくため変更に強く様々な機能をスピーディーにリリースできる

<デメリット>

「２．設計」が甘くなりやすく、後から想定漏れやシステムの方向性がブレる可能性がある

## エンジニアの仕事内容まとめ

エンジニアの作るアプリやシステムは大きく４つに分類される

このシステムをエンジニアは要件定義から始まる６つの工程で作っていく

この工程をする方法も「ウォータフォール」と「アジャイル」という２つがあり

作るシステムやプロジェクトによって変わる

「webアプリ」や「ネイティブアプリ」はアジャイルが多く

「業務システム」や「組み込みシステム」はウォータフォールが多い

# エンジニアの種類

※同じ種類のエンジニアでも企業や人によって定義が異なったり、違う種類のエンジニアであっても一部の仕事が被っている場合がある

エンジニアの定義は明確に定まっているわけではない

## 業務システム

<業務システムでは担当する工程で呼び方が変わる>

### システムエンジニア

「要件定義」「設計」「開発」まわりの開発

- 「要件定義」「設計」をメインの業務にしているシステムエンジニアが多い
- 詳細な設計が求められる「業務システム」の開発や、大規模な開発で活躍の機械が多い仕事
- システムを作りたいお客様と打ち合わせをしながらシステムの詳細や開発のスケジュールなどを立てていく
- プログラミングやシステムの知識だけでなく、高いコミュニケーション能力が求められる

※働く会社によってはコードを一切書かない場合がある

### プログラマー

「開発」を中心に行う

- プログラマーはエンジニアの１種
- 「システムエンジニア」が設計したシステムを実際に作る
- 規模の大きなシステムの開発の場合は設計をする人とコードを書く人が分業されているケースがあり、このようなときにコードを書く人をプログラマーと言うことが多い
- エンジニアを目指すうえで特にコードを書くことに興味がある人におすすめ

## アプリ開発

<アプリ開発では何を作るのかによってエンジニアの呼び方が変わる>

### webエンジニア

webアプリ開発

### アプリエンジニア

ネイティブアプリ開発

「アプリエンジニア」の中で↓

### iOSエンジニア

iPhoneアプリ開発

### Androidエンジニア

Androidアプリ開発

<扱う技術の領域でエンジニアを区別する場合がある>

### フロントエンドエンジニア

「フロントエンド」はユーザーの目に見える見た目の部分を指す

### バックエンドエンジニア

「バックエンド」は目に見えない部分で主にデータベースのやり取りやサーバーの設定、サービスを動かすための詳細なロジックを指す

### フルスタックエンジニア

「フロントエンド」と「バックエンド」の両方に精通したエンジニアを

小規模な会社だと１名の「フルスタックエンジニア」がサービスを全て統括しているようなケースもある

「フロントエンド」か「バックエンド」のどちらかでキャリアをスタートして、

最終的に「フルスタック」を目指すという人も多い

- 「業務システム」では担当する工程で呼び方が変わる
    
    →「業務システム」はウォータフォール開発を採用しやすくするため、工程ごとに分業するケースが多いことが要因
    
- 「アプリ開発」では何を作ることを仕事にしているかで呼び方が変わる
    
    →「アプリ開発」はアジャイル開発を採用しやすくするため、設計から開発まで担当するエンジニアが多いことが要因
    

## 組み込みエンジニア

- 組み込みシステム開発
- 家電などにプログラムを組み込んでいく仕事のためソフトウェアの知識だけでなくハードウェアへの知識も必要
- IT化により需要は増えているが、スキルの習得難易度が高いので未経験から目指すのは一定のハードルがある

## テストエンジニア

- システムの動作をチェックするためのテスト項目を策定し、その確認作業を行う
    
    万が一問題があれば、それを開発しているエンジニアにフィードバックしてシステムの品質を高めていく
    
- テストエンジニアを目指す場合はテスト手法に関する知識のほか、場合によってはコードを読んでチェックすることもあるためプログラミングの知識もあった方が良い
- テスト項目を作成し、根気強くチェックを続ける必要があるので
    
    タスク管理スキルや、細かい点に気を配れる性格が向いている
    
- テストエンジニアは「業務システム」や大規模なシステム開発において特に求められる仕事
- アプリ開発や小規模な開発においては、開発を担当するエンジニアがテストを行うケースもある

## インフラエンジニア

- インフラ＝基盤
- システムが動くための基盤を用意したり適切に稼働し続けるように支え続けるのがインフラエンジニア
- システムを使うためには、そのシステムを動かすサーバーやネットワーク、データベースというものが必要になる
    
    これらを設定したり、システムの状態に応じて最適化するエンジニア
    
- インフラエンジニアになるためには、プログラミングというよりはサーバーやネットワークなどの知識全般が必要
- インフラの設定が適切でないと外部から攻撃を受けたり情報漏洩のリスクが高まるため、セキュリティに関する知識も必要不可欠

## プロジェクトマネージャー（PM）

※自身がコードを書くケースは少ないがシステム全体への理解が求められるため、今回はエンジニアとして区分している

- プロジェクト全体を管理する仕事
    
    プロジェクト全体が予定通り進行しているかを管理しクライアントやメンバーとのコミュニケーションを行う
    
- エンジニアのキャリアとしてマネジメント領域を目指していく人はPMになるケースが多い
- PMも現在深刻な人手不足から市場価値が非常に高い仕事

専門性によってエンジニアの種類は多岐に渡る

例えば、インフラエンジニアも更に、「ネットワークエンジニア」「データベースエンジニア」「セキュリティエンジニア」などに細分化されていく

大切なのは呼び方よりも、作るものや業務内容、開発方法といった中身の部分

- 自分がどんなものを作りたいのか
- その中でどういった役割を担当したいのか

こうしたことを知っておくことが必要不可欠  

---

参考：[youtube](https://youtu.be/0dbRtTtClvk)
