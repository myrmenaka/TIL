# ユーザー登録・ログイン機能実装（Breeze）
スターターキット（Breeze）を使用したユーザー認証  
## 各ファイルの役割
### ■ ディレクトリ構造（認証に関係する部分のみ）
```
app/
 └── Http/
      └── Controllers/
           └── Auth/
                ├── AuthenticatedSessionController.php
                ├── ConfirmablePasswordController.php
                ├── EmailVerificationNotificationController.php
                ├── EmailVerificationPromptController.php
                ├── NewPasswordController.php
                ├── PasswordResetLinkController.php
                ├── RegisteredUserController.php
                └── VerifyEmailController.php

app/
 └── Models/
      └── User.php

routes/
 ├── web.php
 └── auth.php   ← Breeze が追加

resources/
 └── views/
      ├── auth/
      │    ├── login.blade.php
      │    ├── register.blade.php
      │    ├── forgot-password.blade.php
      │    ├── reset-password.blade.php
      │    ├── verify-email.blade.php
      │    └── confirm-password.blade.php
      ├── layouts/
      │    └── guest.blade.php
      └── dashboard.blade.php

database/
 └── migrations/
      └── 2014_10_12_000000_create_users_table.php
```
### ■ Controller（認証のロジック）
| ファイル | 役割 |  
|:--:|:--:|  
| RegisteredUserController | 新規登録（POST /register） |  
| AuthenticatedSessionController | ログイン・ログアウト |  
| PasswordResetLinkController | パスワードリセットメール送信 |  
| NewPasswordController	| 新しいパスワードの設定 |  
| VerifyEmailController | メール認証 |  
| ConfirmablePasswordController	| セキュリティ確認（重要操作前） |  

 → Breeze は これらを直接使う  
 → ログイン後の遷移先変更などはここを編集する

### ■ View（画面）
| ファイル | 役割 |  
|:--:|:--:|  
| login.blade.php | ログイン画面 |  
| register.blade.php | 新規登録画面 |  
| forgot-password.blade.php | パスワードリセットメール送信画面 |  
| reset-password.blade.php | 新しいパスワード入力画面 |  
| verify-email.blade.php | メール認証待ち画面 |  
| guest.blade.php | ログイン前の共通レイアウト |  

### ■ Route（URL と Controller の紐付け）
`routes/web.php`  
→ アプリ本体のルート  
→ 認証必須ページの設定（auth ミドルウェア）はここで行う  

`routes/auth.php`  
→ Breeze が追加した認証ルート  
→ 基本編集しない  

### ■ Migration（DB構造）
`create_users_table.php`  
 → users テーブル（name, email, password, email_verified_at など）を作る  
 → 登録項目を増やすときに編集

## Breeze 認証の「流れ」図解
### ■ ログイン
```
[ユーザー]
   ↓ GET /login
[login.blade.php]
   ↓ POST /login
[AuthenticatedSessionController@store]
   ↓ Fortify が認証
   ↓ セッション保存
   ↓ リダイレクト（デフォルトは /dashboard）
```
### ■ 新規登録
```
[ユーザー]
   ↓ GET /register
[register.blade.php]
   ↓ POST /register
[RegisteredUserController@store]
   ↓ Userモデルで保存
   ↓ ログイン状態にする
   ↓ メール認証が有効なら verify-email へ
```

## 内部の仕組み
```
Breeze = 画面（Blade） + 認証ルート
Fortify = 認証の本体（ログイン・登録・パスワードリセット）
```

## Breeze インストール手順
### ■ Breeze をインストール
```
composer require laravel/breeze --dev
```
### ■ Breeze セットアップ（Blade版）
```
php artisan breeze:install blade
```
### ※ Node.js 未インストールの場合
#### NodeSource のセットアップスクリプト実行（v:18）
```
curl -fsSL https://rpm.nodesource.com/setup_18.x | bash -
```
#### Node.jsのインストール
```
dnf install -y node.js
```
#### バージョン確認
```
node -v
npm -v
```
### ■ npm をビルド
```
npm install
```
Breeze のフロント依存を入れる
```
npm run dev
```
CSS/JS をビルド
### ■ マイグレーション実行
```
php artisan migrate
```  

## インストール後
### ■ Breeze のログイン画面が動くか確認
```
/login
/register
```
 → 画面が表示されるか確認  










## 編集箇所ロードマップ

### ■ 画面（View）を編集
```
resources/views/auth/
```
内容：  
・ログイン画面のデザイン変更（login.blade.php）  
・登録画面の項目追加（register.blade.php）  
・ラベル名変更  
・Tailwind/Bootstrap のクラス調整  
・メール認証画面の文言変更  

ファイル：  
```
login.blade.php
register.blade.php
forgot-password.blade.php
reset-password.blade.php
verify-email.blade.php
confirm-password.blade.php
layouts/guest.blade.php
```
### ■ ユーザー登録項目を増やしたいとき
#### ✔ DB（マイグレーション）
```
database/migrations/2014_10_12_000000_create_users_table.php
```
#### ✔ User モデル
```
app/Models/User.php
```
・$fillable に追加  
・キャストやリレーションもここ  

#### ✔ 登録処理（Controller）
```
app/Http/Controllers/Auth/RegisteredUserController.php
```
・バリデーション追加  
・保存処理追加  

### ■ ログイン方法を変えたいとき（email → login_id など）
#### ✔ ログイン画面
```
resources/views/auth/login.blade.php
```
#### ✔ ログイン処理
```
app/Http/Controllers/Auth/AuthenticatedSessionController.php
```
※ Breeze Blade版では FortifyServiceProvider は存在しないので不要  
※ Fortify::authenticateUsing() も不要
→ ここが Jetstream との最大の違い  

### ■ ログイン後のリダイレクト先を変えたいとき
#### ✔ ログイン後
```
app/Http/Controllers/Auth/AuthenticatedSessionController.php
```
#### ✔ 登録後
```
app/Http/Controllers/Auth/RegisteredUserController.php
```
#### ✔ ルートの優先順位
```
routes/web.php
```
### ■ 認証必須ページを設定したいとき（ミドルウェア）
#### ✔ web.php（ここが本体）
```
routes/web.php
```
例：  
```php
Route::middleware('auth')->group(function () {
    // ログイン必須ページ
});
```
※ auth.php は 認証ルート専用 なので編集しない
（ログイン/登録/パスワードリセットの URL が入っているだけ）  

### ■ メール認証・パスワードリセットの文面を変えたいとき
```
resources/views/auth/verify-email.blade.php
resources/views/auth/forgot-password.blade.php
resources/views/auth/reset-password.blade.php
```
※ メール本文そのものを変える場合は通知クラスを作る  
（Breeze ではデフォルトのままでもOK）

### ■ 編集しないファイル（Breeze Blade版では不要）
#### ❌ Jetstream/Fortify直編集のファイル
```
app/Providers/FortifyServiceProvider.php
```
#### ❌ Fortify::authenticateUsing()
→ Breeze Blade版では使わない

#### ❌ auth.php にミドルウェア追加
→ Breeze では web.php でやる

#### ❌ Jetstream の機能（2FA、APIトークンなど）
→ Breeze には存在しない
