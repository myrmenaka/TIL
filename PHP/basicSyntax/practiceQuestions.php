<?php
    $name = "丸山";
    $age = 32;
    $height = 156.7;
    $isStudent = false;

    echo "こんにちは、{$name}さん！". PHP_EOL;
    echo "年齢: {$age}歳". PHP_EOL;
    echo "身長: {$height}cm". PHP_EOL;
    echo "学生ですか？: " . ($isStudent ? "はい" : "いいえ") . PHP_EOL;

    // 三項演算子
    // 比較条件(比較対象) ? 条件に合致した場合の値(true) : 条件に合致しない場合の値(false)

    // PHP_EOL　改行

    // こんにちは、丸山さん！
    // 年齢: 32歳
    // 身長: 156.7cm
    // 学生ですか？: いいえ
