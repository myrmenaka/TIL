# Rule クラス

バリデーションルールを“オブジェクトとして”書ける仕組み  

文字列ルールは  
・読みにくい  
・カンマ区切りでミスしやすい  
・条件を追加しづらい  
・複合ユニークが書きにくい  
という弱点がある。  
そこで登場するのが Rule クラス。  

## Rule クラスの役割
① ルールを“オブジェクト”として扱える  
→ 文字列より読みやすい  

② 条件を柔軟に追加できる  
→ where 条件、ignore、複合ユニークなど

③ IDE の補完が効く  
→ ミスが減る

④ 公式が推奨している  
→ 実務でも Rule クラス版が主流

## Rule クラスにまとめるべき項目

・条件が必要なもの  
・除外や where が必要なもの  
・複雑な指定が必要なもの  
・配列や複合キーで使うもの  
これらは Rule クラスにまとめるべき

① unique（更新時の除外が必要なとき）※最重要  
② unique（複合ユニーク）  
③ unique（ソフトデリート対応）  
④ exists（条件付き存在チェック）  
⑤ in / notIn（配列で管理したいとき）  
⑥ requiredIf / requiredUnless（条件付き必須）  
⑦ prohibitedIf / prohibitedUnless（条件付き禁止）  
⑧ password（パスワードポリシー）  
⑨ dimensions（画像サイズチェック）  

## Rule クラスの使い方（基本）
```php
Rule::Ruleクラスで定義したもの ...
```
### ■ use 宣言： 
※ FormRequest または Controller の上部に書く 
```php
use Illuminate\Validation\Rule;
```
### ■ unique（新規）  
```php
Rule::unique('users', 'email')
```
### ■ unique（更新時：自分を除外）  
```php
Rule::unique('users', 'email')->ignore($this->id)
```
意味：  
・users テーブル  
・email カラム  
・$this->id のレコードは除外して重複チェック  

### ■ exists  
```php
Rule::exists('categories', 'id')
```

## Rule クラスを使った実務テンプレ
```php
use Illuminate\Validation\Rule;

public function rules(): array
{
    return [
        'email' => [
            'required',
            'email',
            Rule::unique('users', 'email')->ignore($this->id),
        ],

        'category_id' => [
            'required',
            Rule::exists('categories', 'id'),
        ],
    ];
}
```

## ポイント
■ Rule クラスは「文字列ルールの上位互換」  
■ unique や exists を“読みやすく安全に”書ける  
■ ignore や where などの条件が簡単に追加できる  
■ 実務では Rule クラス版が圧倒的に使いやすい  


