# 10. 実践的なコード構成と設計の考え方
## 目次

- [複数クラスの連携（呼び出し・依存関係）](#1)
- [パッケージ分けの設計意図（機能別・責務別）](#2)
- [クラス設計のコツ（責務の分離・再利用性）](#3)


---

<a id="1"></a>

## 複数クラスの連携（呼び出し・依存関係）

### クラス同士の呼び出し例

```java
public class User {
    String name;
    public User(String name) {
        this.name = name;
    }
}

public class Greeter {
    public void greet(User user) {
        System.out.println("こんにちは、" + user.name + "さん！");
    }
}
```

```java
User akane = new User("朱音");
Greeter greeter = new Greeter();
greeter.greet(akane);
```

- `User` クラスが「データ保持」
- `Greeter` クラスが「処理担当」
- こうした分業により、責務が明確になり、再利用性が高まる


<a id="2"></a>

## パッケージ分けの設計意図（機能別・責務別）

### パッケージとは？

- クラスを機能別・責務別に整理するための名前空間  
    例：`com.akane.bank.account`, `com.akane.bank.transaction`

### パッケージ階層のイメージ

```
com              ← 最上位パッケージ（企業・組織名）
└── akane         ← サブパッケージ（個人・プロジェクト名）
    └── bank      ← サブパッケージ（ドメイン・機能領域）
        └── account ← サブパッケージ（具体的な機能単位）
            └── Account.java ← クラスファイル
            
```

- `com.akane.bank.account` が 完全なパッケージ名
- `com` はその中の 最上位パッケージ
- `com.akane` も パッケージ（ただし、より広い範囲を指す）

→ Javaでは「ドット区切り」で階層を表現するので、`どこまでがパッケージか？` は文脈によって変わる
   
### 実際のコードでの使い方

```java
package com.akane.bank.account;

public class Account {
    // クラスの中身
}
```

→ この `package` 宣言は、「このクラスは `com.akane.bank.account` というパッケージに属していますよ」という意味

### なぜ階層構造にするのか？

- 責務ごとに整理できる：UI、ロジック、データなどを分けやすい
- 名前の衝突を防げる：同じクラス名でも、パッケージが違えば共存できる
- 保守性が高まる：機能単位でフォルダ管理できるので、見通しが良くなる


### 実際のプロジェクト構成例（`MVC`）

| 層 | 役割 | クラス例 |  
|:--:|:--:|:--:|  
| Model | データとロジック | `Account`, `User` |  
| View | 表示・UI | `AccountView`, `ConsoleUI` |  
| Controller | 表示・UI | `AccountController` |  


※ `MVC` は「責務の分離」を徹底する設計パターン  
→ 設計視点の整理法

### 機能別のパッケージ例

```java
com.akane.model      // データ構造（Userなど）
com.akane.service    // 処理ロジック（Greeterなど）
com.akane.util       // ツール系（DateFormatterなど）
```

- パッケージは「機能」や「責務」で分けると、見通しが良くなる
- `Eclipse` や `VSCode` では、パッケージ名がフォルダ構造に反映される


<a id="3"></a>

## クラス設計のコツ（責務の分離・再利用性）

### 責務の分離（SRP：単一責任原則）

- 1クラス1責務が基本 = 1つのクラスは「1つの目的」に集中する  
    例：`User` は「ユーザー情報の保持」、`Greeter` は「挨拶処理」
- UI・ロジック・データ管理などを分離することで、保守性・再利用性が向上


### 再利用性を高める設計

- 汎用的な処理は `Util` クラスにまとめる
- インターフェースで「できること」を定義し、複数クラスに実装させる

```java
interface Printable {
    void print();
}

class Invoice implements Printable {
    public void print() {
        System.out.println("請求書を印刷します");
    }
}
```

🔍 こうした設計により、`拡張しやすく`、`保守しやすいコード`が書けるようになる

