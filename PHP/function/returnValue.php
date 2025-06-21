<?php
    function add($a, $b) {
        return $a + $b;
    };

    // コール
    echo "合計は". add(3, 5) ."です。" . PHP_EOL;

    // 変数に戻り値を代入した場合
    $sum = add(3, 5);
    echo "合計は{$sum}です。" . PHP_EOL;

    // 合計は8です。
    