# 1画面テンプレ（index / create / edit の基本形）

Laravelの画面は、どのページもほぼ同じ構造 になる。

だから最初に「型」を作っておくと、どんな画面でも迷わず作れるようになる。

## 1画面テンプレ

```blade
{{-- resources/views/◯◯/index.blade.php --}}

@extends('layouts.app')   {{-- ① レイアウト継承 --}}

@section('title', 'ページタイトル')   {{-- ② タイトル --}}

@section('content')   {{-- ③ メインコンテンツ --}}
    <h1>ページタイトル</h1>

    {{-- ④ メイン処理（一覧・フォームなど） --}}
@endsection
```

## 解説

### ① `@extends('layouts.app')`

・レイアウト（外側の箱）を使う宣言  
・これを書くことで、ヘッダー・フッター・CSS/JS が自動で付く  

### ② `@section('title', 'ページタイトル')`

・`<title>` に入る部分  
・1行で書けるショート版  
・ページごとに変わるのでここで指定  

### ③ `@section('content')`

・レイアウトの `@yield('content')` の穴 を埋める部分  
・ページのメイン部分は全部ここに書く  

### ④ メイン処理

ページによって変わる部分。  

例：  

#### 一覧ページ（index）

```blade
@foreach ($users as $user)
    <p>{{ $user->name }}</p>
@endforeach
```

#### 作成ページ（create）

```blade
<form method="POST" action="{{ route('users.store') }}">
    @csrf
    <input type="text" name="name">
    <button type="submit">登録</button>
</form>
```

#### 編集ページ（edit）

```blade
<form method="POST" action="{{ route('users.update', $user) }}">
    @csrf
    @method('PUT')
    <input type="text" name="name" value="{{ old('name', $user->name) }}">
    <button type="submit">更新</button>
</form>
```

## Bladeの1画面テンプレは

「レイアウトの穴を埋めるための中身の型」  
つまり、

・レイアウト → 外側の箱  
・1画面テンプレ → 中身の型  
・`@yield` → 変更箇所の印  
・`@section` → 印に入れる中身  

### 実務での使い方（超シンプル）

新しい画面を作るときは、このテンプレをコピペして中身だけ変えるだけ。

