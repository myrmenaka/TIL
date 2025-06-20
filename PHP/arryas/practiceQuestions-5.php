<?php
    // 連想配列の中に連想配列
    // 教科ごとの点数リスト
    $student = [
        "name" => "丸山",
        "scores" => ["国語" => 80, "数学" => 90, "英語" => 85 ] 
    ];

    echo "【{$student["name"]}さんの点数】" . PHP_EOL;

    foreach ($student["scores"] as $subject => $score) {
        echo "{$subject}: {$score}点" . PHP_EOL;
    };

    // 【丸山さんの点数】
    // 国語: 80点
    // 数学: 90点
    // 英語: 85点


    // 連想配列の中にインデックス配列
    // スキル一覧表
    $employee = [
        "name" => "丸山",
        "skills" => ["PHP", "JavaScript", "SQL"]
    ];

    echo "{$employee["name"]}さんのスキル: " . PHP_EOL;

    foreach ($employee["skills"] as $skill) {
        echo "-{$skill}" . PHP_EOL;
    };

    // 丸山さんのスキル:
    // -PHP
    // -JavaScript
    // -SQL


    // インデックス配列の中に連想配列の中にインデックス配列
    // 複数ユーザーのスキル一覧
    $users = [
        [
            "name" => "丸山",
            "skills" => ["HTML", "CSS", "JavaScript"]
        ],
        [
            "name" => "田中",
            "skills" => ["PHP", "MySQL"]
        ]
    ];

    foreach ($users as $user) {
        echo "【{$user["name"]}さんのスキル】" . PHP_EOL;
        foreach ($user["skills"] as $skill) {
            echo "- {$skill}" . PHP_EOL;
        }
        // echo PHP_EOL;
    };

    // 【丸山さんのスキル】
    // - HTML
    // - CSS
    // - JavaScript
    // (改行)
    // 【田中さんのスキル】
    // - PHP
    // - MySQL
    // (改行)