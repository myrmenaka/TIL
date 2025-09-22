// インターフェースを多重実装

package confirmationTest1;

// 抽象メソッドがあるので、抽象クラスとなる
public abstract class Human implements ITalk, IEscape {
	
	// 抽象メソッド
	@Override
	public abstract void escape();

	@Override
	public void talk() {
		System.out.println("話します。");
		
	}
	
	

}
