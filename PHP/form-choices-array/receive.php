<?php
    // 入力チェック
    if (!isset($_POST['gender'])) {
        echo "性別を選択してください。";
        exit;
    };

    // $_POST['gender']のエスケープ
    $gender = htmlspecialchars($_POST['gender'], ENT_QUOTES, 'UTF-8');

    // 趣味の複数の値を配列で受け取りチェック
    if (isset($_POST['hobby'])) {
        $hobbyies = $_POST['hobby'];
        echo "あなたの性別: {$gender}<br>";
        echo "あなたの趣味: <br>";
        foreach ($hobbyies as $hobby) {
            echo htmlspecialchars($hobby, ENT_QUOTES, 'UTF-8') . "<br>";
        }
    } else {
        echo "趣味が選択されていません。";
    };
    