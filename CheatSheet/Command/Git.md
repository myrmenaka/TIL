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
※ ここで、コミットID も確認可能  
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
### ■ ブランチ切り替え ※
```
git switch <名前>	
```
### ■ 作成＋切り替え ※
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
### ■ 変更を取り消す（ステージ前） ※
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
※ `HEAD` ：Gitが現在地として見ているコミット  
 → `HEAD~1` ：ひとつ前のコミット  
 → 数字が大きいほど過去  

### ■ 特定のコミット時点のファイルを取り出す ※
```
git restore --source <コミットID> ファイル名
```
## 差分確認
### ■ 変更内容を見る
```
git diff
```
### ■ ステージング済みの差分を見る
```
git diff --staged
```
### ■ 過去コミットとの比較
```
git diff <コミットID>
```
## コミット履歴
### ■ 通常のログ
```
git log
```
### ■ 1行で見やすいログ
```
git log --oneline
```
### ■ グラフ付き（ブランチ構造が見える）
```
git log --oneline --graph --decorate --all
```
※ `--decorate` 有 → ブランチ名・タグ名がコミットに表示される  
 → 無の場合は、コミットIDとメッセージのみ表示される（どのブランチがどこにあるかは表示されない）    
## ファイルの状態を戻す系
### ■ 変更を破棄（ステージ前）
```
git restore <ファイル>
```
### ■ add を取り消す
```
git restore --staged <ファイル>
```
### ■ コミットを取り消す（変更は残す）
```
git reset --soft HEAD~1
```
### ■ コミットを完全に取り消す（危険）
```
git reset --hard HEAD~1
```
## ブランチ操作の便利系
### ■ 今のブランチを確認
```
git branch --show-current
```
### ■ リモートのブランチ一覧
```
git branch -r
```
### ■ ローカル＋リモート全部
```
git branch -a
```
## リモート関連の便利系
### ■ origin の URL を変更
```
git remote set-url origin <新URL>
```
### ■ リモートの情報を詳しく見る
```
git remote show origin
```
## コミットの中身を見る
### ■ 特定コミットの内容を見る
```
git show <コミットID>
```
### ■ 特定ファイルだけ見る
```
git show <コミットID>:<ファイルパス>
```
## タグ（バージョン管理で使う）
### ■ タグ一覧
```
git tag
```
### ■ タグをつける
```
git tag v1.0.0
```
### ■ タグを push
```
git push origin v1.0.0
```



---
# 【memo】
## ※が付いてる箇所について
昔の `git checkout` が担当していたこと  
※ 現在は、非推奨  

### ブランチの切り替え
```
git checkout ブランチ名
```
→ 今の対応コマンド
```
git switch ブランチ名
```
### 新しいブランチを作って切り替え
```
git checkout -b 新ブランチ名
```
→ 今の対応コマンド
```
git switch -c 新ブランチ名
```
### ファイルの変更を破棄（復元）
```
git checkout -- ファイル名
```
→ 今の対応コマンド
```
git restore ファイル名
```
### 特定のコミット時点のファイルを取り出す
```
git checkout <コミットID> -- ファイル名
```
→ 今の対応コマンド
```
git restore --source <コミットID> ファイル名
```
## コミットIDとは  
 → Git はコミットを保存するときに、その内容から SHA-1 というハッシュ値を作る（40桁の16進数）  
 → Git にとって、`コミットID` ＝ `コミットそのもの`  

### ■ コミットID の確認方法
```
git log
```
### ■ 過去の状態に戻したいとき
```
git reset --hard <コミットID>
```
### ■ 過去のファイルだけ取り出したいとき
```
git restore --source <コミットID> ファイル名
```
### ■ 差分を見たいとき
```
git diff <コミットID> <別のID>
```
### ■ バグがいつ入ったか調べるとき
```
git bisect
```
※ 内部でコミットIDを使う  

## HEAD / HEAD~ / HEAD^ の図解
### ■ 直線的な履歴 の場合
※ HEAD ＝ 今のコミット
```
A ---- B ---- C ---- D
                   ↑
                 HEAD
```
このとき：
```
HEAD      = D
HEAD~1    = C
HEAD~2    = B
HEAD~3    = A

HEAD^     = C（親1）
```
※ 直線なので HEAD~1 と同じ  
### ■ マージコミットがある場合（HEAD^）
```
        E
       /
A ---- B ---- C ---- D
                   ↑
                 HEAD
```
D は マージコミット  
（親が2つある）

このとき：  
```
HEAD      = D
HEAD~1    = C（直線的に1つ前）
HEAD~2    = B（直線的に2つ前）

HEAD^     = C（親1：メイン側）
HEAD^2    = E（親2：マージされた側）
```
### ■ 図のまとめ
```
        E  ←── HEAD^2（親2）
       /
A ---- B ---- C ←── HEAD^（親1）
                   ↑
                 HEAD（今いるコミット）

HEAD~1 → C（直線的に1つ前）
HEAD~2 → B（直線的に2つ前）
```

### ポイントまとめ
| 書き方 | 意味 |  
|:--:|:--:|  
| HEAD | 今いるコミット |  
| HEAD~1 | 直線的に1つ前 |  
| HEAD~2 | 直線的に2つ前 |  
| HEAD^ | 親1（通常は main 側） |  
| HEAD^2 | 親2（マージされた側） |  

・`~` は 「まっすぐ後ろに戻る」  
・`^` は 「親を指定する」（マージ時に分岐する）  
・`HEAD~1` と `HEAD^` は 同じとは限らない  
・`HEAD^2` が使えるのは `マージコミットだけ`  
