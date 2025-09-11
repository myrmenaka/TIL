// 2値の最大値と3値の最大値を求めるメソッド（オーバーロード）

import java.util.Scanner;

// 設計図クラス
public class Max {
	// a, bの最大値を返却するメソッド
	static int max(int a, int b) {
		return a > b ? a: b;
	}
	
	// a, b, cの最大値を返却するメソッド
	static int max(int a, int b, int c) {
		int max = a;
		if (b > max) max = b;
		if (c > max) max = c;
		return max;
	}
	
	// 実行用クラス
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		// キーボード入力
		System.out.print("xの値："); int x = stdIn.nextInt();
		System.out.print("yの値："); int y = stdIn.nextInt();
		System.out.print("zの値："); int z = stdIn.nextInt();
		
		// 2値の最大値
		// 引数が2つのため、「a, bの最大値を返却するメソッド」が選択される
		System.out.println("x, yの最大値は" + max(x, y) + "です。");
		// 3値の最大値
		// 引数が3つのため、「a, b, cの最大値を返却するメソッド」が選択される
		System.out.println("x, y, zの最大値は" + max(x, y, z) + "です。");
	}

}
