# 3. 制御構文とロジックの流れ
## 目次

- [条件分岐（`if`, `else`, `switch`）](#1)
- [繰り返し（`for`, `while`, `do-while`）](#2)
- [`break` / `continue` の使い方と注意点](#3)


---

<a id="1"></a>

## 条件分岐（`if`, `else`, `switch`）

### `if` / `else` の基本形

```java
int score = 85;

if (score >= 90) {
    System.out.println("優秀！");
} else if (score >= 70) {
    System.out.println("合格！");
} else {
    System.out.println("再チャレンジ！");
}
```

- 条件が上から順に評価され、最初に一致したブロックが実行される
- `else if` を使うことで複数条件を分岐できる

### switch 文の使い方

```java
int day = 3;

switch (day) {
    case 1:
        System.out.println("月曜日");
        break;
    case 2:
        System.out.println("火曜日");
        break;
    case 3:
        System.out.println("水曜日");
        break;
    default:
        System.out.println("その他の曜日");
}
```

- `switch` は値に応じて分岐する構文  
`break` を忘れると次のケースまで実行されるので注意
- `default` はどのケースにも一致しないときに実行される

### `switch` の使いどころ

- 複数の条件を分岐したいときに、`if` よりも見やすくなる

```java
switch (day) {
    case "月": System.out.println("週の始まり"); break;
    case "金": System.out.println("週末目前"); break;
    default:  System.out.println("通常日");
}
```

`switch` は「値による分岐」に特化していて、可読性と保守性を高めるために使われる


<a id="2"></a>

## 繰り返し（`for`, `while`, `do-while`）

### for 文（回数が決まっているとき）

```java
for (int i = 0; i < 5; i++) {
    System.out.println("iの値は: " + i);
}
```

- 初期化 → 条件判定 → 実行 → 更新 の流れ
- 回数が明確なときに使いやすい

### while 文（条件が満たされる限り）

```java
int i = 0;
while (i < 5) {
    System.out.println("iの値は: " + i);
    i++;
}
```

- 条件が真（true）の間、繰り返し続ける
- 無限ループにならないように注意

### do-while 文（最低1回は実行される）

```java
int i = 0;
do {
    System.out.println("iの値は: " + i);
    i++;
} while (i < 5);
```

- 条件判定が後なので、必ず1回は実行される


<a id="3"></a>

## `break` / `continue` の使い方と注意点

- `break`：ループを強制終了
- `continue`：その回の処理をスキップして次のループへ

```java
for (int i = 0; i < 5; i++) {
    if (i == 3) {
        continue; // 3のときだけスキップ
    }
    System.out.println(i);
}
```



