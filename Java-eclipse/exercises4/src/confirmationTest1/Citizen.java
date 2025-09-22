// 具象クラス

package confirmationTest1;

// Humanクラスを継承している
public class Citizen extends Human {
	
	// Humanクラスの抽象メソッド
	// Humanクラスで抽象メソッドなのでオーバーライド必須
	@Override
	public void escape() {
		System.out.println("市民は、逃げます");
		
	}
	
	

}
