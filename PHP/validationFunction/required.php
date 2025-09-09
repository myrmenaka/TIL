<?php

function validate_required($inputs, $required_keys) {
    $errors = [];
    foreach ($required_keys as $key) {
        if (!isset($inputs[$key]) || trim($inputs[$key]) === '') {
            $errors[$key] = "{$key}は必須です。";
        }
    }
    return $errors;
}

// 使用例
$required = ['name', 'email', 'message'];
$errors = validate_required($_POST, $required);
