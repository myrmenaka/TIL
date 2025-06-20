<?php
    // 多次元配列（ネスト構造）　インデックス配列の中に連想配列
    // ユーザー一覧
    $users = [
        ["name" => "太郎", "age" => 32],
        ["name" => "次郎", "age" => 25],
        ["name" => "三郎", "age" => 18]
    ];
    
    echo "{$users[0]["name"]}さん({$users[0]["age"]}歳)" . PHP_EOL;

    // 太郎さん(32歳)

    foreach ($users as $user) {
        echo "{$user['name']}さん({$user['age']}歳)" . PHP_EOL;
    };

    // 太郎さん(32歳)
    // 次郎さん(25歳)
    // 三郎さん(18歳)