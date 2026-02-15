# SQL基本まとめ（MySQL/MariaDB 対応）

## ログイン
```sql
$ mysql -u root -p
```
または
```sql
$ mysql -u root -pパスワード
```
※ -pとパスワードの間はスペース無し  
※ コマンドヒストリーに残るため危険

## SHOW（確認系）
### ■ データベースの確認
```sql
SHOW DATABASES;
```
### ■ テーブルの確認
```sql
SHOW TABLES;
```

## データベース操作

### ■ データベースの作成
```sql
CREATE DATABASE データベース名;
```
### ■ データベースの削除
```sql
DROP DATABASE データベース名;
```
### ■ 使用するデータベースの指定
```sql
USE データベース名;
```
### ■ 今どのデータベースが選択されているか確認
```sql
SELECT DATABASE();
```

## テーブル操作
### ■ テーブルの作成
```sql
CREATE TABLE テーブル名(
	カラム1 データ型,
	カラム2 データ型,
	...
);
```
### ■ テーブル構造の確認
```sql
DESC テーブル名;
```
※useで選択しているデータベースのテーブル構造が表示される
### ■ テーブル名の変更
```sql
ALTER TABLE 旧テーブル名 rename 新テーブル名;
```

## カラム操作

### ■ カラム名＋定義の変更（CHANGE）
```sql
ALTER TABLE テーブル名 CHANGE カラム名 新しいカラム名 変更するデータ型 その他の設定;
```
### ■ カラム定義のみ変更（MODIFY）
```sql
ALTER TABLE テーブル名 MODIFY カラム名 変更するデータ型 その他の設定;
```
### ■ カラムの追加（ADD）
```sql
ALTER TABLE テーブル名 ADD 新しく追加したいカラム名 カラムのデータ型 その他の設定;
```
### ※ 位置指定で追加
#### 既存のテーブルの最後に追加する場合（FIRST）
```sql
ALTER TABLE テーブル名 ADD 新しく追加したいカラム名 カラムのデータ型 その他の設定 FIRST;
```
#### 指定したカラムの後に挿入（AFTER）
```sql
ALTER TABLE テーブル名 ADD 新しく追加したいカラム名 カラムのデータ型 その他の設定 AFTER 既存カラム名;
```
### ■ 作成済みのテーブルから指定のカラムを削除（DROP）
```sql
ALTER TABLE テーブル名 DROP 削除したいカラム名;
```
※複数選択不可

## 制約（NULL / DEFAULT / PRIMARY KEY）
### ■ NULL許可・禁止（テーブル）
```sql
CREATE TABLE テーブル名(
	カラム名 データ型 NULL,
	カラム名 データ型 NOT NULL,
	...
);
```
### ■ NULL許可・禁止（カラム）
```sql
ALTER TABLE テーブル名 MODIFY カラム名 カラムのデータ型 NOT NULL;
```
### ■ デフォルト値
カラム定義時に
```sql
カラム名 カラムのデータ型 DEFAULT デフォルト値, ...
```
### ■ 主キー（プライマリーキー）
カラム定義時に
```sql
カラム名 カラムのデータ型 PRIMARY KEY, ...
```
### ■ 主キー削除
```sql
ALTER TABLE テーブル名 DROP PRIMARY KEY;
```

## データ操作（INSERT / SELECT / UPDATE / DELETE）
### ■ テーブルにデータを挿入（INSERT）
```sql
INSERT INTO テーブル名 VALUES(値1, 値2, ...);
```
複数行の場合
```sql
INSERT INTO テーブル名 VALUES
	(データ1, データ2, ...),
	(データ1, データ2, ...),
	(データ1, データ2, ...),
	...;
```
### ■ カラムとデータの挿入
```sql
INSERT INTO テーブル名(カラム名1, カラム名2, ...) VALUES(値1, 値2, ...);
```
### ※ コピー元のテーブルを元に複製
```sql
CREATE TABLE 新しいテーブル名 AS (SELECT * FROM コピー元テーブル名);
```
### ■ データのコピー
```sql
INSERT INTO 挿入先テーブル名 (SELECT * FROM 挿入元テーブル名);
```
→テーブルを丸ごとコピー

