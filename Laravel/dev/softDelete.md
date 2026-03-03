# 論理削除（soft delete）対応
Laravel の論理削除は モデルに trait を追加するだけで動く仕組み

## 論理削除はどこに必要か
### ■ リレーション（1対多）で親を削除したら子も削除したい
 → 親のみ SoftDeletes を付ける  

・親が deleted_at になる  
・通常の 子のリレーションは 親 が生きているものだけを辿る  
・結果として子も自然に非表示になる  
・子側は物理削除でも論理削除でもどちらでも動く  

### ■ 親は残したまま子だけ論理削除したい
 → 子にだけ SoftDeletes を付ける  

・荒らしコメントだけ消したい  
・スレッドは残す  
・コメント一覧で whereNull('deleted_at') が自動で付くので自然に非表示になる  

### ■ 親も子も両方論理削除したい
 → 両方に SoftDeletes を付ける  

・親削除 → deleted_at が入る  
・子削除 → deleted_at が入る  
・管理画面で復元機能を作る場合に便利  
・掲示板の履歴管理を重視する場合に向いている  

### ※ あくまで親を論理削除したとき、子は `非表示` になるだけ
「親削除時に、子も論理削除したい」なら  

 → 親コントローラーの `destroy` に追加する

```php
public function destroy($id)
{
    $thread = Thread::findOrFail($id);

    // 子コメントも論理削除
    $thread->comments()->delete();

    // スレッドを論理削除
    $thread->delete();

    return redirect()->route('threads.index');
}
```

## 1. モデルに SoftDeletes を追加する
```php
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class UserRegist extends Model
{
    use SoftDeletes;

    protected $dates = ['deleted_at'];
}
```

## 2. テーブルに deleted_at カラムを追加する
マイグレーションを作成：
```bash
php artisan make:migration add_deleted_at_to_user_regist_table --table=user_regist
```
中身：  
```php
public function up()
{
    Schema::table('user_regist', function (Blueprint $table) {
        $table->softDeletes(); // deleted_at を追加
    });
}

public function down()
{
    Schema::table('user_regist', function (Blueprint $table) {
        $table->dropSoftDeletes();
    });
}
```
`deleted_at` カラムはソフトデリートに必要なフィールド  

実行：  
```bash
php artisan migrate
```

## 3. 削除処理はそのまま delete() でOK
通常の`delete()`メソッドを使うと、ソフトデリートが有効な場合は`deleted_at`が更新されるだけで、レコードはDBから消えない  

いまの削除処理：  
```php
row.delete();
```
または  
```php
UserRegist::where('id', $id)->delete();
```
これは 物理削除 → 論理削除に自動で切り替わる

## 4. 一覧画面は自動で「削除済みを除外」する
SoftDeletes を使うと、通常のクエリは自動で deleted_at IS NULL が付く
```php
UserRegist::all(); // 削除されていないデータだけ取得
```

## 5. 削除済みも含めて取得したい場合
デフォルトでは、ソフトデリートされたレコードはクエリ結果から除外される  
削除済みレコードも含めて取得したい場合は、`withTrashed()` を使う
```php
UserRegist::withTrashed()->get();
```

## 6. 削除済みだけ取得したい場合
削除されたレコードだけを取得するには、`onlyTrashed()` を使用
```php
UserRegist::onlyTrashed()->get();
```

## 7. 論理削除したデータを復元したい場合
論理削除されたレコードは `restore()` メソッドで復元できる  
※ restore() はリソースコントローラー（index / create / store / show / edit / update / destroy）には含まれていないので、追加のアクションとして自分で定義する  
 → route の追加も忘れずに  
```php
UserRegist::withTrashed()->find($id)->restore();
```

## 8. 完全に物理削除したい（物理削除）場合（管理画面などで）
データベースから完全に削除するには、forceDelete()を使う
```php
UserRegist::withTrashed()->find($id)->forceDelete();
```

## ポイント
### ■ 削除確認画面の UI はそのままでOK  
→ 物理削除か論理削除かはバックエンドの挙動だけ変わるので、画面は変えなくていい

### ■ 削除結果画面もそのまま使える  
→ 「削除しました」と表示するだけで問題なし

### ■ カテゴリ削除との整合性も保ちやすい  
→ 論理削除にすると、カテゴリ削除時の category_id = null 処理も安全に動く

## 論理削除に対応することで追加できる機能
・削除済みデータの一覧ページを作る（管理者向け）  
・復元ボタンを追加する  
・完全削除（forceDelete）を管理者だけに許可する  
・削除日時を UI に表示する  

## 復元（例）
※ リレーション関係の Thread（親） と Comment（子） の両方に SoftDeletes を付けている

Thread を復元
```php
$thread = Thread::withTrashed()->find($id);
$thread->restore();
```
子コメントも復元したい場合
```php
$thread->comments()->withTrashed()->restore();
```
### ■ 復元機能を付けるときの UI の考え方
・Thread 一覧に「削除済み一覧」ページを作る  
・Thread 詳細に「削除済みコメント一覧」を作る  
・削除済みのものには「復元」ボタンを表示  
・完全削除（forceDelete）は管理者だけにするのが一般的  
