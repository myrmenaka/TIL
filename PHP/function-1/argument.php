<?php
    function greet($name) {
        echo "{$name}さん、こんにちは！" . PHP_EOL;
    };

    // $name が引数

    greet("太郎");
    greet("花子");

// 太郎さん、こんにちは！
// 花子さん、こんにちは！