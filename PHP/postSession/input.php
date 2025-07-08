<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>確認画面付きフォーム・入力画面</title>
</head>
<body>
    
    <form action="./confirm.php" method="post">
        <p>名前: <input type="text" name="name" required></p>
        <p>メール: <input type="email" name="email" required></p>
        <input type="submit" value="確認する">
    </form>

</body>
</html>