<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>簡易ショッピングカート</title>
</head>
<body>
    
    <?php
    session_start();

    // POSTで追加された商品をセッションに保存
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        $id = $_POST['id'];
        $name = $_POST['name'];
        $price = (int)$_POST['price'];
        $quantity = (int)$_POST['quantity'];

        if (!isset($_SESSION['cart'][$id])) {
            $_SESSION['cart'][$id] = [
                'name' => $name,
                'price' => $price,
                'quantity' => $quantity
            ];
        } else {
            $_SESSION['cart'][$id]['quantity'] += $quantity;
        }
    }
    ?>

    <h2>カートの中身</h2>
    <?php if (!empty($_SESSION['cart'])): ?>
        <ul>
            <?php foreach ($_SESSION['cart'] as $id => $item): ?>
                <li>
                    <?php echo htmlspecialchars($item['name'], ENT_QUOTES, 'UTF-8'); ?> -
                    ￥<?php echo $item['price']; ?> × <?php echo $item['quantity']; ?> =
                    ￥<?php echo $item['price'] * $item['quantity']; ?>
                    <a href="./delete.php?id=<?php echo $id; ?>">削除</a>
                </li>
            <?php endforeach; ?>
        </ul>
    <?php else: ?>
        <p>カートは空です。</p>
    <?php endif; ?>

    <p><a href="./shop.php">商品一覧に戻る</a></p>

</body>
</html>