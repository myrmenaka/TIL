<?php
    // 偶数か奇数かを判定する関数
    function isEven($sum) {
        if ($sum % 2 === 0) {
            return "偶数";
        } else {
            return "奇数";
        }
    };

    $num = 7;
    $int = isEven($num);
    echo "{$num}は{$int}です。";

    // 7は奇数です。

    // 偶数は2で割った余りが0なので条件式が $sum % 2 === 0 になる
    // 奇数の場合は、$sum % 2 === 1 となる