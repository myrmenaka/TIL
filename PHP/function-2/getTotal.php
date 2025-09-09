<?php
    function getTotal($price, $taxRate) {
        $total = $price * (1 + $taxRate);
        return $total;
    };

    // コール
    echo getTotal(1000, 0.1);

    // 1100
    // 税込価格を表示する関数
    // 1 + $taxRate とすることで柔軟性が高まる