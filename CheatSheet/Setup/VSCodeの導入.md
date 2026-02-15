# VSCodeの導入（Linux ＆ Windows）

## ■ Linux（RHEL / Rocky / Alma など）での VSCode 導入
### パッケージのダウンロード
```
$ wget -O vsc.rpm https://code.visualstudio.com/sha/download?build=stable&os=linux-rpm-x64
```
※ Microsoft 公式の RPM パッケージ  
※ このURLでは常に最新の安定版が落ちてくる  
## ダウンロード確認
```
$ ls -l vsc.rpm
```
## パッケージのインストール
```
# dnf -y install vsc.rpm
```
「アクティビティ」の「アプリケーションを表示する」からVSCodeをクリック
## VSCode の起動
### GUIから：
「アクティビティ」→「アプリケーションを表示」→「Visual Studio Code」  
### CLIから：  
```
$ code .
```
→ 現在のディレクトリを VSCode で開く  
## ◆VSCodeの設定

### 日本語化
1．左側の「拡張機能（Extensions）」を開く  
2．検索欄に `japa`  
3．`Japanese Language Pack for Visual Studio Code` をインストール  
4．「Change Language and Restart」で再起動

### Emmet のテンプレートの日本語設定
1．左下の「管理（歯車アイコン）」→「設定」  
2．検索欄に `emmet`  
3．`Emmet: Variables` の「項目の追加」  
4．`lang` の値を `ja` に変更  
5．保存  

### フォルダー設定
1．「ファイル」→「フォルダを開く」  
2．プロジェクトのディレクトリを選択  
3．「作成者を信頼します」にチェック  
4．「開く」  

## ■ Windows で VSCode のインストール
```
> winget install --id Microsoft.VisualStudioCode
```
### VSCode の実行ファイルの場所を確認
```
> where code
C:\Users\ユーザー名\AppData\Local\Programs\Microsoft VS Code\bin\code.cmd
```
※ `where code` はPATHに通っているか確認するコマンド  
## ■ WindowsでGitのインストール
```
> winget install --id Git.Git
```
### インストールできたか確認
```
> git --version
```


