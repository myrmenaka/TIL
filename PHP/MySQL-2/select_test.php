<?php

use PhpOffice\PhpSpreadsheet\Chart\Title;

$dsn = 'mysql:host=localhost;dbname=portfolio_db;charset=utf8mb4';
$user = 'root';
$password = '';

try {
    $pdo = new PDO($dsn, $user, $password);

    $sql = "SELECT * FROM learning_logs";
    $stmt = $pdo->query($sql);

    foreach ($stmt as $row) {
        echo "タイトル: " . htmlspecialchars($row['title']) . "<br>";
        echo "内容: " . nl2br(htmlspecialchars($row['description'])) . "<br><hr>";
    }
} catch (PDOException $e) {
    echo "エラー: " . $e->getMessage();
}
?>