<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>セッション</title>
</head>
<body>
    
    <?php session_start(); ?>
    <form action="./confirm.php" method="post">
        <p>名前: <input type="text" name="name"></p>
        <p>メール: <input type="email" name="email"></p>
        <input type="submit" value="確認画面へ">
    </form>


</body>
</html>
