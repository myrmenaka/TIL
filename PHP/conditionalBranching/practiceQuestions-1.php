<?php
    // 年齢によるメッセージ
    $age = 32;

    if ($age >= 65) {
        echo "高齢者です。";
    } elseif ($age >= 20) {
        echo "成人です。";
    } elseif ($age >= 13) {
        echo "未成年です。";
    } else {
        echo "子供です。";
    };

    // 成人です。