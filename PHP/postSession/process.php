<?php
session_start();

if (!isset($_SESSION['form'])) {
    header('Location: input.php');
    exit;
}

// ここでDB登録処理やメール送信など行う（今回は省略）

// 一度だけ表示させるためのフラグ
$_SESSION['complete'] = true;

// 二重送信防止のためセッション内容を一部削除
unset($_SESSION['form']);

header('Location: thanks.php');
exit;
