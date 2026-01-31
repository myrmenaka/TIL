# リソースコントローラーの構造

1つのモデルに対する CRUD を、決まった7つのメソッドに分けて整理するためのコントローラー構造

## 7つのメソッドと HTTP・URL・役割
### 1. index（一覧）
・HTTP：GET  
・URL：/users  
・役割：レコードの一覧表示（＋検索・ソート・ページネーション）

```php
public function index()
{
    $users = User::query()
        ->latest()
        ->paginate(20);

    return view('users.index', compact('users'));
}
```
### ポイント：
・一覧画面の“入口”  
・検索条件やソート条件をここで受け取ることが多い  
・API なら JSON を返すだけになる  

## 2. create（新規作成フォーム）
・HTTP：GET  
・URL：/users/create  
・役割：新規作成フォームの表示  

```php
public function create()
{
    // マスターデータがあればここで取得
    // $roles = Role::all();

    return view('users.create');
}
```

### ポイント：
・DB への保存はしない、“フォームを見せるだけ”  
・セレクトボックス用のマスターデータを渡すのはここ  

## 3. store（新規登録処理）
・HTTP：POST  
・URL：/users  
・役割：フォーム送信されたデータを保存する  

```php
public function store(StoreUserRequest $request)
{
    $data = $request->validated();   // validated() がここで効く

    User::create($data);

    return redirect()->route('users.index')
        ->with('success', 'ユーザーを登録しました。');
}
```

### ポイント：
・FormRequest を使うのが実務標準  
・`$request->validated()` で「安全なデータだけ」を取り出す  
・成功後はリダイレクト＋フラッシュメッセージが定番  

## 4. show（詳細表示）
・HTTP：GET  
・URL：/users/{user}  
・役割：1件分の詳細表示  

```php
public function show(User $user)
{
    return view('users.show', compact('user'));
}
```

### ポイント：
・ルートモデルバインディングで $user が自動解決される  
（/users/5 → id=5 の User が入る）  
・閲覧権限のチェックをここでやることも多い  （Policy など）  

## 5. edit（編集フォーム）
・HTTP：GET  
・URL：/users/{user}/edit  
・役割：既存データの編集フォーム表示  

```php
public function edit(User $user)
{
    return view('users.edit', compact('user'));
}
```

### ポイント：
・show と似ているが、「編集用フォーム」を出す  
・セレクトボックス用のマスターデータもここで渡す  

## 6. update（更新処理）
・HTTP：PUT / PATCH  
・URL：/users/{user}  
・役割：編集フォームから送信されたデータで更新する  

```php
public function update(UpdateUserRequest $request, User $user)
{
    $data = $request->validated();

    $user->update($data);

    return redirect()->route('users.index')
        ->with('success', 'ユーザー情報を更新しました。');
}
```

### ポイント：
・store と対になるメソッド  
・unique のときは「自分を除外する」Rule が必要になる（ignore($user->id)）  
・FormRequest を create 用と update 用で分けるのがよくあるパターン  

## 7. destroy（削除）
・HTTP：DELETE  
・URL：/users/{user}  
・役割：レコードの削除（物理削除 or 論理削除）  

```php
public function destroy(User $user)
{
    $user->delete();  // SoftDeletes なら論理削除

    return redirect()->route('users.index')
        ->with('success', 'ユーザーを削除しました。');
}
```

### ポイント：
・SoftDeletes を使っているなら論理削除になる  
・認可（このユーザーを削除していいか？）は Policy や authorize でチェック  

## Route::resource とルート名
```
Route::resource('users',UserController::class);
```
これ1行で、さっきの7メソッドに対応するルートが全部生える。

| メソッド | HTTP | URL | ルート名 |  
|:--:|:--:|:--:|:--:|  
| index | GET | /users | users.index |  
| create | GET | /users/create | users.create |  
| store | POST | /users | users.store |  
| show | GET | /users/{user} | users.show |  
| edit | GET | /users/{user}/edit | users.edit |  
| update | PUT/PATCH | /users/{user} | users.update |  
| destroy | DELETE | /users/{user} | users.destroy |  

### ビュー側でのリンク例：

```blade
<a href="{{ route('users.index') }}">一覧</a>
<a href="{{ route('users.create') }}">新規作成</a>
<a href="{{ route('users.edit', $user) }}">編集</a>

<form method="POST" action="{{ route('users.destroy', $user) }}">
    @csrf
    @method('DELETE')
    <button type="submit">削除</button>
</form>
```

### ルートモデルバインディングとの関係
```php
public function show(User $user)
```
この書き方をすると：  
・URL の {user} 部分（例：/users/5）を見て  
・自動的に User::findOrFail(5) してくれる  

### メリット：
・$id を受け取って User::findOrFail($id) と書かなくていい  
・型でモデルが分かるので読みやすい  
・404 の処理も Laravel が勝手にやってくれる  

## FormRequest とリソースコントローラーの組み合わせ（実務テンプレ）

### StoreUserRequest（新規用）
```php
class StoreUserRequest extends FormRequest
{
    public function authorize(): bool
    {
        return true; // or 認可ロジック
    }

    public function rules(): array
    {
        return [
            'name'  => ['required', 'string', 'max:50'],
            'email' => ['required', 'email', 'unique:users,email'],
        ];
    }
}
```

## UpdateUserRequest（更新用）
```php
class UpdateUserRequest extends FormRequest
{
    public function authorize(): bool
    {
        return true; // or 認可ロジック
    }

    public function rules(): array
    {
        return [
            'name'  => ['required', 'string', 'max:50'],
            'email' => [
                'required',
                'email',
                Rule::unique('users', 'email')->ignore($this->user->id),
            ],
        ];
    }
}
```
Controller 側
```php
public function store(StoreUserRequest $request)
{
    User::create($request->validated());
    return redirect()->route('users.index');
}

public function update(UpdateUserRequest $request, User $user)
{
    $user->update($request->validated());
    return redirect()->route('users.index');
}
```

### ここまで来ると：
・ルーティング  
・コントローラーの役割分担  
・バリデーション  
・認可（authorize）  
・モデルバインディング  

が全部きれいに噛み合う。

## まとめ
・リソースコントローラー＝CRUD を7メソッドに分解した“型”  
・index/create/edit/show → 画面表示系  
・store/update/destroy → データ変更系（＋バリデーション・認可）  
・Route::resource でルートと名前が一括定義される  
・モデルバインディングで $id → User $user にできる  
・store/update は FormRequest＋validated() をセットで使うのが実務標準  




