<?php
    // URLのパラメータから名前を受け取って表示する

    /*
    1. ブラウザのURLに ?name=○○ をつける
    2. PHPファイルで $_GET['name'] を使って値を取得する
    3. echoで画面に表示する
    */

    $name = $_GET['name'];
    echo "こんにちは、{$name}さん！";

    // get_hello.php?name=akane の場合
    // こんにちは、akaneさん！

