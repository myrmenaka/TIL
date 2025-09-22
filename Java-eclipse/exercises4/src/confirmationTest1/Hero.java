// 具象クラス

package confirmationTest1;

// Humanクラスを継承している
public class Hero extends Human {
	
	// Heroクラスの独自メソッド
	public void battle() {
		System.out.println("戦います");
	}
	
	// Humanクラスの抽象メソッド
	// Humanクラスで抽象メソッドなのでオーバーライド必須
	@Override
	public void escape() {
		System.out.println("勇者は、逃げます");
		
	}

}
