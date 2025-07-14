<?php
session_start();
require_once './validate.php';

$required = ['name', 'email', 'message'];
$errors = validate_required($_POST, $required);

if (!empty($errors)) {
    $_SESSION['errors'] = $errors;
    $_SESSION['inputs'] = $_POST;
    header('Location: input.php');
    exit;
}

// ※セッションが動作しなかった理由
$_SESSION['inputs'] = $_POST;

?>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>確認画面</title>
</head>
<body>
    
    <h3>内容をご確認ください</h3>
    <p>名前：<?= htmlspecialchars($_POST['name'], ENT_QUOTES, 'UTF-8') ?></p>
    <p>メールアドレス：<?= htmlspecialchars($_POST['email'], ENT_QUOTES, 'UTF-8') ?></p>
    <p>メッセージ：<br><?= nl2br(htmlspecialchars($_POST['message'], ENT_QUOTES, 'UTF-8')) ?></p>

    <form action="complete.php" method="post">
        <input type="hidden" name="name" value="<?= htmlspecialchars($_POST['name'], ENT_QUOTES, 'UTF-8') ?>">
        <input type="hidden" name="email" value="<?= htmlspecialchars($_POST['email'], ENT_QUOTES, 'UTF-8') ?>">
        <input type="hidden" name="message" value="<?= htmlspecialchars($_POST['message'], ENT_QUOTES, 'UTF-8') ?>">
        <input type="submit" value="送信する">
    </form>

    <form action="input.php" method="post">
        <input type="submit" value="修正する">
    </form>

</body>
</html>