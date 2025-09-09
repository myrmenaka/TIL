<?php
$dsn = 'mysql:host=localhost;dbname=portfolio_db;charset=utf8mb4';
$user = 'root';
$password = '';

try {
    $pdo = new PDO($dsn, $user, $password);
    echo "接続成功";
} catch (PDOException $e) {
    echo "接続失敗: " . htmlspecialchars($e->getMessage(), ENT_QUOTES, 'UTF-8');
}
?>