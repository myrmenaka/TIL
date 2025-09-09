<?php
    // 多次元配列　インデックス配列の中にインデックス配列
    // 座席表
    $seats = [
        ["A1", "A2", "A3"],
        ["B1", "B2", "B3"],
        ["C1", "C2", "C3"]
    ];

    echo $seats[0][1];

    // A2

    // 2重ループで全部出力する
    foreach ($seats as $row) {
        foreach ($row as $seat) {
            echo $seat . " ";
        }
        echo PHP_EOL;
    };

    // 最初のforeachでネストされた配列を$rowに代入
    // ネストされたforeachで$rowに代入された配列の要素を$seatに代入
    // 出力時にスペースを空けるため、空文字を記述
    // 一つの配列のループが終わった際に改行してほしいのでネストの外に記述
    // A1 A2 A3 
    // B1 B2 B3
    // C1 C2 C3