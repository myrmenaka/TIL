//　乱数をさいころに見立てて２つのさいころを振る

import java.util.Random;

public class DiceApplication {

	public static void main(String[] args) {
		Random dice1 = new Random();
		Random dice2 = new Random();
		
		System.out.println(
				"さいころ一つ目" + dice1.nextInt(6) +
				"、二つ目" + dice2.nextInt(6));
		System.out.println("もう一度");
		System.out.println(
				"さいころ一つ目" + dice1.nextInt(6) +
				"、二つ目" + dice2.nextInt(6));

	}

}
