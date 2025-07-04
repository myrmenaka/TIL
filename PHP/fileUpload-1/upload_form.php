<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ファイルアップロード</title>
</head>
<body>
    
    <form action="./upload.php" method="post" enctype="multipart/form-data">
        <p>画像を選んでください : <input type="file" name="image"></p>
        <input type="submit" value="アップロード">
    </form>

</body>
</html>