<?php
require 'db_connect.php';
session_start();

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $name = $_POST['name'];
    $email = $_POST['email'];
    $pass = $_POST['password'];

    // パスワードをハッシュ化
    $hash = password_hash($pass, PASSWORD_DEFAULT);

    // メールアドレス重複チェック
    $stmt = $pdo->prepare("SELECT * FROM users WHERE email=?");
    $stmt->execute([$email]);
    if ($stmt->fetch()) {
        echo "このメールアドレスは既に登録されています";
    } else {
        $stmt = $pdo->prepare("INSERT INTO users(name, email, password) VALUES(?,?,?)");
        $stmt->execute([$name, $email, $hash]);
        echo "登録が完了しました";
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>会員登録フォーム</title>
</head>
<body>
    
    <form method="post">
        名前: <input type="text" name="name" required><br>
        メール: <input type="email" name="email" required><br>
        パスワード: <input type="password" name="password" required><br>
        <input type="submit" value="登録">
    </form>

</body>
</html>