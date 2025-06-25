<?php

    // POSTから値を取得
    $name = $_POST['name'];
    $email = $_POST['email'];
    $message = $_POST['message'];

    // 空欄チェック
    // exit命令により、条件分岐に当てはまる場合以降の処理を実行しない
    if (empty($name) || empty($email) || empty($message)) {
        echo "すべての項目を入力してください。";
        exit;
    }

    // サニタイズ
    $name = htmlspecialchars($_POST['name'], ENT_QUOTES, 'UTF-8');
    $email = htmlspecialchars($_POST['email'], ENT_QUOTES, 'UTF-8');
    $message = htmlspecialchars($_POST['message'], ENT_QUOTES, 'UTF-8');

    // 表示処理
    echo "こんにちは、{$name}さん！<br>"; 
    echo "メールアドレスは {$email} ですね。<br>"; 
    echo "あなたの好きな言葉は……<br>"; 
    echo nl2br($message); // 改行をHTMLで表示

    // 値を取得 → 空欄チェック → サニタイズ
    // 順番大事