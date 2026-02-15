# DNSサーバーの構築
※ 今回は DNS コンテンツサーバも DNS キャッシュサーバも BIND（named-chroot） のみで構築
※ 一般的にはコンテンツサーバとキャッシュサーバは分けるが、学習目的で1台構成

## 構築の流れ
① BIND のインストール  
② 設定ファイルの編集  
③ ゾーンファイルの作成  
④ BIND（named-chroot）の起動・自動起動  
⑤ ファイアウォール設定  
⑥ 名前解決の確認  

## ①BINDパッケージとbind-chrootパッケージのインストール

### ■ インストール済みパッケージ確認 
```
$ dnf list installed | grep bind
```
※3つ出てくる（ライブラリ、ライセンス）が、いずれも新しくインストールするものとはほぼ関係なし  
※ `bind-utils` が無い環境もある（dig/nslookup を使う場合は必要）  

※ ここから root 権限で作業  

### ■ BINDパッケージとbind-chrootパッケージのインストール
```
# dnf install bind bind-chroot
```
一度y

### ■ インストール確認
```
# dnf list installed | grep bind
```

## ②設定ファイル（/etc/named.conf）の編集
✔ /etc/named.conf の編集  
✔ ゾーン定義ファイルの新規作成 /var/named/named.empty←テンプレートコピーして使う  
※ named-chroot を使用していても、授業環境では /etc/named.conf を編集する方式で統一されているため、この場所で問題なし

### ■ /etc/named.conf 設定ファイルの確認
```
# cat /etc/named.conf | less
```
### ■ 編集前にバックアップ
```
# cp /etc/named.conf /etc/named.conf.org
```
### ■ 編集内容（example1.jpの場合）
```
10 options {
11	listen-on port 53 { 127.0.0.1; 【ここにサーバー自身のIPアドレスを追加;】 };
	～

	～
19	allow-query    { localhost; } ← any; に変更（全端末からの問い合わせを許可）

31	recursion yes; ← no; に変更（再帰問合せ、DNSコンテンツサーバでは禁止にする）

～

※ ゾーン定義：56行目あたりに追記（場所は任意）
zone "example1.jp" IN {
        type master;
        file "example1.jp.zone";
        allow-update { none; };
};
```
### ■ ゾーン定義補足
```
zone " example1.jp " IN { ← 管理するゾーンの指定
	type master; ← サーバのタイプ
	file "example1.jp.zone"; ← ゾーン定義ファイル名を指定
				（/var/named/の直下にファイルを作成する必要がある）
	allow-update { none; }; ← アップデートの許可・拒否
};
```
### ■ 設定ファイルの書式チェック
```
# named-checkconf
```
→問題がある場合は、問題がありそうな箇所の行番号が表示される

## ③ ゾーンファイルの作成（/var/named/example1.jp.zone）
### ■ ゾーンファイルの作成（ゾーン名.zone が慣習）
/var/named/named.emptyがゾーンファイルのテンプレートになるのでコピーする
```
# cp -p /var/named/named.empty /var/named/example1.jp.zone
```
※コピー元と同じ所有ユーザー（root）・パーミッション（640）にするために -p オプションを付ける

### ■ コピー確認（併せて、所有ユーザー・パーミッションの設定も確認）
```
# ls -l /var/named/example1.jp.zone
```

### ■ 作成したゾーンファイルの編集
```
■テンプレート内容
$TTL 3H
@       IN SOA  @ rname.invalid. (
                                        0       ; serial（シリアル番号）
                                        1D      ; refresh（次の更新までの待機時間）
                                        1H      ; retry（読込失敗時に再読込するまでの時間）
                                        1W      ; expire（retry失敗時に再読込するまでの時間）
                                        3H )    ; minimum（キャッシュ時間）
        NS      @
        A       127.0.0.1
        AAAA    ::1
```
### ■ 編集内容
```
$TTL 3H
$ORIGIN example1.jp.　←ドット忘れずに（FQDN）
@       IN SOA  host1 root (
                                        2025011901       ; serial
                                        1D      ; refresh
                                        1H      ; retry
                                        1W      ; expire
                                        3H )    ; minimum
        NS      host1.example1.jp.
	MX 10   mail.example1.jp.

host1   A       192.168.11.127
www     A       192.168.11.127
mail    A       192.168.11.127
```

