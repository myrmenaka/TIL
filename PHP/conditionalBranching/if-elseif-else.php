<?php
    $score = 85;

    if ($score >= 90) {
        echo "評価：S";
    } elseif ($score >= 80) {
        echo "評価：A";
    } elseif ($score >= 70) {
        echo "評価：B";
    } else {
        echo "評価：C以下";
    };

    // 評価：A