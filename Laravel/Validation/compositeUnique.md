# 複合ユニーク（複数カラムの組み合わせで重複禁止）

「A だけなら重複OK、B だけなら重複OK、でも A×B の組み合わせは重複NG」  
というチェック。

例えば：  
company_id が同じ会社の中では、email は重複してはいけない  
こういうケースが典型。

## 複合ユニークは Rule クラスでしか書けない

文字列ルール（unique:...）では複合ユニークは書けないので Rule クラス必須。

## 複合ユニークの基本形（Rule クラス）

```php
use Illuminate\Validation\Rule;

Rule::unique('users')
    ->where('company_id', $this->company_id)
```
意味：
・users テーブルで  
・company_id が $this->company_id のレコードの中で  
・重複していないこと

### 実務例①：会社ごとに email をユニークにしたい
```php
'email' => [
    'required',
    'email',
    Rule::unique('users')
        ->where('company_id', $this->company_id),
],
```
✔ こういう動きになる  
| company_id | email | 結果 |  
|:--:|:--:|:--:|  
| 1 | a@example.com | OK |  
| 1 | a@example.com | ❌ NG（同じ会社内で重複） |  
| 2 | a@example.com | OK（会社が違うのでOK） |  

### 実務例②：更新時に自分を除外する（複合ユニーク＋ignore）
```php
'email' => [
    'required',
    'email',
    Rule::unique('users')
        ->ignore($this->id)
        ->where('company_id', $this->company_id),
],
```
✔ ポイント
・ignore で「自分のレコードは除外」  
・where で「同じ会社内だけチェック」

## 実務例③：2つ以上のカラムを組み合わせる

例えば：  
year × month × user_id の組み合わせで重複禁止  

```php
Rule::unique('reports')
    ->where('year', $this->year)
    ->where('month', $this->month)
    ->where('user_id', $this->user_id)
```

## 複合ユニークの本質

■ unique は「1カラムだけ」では不十分なことがある  
■ 実務では「A×B の組み合わせでユニーク」がよくある  
■ 文字列ルールでは書けない → Rule クラス必須  
■ ignore と組み合わせるのが実務では当たり前  


# 実務で必須の応用パターン

## ① DB側の複合ユニーク（unique index）と合わせる
複合ユニークは DB側にも unique index を作るのが正解。

### 例：company_id × email をユニークにしたい
```sql
CREATE UNIQUE INDEX users_company_email_unique
ON users (company_id, email);
```
なぜ必要？
・バリデーションはアプリ側のチェック  
・unique index は DB側の最終防衛ライン  
両方あることで 競合状態（race condition） を防げる。

## ② ソフトデリート対応の複合ユニーク
Laravel の SoftDeletes を使っていると、
削除済みレコードが残るので unique が誤判定する。

### ✔ ソフトデリートを無視してユニークにしたい
```php
Rule::unique('users')
    ->where('company_id', $this->company_id)
    ->whereNull('deleted_at')
```
### ✔ 更新時は ignore も併用
```php
Rule::unique('users')
    ->ignore($this->id)
    ->where('company_id', $this->company_id)
    ->whereNull('deleted_at')
```

## ③ 配列（items.*.id）と複合ユニークの組み合わせ
例えば、  
「1つの注文（order）に同じ product_id を2回入れたくない」  
というケース。

### 入力例
```json
{
  "items": [
    { "product_id": 1, "quantity": 2 },
    { "product_id": 1, "quantity": 5 }
  ]
}
```
### ✔ バリデーションで弾きたい
```php
'items.*.product_id' => [
    'required',
    Rule::unique('order_items')
        ->where('order_id', $this->order_id),
],
```
これで  
order_id × product_id の組み合わせが重複しない  
というチェックができる。

## ④ 複合ユニークを“複数 where”で書くパターン
複合ユニークは where を何個でも追加できる。

### 例：year × month × user_id の組み合わせでユニーク
```php
Rule::unique('reports')
    ->where('year', $this->year)
    ->where('month', $this->month)
    ->where('user_id', $this->user_id)
```
## ⑤ 複合ユニークの更新時（ignore + 複数 where）
更新時は ignore を必ず入れる。

```php
Rule::unique('reports')
    ->ignore($this->id)
    ->where('year', $this->year)
    ->where('month', $this->month)
    ->where('user_id', $this->user_id)
```
## ⑥ 複合ユニークを“ルール配列の中で綺麗に書く”テンプレ
```php
use Illuminate\Validation\Rule;

public function rules(): array
{
    return [
        'email' => [
            'required',
            'email',
            Rule::unique('users')
                ->ignore($this->id)
                ->where('company_id', $this->company_id),
        ],
    ];
}
```
## ⑦ 複合ユニークの“よくある間違い”

### ❌ 文字列ルールで書こうとする
```php
'unique:users,email,company_id,' . $this->company_id
```
これは 複合ユニークにはならない。  
文字列版 unique は 3つ目と4つ目は「除外ID」の指定にしか使えないため


## 複合ユニークの最終テンプレ

### ■ 新規登録
```php
Rule::unique('users')
    ->where('company_id', $this->company_id)
```

### ■ 更新（自分を除外）
```php
Rule::unique('users')
    ->ignore($this->id)
    ->where('company_id', $this->company_id)
```
### ■ 3つ以上の複合ユニーク
```php
Rule::unique('reports')
    ->where('year', $this->year)
    ->where('month', $this->month)
    ->where('user_id', $this->user_id)
```
### ■ 3つ以上の複合ユニーク（除外ありvar）
```php
Rule::unique('reports')
    ->ignore($this->id)
    ->where('year', $this->year)
    ->where('month', $this->month)
    ->where('user_id', $this->user_id)
```
### ■ ソフトデリート対応
```php
Rule::unique('users')
    ->ignore($this->id)
    ->where('company_id', $this->company_id)
    ->whereNull('deleted_at')
```