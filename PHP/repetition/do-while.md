## whileとdo-whileの違い

### while文

最初に条件を判定し、条件が true の場合にのみ処理を実行する  
条件が最初から偽の場合、処理は一度も実行されない  

### do-while文

最初に処理を実行し、その後で条件を判定する  
そのため、条件が最初から false であっても、少なくとも一度は処理が実行される  


## do-while文のメリット

ループを必ず一度は実行したい場合に有効  
例えば、ユーザーに入力を促す処理を必ず一度は実行したい場合などに使える  
無限ループを意図的に作りたい場合にも使用できる  

## do-while文のデメリット

条件が最初から false の場合でも、一度は処理が実行されるため、意図しない動作を引き起こす可能性がある  


## まとめ

do-while文は、while文とは異なり、ループ内の処理を必ず一度は実行したい場合に有効な構文  
状況に応じて使い分けることで、より柔軟なプログラムを作成できる  

`while` 条件の判定を先に行う  
`do-while` 処理後に判定を行う  

