// 識別子の有効範囲を確認する

class Scope {
	// メンバ変数
	static int x = 700;
	
	// ScopeクラスのprintX()メソッド
	static void printX() {
		System.out.println("x =" + x);
	}
	
	// mainメソッド
	public static void main(String[] args) {
		System.out.println("x =" + x);
		// ローカル変数
		int x = 800;
		System.out.println("x =" + x);
		// メンバ変数を指定する場合　→　クラス名.メンバ変数
		System.out.println("Scope.x =" + Scope.x);
		// printX()メソッド呼出
		printX();

	}

}
