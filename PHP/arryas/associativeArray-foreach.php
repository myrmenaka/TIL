<?php
    $user = [
        "name" => "朱音",
        "age" => 32,
        "number" => 112233
    ];

    foreach ($user as $key => $value) {
        echo "{$key}: {$value}" . PHP_EOL;
    };

    // キーは $key へ、要素は $value へ順に入る
    // name: 朱音
    // age: 32
    // number: 112233