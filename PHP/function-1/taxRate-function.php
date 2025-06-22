<?php
    function calcTax($price) {
        $taxRate = 0.1;
        return $price * (1 + $taxRate);
    };

    // コール
    $inclTax = calcTax(1000);
    echo "税込価格: {$inclTax}円" . PHP_EOL;

    // 税込価格: 1100円