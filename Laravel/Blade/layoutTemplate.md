# レイアウトテンプレ(layouts/app.blade.php)

## レイアウト（layouts/app.blade.php）とは？

全ページ共通の“枠組み”を作るファイル。

・ヘッダー  
・フッター  
・共通CSS/JS  
・共通のレイアウト構造（main / sidebar など）  

これを1つにまとめておくことで、各ページ（index, create, edit）は中身だけ書けばよくなる。

## レイアウトの全体像



```blade

{{-- resources/views/layouts/app.blade.php --}}

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">

    {{-- ① ページタイトル --}}
    <title>@yield('title')</title>

    {{-- ② 共通CSS --}}
    <link rel="stylesheet" href="/css/app.css">

</head>
<body>

    {{-- ③ 共通ヘッダー --}}
    @include('partials.header')

    <main class="container">

    {{-- ④ 各ページのメインコンテンツ --}}
    @yield('content')

    </main>

    {{-- ⑤ 共通フッター --}}
    @include('partials.footer')

    {{-- ⑥ 共通JS --}}
    <script src="/js/app.js"></script>

</body>
</html>

```

## 解説

### ① <title>@yield('title')</title>

・各ページが `@section('title', 'ページ名')` で差し込む場所  
・ページごとにタイトルを変えられる  

### ② 共通CSS

・全ページで使うCSSを読み込む  
・Laravel Mix / Vite を使う場合は別の書き方になるけど、まずはこれでOK  

### ③ @include('partials.header')

・共通ヘッダーを読み込む  
・resources/views/partials/header.blade.php を参照  

### ④ @yield('content')

・各ページのメイン部分が入る“穴”  
・子ビューが `@section('content')` で埋める  

### ⑤ @include('partials.footer')

・共通フッターを読み込む  

### ⑥ 共通JS

・全ページで使うJavaScriptを読み込む  

## 子ビュー（ページ側）のテンプレ

レイアウトを使うページはこう書く  

```blade

{{-- resources/views/users/index.blade.php --}}

@extends('layouts.app')

@section('title', 'ユーザー一覧')

@section('content')

    <h1>ユーザー一覧</h1>

    @foreach ($users as $user)
        <p>{{ $user->name }}</p>
    @endforeach

@endsection

```

### MVCの流れがここでつながる

```
Controller → view('users.index')
                → layouts/app.blade.php を継承
                → @yield('content') に差し込まれる

```
この流れが理解できると、Bladeの全体像が一気にクリアになる。

