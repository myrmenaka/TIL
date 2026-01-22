# RESTful7アクション × コントローラーメソッド

## 1. index（一覧）

### 🔹 よく使うメソッド（スロット＋意味）

```php
モデル名::where('絞り込みたい項目', '探したい値')
    → 条件に合うレコードだけを取り出す

モデル名::orderBy('並べたい項目', 'asc または desc')
    → 並び順を指定する

モデル名::paginate('1ページに表示したい件数')
    → ページネーション付きで取得する

view('ビュー名', compact('渡したい変数'))
    → ビューにデータを渡して表示する
```

### 🔹 使い方テンプレ

```php
public function index()
{
    $items = モデル名::where('絞り込みたい項目', '探したい値')
                     ->orderBy('並べたい項目', 'desc')
                     ->paginate(10);

    return view('リソースメイ.index', compact('items'));
}
```

## 2. show（詳細）

### 🔹 よく使うメソッド（スロット＋意味）

```php
モデル名::where('id', '探したいID')->firstOrFail()
    → 1件だけ取得。なければ404エラーを返す

view('ビュー名', compact('渡したい変数'))
    → ビューにデータを渡す
```

### 🔹 使い方テンプレ

```php
public function show($id)
{
    $item = モデル名::where('id', $id)->firstOrFail();

    return view('リソースメイ.show', compact('item'));
}
```

## 3. create（新規フォーム）

### 🔹 よく使うメソッド（スロット＋意味）

```php
view('ビュー名')
    → フォーム画面を表示するだけ（DB操作なし）
```

### 🔹 使い方テンプレ

```php
public function create()
{
    return view('リソースメイ.create');
}
```

## 4. store（新規保存）

### 🔹 よく使うメソッド（スロット＋意味）

```php
$request->validate(['カラム名' => 'バリデーションルール'])
    → 入力チェックを行う

$request->input('取り出したい項目名')
    → フォームから送られた値を取り出す

モデル名::create(['カラム名' => '保存したい値'])
    → 新しいレコードをDBに保存する

redirect()->route('リソースメイ.index')
    → 一覧ページへリダイレクトする
```

🔹 使い方テンプレ

```php
public function store(Request $request)
{
    $request->validate([
        'カラム名' => 'required',
    ]);

    モデル名::create([
        'カラム名' => $request->input('カラム名'),
    ]);

    return redirect()->route('リソースメイ.index');
}
```

## 5. edit（編集フォーム）

### 🔹 よく使うメソッド（スロット＋意味）

```php
モデル名::where('id', '探したいID')->firstOrFail()
    → 編集したい1件を取得する

view('ビュー名', compact('渡したい変数'))
    → 編集フォームにデータを渡す
```

### 🔹 使い方テンプレ

```php
public function edit($id)
{
    $item = モデル名::where('id', $id)->firstOrFail();

    return view('リソースメイ.edit', compact('item'));
}
```

## 6. update（更新）

### 🔹 よく使うメソッド（スロット＋意味）

```php
$request->validate(['カラム名' => 'バリデーションルール'])
    → 入力チェック

モデル名::where('id', '探したいID')->firstOrFail()
    → 更新対象を取得

$item->update(['カラム名' => '更新後の値'])
    → 既存レコードを更新する

redirect()->route('リソースメイ.index')
    → 一覧へ戻る
```

### 🔹 使い方テンプレ

```php
public function update(Request $request, $id)
{
    $request->validate([
        'カラム名' => 'required',
    ]);

    $item = モデル名::where('id', $id)->firstOrFail();

    $item->update([
        'カラム名' => $request->input('カラム名'),
    ]);

    return redirect()->route('リソースメイ.index');
}
```

## 7. destroy（削除）

### 🔹 よく使うメソッド（スロット＋意味）

```php
モデル名::where('id', '探したいID')->firstOrFail()
    → 削除対象を取得

$item->delete()
    → レコードを削除する

redirect()->route('リソースメイ.index')
    → 一覧へ戻る
```

### 🔹 使い方テンプレ

```php
public function destroy($id)
{
    $item = モデル名::where('id', $id)->firstOrFail();

    $item->delete();

    return redirect()->route('リソースメイ.index');
}
```

## 🔸 独自アクション（search）もスロット＋意味つき

### 🔹 よく使うメソッド（スロット＋意味）

```php
$request->query('パラメータ名')
    → URLの?keyword= の値を取得する

モデル名::when('条件', function($q) { ... })
    → 条件がある時だけ where を追加する

モデル名::where('絞り込みたい項目', '探したい値')->paginate('件数')
    → 絞り込み＋ページネーション
```
