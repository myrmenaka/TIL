<?php
require 'db_connect.php';
$id = $_GET['id'];
$stmt = $pdo->prepare("DELETE FROM members WHERE id=?");
$stmt->execute([$id]);

header("Location: index.php");
exit;
?>