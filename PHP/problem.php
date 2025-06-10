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
for($i = 1; $i <= 9; $i++){
    for($j = 1; $j <= 9; $j++){
        echo "{$i} × {$j} = ". ($i*$j) . PHP_EOL;
    };
};
