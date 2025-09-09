<?php
// Day17 のファイルに追記しています
// ファイルがアップロードされているか確認
if (isset($_FILES['image']) && $_FILES['image']['error'] === 0) {
    $tmpPath = $_FILES['image']['tmp_name'];
    $originalName = basename($_FILES['image']['name']);
    // $saveDir = 'uploads/';  // 下に移動


    // 追加 : 拡張子チェック
    $ext = strtolower(pathinfo($originalName, PATHINFO_EXTENSION));
    $allowed_ext = ['jpg', 'jpeg', 'png', 'gif'];

    if (!in_array($ext, $allowed_ext)) {
        echo "許可されていない拡張子です。";
        exit;
    }

    // 追加 : MIMEタイプ検証
    $finfo = finfo_open(FILEINFO_MIME_TYPE);
    $mimeType = finfo_file($finfo, $tmpPath);
    finfo_close($finfo);

    $allowed_mime = ['image/jpeg', 'image/png', 'image/gif'];
    if (!in_array($mimeType, $allowed_mime)) {
        echo "不正なファイル形式です。";
        exit;
    }

    // 移動 : 保存処理
    $saveDir = 'uploads/';
    // 保存先のディレクトリがなければ作成
    if (!is_dir($saveDir)) {
        mkdir($saveDir, 0755, true);
    }

    // 追加 : ファイル名をユニークにする
    $newFileName = uniqid('img_', true) . '.' . $ext;
    // 保存パスを指定　// Day18 ファイル名の変数を変更
    $savePath = $saveDir . $newFileName;

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

