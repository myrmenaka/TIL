
public class ArrayFor {

	public static void main(String[] args) {
		String[] fortune = {
				"犬がワンワン吠えます",
				"猫がニャーニャー鳴きます",
				"小鳥がチュンチュン鳴きます"
		};
		
		for (int i = 1; i < fortune.length; i++) {
			System.out.println(fortune[i]);
		}

	}

}
