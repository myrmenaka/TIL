<?php
session_start();

// トークン検証
if (!isset($_POST['token']) || $_POST['token'] !== $_SESSION['token']) {
    echo "不正なアクセスです。";
    exit;
};

// 正常な処理
$name = htmlspecialchars($_POST['name'], ENT_QUOTES, 'UTF-8');
echo "こんにちは、{$name}さん！";
