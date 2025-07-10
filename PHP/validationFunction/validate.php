<?php
function validate_required($inputs, $required_keys) {
    $errors = [];
    foreach ($required_keys as $key) {
        if (!isset($inputs[$key]) || trim($inputs[$key]) === '') {
            $errors[$key] = "{$key}は必須項目です。";
        }
    }
    return $errors;
}

// 実際にフォームから送信された値を受け取る
$required = ['name', 'email', 'age'];
$errors = validate_required($_POST, $required);

// エラーがあれば表示
if (!empty($errors)) {
    foreach ($errors as $field => $error) {
        echo "<p>{$error}</p>";
    }
} else {
    echo "<h3>✔ 入力が正常です！</h3>";
    echo "<p>名前: " . htmlspecialchars($_POST['name']) . "</p>";
    echo "<p>メール: " . htmlspecialchars($_POST['email']) . "</p>";
    echo "<p>年齢: " . htmlspecialchars($_POST['age']) . "</p>";
}
?>