# 基本制御構文

## 比較演算子

PHPと同じ  

## if文

PHPと同じ

## switch文
```js
switch(変数名) {
    case 値1:
        処理1;
        break;
    case 値2:
        処理2;
        break;
    default :
        処理;
        break;
}
```
・ラベルに指定可能な値は「定数」と「計算式」  
・意図しないbreakの記述忘れによるブレイクスルーに注意  
・fefaultの記述は省略可能  

## for文

PHPと同じ

## 配列
### new Array 使用
```js
let 配列名 = new Array();

// 要素数の指定
let 配列名 = new Array(要素数);

// 初期化
let 配列名 = new Array(値1, 値2, ...);
```

### 配列リテラル使用
```js
let 配列名 = [];

// 初期化
let 配列名 = [値1, 値2, ...];
```

### 要素の参照
```js
配列名[インデックス]

// 要素数の取得
配列名.length
```

