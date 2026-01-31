# コントローラーのスリム化まとめ

## 1. FormRequest を使ってバリデーションを外に出す

### 🔹 書式
```php
$request->validate(['項目' => 'ルール']);  
```
→ コントローラーに書くと太るので、  
FormRequest に移動する

### 🔹 スリム化後の書き方
```php
public function store(リクエストクラス $request)
{
    モデル名::create($request->validated());
    return redirect()->route('リソースメイ.index');
}
```
### 🔹 効果
・コントローラーからバリデーションが消えてスッキリ  
・ルールの再利用ができる  
・テストしやすい  

## 2. モデルにロジックを寄せる（Fat Model / Thin Controller）

### 🔹 書式
```php
モデル名::where('項目', '値')->get();
```
→ 何度も使う検索ロジックはモデルに移動する

### 🔹 スコープ化（おすすめ）
```php
// モデル側
public function scope公開($q)
{
    return $q->where('status', '公開');
}
```
#### コントローラー側
```php
$items = モデル名::公開()->paginate(10);
```
### 🔹 効果
・コントローラーが「処理の流れ」だけになる  
・検索条件の重複がなくなる  
・モデルが“データの振る舞い”を持つようになる  

## 3. サービスクラスにビジネスロジックを移動する

「保存前に何か計算する」「外部APIを叩く」などの処理は  
サービスクラスに移動する  

### 🔹 スリム化後の書き方
```php
public function store(Request $request, PostService $service)
{
    $service->保存処理($request);
    return redirect()->route('posts.index');
}
```
### 🔹 効果
・コントローラーが“画面遷移だけ”を担当  
・複雑な処理を外に逃がせる  
・テストがしやすい  

## 4. リポジトリパターンで DB 操作を分離する（必要な場合）

DB操作を全部コントローラーに書くと太るので  
Repository に移動する

### 🔹 スリム化後の書き方
```php
public function index(PostRepository $repo)
{
    $items = $repo->一覧取得();
    return view('posts.index', compact('items'));
}
```
### 🔹 効果
・DB操作の変更に強くなる  
・コントローラーがさらに薄くなる  

## 5. ルートモデルバインディングを使う

### 書式
```php
モデル名::where('id', $id)->firstOrFail();
```
→ これを自動化する

### 🔹 スリム化後の書き方
```php
public function show(モデル名 $item)
{
    return view('posts.show', compact('item'));
}
```
### 🔹 効果
・where + firstOrFail が消える  
・コードが短くて読みやすい  

## 6. Resource Controller / apiResource を使う

7アクションを自動でルーティングしてくれる  
→ コントローラーの構造が統一される

### 🔹 ルート
```php
Route::resource('posts', PostController::class);
```

### 🔹 効果
・ルート定義が激減  
・コントローラーの構造が揃う  

## 7. 共通処理はミドルウェアへ

「ログイン必須」「管理者だけ」などの共通処理は  
コントローラーに書かずミドルウェアへ

### 🔹 スリム化後の書き方
```php
public function __construct()
{
    $this->middleware('auth');
}
```
### 🔹 効果
・コントローラーから認可処理が消える  
・役割が明確になる

## まとめ

### コントローラーのスリム化は
① バリデーションを外に出す（FormRequest）  
② 検索ロジックをモデルに寄せる（スコープ）  
③ 複雑な処理はサービスへ  
④ DB操作はリポジトリへ（必要なら）  
⑤ ルートモデルバインディングで where を消す  
⑥ Resource Controller で構造を統一  
⑦ 認可はミドルウェアへ  

これを守ると、  
コントローラーは「画面遷移とデータの受け渡し」だけになる。



