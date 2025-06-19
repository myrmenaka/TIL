<?php
    // whileでカウントダウン
    $count = 5;

    while ($count > 0) {
        echo "残り: {$count}秒" . PHP_EOL;
        $count--;
    };

    /*
    $countが０より大きい間、処理を行う
    echoの出力と、毎回1を引く
    */

    // 残り: 5秒
    // 残り: 4秒
    // 残り: 3秒
    // 残り: 2秒
    // 残り: 1秒