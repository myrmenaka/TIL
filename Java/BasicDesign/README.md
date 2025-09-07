# 7. オブジェクト指向の基本設計
## 目次

- [カプセル化（`private`＋`getter`/`setter`）](#1)
- [継承（extends）とオーバーライド（@Override）](#2)
- [ポリモーフィズムの考え方と実例](#3)
- [`abstract` クラスと `interface` の違い](#4)


---

<a id="1"></a>

## カプセル化（`private`＋`getter`/`setter`）

### カプセル化とは？
- オブジェクトの内部状態（フィールド）を隠し、外部からの不正なアクセスを防ぐ設計手法
- 「データと振る舞いを一体化し、外から勝手に触れさせない」＝安全性と保守性の向上  

→ `カプセル化`により、不正なアクセスや予期せぬ変更を防ぐことができる

```java
public class User {
    private String name; // 外部から直接アクセスできない

    public String getName() {
        return name; // 読み取り用
    }

    public void setName(String name) {
        this.name = name; // 書き込み用
    }
}
```

- `private`：フィールドを外部から隠す
- `getter`/`setter`：安全に値を読み書きするためのメソッド  

```java
public class Account {
    private int balance; // 外部から直接アクセス不可

    public void deposit(int amount) {
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }
}
```

### なぜ `private` にするのか？
- 外部から- 直接 `balance` を変更できると、整合性が崩れるリスクがある
- `getter`/`setter` を通すことで、制御されたアクセスが可能になる  
    例：`setBalance()` に「負の値は拒否する」などのロジックを入れられる


<a id="2"></a>

## 継承（extends）とオーバーライド（@Override）

### 継承とは？

- 既存のクラス（親）をベースに、新しいクラス（子）を作る仕組み
- 共通機能を再利用しつつ、追加・変更が可能

### 継承の基本

```java
public class Animal {
    void speak() {
        System.out.println("何かが鳴いた");
    }
}

public class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("ワン！");
    }
}
```

- `extends`：親クラス（スーパークラス）を継承
- `@Override`：親クラスのメソッドを上書き（オーバーライド）  

→ 継承により、共通の処理を親クラスにまとめて、子クラスで個別の振る舞いを定義できる

### `super` の意味と使い方

- 親クラスのメソッドやコンストラクタを呼び出すためのキーワード

```java
public class Dog extends Animal {
    void speak() {
        super.speak(); // 親の speak() を呼ぶ
        System.out.println("そしてワン！");
    }
}
```

→ 継承は「共通化による効率化」だけでなく、「責務の分離と拡張性」を意識した設計が重要


<a id="3"></a>

## ポリモーフィズムの考え方と実例

`ポリモーフィズム（多態性）` とは、同じメソッド呼び出しでも、オブジェクトの型によって振る舞い（実行される内容）が変わる性質


```java
Animal a1 = new Dog();
Animal a2 = new Cat();

a1.speak(); // → ワン！
a2.speak(); // → ニャー！
```

「型は`Animal`だけど、実体は`Dog`」＝実行時に正しい振る舞いを選ぶことで柔軟な設計が可能  
→ `Animal` 型として扱っていても、実際のオブジェクトに応じて `speak()` の内容が変わる


<a id="4"></a>

## `abstract` クラスと `interface` の違い

| 比較項目 | `abstract` | `interface` |  
|:--:|:--:|:--:|  
| 継承方法 | `extends` | `implements` |  
| メソッド | 抽象＋具象メソッドを持てる | すべて抽象（Java 8以降はdefaultも可） |  
| フィールド | 持てる | 基本的に定数のみ |  
| 多重継承 | 不可 | 可能（複数の`interface`を実装できる） |  
| 用途 | 共通処理＋抽象化を両立 | 契約（仕様）を定義 |  


### 例：`interface` の定義と実装

```java
interface Flyable {
    void fly();
}

abstract class Bird {
    void eat() {
        System.out.println("餌を食べる");
    }
    abstract void sing();
}
```

→ 
`interface` は「できることの契約」  
    「こういう機能を持っているべき」という役割の定義  
`abstract` クラスは「共通の土台」  
    「共通処理を持ちつつ、部分的に未定義」という設計のテンプレート
として使い分ける


