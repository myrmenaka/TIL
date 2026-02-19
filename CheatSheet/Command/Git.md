# Gitコマンド

## “よく使う” セット
### ✔ 状況確認
```
git status
git remote -v
```
### ✔ 変更を GitHub に反映
```
git add .
git commit -m "update"
git push
```
### ✔ 初回だけ必要
```
git init
git branch -M main
git remote add origin <URL>
git push -u origin main
```
---
---
## 初期設定・プロジェクト開始
### ■ .git を作って Git プロジェクト化
```
git init
```
### ■ ブランチ名を main に変更
```	
git branch -M main	
```
### ■ GitHub と紐づけ
```
git remote add origin	
```
### ■ remote の確認
```
git remote -v	
```
## 変更の記録（コミット関連）
### ■ 変更状況を確認
```
git status	
```
### ■ 全部ステージング
```
git add .	
```
### ■ 特定ファイルだけステージング
```
git add <ファイル>	
```
### ■ コミット（-m は message）
```
git commit -m "メッセージ"	
```
### ■ コミット履歴を見る
```
git log	
```
## GitHub とのやり取り（push / pull）
### ■ GitHub に反映
```
git push	
```
### ■ 初回 push（紐づけも兼ねる）
```
git push -u origin main	
```
### ■ GitHub の最新を取得
```
git pull	
```
## ブランチ操作
### ■ ブランチ一覧
```
git branch	
```
### ■ 新しいブランチ作成
```
git branch <名前>	
```
### ■ ブランチ切り替え
```
git switch <名前>	
```
### ■ 作成＋切り替え
```
git switch -c <名前>	
```
### ■ ブランチを統合
```
git merge <ブランチ>	
```
## remote（紐づけ）関連
### ■ 紐づけ確認
```
git remote -v	
```
### ■ 紐づけ
```
git remote add origin	
```
### ■ 紐づけ解除
```
git remote remove origin	
```
### ■ URL の変更
```
git remote set-url origin	
```
## 取り消し・やり直し
### ■ 変更を取り消す（ステージ前）
```
git restore <ファイル>	
```
### ■ add を取り消す
```
git restore --staged <ファイル>	
```
### ■ すべての変更を破棄（危険）
```
git reset --hard	
```
### ■ 直前のコミットだけ取り消す（変更は残す）
```
git reset --soft HEAD~1	
```
