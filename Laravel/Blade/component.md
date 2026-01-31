# Bladeコンポーネント

「何度も使う小さな部品を、1つのファイルにまとめて再利用する仕組み」  
画面が綺麗に保てる。

## Bladeコンポーネント（再利用部品）の全体像

Bladeコンポーネントは、React のコンポーネントに近い“部品化”の仕組み

・ボタン  
・アラート  
・モーダル  
・入力フォームの一部  
・カードUI  
・ページネーション  

こういう 「何度も出てくるUI」 を1つにまとめる。

## スロット式テンプレ（最小のコンポーネント）

### ◆ ① コンポーネントファイル（部品）

#### resources/views/components/alert.blade.php

```blade

<div class="alert alert-success">
    {{ $slot }}
</div>

```

### ◆ ② 呼び出し側（子ビュー）

```blade

<x-alert>
&nbsp;   登録が完了しました
</x-alert>

```

## 解説

### ✔ コンポーネントは「タグ化」できるのが最大の強み

```blade

<x-alert>メッセージ</x-alert>

```

このように HTMLタグのように使える のが Bladeコンポーネントの魅力  
・include より読みやすい  
・include より引数が扱いやすい  
・include より構造が綺麗  

## もう少し実務寄りの例：成功メッセージ

### ◆ ① コンポーネント側

#### resources/views/components/flash.blade.php

```blade

@if (session($type))
    <div class="alert alert-{{ $type }}">
        {{ session($type) }}
    </div>
@endif

```

### ◆ ② 呼び出し側

```blade

<x-flash type="success" />

<x-flash type="error" />

```

## コンポーネントの“本質”

✔ 「何度も使うUI」を1つにまとめる  
✔ 呼び出し側はタグ1行で済む  
✔ コードが綺麗になる  
✔ 修正が1箇所で済む（保守性UP）  

## さらに深掘り：コンポーネントの種類

Bladeコンポーネントには2種類ある。  

### ◆ ① アノニマスコンポーネント（最も一般的）

#### resources/views/components/alert.blade.php

#### 呼び出し：

```blade

<x-alert>内容</x-alert>

```

#### 特徴：

・ファイルだけで完結  
・一番シンプル  
・実務で最も使われる  

### ◆ ② クラスベースコンポーネント（ロジックを含む）

#### コマンド：

php artisan make:component Alert  

・app/View/Components/Alert.php（ロジック）  
・resources/views/components/alert.blade.php（見た目）  

#### 特徴：

・複雑なロジックを含めたいときに使う  
・例えば「ログインユーザーの権限で表示を変える」など  

## include と components の違い

include : Bladeファイルの中に、別のBladeファイルをそのまま挿入する仕組み

```blade

@include('partials.header')

```

|目的|include|component|
|:-:|:-:|:-:|
|小さなHTMLを挿入|◎|◎|
|引数を渡す|△（配列で渡す）|◎（属性で渡せる）|
|タグのように使える|×|◎|
|再利用性|○|◎|
|Laravelの推奨|△|◎|

#### 結論：

・include → “ただの貼り付け”  
・component → “部品として扱える”  

Laravelの現場では components が主流  

## 理解すべき“本質まとめ”

✔ コンポーネントは「再利用できる小さな部品」  
✔ 呼び出しは `<x-◯◯>` のタグ形式  
✔ include より綺麗で保守性が高い  
✔ 実務ではアラート・ボタン・フォーム部品などで多用  




