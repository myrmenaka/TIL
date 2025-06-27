<?php
    $name = $_POST['name'];
    $email = $_POST['email'];
    $age = $_POST['age'];

    // 空欄チェック
    if (empty($name) || empty($email) || empty($age)) {
        echo "すべての項目を入力してください。";
        exit;
    };

    // メールアドレスのバリデーション
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        echo "正しいメールアドレスを入力してください。";
        exit;
    };

    // 年齢（数字）のバリデーション
    if (!is_numeric($age)) {
        echo "年齢は数字で入力してください。";
        exit;
    };

    // エスケープ処理（XSS対策）
    $name = htmlspecialchars($name, ENT_QUOTES, 'UTF-8');
    $email = htmlspecialchars($email, ENT_QUOTES, 'UTF-8');
    $age = htmlspecialchars($age, ENT_QUOTES, 'UTF-8');

    // 正常時の出力
    echo "こんにちは、{$name}さん。<br>";
    echo "メールアドレス: {$email}<br>";
    echo "年齢: {$age}歳";


