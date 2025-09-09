<?php
    $price = 1200;
    $taxRate = 0.1;

    // 税込み価格を計算
    $taxIncluded = $price + ($price * $taxRate);
    
    // なぜ、1.1ではないのか
    // → 税額を別で計算できたり、税率が変わった際に柔軟に対応できる
    // また、税抜き価格も後から求めやすい
    // コードは少し長くなるが、凡庸性が高くなる

    // 表示
    echo "商品価格: {$price}円". PHP_EOL;
    echo "税込価格: ". $taxIncluded ."円". PHP_EOL;

    // 連結と代入演算子の練習
    $message = "ご購入ありがとうございます。". PHP_EOL;
    $message .= "またのご利用をお待ちしております。";
    echo $message;

    // .= 文字列演算子の代入演算子