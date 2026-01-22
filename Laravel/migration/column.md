# Laravel Schemaビルダー：カラム定義一覧

## ① 主キー（ID）

| 書き方 | 意味 | 正式名称 |  
|:--:|:--:|:--:|  
| $table->id(); | 自動で増えるID | big integer auto-increment |  
| $table->biglncrements('ID名'); | 大きい数字の自動ID | BIGINT auto-increment |  
| $table->increments('ID名'); | 普通の数字の自動ID | INT auto-increment |  

## ② 文字（文章）

| 書き方 | 意味 | 正式名称 |  
|:--:|:--:|:--:|  
| $table->string('カラム名'); | 短い文字（最大255） | VARCHAR(255) |  
| $table->string('カラム名', 長さ); | 長さを指定した文字 | VARCHAR(n) |  
| $table->text('カラム名'); | 長文 | TEXT |  
| $table->mediumText('カラム名'); | 中くらいの長文 | MEDIUMTEXT |  
| $table->longText('カラム名'); | 超長文 | LONGTEXT |  
| $table->char('カラム名', 桁数); | 固定長の文字 | CHAR(n) |  

## ③ 数字

| 書き方 | 意味 | 正式名称 |  
|:--:|:--:|:--:|  
| $table->integer('カラム名'); | 普通の数字(-2,147,483,648~2,147,483,647) | INT |  
| $table->bigInteger('カラム名'); | 大きい数字 | BIGINT |  
| $table->tinyInteger('カラム名'); | 小さい数字（-128~127） | TINYINT |  
| $table->smallInteger('カラム名'); | 小さい数字(-32,768~32,767) | SMALLINT |  
| $table->unsignedInteger('カラム名'); | マイナスなしの数字(0~4,294,967,295) | UNSIGNED INT |  
| $table->unsignedBigInteger('カラム名'); | マイナスなしの大きい数字 | UNSIGNED BIGINT |  

## ④ 小数・金額

| 書き方 | 意味 | 正式名称 |  
|:--:|:--:|:--:|  
| $table->decimal('カラム名', 桁数, 小数点以下); | 金額などの小数 | DECIMAL(precision, scale) |  
| $table->float('カラム名', 桁数, 小数点以下); | 浮動小数点 | FLOAT |  
| $table->double('カラム名', 桁数, 小数点以下); | 大きい小数 | DOUBLE |  

## ⑤ TRUE / FALSE（真偽値）

| 書き方 | 意味 | 正式名称 |  
|:--:|:--:|:--:|  
| $table->boolean('カラム名'); | 真偽値 | BOOLEAN |  

## ⑥ 日付・時間

| 書き方 | 意味 | 正式名称 |  
|:--:|:--:|:--:|  
| $table->date('カラム名'); | 日付 | DATE |  
| $table->dateTime('カラム名'); | 日付＋時間 | DATETIME |  
| $table->time('カラム名'); | 時間だけ | TIME |  
| $table->year('カラム名'); | 年だけ | YEAR |  
| $table->timestamps(); | created_at / updated_at | TIMESTAMP |  
| $table->softDeletes(); | 削除した日時を記録(null可) | TIMESTAMP nullable |  

※created_at : レコードが作られた時間
 updated_at : レコードが更新された時間

## ⑦ 外部キー（別テーブルとつなぐ）

| 書き方 | 意味 | 正式名称 |  
|:--:|:--:|:--:|  
| $table->foreignId('')->constrained(); | users.id と自動で紐づく | BIGINT unsigned + FOREIGN KEY |  
| $table->foreignId('')->constrained(''); | 参照先テーブルを指定 | FOREIGN KEY |  
| $table->foreignId('')->nullablle()->constrained(); | 外部キー＋null許可 | nullable FK |  

## ⑧ インデックス（検索を速くする）

| 書き方 | 意味 | 正式名称 |  
|:--:|:--:|:--:|  
| $table->index('カラム名'); | 普通のインデックス | 普通のインデックス |  
| $table->unique('カラム名'); | 重複禁止 | UNIQUE INDEX |  
| $table->primary('カラム名'); | 主キー指定 | PRIMARY KEY |  
| $table->fullText('カラム名'); | 文章検索用 | FULLTEXT INDEX |  

## ⑨ JSON・その他

| 書き方 | 意味 | 正式名称 |  
|:--:|:--:|:--:|  
| $table->json('カラム名'); | JSONデータ | JSON |  
| $table->binary('カラム名'); | バイナリデータ | BLOB |  
| $table->uuid('カラム名'); | UUID形式のID | UUID |  
| $table->ipAddress('カラム名'); | IPアドレス | VARCHAR(45) |  
| $table->macAddress('カラム名'); | MACアドレス | VARCHAR(17) |  

※ ipAddress はIPv6に対応するため、VARCHAR(45)となっている。（IPv4→最大15文字、IPv6→最大45文字）

