<?php
// ファイルがアップロードされているか確認
if (isset($_FILES['image']) && $_FILES['image']['error'] === 0) {
    $tmpPath = $_FILES['image']['tmp_name'];
    $originalName = basename($_FILES['image']['name']);
    $saveDir = 'uploads/';

    // 保存先のディレクトリがなければ作成（パーミッションに注意）
    if (!is_dir($saveDir)) {
        mkdir($saveDir, 0755, true);
    }

    // 保存パスを指定
    $savePath = $saveDir . $originalName;

    // ファイルを保存
    if (move_uploaded_file($tmpPath, $savePath)) {
        echo "アップロード成功 <br>";
        echo "<img src='{$savePath}' alt='アップロード画像' style='max-width:300px;'>";
    } else {
        echo "アップロードに失敗しました。";
    }
} else {
    echo "ファイルが選択されていないか、エラーが発生しました。";
};

