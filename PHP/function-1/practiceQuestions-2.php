<?php
    // 掛け算をする関数
    function multiply($x, $y) {
        return $x * $y;
    };

    $product = multiply(3, 5);
    echo "3 × 5 = {$product}" . PHP_EOL;

    $product = multiply(10, 12);
    echo "10 × 12 = {$product}" . PHP_EOL;

    // 3 × 5 = 15
    // 10 × 12 = 120