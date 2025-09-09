<?php

session_start();

// バリデーション（空かどうか、形式が正しいか、など）
if (isset($_POST['name'], $_POST['email']) && $_POST['name'] !== '' && filter_var($_POST['email'], FILTER_VALIDATE_EMAIL)) {
    // バリデーションOKならセッションに保存
    $_SESSION['name'] = $_POST['name'];
    $_SESSION['email'] = $_POST['email'];
} else {
    echo "名前またはメールアドレスが正しく入力されていません。";
    exit;
}

// エスケープして表示
$name = htmlspecialchars($_SESSION['name'], ENT_QUOTES, 'UTF-8');
$email = htmlspecialchars($_SESSION['email'], ENT_QUOTES, 'UTF-8');
?>

<!-- HTML確認画面 -->
<p>名前: <?php echo $name; ?></p>
<p>メール: <?php echo $email; ?></p>