<?php
    // パスワードチェック
    $input = "abc123";
    $correct = "secret";

    if ($input === $correct) {
        echo "ログイン成功";
    } else {
        echo "パスワードが間違っています。";
    };

    // パスワードが間違っています。