<?php
require 'db_connect.php';

// データベースから全会員情報を取得
$stmt = $pdo->query("SELECT * FROM members");
$members = $stmt->fetchAll();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>会員一覧</title>
</head>
<body>
    <h1>会員一覧</h1>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>名前</th>
            <th>メール</th>
            <th>操作</th>
        </tr>

        <?php foreach ($members as $member): ?>
        <tr>
            <td><?= htmlspecialchars($member['id']) ?></td>
            <td><?= htmlspecialchars($member['name']) ?></td>
            <td><?= htmlspecialchars($member['email']) ?></td>
            <td>
                <a href="edit.php?id=<?= $member['id'] ?>">編集</a> |
                <a href="delete.php?id=<?= $member['id'] ?>" onclick="return confirm('本当に削除しますか？')">削除</a>
            </td>
        </tr>
        <?php endforeach; ?>
    </table>
</body>
</html>