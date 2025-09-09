<?php
    // 税率計算
    $price = 1200; // 商品価格
    $taxRate = 0.1; // 税率10％

    $tax = $price * $taxRate; // 税額
    $total = $price + $tax; // 税込価格

    echo $total ."円";

    // 1320円