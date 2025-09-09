<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>簡易ショッピングカート</title>
</head>
<body>
    
    <?php session_start(); ?>
    <h2>商品一覧</h2>

    <form action="./cart.php" method="post">
        <input type="hidden" name="id" value="1">
        <input type="hidden" name="name" value="りんご">
        <input type="hidden" name="price" value="150">
        数量: <input type="number" name="quantity" value="1" min="1">
        <input type="submit" value="カートに追加">
    </form>

    <p><a href="./cart.php">カートを見る</a></p>
    
</body>
</html>