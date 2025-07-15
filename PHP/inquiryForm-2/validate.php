<?php
function sanitize_email($email) {
    // 改行や不正文字を削除
    $email = str_replace(["\r", "\n", "%0a", "%0d"], '', $email);

    // メール形式をチェック
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        return '';
    }

    return $email;
}

function validate_required($inputs, $required_keys) {
    $errors = [];

    foreach ($required_keys as $key) {
        if (!isset($inputs[$key]) || trim($inputs[$key]) === '') {
            $errors[$key] = "{$key}は必須項目です。";
        }
    }

    // メール形式チェック
    if (!empty($inputs['email']) && !filter_var($inputs['email'], FILTER_VALIDATE_EMAIL)) {
        $errors['email'] = "有効なメールアドレスを入力してください。";
    }

    // 名前の文字数チェック（例：50文字まで）
    if (!empty($inputs['name']) && mb_strlen($inputs['name']) > 50) {
        $errors['name'] = "名前は50文字以内で入力してください。";
    }

    // メッセージの長さチェック（500文字まで）
    if (!empty($inputs['message']) && mb_strlen($inputs['message']) > 500) {
        $errors['message'] = "メッセージは500文字以内で入力してください。";
    }

    return $errors;
}


