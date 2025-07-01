<?php
    // 存在チェック、POSTデータの受け取り
    if (isset($_POST['gender']) && isset($_POST['fruit'])) {
        $gender = $_POST['gender'];
        $fruit = $_POST['fruit'];

        // バリデーション（選択肢が有効かチェック）
        $validGenders = ['男性', '女性'];
        $validFruits = ['りんご', 'バナナ', 'オレンジ'];

        if (!in_array($gender, $validGenders)) {
            echo "性別が正しくありません。";
            exit;
        };

        if (!in_array($fruit, $validFruits)) {
            echo "果物が正しくありません。";
            exit;
        };

        // 結果の表示（エスケープ処理含む）
        echo "性別: " . htmlspecialchars($gender, ENT_QUOTES, 'UTF-8') . "<br>";
        echo "果物: " . htmlspecialchars($fruit, ENT_QUOTES, 'UTF-8');
    } else {
        echo "フォームからデータが送信されていません。";
    };