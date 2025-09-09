<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cookie 入力・保存処理</title>
</head>
<body>
    
    <?php
    // フォームが送信されたらCookieを保存
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        $name = $_POST['name'];
        setcookie('saved_name', $name, time() + 60 * 60 * 24); // 1日
    }
    ?>

    <form method="post" action="">
        <p>名前: <input type="text" name="name"
                value="<?php echo isset($_COOKIE['saved_name']) ? htmlspecialchars($_COOKIE['saved_name'], ENT_QUOTES, 'UTF-8') : ''; ?>">
        </p>
        <input type="submit" value="送信">
    </form>

    <?php
    if (isset($_POST['name'])) {
        echo "こんにちは、" . htmlspecialchars($_POST['name'], ENT_QUOTES, 'UTF-8') . "さん！";
    }
    ?>

</body>
</html>