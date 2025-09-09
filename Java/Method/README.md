# 4. メソッドと処理の分割
## 目次

- [メソッドの定義・呼び出し・引数・戻り値](#1)
- [オーバーロードの意味と使い方](#2)
- [スコープ（変数の有効範囲）](#3)


---

<a id="1"></a>

## メソッドの定義・呼び出し・引数・戻り値

### メソッドの基本構文

```java
public static int add(int a, int b) {
    return a + b;
}
```

- `public static`：アクセス修飾子と静的メソッド指定
- `int`：戻り値の型（int=整数型）
- `add`：メソッド名（キャメルケース推奨）
- `(int a, int b)`：引数（型と名前）
- `return`：戻り値を返す

### 呼び出し方

```java
int result = add(3, 5);
System.out.println("合計は: " + result);
```


<a id="2"></a>

## オーバーロードの意味と使い方

`オーバーロード`とは  
同じメソッド名で「引数の型や数が違う」複数のメソッドを定義すること

```java
public static int multiply(int a, int b) {
    return a * b;
}

public static double multiply(double a, double b) {
    return a * b;
}
```

- 呼び出し時の引数に応じて、適切なメソッドが選ばれる
- 処理の意味が同じでも、型が違う場合に便利


<a id="3"></a>

## スコープ（変数の有効範囲）

変数には「どこまで使えるか」という `スコープ（有効範囲）` がある

```java
public static void example() {
    int x = 10; // このxはexampleメソッドの中だけで有効
    System.out.println(x);
}
```

- メソッド内で宣言された変数は、そのメソッドの中でしか使えない
- ブロック `{}` の中で宣言された変数は、そのブロック内のみ有効

→ スコープを意識することで、バグを防ぎ、コードの見通しが良くなる

※ スコープ（有効範囲）は `変数の「型」ではなく、宣言された「場所」` によって決まるので、参照型の変数もプリミティブ型と同じようにスコープの制約を受ける  


### 参照型の変数とスコープの関係

たとえば、`String` や `Scanner` のような参照型の変数も、以下のようにスコープが限定される

```java
public class ScopeExample {
    public static void main(String[] args) {
        String message = "こんにちは"; // ← この変数は main メソッド内でのみ有効
        System.out.println(message);
    }

    public static void greet() {
        // System.out.println(message); ← エラー！message はここでは使えない
    }
}
```


- `message` は参照型（`String`）だが、`mainメソッド内` で宣言されたため、他のメソッドでは使えない
- これはプリミティブ型でも参照型でも同じルール

### 参照型の「中身」はどうなる？

参照型は「オブジェクトのアドレス（参照）」を持っているので、スコープ外に出るとその参照自体が消えるだけで、オブジェクトの中身（ヒープ領域）はすぐには消えない

```java
public static void createObject() {
    Scanner sc = new Scanner(System.in); // sc はこのメソッド内だけ有効
    // ここで sc を使って入力処理などを行う
}
// createObject() を抜けると sc は消えるが、ScannerオブジェクトはGCの対象になる
```

- `sc` はスコープ外に出ると使えなくなる
- ただし、オブジェクト自体はメモリ上に残っていて、`Javaのガベージコレクション（GC）` が不要になったタイミングで自動的に回収する  

→ つまり、参照型の変数もスコープに従って消えるけれど、その中身（オブジェクト）は別のルールで管理されているということ  





