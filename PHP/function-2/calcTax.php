<?php
    function calcTax($price) {
        if (!is_numeric($price)) {
            return "数値を入力してください。";
        }
        $taxRate = 0.1;
        return $price * (1 + $taxRate);
    };

    // コール
    echo calcTax(500);
    echo calcTax("abc");

    // 550
    // 数値を入力してください。

    // !is_numeric → 数値でないときに true