```sql
INSERT INTO 挿入先テーブル名(カラム名1, カラム名2, ...)
SELECT カラム名1, カラム名2, ... FROM 挿入元テーブル名 WHERE 検索条件;
```
→他の表から取り出した行をコピーする



### ■ テーブルデータの表示・検索（SELECT）
### データの表示・検索
```sql
SELECT カラム名, ... FROM テーブル名;
```
```sql
SELECT カラム名1, カラム名2, ... FROM テーブル名;
```
### すべてのカラムを検索
```sql
SELECT * FROM テーブル名;
```
### 重複データの除外
```sql
SELECT DISTINCT 検索するカラム名 FROM テーブル名;
```
### エイリアス（別名表示）
```sql
カラム名 AS 別名;
```
※ASは省略可能  
（例）  
```sql
select *, price * 1.1 as New_Price from mt_goods;
```
→ mt_goodsテーブルのpriceの値を1.1倍した値を New_Price という別名で検索

### ■ 条件によるカラムの検索（WHERE）
```sql
WHERE 検索条件;
```
### 検索条件に合致したカラムのみ表示（比較演算子）
```sql
WHERE カラム名 比較演算子 値;
```
（例）
```sql
select goods_name, price from mt_goods where price >= 800;
```
→ mt_goodsテーブルからpriceが800円以上のgoods_nameとpriceを検索

### 論理演算子
```sql
WHERE 条件1 AND 条件2 : 2つの条件のどちらも満たした場合に真
WHERE 条件1 OR 条件2 : 2つの条件のどちらかが満たした場合に真
WHERE NOT 条件 : 後に定義した条件の真偽を反転
```
※優先順位は、NOT > AND > OR  
（例）  
```sql
select * from mt_goods where not classify='清涼飲料水' and price > 600;
```
→ mt_goodsテーブルからclassifyが'清涼飲料水'以外かつ、priceが600円より高いものを検索

### BETWEEN演算子（○○以上○○以下（範囲））
```sql
WHERE カラム名 BETWEEN 値1 AND 値2
```
指定された値の範囲内にカラムの値が含まれるときにその範囲内を返す  
（例）  
```sql
select goods_code, goods_name from mt_goods where price between 500 and 900;
```
→ mt_goodsテーブルから、priceが500円以上900以下の商品コードと商品を検索する  
※price >= 500 and price <= 900; と同じ  
※自動で、値1以上（から） 値2以下（まで） となる  

### IN演算子（複数一致）
```sql
WHERE カラム名 IN (値1, 値2, ...)
```
（例）  
```sql
select goods_name, classify from mt_goods where classify in('アルコール', '定番飲料');
```
→ mt_goodsテーブルから、classifyがアルコールか定番飲料の商品を検索  
※OR と同じ  
```sql
classify='アルコール' or classify='定番飲料';
```

### LIKE演算子（あいまい検索）
```sql
WHERE カラム名 LIKE 'ワイルドカード値'
```
#### ワイルドカード :   
`%` → 任意の長さの文字列  
`_` → 任意の一文字  

％値 : 後方一致（値で終わる）  
値％ : 前方一致（値で始まる）  
％値％ : 中間一致（あいまい検索、どこかに入っていたら）  

（例）  
```sql
select goods_name from mt_goods where goods_name like '%コーヒー%';
```
→ mt_goodsテーブルから、goods_nameにコーヒーという文字列が入っている商品を検索  
※%、_は半角のみ有効  

### ISNULL演算子（NULL判定）
```sql
カラム名 IS NULL : NULLかどうか
カラム名 IS NOT NULL : NULLではないかどうか
```
（例）  
```sql
select goods_name from mt_goods where goods_code is not null;
```
→ mt_goodsテーブルから、goods_codeにデータが存在する商品名を検索  
※データが存在する商品名＝NULLではない  

