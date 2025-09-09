<?php
require 'db_connect.php';
$id = $_POST['id'];
$name = $_POST['name'];
$email = $_POST['email'];

$stmt = $pdo->prepare("UPDATE members SET name=?, email=? WHERE id=?");
$stmt->execute([$name, $email, $id]);

header("Location: index.php");
exit;
?>