<?php
    // 連想配列のループ
    $book = [
        "タイトル" => "PHP入門",
        "著者" => "山田太郎",
        "価格" => 3480
    ];

    foreach ($book as $key => $value) {
        echo "{$key}: {$value}" . PHP_EOL;
    };

    // タイトル: PHP入門
    // 著者: 山田太郎
    // 価格: 3480