### ■ データの更新（UPDATE）
```sql
UPDATE テーブル名 SET カラム名1=更新する値1, カラム名2=更新する値2, ... WHERE 検索条件;
```
→ 条件を満たす行のカラム名の値を更新する  
※WHEREを含めないと全てのレコードが対象になってしまうので注意  

### ■ データの削除（DELETE）
```sql
DELETE FROM テーブル名;
```
→ 指定したテーブルのすべての行を削除
```sql
DELETE FROM テーブル名 WHERE 条件;
```
→ 条件を満たす行のみ指定したテーブルから削除  
※ WHERE を忘れると全件削除  

#### ※ 間違えてデータを更新・削除しないようにログイン時にオプションを付けることで、WHEREの条件が無い場合に更新・削除ができないようにすることができる
```
mysql -u root -p --safe-updates
```
このモードのON/OFFの確認は以下のコマンド（起動中）
```sql
show variables like 'sql_safe_updates';
```

## 集約（COUNT / SUM / GROUP BY / HAVING）
### ■ COUNT関数　（行数のカウント）
```sql
SELECT COUNT(*) FROM テーブル名;
```
COUNT (カラム名) : 指定したカラム名のNULL以外の行数を返す  
COUNT (*) : すべての行数を返す  
（例）  
```sql
select count(*) as '総数' from mt_book;
```
→ mt_bookテーブルに登録されているレコードの総数を求める  
※asでエイリアスを登録して表示することが多い

### ■ SUM関数　（合計値）
```sql
SELECT SUM(カラム名) FROM テーブル名;
```
SUM (カラム名): 指定したカラムのNULL以外の合計値を返す  
（例）
```sql
select sum(price) as '値段総合計' from mt_goods;
```
→ mt_goodsテーブルのpriceの合計値を求める

### ■ GROUP BY（グループ化）
```sql
select 集約キーまたは集計関数 FROM テーブル名 GROUP BY カラム名（＝集約キー）;
```
（例）
```sql
select classify, sum(price) as '合計金額' from mt_goods group by classify;
```
→ mt_goodsテーブルのclassifyごとのpriceの合計を出力  
※グループ化した場合、SELECTの直後に書けるのは、集約キーまたは集約関数のみ  
※集約キー: GROUP BY カラム名←このカラム名が集約キー  
### ■ HAVING（グループ化後の条件）
```sql
SELECT 集約キーまたは集計関数 FROM テーブル名 GROUP BY カラム名（＝集約キー） HAVING 条件;
```
（例）
```sql
select classify, sum(price) as '合計金額' from mt_goods group by classify having sum(price) <= 3000;
```
→ mt_goodsテーブルのclassify別のpriceの合計値が3000以下のレコードを出力  
### ※ WHEREとHAVING
WHERE＝グループ化される前に実行する（集約関数は利用不可）  
HAVING＝グループ化後に実行する（集約関数利用可）  

### ■ ORDER BY（並べ替え）
```sql
ORDER BY カラム名 ASCまたはDESC
```
ASC: 昇順（低い順）に並べ替え(デフォルト)  
DESC: 降順（高い順）に並べ替え  

（例）
```sql
select * from mt_goods order by price;
```
→ mt_goodsテーブルから、priceを基準に低い順に並べ替えてデータを検索

（例）
```sql
select * from mt_goods order by price desc;
```
→ mt_goodsテーブルから、priceを基準に高い順に並べ替えてデータを検索  

※ ORDER BY は必ず最後に記述（WHEREよりも）  
※ 文字列型のカラムの場合は辞書順  
数字（0-9）→アルファベット（a-z A-Z）→ひらがな（あ-ん）→漢字  
※エイリアスを含む場合は、``を外す（文字列リテラルと認識されカラム名と認識されないため）  

### ■ LIMIT（上位（または下位）の件数を絞って表示）
```sql
LIMIT レコード件数 [OFFSET 表示開始レコードのシフト数]
```
※ ORDER BYと組み合わせて用いる  

