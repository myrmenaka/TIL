<?php
    // 値の存在と数値のバリデーション
    if (isset($_POST['num1'], $_POST['num2']) && is_numeric($_POST['num1']) && is_numeric($_POST['num2'])) {
        // 変数に代入
        $num1 = $_POST['num1'];
        $num2 = $_POST['num2'];

        try {
            if ($num2 == 0) {
                // 条件式の際にエラーを投げる
                throw new Exception("0で割ることはできません。");
            }
            // エラー発生時はスキップされる
            $result = $num1 / $num2;
            echo "結果: {$result}";
        } catch (Exception $e) {
            // エラー発生したらすぐcatchに移動、エラーをキャッチして処理が実行される
            echo "エラー: " . $e->getMessage(); // エラーメッセージの取得
        }
    } else {
        // tryブロックでスローされる前に引っかかった場合に処理される
        echo "数値を選択してください。";
    };