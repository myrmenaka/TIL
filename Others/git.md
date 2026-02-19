# Git まとめ

参考：[君には1時間でGitについて知ってもらう(with VSCode)](https://qiita.com/jesus_isao/items/63557eba36819faa4ad9)  
参考：[図解！ Gitのブランチ・ツリーをちゃんと読む](https://qiita.com/jesus_isao/items/2a0495c973a4c911c2cc)  
参考：[Git ブランチの命名規則](https://qiita.com/Hashimoto-Noriaki/items/5d990e21351b331d2aa1)  

---

## GitHub とローカルを紐づける手順まとめ（HTTPS 版）

### ① プロジェクトフォルダに移動する
```
cd プロジェクトのパス
```
※ プロジェクトフォルダが無い場合は新規で作成  
注意：日本語パスが入らない場所に作成する  
#### 例：
```
cd C:\Projects\sample-app
```
### ② Git 管理を開始する（.git を作る）
このフォルダを Git プロジェクトとして扱うための .git フォルダを作るコマンド  
※ `.git` の有無でGitプロジェクトか判別している
```
git init
```
「現在のブランチ名を main に強制変更する」コマンド  
※ GitHub に初回 push するときに必要になる “お作法” のひとつ
```
git branch -M main
```
※ `-M`：MoveのM、強制的な名前変更  

※ git init をすると、Git はデフォルトで master ブランチを作る  
 → 昔の Git は master が標準だったが、今は GitHub も含めて世界的に main が標準  

※ GitHubの新規リポジトリは `mainブランチを前提` にしているのでローカルが master のままだとエラーが起こるケースがある  

### ③ GitHub で空のリポジトリを作る
（README や .gitignore は作らない方が安全）

#### 例：test-app
※ プロジェクトフォルダ名とリポジトリ名は一致してなくてOK  

### ④ GitHub の HTTPS URL をコピーする
GitHub の作成したリポジトリの「Code」→「HTTPS」で表示される URL  
※ もしくは空の場合は、最初の画面に表示されている  

例：
```
https://github.com/username/repository.git
```
### ⑤ remote（紐づけ）を登録する
```
git remote add origin https://github.com/username/repository.git
```

### ⑥ ファイルを Git に登録してコミット
```
git add .
git commit -m "first commit"
```
`add` ＝ `ステージング`  
`commit -m` `"コミットのコメント"`

※ `.`はカレントディレクトリ  
※ `-m` → message の略  
 → `git commit` だけだと、エディタ（VSCode や Vim）が開いて
コミットメッセージを手動で書くモードになる

### ⑦ GitHub に push（初回）
```
git push -u origin main
```
※ 初回だけ GitHub のログイン or トークン入力が必要になることがある

これで紐づけ完了！

### ■ 以降の変更

```
git add .
git commit -m "update"
git push
```
`ステージング` → `コミット` → `プッシュ`  
GitHub に反映される

## 紐づけを解除したいとき
```
git remote remove origin
```
これで GitHub との接続が完全に消える  

## 補足：フォルダ名とリポジトリ名は一致してなくてOK
Git が見ているのは remote の URL  
```
git remote -v
```
ここに GitHub の URL が出ていれば、  
フォルダ名が違っていても問題なし  

## 補足：origin とは何か
※ remote や初回 push 時のコマンドに含まれていたもの  

### ■ `origin` = 「GitHub のリポジトリにつけたニックネーム」  

Git は URL をそのまま扱うと長いので  
短い名前（エイリアス）をつけて管理している  

そのデフォルト名（慣習）が origin  
※ 慣習で `origin` と付けているだけなので、変更は可能  

つまり
```
origin → https://github.com/username/repository.git
```
origin = 名前  
URL = 実際の住所（push 先）  

Git はこの「名前 → URL」の対応表を `.git/config` に保存している  


### ■ 例えば push のとき：  
```
git push origin main
```
これは実際には：
```
git push https://github.com/username/repository.git main
```
と同じ意味  

### ■ origin を確認するコマンド
```
git remote -v
```
出力例：
```
origin  https://github.com/username/repository.git (fetch)
origin  https://github.com/username/repository.git(push)
```
これを見て判断する：
「origin は repository に繋がってるんだな」  
「このローカルは repository のプロジェクトなんだな」  