（例）
```sql
select * from mt_goods order by price desc limit 5;
```
→ mt_goodsテーブルのpriceの値が高い順に並んだ5行の結果を表示

（例）
```sql
select * from mt_goods order by price desc limit 5 OFFSET 2;
```
→ mt_goodsテーブルのpriceの値が高い順に並んだ5行の結果を表示（OFFSETで2行分シフト）

## UNION（結合）
```sql
クエリA UNION クエリB
```
2つのクエリの出力を和結合する  
※ 重複は自動で除外  

（例）
```sql
select * from mt_goods where price > 900 union select * from mt_goods where classify='清涼飲料水';
```
→ mt_goodsテーブルから、priceが900より大きいレコードとclassifyが清涼飲料水のレコードを検索

## サブクエリ
SELECTの中にSELECT文を記述する

### ■ 使用例①
```sql
SELECT カラム名 FROM(SELECT カラム名 FROM テーブル名) as 別名 WHERE 条件式;
```
### ■ 使用例②
```sql
SELECT カラム名 FROM テーブル名 WHERE カラム名 IN(SELECTによるサブクエリ... )
```
### ■ 使用例③
```sql
SELECT カラム名 FROM テーブル名 WHERE カラム名 比較演算子(SELECTによるサブクエリ...  )
```
### ※ まとめ
・SELECT 検索するカラム名の定義
・FROM テーブル名の定義
・WHERE 条件の定義
・GROUP BY グループの定義
・集約関数を利用→HAVING グループ化されたデータごとの検索条件
・並び替えをする→ORDER BY 並び替えの条件

## インポート（LOAD DATA）
### ■ ファイルからデータをインポート
```sql
LOAD DATA LOCAL INFILE '/ディレクトリ/ファイル名' INTO TABLE インポート先テーブル名 オプション;
```
※ オプション  
```sql
FIELDS TERMINATED BY '区切り文字' LINES TERMINATED BY '改行文字';
```

## トランザクション
複数の処理を一つのまとまりとして扱うこと  
→ 全て成功もしくは全て失敗  

### ■ トランザクション開始
```sql
START TRANSACTION;
```
または `BEGIN;`  
※「`Query OK`」のメッセージが出れば成功  
※ ロールバックまたはコミットしたら抜ける(トランザクション終了)  

### ■ ロールバック(元に戻す)
```sql
ROLLBACK;
```
※ 最後にトランザクションを開始した地点まで戻る

### ■ コミット(反映・確定)
```sql
COMMIT;
```
※ 処理の確定

### ■ セーブポイント(小さな戻りポイント)
```sql
SAVEPOINT セーブポイント名;
```
#### ※ セーブポイントが消える条件  
・`RELEASE SAVEPOINT`したら消える  
・`COMMIT`したら消える  
・`ROLLBACK(全体ロールバック)`したら消える  

### ■ セーブポイントまでのロールバック
```sql
ROLLBACK TO セーブポイント名;
```
※ セーブポイントが残っている限り何回でもできる  
※ ROLLBACK TO 時は直前のセーブポイントより後のセーブポイントは消える  
（例）  
```
SAVEPOINT s1;
...
SAVEPOINT s2;
...
ROLLBACK TO s1; ← セーブポイントs2も同時に消える
```
### ■ セーブポイントを明示的に消す
```sql
RELEASE SAVEPOINT セーブポイント名;
```
※ 現在のセーブポイント一覧を確認するコマンドは存在しない

### ※ トランザクションのデメリット:
ロック・デッドロック・パフォーマンス低下  

・複数テーブルの更新  
・大量データの一括更新  

での利用が好ましいが、短く・必要な範囲だけがベスト  

逆に  

・1件のみの追加・更新  
・SELECT文のみ  
・外部APIを持つ処理  

の使用は避ける  

また、DDL（テーブル変更）との相性が悪い場合もあるので使用には注意  
→ MySQLでは一部のDDLが暗黙コミットされる（ロールバックできない変更が混ざる危険あり）  