※ IN SOA  @ ゾーンを管理するホスト名 管理者のメールアドレス  

※ SOA の `host1 root` は内部的に  
`host1.example1.jp. root.example1.jp.` と解釈される

※ @ = ORIGINの値を省略  
	正式には、host1.example1.jp.   root@example1.jp  
	どちらでも反映される（動く）  

※ シリアル番号は、編集した年月日＋番号（2桁）　例:2025011901  
編集ごとに更新する、同じ日に編集したら番号を02にしたりする  
→バックアップサーバ（セカンダリサーバ）がシリアル番号を参照して値が大きくなっていたら変更を認識する仕組み  

※ NSレコードとMXレコードの前の空白は@が補完されている  

※ NSレコード＝ネームサーバー  
　”このゾーンを管理するネームサーバーは指定したホスト名を持つコンピュータです”  
 `注意：ここでは必ず簡略ではなく、FQDN形式で記述しなければならない`  

※ MXレコード、Aレコードのmailはメールサーバー情報  

※ Aレコードでは名前とIPアドレスの対応を定義、「ホスト名　A　IPアドレス」という形式  
　「www」= webサーバー  
　必須項目: ホスト名のコンピュータのIPアドレス  

### ■ ゾーンファイルの編集
```
# vi /var/named/example1.jp.zone
```
### ■ ゾーンファイルの書式チェック
```
# named-checkzone example1.jp. /var/named/example1.jp.zone
```  

## ☆（応用）上位 jp ゾーンを管理する場合
### ■ 編集前にバックアップ
```
# cp /etc/named.conf /etc/named.conf.org
```
### ■ /etc/named.conf 編集箇所
```
10 options {
11	listen-on port 53 { 127.0.0.1; 【ここにサーバー自身のIPアドレスを追加;】 };
	～

	～
19	allow-query    { localhost; } ← any; に変更（全端末からの問い合わせを許可）

31	recursion yes; ← yesのまま（再帰問合せ）
～

※ ゾーン定義：56行目あたりに追記（場所は任意）
zone "jp" IN {
        type master;
        file "jp.zone";
        allow-update { none; };
};
```
### ■ ゾーン定義補足
```
zone " jp " IN { ← 管理するゾーンの指定
	type master; ← サーバのタイプ
	file "jp.zone"; ← ゾーン定義ファイル名を指定
				（/var/named/の直下にファイルを作成する必要がある）
	allow-update { none; }; ← アップデートの許可・拒否
};
```
### ■ 設定ファイルの書式のチェック
```
# named-checkconf
```
→問題がある場合は、問題がありそうな箇所の行番号が表示される

### ■ ゾーンファイルの作成（jp.zone）
/var/named/named.emptyがゾーンファイルのテンプレートになるのでコピーする
```
# cp -p /var/named/named.empty /var/named/jp.zone
```
※コピー元と同じ所有ユーザー（root）・パーミッション（640）にするために -p オプションを付ける

### ■ コピーできたか確認（併せて、所有ユーザー・パーミッションの設定も確認）
```
# ls -l /var/named/jp.zone
```
### ■ 編集内容
```
$TTL 3H
$ORIGIN jp.
@       IN SOA  host0 root (
                                        2025012101       ; serial
                                        1D      ; refresh
                                        1H      ; retry
                                        1W      ; expire
                                        3H )    ; minimum
        NS      host0.jp.
example1.jp. NS      host1.example1.jp.
example2.jp. NS      host2.example2.jp.

host0   A       192.168.11.37
host1.example1.jp.   A       192.168.11.127
host2.example2.jp.   A       192.168.11.227
```

### ■ ゾーンファイルの編集
```
# vi /var/named/jp.zone
```
### ■ ゾーンファイルの書式確認
```
# named-checkzone jp. /var/named/jp.zone
```
※ example.jp.のゾーンの、/var/named/jp.zoneこのファイルの書式の確認

## ④BIND（named-chroot）の起動・自動起動

