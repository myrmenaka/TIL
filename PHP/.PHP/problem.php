<?php
// Hello World!
echo "Hello World!";


// 田中太郎さんは25歳です
$name = "田中太郎";
$age = 25;
echo "{$name}さんは{$age}歳です";


// 60点以上なら合格、それ未満は不合格
$score = 50;
if ($score >= 60){
    $message = "合格";
} else {
    $message = "不合格";
};
echo $message;

// 1～10を出力
for ($i = 1; $i <= 10; $i++){
    echo $i;
};

// りんご、バナナ、ぶどうという配列を1行ずつ表示
$fruits = ["りんご","バナナ","ぶどう"];
foreach($fruits as $fruit){
    echo $fruit.PHP_EOL;
};

// 九九表を出力
for($i = 1; $i <= 9; $i++){
    for($j = 1; $j <= 9; $j++){
        echo "{$i} × {$j} = ". ($i*$j) . PHP_EOL;
    };
};

// 曜日表示
$day = 1;
switch ($day) {
    case 1:
        echo "月曜日";
        break;
    case 2:
        echo "火曜日";
        break;
    case 3:
        echo "水曜日";
        break;
    case 4:
        echo "木曜日";
        break;
    case 5:
        echo "金曜日";
        break;
    case 6:
        echo "土曜日";
        break;
    case 7:
        echo "日曜日";
        break;
    default:
        echo "不正な値です";
};

// 20歳以上の男性なら「成人男性」、それ以外は「対象外」
$age = 20;
$gender = "男性";
if ($age >= 20 && $gender === "男性") {
    echo "成人男性";
} else {
    echo "対象外";
};

// 三項演算子　60点以上「合格」、未満は「不合格」
$score = 75;
$scores = $score >= 60 ? print "合格" : print "不合格";
echo $scores;

// "名前：（年齢）歳"で出力
$people = [
    "田中" => 28,
    "佐藤" => 35,
    "鈴木" => 22
];
foreach($people as $name => $age) {
    echo "{$name}さん: {$age}歳".PHP_EOL;
};

// 1~100までの合計
$i = 1;
$sum = 0;
while ($i <= 100) {
    $sum += $i;
    $i++;
};
echo "合計は {$sum} です";

// 1~10のうち、偶数のみ表示
for ($i = 1; $i <= 10; $i++) {
    if ($i % 2 !== 0) {
        continue;
    };
    echo $i .PHP_EOL;
};

// 1~100までループ、合計が100を超えたら終了、合計とループ回数を表示
$sum = 0; //合計用
$count = 0; //回数用
for ($i = 1; $i <= 100; $i++) { //1~100以上になるまでカウントアップ
    $sum += $i; //ループのたびに足していく
    $count++; //ループ回数のために記述
    if ($sum > 100) {
        break; //$sumが100より多くなったら終了
    };
};
echo "合計: {$sum} ". PHP_EOL;
echo "ループ回数: {$count}";

// 定数 SITE_NAME に「MySite」という値を定義、表示
define("SITE_NAME","MySite");
echo SITE_NAME;

// 変数$userが存在しない場合に「ゲスト」と表示する
$user = "丸山"; //コメントアウト外すと「丸山」と表示される
$message = $user ?? "ゲスト";
echo $message;

// 変数が存在するかを調べ、なければ「未設定」と表示
$user = "丸山"; //コメントアウト外すと「丸山」と表示される
if (isset($user)) {
    echo $user;
} else {
    echo "未設定";
};

// 変数 $valie = 123.45; のデータ型を表示
$valie = 123.45;
echo gettype($valie);

// chatGPTより出題
