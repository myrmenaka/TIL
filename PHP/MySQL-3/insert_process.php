<?php
$title = $_POST['title'] ?? '';
$description = $_POST['description'] ?? '';

if ($title === '' || $description === '') {
    exit('入力値が不足しています');
}

try {
    $pdo = new PDO('mysql:host=localhost;dbname=portfolio_db;charset=utf8mb4', 'root', '');

    $sql = "INSERT INTO learning_logs (title, description, created_at) VALUES (:title, :description, NOW())";
    $stmt = $pdo->prepare($sql);

    $stmt->bindParam(':title', $title);
    $stmt->bindParam(':description', $description);
    $stmt->execute();

    echo "登録が完了しました";

} catch (PDOException $e) {
    echo 'DBエラー: ' . htmlspecialchars($e->getMessage());
}
?>