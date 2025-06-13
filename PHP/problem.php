<?php
// Hello World!
// echo "Hello World!";


// 田中太郎さんは25歳です
// $name = "田中太郎";
// $age = 25;
// echo "{$name}さんは{$age}歳です";


// 60点以上なら合格、それ未満は不合格
// $score = 50;
// if ($score >= 60){
//     $message = "合格";
// } else {
//     $message = "不合格";
// };
// echo $message;

// 1～10を出力
// for ($i = 1; $i <= 10; $i++){
//     echo $i;
// };

// りんご、バナナ、ぶどうという配列を1行ずつ表示
// $fruits = ["りんご","バナナ","ぶどう"];
// foreach($fruits as $fruit){
//     echo $fruit.PHP_EOL;
// };

// 九九表を出力
// for($i = 1; $i <= 9; $i++){
//     for($j = 1; $j <= 9; $j++){
//         echo "{$i} × {$j} = ". ($i*$j) . PHP_EOL;
//     };
// };

// 曜日表示
// $day = 1;
// switch ($day) {
//     case 1:
//         echo "月曜日";
//         break;
//     case 2:
//         echo "火曜日";
//         break;
//     case 3:
//         echo "水曜日";
//         break;
//     case 4:
//         echo "木曜日";
//         break;
//     case 5:
//         echo "金曜日";
//         break;
//     case 6:
//         echo "土曜日";
//         break;
//     case 7:
//         echo "日曜日";
//         break;
//     default:
//         echo "不正な値です";
// };

// 20歳以上の男性なら「成人男性」、それ以外は「対象外」
// $age = 20;
// $gender = "男性";
// if ($age >= 20 && $gender === "男性") {
//     echo "成人男性";
// } else {
//     echo "対象外";
// };

// 三項演算子　60点以上「合格」、未満は「不合格」
$score = 75;
$scores = $score >= 60 ? print "合格" : print "不合格";
echo $scores;



