<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>確認画面</title>
</head>
<body>
    
    <?php
    session_start();

    // POSTデータの受け取りとセッション保存
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        $_SESSION['form'] = [
            'name' => $_POST['name'],
            'email' => $_POST['email']
        ];
    } else {
        header('Location: input.php');
        exit;
    }

    $form = $_SESSION['form'];
    ?>

    <h2>確認画面</h2>
    <p>名前: <?php echo htmlspecialchars($form['name'], ENT_QUOTES, 'UTF-8'); ?></p>
    <p>メール: <?php echo htmlspecialchars($form['email'], ENT_QUOTES, 'UTF-8'); ?></p>

    <form action="./process.php" method="post">
        <input type="submit" value="送信する">
    </form>
    <form action="./input.php" method="get">
        <input type="submit" value="修正する">
    </form>

</body>
</html>