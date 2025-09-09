<?php
session_start(); // セッション操作のため必要

if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    header('Location: input.php');
    exit;
}

// セッションを破棄して入力値をクリア！
session_unset();      // セッション変数をすべて削除
session_destroy();    // セッション自体も破棄

// 入力内容の表示はメール内のみなのでエスケープ処理しない
// $name = htmlspecialchars($_POST['name'], ENT_QUOTES, 'UTF-8');
// $email = htmlspecialchars($_POST['email'], ENT_QUOTES, 'UTF-8');
// $message = htmlspecialchars($_POST['message'], ENT_QUOTES, 'UTF-8');


// Day24 メール送信処理追加

// sanitize_email など追加したため記述
require_once 'validate.php'; 

// ユーザーの情報
$name_raw = $_POST['name'];
$email_raw = $_POST['email'];
$message_raw = $_POST['message'];

// メールアドレスの安全化（ヘッダーインジェクション対策）
$email_safe = sanitize_email($email_raw);

// メール送信のための文字エンコーディング設定（日本語対応）
mb_language("Japanese");
mb_internal_encoding("UTF-8");

// 管理者宛てメール
$admin_to = "system@example.com"; // 管理者の受信アドレス
$admin_subject = "【お問い合わせ】{$name_raw}様よりメッセージが届きました";
$admin_body = "以下の内容が送信されました：\n\n名前：{$name_raw}\nメールアドレス：{$email_raw}\n\nメッセージ：\n{$message_raw}";

mb_send_mail($admin_to, $admin_subject, $admin_body, "From: system@example.com");

// ユーザー宛て確認メール
$user_subject = "【お問い合わせありがとうございます】";
$user_body = <<<EOT
{$name_raw}様

お問い合わせありがとうございます。
以下の内容を受け付けました。

-----------------------------------
名前：{$name_raw}
メールアドレス：{$email_raw}
メッセージ：
{$message_raw}
-----------------------------------

ご返信までしばらくお待ちください。

（このメールは自動返信です）

EOT;

$notice_message = '';

if ($email_safe !== '') {
    mb_send_mail($email_safe, $user_subject, $user_body, "From: system@example.com");
} else {
    // 不正なアドレスなら送信しない＋画面に注意表示など
    $notice_message = '<p style="color:red;">メールアドレスが不正です。送信できませんでした。</p>';
}

?>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>完了画面</title>
</head>
<body>

    <h2>送信完了しました。</h2>
    <p>管理者には正常にメールが送信されました。</p>
    <?= $notice_message ?>
    <p><a href="input.php">入力フォームに戻る</a></p>

</body>
</html>