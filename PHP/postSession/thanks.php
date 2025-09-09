<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>完了画面</title>
</head>
<body>

    <?php
    session_start();

    if (empty($_SESSION['complete'])) {
        header('Location: input.php');
        exit;
    }
    unset($_SESSION['complete']);
    ?>

    <h2>送信が完了しました！</h2>
    <p>ご協力ありがとうございました。</p>
    
</body>
</html>