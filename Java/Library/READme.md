# 9. 標準ライブラリの活用
## 目次

- [`String` クラスのよく使うメソッド](#1)
- [`Scanner` による入力処理](#2)
- [`Math` クラスの便利な関数](#3)


---

<a id="1"></a>

## `String` クラスのよく使うメソッド

Javaでは文字列は `String` クラスとして扱われ、便利なメソッドが多数用意されている

```java
String message = "Hello, Akane!";
System.out.println(message.length());       // → 文字数を取得
System.out.println(message.toUpperCase());  // → 全て大文字に
System.out.println(message.contains("Aka")); // → 部分一致判定
System.out.println(message.replace("Akane", "Java")); // → 置換
```

→ `String` はイミュータブル（変更不可）なので、操作結果は新しい文字列として返される


<a id="2"></a>

## `Scanner` による入力処理

コンソールからの入力を受け取るには `Scanner` クラスが便利

```java
import java.util.Scanner;

Scanner sc = new Scanner(System.in);
System.out.print("名前を入力してください: ");
String name = sc.nextLine();
System.out.println("こんにちは、" + name + "さん！");
```

- `nextLine()`：1行の文字列を取得
- `nextInt()`：整数を取得
- `nextDouble()`：小数を取得  

→ 入力処理は例外が起きやすい  
`try-catch` とセットで使うと安心


<a id="3"></a>

## `Math` クラスの便利な関数

数学的な処理には `Math` クラスが活躍

```java
System.out.println(Math.max(10, 20));     // → 最大値（20）
System.out.println(Math.min(5, 3));       // → 最小値（3）
System.out.println(Math.pow(2, 3));       // → 2の3乗（8.0）
System.out.println(Math.sqrt(16));        // → 平方根（4.0）
System.out.println(Math.random());        // → 0.0〜1.0の乱数
```

→ `Math.random()` を使えば、簡単なゲームや抽選ロジックも作れる

