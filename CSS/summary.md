# CSS 実務チェックリスト（総まとめ）

## 1. レイアウト（Flex / Grid）
### ✔ Flexbox
・`display: flex;` を使っている  
・`justify-content` と `align-items` が正しく使われている  
・`gap` を使って margin 調整を減らしている  
・不要な `position` や `float` を使っていない  

### ✔ Grid
・`grid-template-columns` が明確  
・`repeat()` や `fr` を使っている  
・レスポンシブは `auto-fit + minmax()`  
・Flex と Grid の役割が混ざっていない  

## 2. ボックスモデル（Spacing）
・余白は `4px / 8px / 16px / 24px / 32px` のスケールで統一  
・padding と margin の役割が明確  
・`box-sizing: border-box;` を使っている  
・margin の相殺（collapse）を理解している  

## 3. タイポグラフィ（フォント）
・`line-height: 1.5〜1.7`  
・フォントサイズは段階的スケール  
・見出しと本文のコントラストがある  
・文字色は `#333 / #666 / #999` のグレー階層  

## 4. 色（Color）
・メインカラー・アクセントカラー・グレーの3色構成  
・コントラスト比が十分  
・背景色と文字色の組み合わせが適切  
・色だけで情報を伝えていない（アクセシビリティ）  

## 5. 影（Shadow）と角丸（Radius）
・影は薄め（`rgba(0,0,0,0.1)`）  
・角丸は `4px / 8px / 12px` のスケール  
・影と角丸の使いすぎを避けている  

## 6. CSS 設計（BEM / Utility / コンポーネント）
### ✔ BEM
・`.block__element--modifier` の構造  
・セレクタが深すぎない（2階層まで）  
・id を使わない（class で統一）  

### ✔ Utility
・`.flex`, `.mt-4`, `.text-center` など小さなクラスを適度に使用  
・Utility と BEM が混ざりすぎていない  

### ✔ コンポーネント化
・`.card`, `.button`, `.form` など部品単位で分割  
・CSS ファイルが巨大化していない  

## 7. セレクタの最適化
・タグセレクタを乱用していない  
・`.header` `nav` `ul` `li` `a` のような深いセレクタを避ける  
・クラス名が意味的で分かりやすい  
・`!important` を使っていない（例外を除く）  

## 8. レスポンシブ（スマホ対応）
・`@media (max-width: 768px)` などで調整  
・Flex → column  
・Grid → 列数が自動調整  
・文字サイズ・余白が適切に縮む  

## 9. 禁止事項（レビューで止まるポイント）
・`position: absolute` で無理やり配置  
・margin だけで中央寄せ  
・セレクタが深すぎる  
・id でスタイルを当てる  
・影が濃すぎる  
・色が多すぎる  
・クラス名が意味不明（`.red-box`, `.big-div` など）  

## CSS 総まとめテンプレ
```css
/* レイアウト */
.container {
    display: flex;
    gap: 16px;
}

/* 余白 */
.section {
    margin-bottom: 32px;
    padding: 16px;
}

/* タイポグラフィ */
body {
    font-size: 16px;
    line-height: 1.6;
    color: #333;
}

/* カード（コンポーネント） */
.card {
    background: #fff;
    padding: 16px;
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}

/* BEM */
.card__title {
    font-size: 20px;
    margin-bottom: 8px;
}

.card--highlight {
    border: 2px solid #4da3ff;
}
```