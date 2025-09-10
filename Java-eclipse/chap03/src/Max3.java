//　三つの整数値の最大値を求める

import java.util.Scanner;

public class Max3 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner stdIn = new Scanner("3 5 7");
		
		System.out.print("整数値a:");
		int a = stdIn.nextInt();
		System.out.print("整数値b:");
		int b = stdIn.nextInt();
		System.out.print("整数値c:");
		int c = stdIn.nextInt();
		
		int max = a;   // 暫定でaの値で最大値を指す変数maxを初期化
		if (b > max) { // bが最大値maxを超えたら
			max = b;   // maxにbの値を代入
		}
		if (c > max) { // cが最大値maxを超えたら
			max = c;   // maxにcの値を代入
		}
		
		System.out.println("最大値は" + max + "です。");

	}

}
