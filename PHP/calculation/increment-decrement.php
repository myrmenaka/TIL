<?php
    $num_1 = 7;
    $num_2 = ++$num_1;

    echo $num_1 . PHP_EOL; // 8
    echo $num_2 . PHP_EOL; // 8

    $num_3 = 7;
    $num_4 = $num_3++;

    echo $num_3 . PHP_EOL; // 8
    echo $num_4 . PHP_EOL; // 7

    // $num_4 が「7」なのは、
    // 後置加算子によって演算前に値が返されているため
    
