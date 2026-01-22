# RESTful7以外でよく使うメソッド（日本語スロット＋意味＋使い方テンプレ）

## 1. pluck（特定カラムだけ取り出す）

### 🔹 日本語スロット

```php
モデル名::pluck('取り出したいカラム名');
→ 指定したカラムだけを配列で取り出す
```

### 🔹 使い方テンプレ

```php
$names = Category::pluck('name');
```

## 2. find / findOrFail（IDで1件取得）

### 🔹 日本語スロット

```php
モデル名::find('ID');
→ IDで1件取得（見つからなくてもnull）

モデル名::findOrFail('ID');
→ 見つからなければ404エラー
```

### 🔹 使い方テンプレ

```php
$item = モデル名::findOrFail($id);
```


## 3. only / except（配列から必要な項目だけ取り出す）

### 🔹 日本語スロット

```php
$request->only(['必要な項目1', '必要な項目2']);
→ 指定した項目だけ取り出す

$request->except(['除外したい項目']);
→ 指定した項目以外を取り出す
```

### 🔹 使い方テンプレ

```php
$data = $request->only(['title', 'content']);
```

## 4. has / filled（入力チェック）

## 🔹 日本語スロット

```php
$request->has('項目名');
→ その項目が送られてきているか確認

$request->filled('項目名');
→ 空でない値が送られてきているか確認
```

### 🔹 使い方テンプレ

```php
if ($request->filled('keyword')) {
    // キーワードが入力されている時だけ処理
}
```

## 5. when（条件がある時だけ処理を追加）

### 🔹 日本語スロット

```php
モデル名::when('条件', function($q) {
    $q->where('絞り込みたい項目', '探したい値');
});
→ 条件がある時だけ where を追加する
```

### 🔹 使い方テンプレ

```php
$items = モデル名::when($request->filled('keyword'), function($q) use ($request) {
    $q->where('title', 'like', "%{$request->keyword}%");
})->get();
```

## 6. with（リレーションを一緒に取得）

### 🔹 日本語スロット

```php
モデル名::with('リレーション名')->get();
→ 関連テーブルもまとめて取得する
```

### 🔹 使い方テンプレ

```php
$posts = Post::with('user')->get();
```

## 7. select（取得するカラムを限定）

### 🔹 日本語スロット

```php
モデル名::select(['カラム1', 'カラム2'])->get();
→ 必要なカラムだけ取得する
```

### 🔹 使い方テンプレ

```php
$items = モデル名::select(['id', 'title'])->get();
```

## 8. count / exists（件数や存在チェック）

### 🔹 日本語スロット

```php
モデル名::where('条件')->count();
→ 条件に合う件数を数える

モデル名::where('条件')->exists();
→ 条件に合うレコードが存在するか確認
```

### 🔹 使い方テンプレ

```php
if (User::where('email', $email)->exists()) {
    // すでに登録されている
}
```

## 9. response()->json（JSON返却）

### 🔹 日本語スロット

```php
response()->json(['キー' => '値']);
→ JSON形式で返す（API用）
```

### 🔹 使い方テンプレ

```php
return response()->json(['status' => 'ok']);
```

## 10. abort（強制的にエラーを返す）

### 🔹 日本語スロット

```php
abort(403);
→ アクセス禁止エラーを返す

abort(404);
→ ページが見つからないエラーを返す
```

### 🔹 使い方テンプレ

```php
if (!$user->isAdmin()) {
    abort(403);
}
```

# 後で必要になるかも…

## 🔸 groupBy（集計）

```php
モデル名::groupBy('項目')->get();
```

## 🔸 having（集計後の絞り込み）

```php
->having('count', '>', 5)
```

## 🔸 join / leftJoin（テーブル結合）

```php
->join('テーブル名', '結合条件', '=', '結合条件')
```

## 🔸 withCount（リレーションの件数を取得）

```php
モデル名::withCount('リレーション名')->get();
```

## 🔸 scopeXxx（モデル側の検索ロジックまとめ）

```php
public function scopePublished($q) { ... }
```

---
---

# MEMO
- all(): 
```php
モデル名::all();
→ テーブルの全レコードを取得する（絞り込みなし）
```
 all() とよく比較されるメソッド

| メソッド | 意味 | 使いどころ |  
|:--:|:--:|:--:|  
| all() | 全件取得 | 小規模データ、マスタ |  
| get() | 条件付きで取得 | whereとセット |  
| paginate() | ページ分割 | 一覧ページ |  
| pluck() | 特定カラムだけ取得 | セレクトボックス |  

※ `all()` は基本的に使わない。使うのは「小規模データ」や「セレクトボックス」など例外的な場面だけ。
一覧ページでは paginate() が正解。













