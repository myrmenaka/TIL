// int型の値を左右にシフトした値を表示

import java.util.Scanner;

public class ShiftOperation {
	
	// int型のビット構成を表示するメソッド
	static void printBits(int x) {
		for (int i = 31; i >= 0; i--)
			System.out.print(((x >>> i & 1) == 1) ? '1' : '0');
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.print("整数：");
		int x = stdIn.nextInt();
		System.out.print("シフトするビット数：");
		int n = stdIn.nextInt();
		
		System.out.print( "整数　　=　"); printBits(x);
		// 左シフト
		System.out.print("\nx << n ="); printBits(x << n);
		// 右シフト（算術シフト）
		System.out.print("\nx >> n ="); printBits(x >> n);
		// 右シフト（論理シフト）
		System.out.print("\nx >>> n ="); printBits(x >>> n);

	}

}
