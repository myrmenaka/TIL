// 二つの整数値の小さいほうの値と大きいほうの値を求めて表示

import java.util.Scanner;

public class MinMax {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.print("整数a:");
		int a = stdIn.nextInt();
		System.out.print("整数b:");
		int b = stdIn.nextInt();
		
		int min, max; // 小さいほうの値, 大きいほうの値
		if (a < b) {  // aがbより小さければ
			min = a;
			max = b;
		} else {      // そうでなければ
			min = b;
			max = a;
		}
		
		
		if (a < b) { min = a; max = b; }
		else { min = b; max = a; }
		
		/*
		{}がない場合の文法上の解釈
		
		if (a < b) min = a; // if文
		max = b; // 代入分
		else min = b;       // else文（直前にif文が無いのでコンパイルエラーとなる）
		max = a;            // 代入分
		*/
		
		System.out.println("小さいのは" + min + "です。");
		System.out.println("大きいのは" + max + "です。");
	}

}
