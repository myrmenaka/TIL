# CSS デザイン基礎（余白・フォント・色・影）
CSS のデザインは、4つの要素でほぼ決まる  

✔ 余白（spacing）  
✔ フォント（typography）  
✔ 色（color）  
✔ 影・角丸（shadow / radius）  

この4つを理解すると、  
どんな UI でも「整って見える」ようになる  

## 1. 余白（Spacing）＝デザインの 80%
デザインが「なんかダサい」と感じる原因のほとんどは `余白不足`  

### ■ 余白の黄金比：4 の倍数（4px グリッド）
実務では 4px / 8px / 12px / 16px / 24px / 32px のように  
`4 の倍数`で余白を作ると一気に整う
```
4px → 8px → 12px → 16px → 24px → 32px → 48px
```

### ■ よく使う余白パターン
・ボタンの内側：`padding: 8px 16px;`  
・セクション間：`margin-bottom: 32px;`  
・カードの内側：`padding: 16px;`  

## 2. フォント（Typography）
### ■ line-height は 1.5〜1.7 が基本
```css
body {
    line-height: 1.6;
}
```

### ■ フォントサイズのスケール
実務では `段階的なスケール` を使う
```
12px / 14px / 16px / 20px / 24px / 32px
```
・本文：16px  
・小さめ：14px  
・見出し：20〜32px  

## 3. 色（Color）
### ■ 色は 3色あれば十分
・メインカラー（ブランド色）  
・アクセントカラー（強調）  
・グレー（文字・枠線・背景）  

### ■ グレーの使い方が UI の質を決める
```
#333 → 文字  
#666 → サブ文字  
#999 → 補足  
#eee → 枠線  
#f7f7f7 → 背景
```
## 4. 影（Shadow）と角丸（Radius）
### ■ 影（box-shadow）
最も使われる影：  
```css
box-shadow: 0 2px 8px rgba(0,0,0,0.1);
```
・0 → 横方向  
・2px → 縦方向  
・8px → ぼかし  
・0.1 → 薄い影（濃い影はNG）  

### ■ 角丸（border-radius）
・小さめ：4px  
・標準：8px  
・カード：12px  
・pill（丸いボタン）：9999px  

## CSS デザインのテンプレ
```css
/* 余白 */
.section {
    margin-bottom: 32px;
    padding: 16px;
}

/* フォント */
body {
    font-size: 16px;
    line-height: 1.6;
}

/* 色 */
.text-muted {
    color: #666;
}

/* 影と角丸 */
.card {
    background: #fff;
    padding: 16px;
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}
```
