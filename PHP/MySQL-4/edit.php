<?php
require `db_connect.php`;
$id = $_GET['id'];
$stmt = $pdo->prepare("SELECT * FROM members WHERE id=?");
$stmt->execute([$id]);
$member = $stmt->fetch();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    
    <form action="./update.php" method="post">
        <input type="hidden" name="id" value="<?= htmlspecialchars($member['id']) ?>">
        名前: <input type="text" name="name" value="<?= htmlspecialchars($member['name']) ?>"><br>
        メール: <input type="email" name="email" value="<?= htmlspecialchars($member['email']) ?>"><br>
        <input type="submit" value="更新">
    </form>

</body>
</html>