### BINDの起動
```
# systemctl start named-chroot
```
### 起動状態の確認
```
# systemctl status named-chroot
```
### ※よくあるミス
- ゾーン定義ファイルの管理するゾーンの名前の間違い  
→ ゾーン定義ファイルの管理するゾーンの名前＝ゾーン設定ファイルの$ORIGINの後の名前   
- /var/named/のファイル名の間違い  
→ これもゾーン定義ファイルに記述しているファイル名と異なると読み込まない  

### ■ 起動
```
# systemctl start named-chroot
```
### ■ 状態確認
```
# systemctl status named-chroot
```
### ■ 自動起動の設定
```
# systemctl enable named-chroot
```
### ■ 設定変更後に再起動
```
# systemctl restart named-chroot
```
### ■ 自動起動の設定が有効か確認
```
# systemctl status named-chroot
```

## ⑤ファイアウォールの設定

### ■ dnsサービス（53番ポート）の接続を許可する（永続的）
```
# firewall-cmd --add-service=dns --zone=public --permanent
```
※permanentオプションを付けて実行した場合、即座には反映されないので、reload で変更を反映させる必要がある
```
# firewall-cmd --reload
```
いずれも成功時には success と表示される

### ■ ファイアウォールの設定確認
```
# firewall-cmd --list-all
```
※service指定の場合は-all→-services
### ■ 設定変更後に再起動
```
# systemctl restart named-chroot
```
### ■ 自動起動・永続的な設定になっているか確認
```
# systemctl status named-chroot
```
→サーバーが起動しているか
```
# firewall-cmd --list-services
```
→dnsがあるか  
リモートで接続できるか


## ⑥名前解決の確認

※ 参照するDNSサーバーの設定は /etc/resolv.conf に記述してある  
→nmcliコマンドで設定したDNSサーバーのアドレス（nameserver）  
※これはSSH通信では変更不可  
※もしくはnmtuiコマンドで疑似GUIで編集

### ■ DNSサーバーの設定（resolv.conf）
```
# nmcli connection modify 接続名 ipv4.dns "DNSサーバのIP"
```
### ■ 設定の有効化
```
# nmcli connection down 接続名
# nmcli connection up 接続名
```
### ■ 設定が変更されたか確認
```
# cat /etc/resolv.conf
```
※この設定で自身のIPアドレスにすることで参照するDNSサーバーの設定を自分自身のみにする  

※設定変更後は再起動する  

## ホスト名からIPアドレスが解決されるか確認（２パターン）

### ①digコマンド
```
# dig [ホスト名（FQDN）]
```
### ※ 確認箇所
```
;; QUESTION SECTION:
;host1.example1.jp.             IN      A
```
↑これを聞いた結果
```
;; ANSWER SECTION:
host1.example1.jp.      10800   IN      A       192.168.11.127
```
↑これを返しました  

ANSWER SECTIONでIPアドレスを返答しているかを確認  

### つながってない場合
```
;; QUESTION SECTION:
;test.example1.jp.              IN      A
```
```
;; AUTHORITY SECTION:
example1.jp.            10800   IN      SOA     host1.example1.jp. root.example1.jp. 2025012001 86400 3600 604800 10800
```
↑権威のみ返る

※ゾーン設定ファイルのAレコードに記述したホスト名とIPアドレスを名前解決してくれている  

### ■ 参照するDNSサーバーを指定
```
# dig [ホスト名（FQDN）] @DNSサーバーのホスト名（IPアドレス）
```
※ 元の設定を変更せずに一時的に明示することができる  
→自身への名前解決の確認などに使用する  
※ `@` 忘れずに  

## ②nslookupコマンド　※ Windowsでも利用可能
書式はdigコマンドと一緒
```
# nslookup [ホスト名（FQDN）] [DNSサーバーのホスト名（IPアドレス）]
```
```
Server:         192.168.11.127
Address:        192.168.11.127#53

Name:   host1.example1.jp　←
Address: 192.168.11.127　←
```

## ブラウザで.htmlを確認する
名前解決がされるので、  
`http://www.DNSサーバーのホスト名`  
でアクセス可能  
※プロキシのブロック、ホストOSのDNSサーバのアドレスが違うと拒否される  

## ※DNSのキャッシュをクリアするコマンド（コマンドプロンプト（Windows）） 
```
ipconfig /flushdns
```
