# 8. 例外処理とエラー対策
## 目次

- [`try-catch-finally` の使い方](#1)
- [`throw` / `throws` の意味](#2)
- [よくある例外とその原因](#3)


---

<a id="1"></a>

## `try-catch-finally` の使い方

```java
try {
    int result = 10 / 0; // 例外が発生する（ArithmeticException）
} catch (ArithmeticException e) {
    System.out.println("0で割ることはできません！");
} finally {
    System.out.println("処理終了");
}
```

- `try`：例外が発生する可能性のある処理を囲む
- `catch`：例外が発生したときの処理を書く
- `finally`：例外の有無に関係なく、最後に必ず実行される処理  

→ `finally` はファイルのクローズ処理など、後始末に使うのが定番

### なぜ try-catch が必要なのか？

- 実行時に起こる `予期せぬエラー（例外）` を安全に処理するため
- プログラムが途中で止まらず、適切な対応ができるようにする


<a id="2"></a>

## `throw` / `throws` の意味

### `throw`：例外を「発生させる」

```java
throw new IllegalArgumentException("不正な引数です");
```

- 明示的に例外を発生させるときに使います
- `new` で例外オブジェクトを作成して投げる

### `throws`：例外を「外に投げる」

```java
public void readFile() throws IOException {
    // ファイル読み込み処理（IOExceptionの可能性あり）
}
```

- メソッドの宣言に `throws` をつけることで、呼び出し元に例外処理を委ねる
- 複数の例外をカンマ区切りで指定することも可能


<a id="3"></a>

## よくある例外とその原因

| 例外名 | 原因 | 対策 |  
|:--:|:--:|:--:|  
| `NullPointerException` | `null参照` にアクセス | `nullチェック` を徹底 |  
| `ArrayIndexOutOfBoundsException` | 配列の範囲外にアクセス | 配列長を確認してからアクセス |  
| `NumberFormatException` | 文字列 → 数値変換失敗 | `try-catch`＋入力バリデーション |  
| `IOException` | ファイルやネットワークの入出力エラー | `try-catch` で囲む＋リソース管理 |  

→ 例外は「予期できるエラー」に対して備える仕組み  
落ちないコード＝信頼されるコード  

※例外処理は「保険」ではなく「設計の一部」  
どこで何が起こり得るかを予測する力が問われる  

