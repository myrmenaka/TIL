<?php

    // 入力値を取得し、サニタイズ
    $username = htmlspecialchars($_POST['username'], ENT_QUOTES, 'UTF-8');
    echo "こんにちは、{$username} さん！";

    