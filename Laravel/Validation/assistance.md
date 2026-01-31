# バリデーション補助機能

## 1. prepareForValidation() と passedValidation() の使い分け

### ■ prepareForValidation()
バリデーションが実行される“前”に入力値を整形する処理を書く場所。

### 使う場面
・全角 → 半角変換  
・空文字 → null 変換  
・数値文字列 → int に変換  
・配列の整形  
・トリム（trim）  

### 例
```php
protected function prepareForValidation()
{
    $this->merge([
        'price' => $this->price !== '' ? (int)$this->price : null,
        'email' => trim($this->email),
    ]);
}
```

「バリデーション前に、入力を“正しい形”に整える場所」

## ■ passedValidation()
バリデーションが成功した“後”に実行される処理を書く場所。

### 使う場面
・validated() の値を元に追加加工したい  
・slug の生成  
・配列の再構築  
・モデル保存前の前処理  

### 例
```php
protected function passedValidation()
{
    $this->merge([
        'slug' => Str::slug($this->title),
    ]);
}
```

「バリデーション後に、保存前の加工を行う場所」

## ■ 書く場所（FormRequest）

### prepareForValidation()
```php
protected function prepareForValidation()
{
    $this->merge([
        'price' => $this->price !== '' ? (int)$this->price : null,
    ]);
}
```

### passedValidation()
```php
protected function passedValidation()
{
    $this->merge([
        'slug' => Str::slug($this->title),
    ]);
}
```

## 2. sometimes()（条件付きバリデーション）
FormRequest ではなく Controller 側で条件付きルールを追加したい時に使う。

### 例：type が premium のときだけ price を必須にしたい
```php
$request->validate([
    'type' => ['required'],
    'price' => ['integer'],
], [], [])
->sometimes('price', 'required', function ($input) {
    return $input->type === 'premium';
});
```

「条件が成立したときだけルールを追加する仕組み」  

※ FormRequest でも使えるけど、  
基本は Controller 側での柔軟な追加に使う。

## ■ 書く場所（Controller）
```php
$validator = Validator::make($request->all(), [
    'type' => ['required'],
    'price' => ['integer'],
]);

$validator->sometimes('price', 'required', function ($input) {
    return $input->type === 'premium';
});

$validated = $validator->validate();
```

## 3. bail（最初のエラーで止める）
通常、Laravel は 全ルールを評価してからエラーを返す。  
bail を使うと 最初のエラーで止める。

### 例
```php
'name' => ['bail', 'required', 'string', 'max:50'],
```

### 動き
・required でエラー → string や max は評価されない  
・エラーが1つだけ返るのでユーザーに優しい  

「不要なエラーを増やさず、最初のエラーだけ返す」  

## ■ 書く場所（FormRequestの rules() の中）
```php
'name' => ['bail', 'required', 'string', 'max:50'],
```
## ■ 書く場所（Controllerの validate() の中）
```php
$request->validate([
    'name' => 'bail|required|string|max:50',
]);
```

## 4. nullable と required の本当の違い（実務で最重要）

### required
値が必須。空文字・null・空配列はNG。

### nullable
null を許可する。  
ただし null 以外の値が来たら他のルールが適用される。

### 例：nullable + integer
```php
'age' => ['nullable', 'integer'],
```

### 動き：

| 入力 | 結果 |  
|:--:|:--:|  
| null | OK（nullable） |  
| "" | OK（空文字は null として扱われる） |  
| "20" | OK（integer として扱える） |  
| "abc" | NG（integer ではない） |  

nullable → null を許可するだけで「任意入力」ではない  
required → 値が必須  

## 5. フォーム入力と API 入力の違い（nullable の挙動が変わる）

### フォーム（HTML form）
空欄は 空文字 `""` で送られる。

→ nullable を付けないと
"" が string として扱われてエラーになることがある。

### API（JSON）
空欄は `null` で送られる。

→ nullable が無いと null が弾かれる。

### 例：フォームとAPIで挙動が変わる
```php
'price' => ['nullable', 'integer'],
```

### フォーム：
"" → null として扱われる → OK

### API：
null → OK

フォームは空文字、APIはnullが来る。
nullable はその差を吸収するために必要。

## まとめ
■ prepareForValidation()  
   バリデーション前の整形（空文字→null、trim、型変換）

■ passedValidation()
   バリデーション後の加工（slug生成など）

■ sometimes()
   条件が成立したときだけルールを追加する

■ bail
   最初のエラーで止める（ユーザーに優しい）

■ nullable の本質
   null を許可するだけで任意入力ではない
   フォームは空文字、APIはnullが来る