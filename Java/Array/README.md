# 5. 配列とコレクションの基礎
## 目次

- [配列の宣言・初期化・アクセス](#1)
- [`ArrayList` の使い方と違い](#2)
- [`for-each`文と通常の`for`文の違い](#3)


---

<a id="1"></a>

## 配列の宣言・初期化・アクセス

### 配列の基本構文

```java
int[] scores = new int[5]; // 要素数5のint型配列を作成
scores[0] = 90;
scores[1] = 80;
// ...
System.out.println(scores[0]); // → 90
```

- `int[] scores`：int型の配列を宣言
- `new int[5]`：要素数5の配列を生成
- `scores[0]`：インデックスは0から始まる

### 初期化と同時に代入

```java
String[] names = {"Akane", "Taro", "Yuki"};
System.out.println(names[1]); // → Taro
```

- 配列リテラル `{}` を使うと、宣言と初期化を同時にできる


<a id="2"></a>

## `ArrayList` の使い方と違い

### `ArrayList` の基本構文

```java
import java.util.ArrayList;

ArrayList<String> list = new ArrayList<>();
list.add("Akane");
list.add("Taro");
System.out.println(list.get(0)); // → Akane
```

- `ArrayList` はサイズ可変のリスト  
`add()` で追加、`get()` で取得
- 配列と違って、要素数を気にせず使えるのがメリット

### 配列とコレクションの違い

#### 配列（Array）

- 固定長・同じ型のデータを格納
- メモリ効率は良いが、柔軟性に欠ける

```java
int[] scores = new int[3];
scores[0] = 90;
```

#### コレクション（ArrayListなど）

- 要素数が可変・便利なメソッドが豊富
- 実務では `ArrayList` や `Map` が主流

```java
ArrayList<String> names = new ArrayList<>();
names.add("朱音");
names.add("太郎");
```


### 配列との違いまとめ

| 比較項目 | 配列 | `ArrayList` |  
|:--:|:--:|:--:|  
| サイズ変更 | 不可 | 可能（自動拡張） |  
| 型 | プリミティブ型もOK | 参照型のみ（`int`は使えず、`Integer`など） |  
| 機能 | シンプル | メソッドが豊富（`add`, `remove`, `contains`など） |  


<a id="3"></a>

## `for-each`文と通常の`for`文の違い

### 通常のfor文

```java
for (int i = 0; i < names.length; i++) {
    System.out.println(names[i]);
}
```

- インデックスを使ってアクセス
- 要素の位置が必要なときに便利

### for-each文（拡張for文）

```java
for (String name : names) {
    System.out.println(name);
}
```

- 配列やコレクションの全要素を順番に処理
- インデックスが不要なときにスッキリ書ける

### `for-each` と通常 `for` の違いまとめ

| 構文 | 特徴 | 使い分け |  
| `for (int i = 0; i < arr.length; i++)` | インデックス操作が可能 | 要素の位置が重要なとき |  
| `for (String name : names)` | 可読性が高い | 単純な全件処理に向く |  

→ コレクションは「柔軟性と保守性」を重視した設計  
配列は軽量・高速処理に特化　　

