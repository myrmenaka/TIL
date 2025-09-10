//　読み込んだ整数値の桁数（ゼロ、1桁、2桁以上）を判定

import java.util.Scanner;

public class DigitsNo1 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.print("整数値:");
		int n = stdIn.nextInt();
		
		if (n == 0)
			System.out.println("ゼロです。");
		else if (n >= -9 && n <= 9)
			System.out.println("1桁です。");
		else
			System.out.println("2桁以上です。");

	}

}
