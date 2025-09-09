<?php
    $user = [
        "name" => "朱音",
        "age" => 32,
        "number" => 112233
    ];

    echo $user["name"] . PHP_EOL;
    echo $user["age"] . PHP_EOL;
    echo $user["number"] . PHP_EOL;

    // 朱音
    // 32
    // 112233

    print_r($user);

    //     Array
    // (
    //     [name] => 朱音
    //     [age] => 32
    //     [number] => 112233
    // )

    // 追加
    $user["email"] = "akane@ooo,com";
    print_r($user);

    // "email"が最後尾に追加
    //     Array
    // (
    //     [name] => 朱音
    //     [age] => 32
    //     [number] => 112233
    //     [email] => akane@ooo,com
    // )

    // 変更
    $user["number"] = 223344;
    print_r($user);

    // "number" 112233 → 223344に変更
    //     Array
    // (
    //     [name] => 朱音
    //     [age] => 32
    //     [number] => 223344
    //     [email] => akane@ooo,com
    // )