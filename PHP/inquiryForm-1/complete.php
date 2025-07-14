<?php
session_start(); // セッション操作のため必要

if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    header('Location: input.php');
    exit;
}

// セッションを破棄して入力値をクリア！
session_unset();      // セッション変数をすべて削除
session_destroy();    // セッション自体も破棄

$name = htmlspecialchars($_POST['name'], ENT_QUOTES, 'UTF-8');
$email = htmlspecialchars($_POST['email'], ENT_QUOTES, 'UTF-8');
$message = htmlspecialchars($_POST['message'], ENT_QUOTES, 'UTF-8');

// ここでメール送信やDB保存などの処理を追加してもOK
?>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>完了画面</title>
</head>
<body>

    <h2>お問い合わせを受け付けました</h2>
    <p>名前：<?= $name ?></p>
    <p>メールアドレス：<?= $email ?></p>
    <p>メッセージ：<br><?= nl2br($message) ?></p>
    
    <p><a href="input.php">入力フォームに戻る</a></p>

</body>
</html>