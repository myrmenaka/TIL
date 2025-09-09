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


