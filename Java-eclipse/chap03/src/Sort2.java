// 二つの変数を昇順（小さい順）にソート

import java.util.Scanner;

public class Sort2 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.print("整数a:");
		int a = stdIn.nextInt();
		System.out.print("整数b:");
		int b = stdIn.nextInt();
		
		if (a > b) {   // aがbより大きければ
			int t = a; // それらの値を交換
			a = b;
			b = t;
			System.out.println("入れ替えました。");
		} else {
			System.out.println("すでに昇順です。");
		}
		System.out.println("昇順にソートしました");
		System.out.println("変数aは" + a + "です。");
		System.out.println("変数bは" + b + "です。");
	}

}
