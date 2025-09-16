// サブクラス
// ペットクラスをオーバーライド

public class RobotPet extends Pet {
	
	// コンストラクタ
	public RobotPet(String name, String masterName) {
		super(name, masterName); // スーパークラスのコンストラクタ
	}
	
	// 自己紹介
	// スーパークラスのintroduceメソッドのオーバーライド
	
	// @Override は「このメソッドは親のメソッドを上書きしてますよ」という宣言
	// 親クラスに同名メソッドがないとき、コンパイルエラーとなる
	// サブクラス側に書く
	@Override // アナテイション　
	public void introduce() {
		System.out.println("◇私はロボット。名前は" + getName() + "。");
		System.out.println("◇ご主人様は" + getMasterName() + "。");
	}
	
	// 家事をする
	// RobotPet専用のメソッド
	public void work(int sw) {
		switch(sw) {
		case 0 : System.out.println("掃除をします。");
			break;
		case 1 : System.out.println("洗濯をします。");
			break;
		case 2 : System.out.println("炊事をします。");
			break;
		}
	}

}
