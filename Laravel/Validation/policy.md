# Policy ファイルの書き方

```php
<?php

namespace App\Policies;

use App\Models\User;
use App\Models\Post;

class PostPolicy
{
    /**
     * モデルの閲覧を許可するか
     */
    public function view(User $user, Post $post)
    {
        // ★ここに条件を書く
        return true;
    }

    /**
     * モデルの作成を許可するか
     */
    public function create(User $user)
    {
        // ★ここに条件を書く
        return true;
    }

    /**
     * モデルの更新を許可するか
     */
    public function update(User $user, Post $post)
    {
        // ★ここに条件を書く
        return $user->id === $post->user_id;
    }

    /**
     * モデルの削除を許可するか
     */
    public function delete(User $user, Post $post)
    {
        // ★ここに条件を書く
        return $user->id === $post->user_id;
    }
}
```

## 解説

### ■ 1. Policy は「モデル専用の認可ルールブック」
・Post に対する view / create / update / delete をまとめる場所  
・だから CRUD があるモデルには Policy が向いている  

### ■ 2. メソッド名は “決まり” がある
Laravel が自動で呼び出すメソッド名は決まっている。

| 操作 | Policy のメソッド名 |  
|:--:|:--:|  
| 閲覧 | view |  
| 一覧 | viewAny |  
| 作成 | create |  
| 更新 | update |  
| 削除 | delete |  
| 復元 | restore |  
| 強制削除 | forceDelete |  

→ authorize('update', $post) と書くと、  
Laravel が自動で PostPolicy::update() を呼ぶ。

## ■ 3. 引数は「User と モデル」
・$user はログイン中のユーザー  
・$post は対象のモデル  

### 例：

```php
public function update(User $user, Post $post)
{
    return $user->id === $post->user_id;
}
```
→ 「投稿者本人なら編集OK」という意味。

## ■ 4. return は true / false
・true → 許可  
・false → 拒否  

Laravel が自動で判断してくれる。

## 最小構成の“テンプレ”
```php
class PostPolicy
{
    public function update(User $user, Post $post)
    {
        // 投稿者本人だけ編集可能
        return $user->id === $post->user_id;
    }

    public function delete(User $user, Post $post)
    {
        // 投稿者本人だけ削除可能
        return $user->id === $post->user_id;
    }
}
```
これだけで十分実務で使える。

## 呼び出し方（連携）

### ■ コントローラ

```php
$this->authorize('update', $post);
```
### ■ Blade
```blade
@can('update', $post)
    <button>編集</button>
@endcan
```
→ Laravel が自動で Policy の update() を呼ぶ。

## まとめ
・Policy は「モデルごとの認可ルールを書くクラス」  
・メソッド名は Laravel が決めている  
・return true/false で許可・拒否  
・コントローラや Blade から authorize() / @can で呼び出す  
・AuthServiceProvider は自動登録されるので触らなくてOK  



