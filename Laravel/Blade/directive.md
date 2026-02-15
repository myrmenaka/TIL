# よく使うBladeディレクティブ

## 1\. 条件分岐：@if / @elseif / @else / @endif

```blade
@if (条件)
    処理
@elseif (別の条件)
    処理
@else
    処理
@endif
```

### ◆ 実務での使いどころ

・ログインしているかどうか  
・データが存在するかどうか  
・権限チェック（管理者だけ表示など）  

例：ログインしているユーザー名を表示

```blade
@if (Auth::check())
    <p>{{ Auth::user()->name }} さん</p>
@endif
```

## 2\. ループ：@foreach / @endforeach

```blade
@foreach ($items as $item)
    {{ $item->name }}
@endforeach
```

### ◆ 実務での使いどころ

・一覧画面（index）  
・テーブル表示  
・セレクトボックスの選択肢  

例：ユーザー一覧

```blade
@foreach ($users as $user)
    <li>{{ $user->name }}</li>
@endforeach
```

## 3\. 空チェック：@forelse / @empty / @endforelse

```blade
@forelse ($items as $item)
    {{ $item->name }}
@empty
    データがありません
@endforelse
```

### ◆ 実務での使いどころ

・一覧画面で「0件」のときの表示  
・foreach より forelse の方が親切  
　→データがないとき @empty の内容が表示される  

## 4\. 変数表示 （サニタイズ）：{{ }} / {!! !!}

```blade
{{ $user->name }}   {{-- 安全に表示 --}}
{!! $html !!}       {{-- HTMLをそのまま表示（慎重に） --}}
```

### ◆ 実務での使いどころ

・画面に値を出すときは基本 `{{ }}`  
・`{!! !!}` はメール本文やCMSなどで使うことがある  

## 5\. レイアウト関連：@extends / @section / @yield

```blade
@extends('layouts.app')

@section('title', 'タイトル')

@section('content')
    メインコンテンツ
@endsection
```

### ◆ 実務での使いどころ

・全ページ共通のレイアウトを使う  
・ページごとにタイトルや中身を差し込む  

## 6\. include：@include（部分テンプレート）

```blade
@include('partials.header')
```

### ◆ 実務での使いどころ

・ヘッダー・フッター  
・小さな部品（検索フォームなど）  
・同じHTMLを何度も書きたくないとき  

## 7\. CSRF：@csrf（フォーム必須）

```blade
<form method="POST">
    @csrf
</form>
```

### ◆ 実務での使いどころ

・POST/PUT/PATCH/DELETE のフォームは必ず必要  
・Laravelが自動でトークンを埋め込む  

## 8\. メソッド偽装：@method（PUT / PATCH / DELETE）

```blade
<form method="POST">
    @csrf
    @method('PUT')
</form>
```

### ◆ 実務での使いどころ

・編集（update）  
・削除（destroy）  

HTMLフォームは GET/POST しか使えないため、  
Laravelはこの仕組みで PUT/PATCH/DELETE を実現している。

## 9\. old()（フォーム再表示）

```blade
<input type="text" name="title" value="{{ old('title') }}">
```

### ◆ 実務での使いどころ

・バリデーションエラー後に入力値を保持する  
・フォームのUX向上に必須  

## 10\. エラーメッセージ：@error

```blade
@error('title')
    <div class="error">{{ $message }}</div>
@enderror
```

### ◆ 実務での使いどころ

・バリデーションエラーを表示する  
・フォーム画面で必須  

## よく使うディレクティブまとめ

|種類|ディレクティブ|用途|
|:-:|:-:|:-:|
|条件|@if / @elseif / @else|ログイン・権限・存在チェック|
|ループ|@foreach / @forelse|一覧表示・選択肢|
|レイアウト|@extends / @section / @yield|ページ構造|
|部品|@include|共通パーツ|
|フォーム|@csrf / @method|セキュリティ・HTTPメソッド|
|フォーム補助|old() / @error|入力保持・エラー表示|
|表示|{{ }} / {!! !!}|変数表示|

## ディレクティブの“使い分けの基準”

Bladeのディレクティブは、  
「HTMLでできないことを補う」 ために存在している。

### HTMLでできない → Bladeで補う

|HTMLでできないこと|Bladeで補うディレクティブ|
|:-:|:-:|
|PUT / PATCH / DELETE を送れない|@method|
|CSRFトークンを自動で入れられない|@csrf|
|0件時の表示を自動で切り替えられない|@forelse|
|エラーメッセージを自動で出せない|@error|
|入力値を保持できない|old()|
|レイアウトを継承できない|@extends / @section / @yield|

### PHPで書くと長くなる → Bladeで短く書く

|PHPで書くと長い|Bladeで短く書ける|
|:-:|:-:|
|if / else|@if / @else|
|foreach|@foreach|
|include|@include|
|echo|{{ }}|



