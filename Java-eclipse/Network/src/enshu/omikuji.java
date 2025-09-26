// 乱数によりランダムにおみくじの結果が表示されるプログラム
package enshu;

import java.util.Random;

public class omikuji {

	public static void main(String[] args) {
		System.out.println("おみくじの結果は・・・" + getOmikuji());
		
		// 1.
		String omikuji_1 = getOmikuji_1();
		System.out.println("あなたの運勢は:" + omikuji_1);
		// 2.
		String omikuji_2 = getOmikuji_2();
		System.out.println("あなたの運勢は:" + omikuji_2);
		// 3.
		String omikuji_3 = getOmikuji_3();
		System.out.println("あなたの運勢は:" + omikuji_3);

	}
	
	public static String getOmikuji() {
		double rand = Math.random(); // 0.0以上で1.0より小さい、正の符号の付いたdouble値
		
		if (rand < 0.25) {
			return "大吉";
		} else if (rand < 0.5) {
			return "中吉";
		} else if (rand < 0.75) {
			return "小吉";
		} else {
			return "凶";
		}
		
	}
	
	// 1. switch
	private static String getOmikuji_1() {
		int random = new Random().nextInt(4);
		
		String omikuji = "";
		switch (random) {
		case 0 : omikuji = "大吉";
				break;
		case 1 : omikuji = "中吉";
				break;
		case 2 : omikuji = "小吉";
				break;
		default : omikuji = "凶";
				break;
		}
		return omikuji;
	}
	// 2. switch(java14以降)
	private static String getOmikuji_2() {
		int random = new Random().nextInt(4);
		
		String omikuji = switch (random) {
		case 0 -> "大吉";
		case 1 -> "大吉";
		case 2 -> "大吉";
		default -> "大吉";
		
		};
		return omikuji;
	}
	// 3. 配列
	private static String getOmikuji_3() {
		String[] omikujiArrays = {"大吉", "中吉", "小吉", "凶"};
		int index = new java.util.Random().nextInt(omikujiArrays.length);
		return omikujiArrays[index];
	}
}
