<?php
    // フォームから送信された「fruits」の値が存在し、かつ配列であるか確認
    if (isset($_POST['fruits']) && is_array($_POST['fruits'])) {
        $selectFruit = $_POST['fruits'];  // 変数に代入
        echo "あなたの好きなフルーツは ";  // 出力メッセージの冒頭

        // フルーツの要素数を取得
        $count = count($selectFruit);

        // 各フルーツを1つずつループ処理
        foreach ($selectFruit as $i => $fruit) {
            // 出力時のエスケープ
            echo htmlspecialchars($fruit, ENT_QUOTES, 'UTF-8');

            // 最後の要素以外には読点
            if ($i < $count -1) {
                echo "、"; 
            }
        }
        // 文末の出力
        echo "です。";  // foreach外での出力
    } else {
        // 未選択の場合の処理
        echo "選択されていません。";
    };

