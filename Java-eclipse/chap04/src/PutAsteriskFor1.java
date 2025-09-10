// 読み込んだ個数だけ＊を表示

import java.util.Scanner;

public class PutAsteriskFor1 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		System.out.print("何個＊を表示しますか？:");
		int n = stdIn.nextInt();
		
		// デバッガで動作を確認するとき、以下のように改行すると見やすい
		for (
				int i = 0;
				i < n;
				i++
				)
			System.out.print("*");
		
		System.out.println("終わり");

	}

}
