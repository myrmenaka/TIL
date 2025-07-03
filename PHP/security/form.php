<?php
session_start();
// トークン生成
$token = bin2hex(random_bytes(32));
$_SESSION['token'] = $token;
?>

<form action="./confirm.php" method="post">
    <p>名前: <input type="text" name="name"></p>
    <input type="hidden" name="token" value="<?php echo htmlspecialchars($token, ENT_QUOTES, 'UTF-8'); ?>">
    <input type="submit" value="送信">
</form>