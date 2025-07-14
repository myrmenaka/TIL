<?php
session_start();

// 入力値とエラーの取得
$inputs = $_SESSION['inputs'] ?? [];
$errors = $_SESSION['errors'] ?? [];

// 表示後はセッションを削除しておく
unset($_SESSION['inputs'], $_SESSION['errors']);
?>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>お問い合わせフォーム</title>
</head>
<body>

    <form action="./confirm.php" method="post">
        <p>お問い合わせ</p>

        <p>名前<br>
            <input type="text" name="name" placeholder="山田 太郎" 
                   value="<?= htmlspecialchars($inputs['name'] ?? '', ENT_QUOTES, 'UTF-8') ?>">
            <?php if (!empty($errors['name'])): ?>
                <br><span style="color:red"><?= htmlspecialchars($errors['name'], ENT_QUOTES, 'UTF-8') ?></span>
            <?php endif; ?>
        </p>

        <p>メールアドレス<br>
            <input type="email" name="email" placeholder="test@email.com" 
                   value="<?= htmlspecialchars($inputs['email'] ?? '', ENT_QUOTES, 'UTF-8') ?>">
            <?php if (!empty($errors['email'])): ?>
                <br><span style="color:red"><?= htmlspecialchars($errors['email'], ENT_QUOTES, 'UTF-8') ?></span>
            <?php endif; ?>
        </p>

        <p>メッセージ<br>
            <textarea name="message" placeholder="メッセージ"><?= htmlspecialchars($inputs['message'] ?? '', ENT_QUOTES, 'UTF-8') ?></textarea>
            <?php if (!empty($errors['message'])): ?>
                <br><span style="color:red"><?= htmlspecialchars($errors['message'], ENT_QUOTES, 'UTF-8') ?></span>
            <?php endif; ?>
        </p>

        <input type="submit" value="確認画面へ">
    </form>

    
</body>
</html>