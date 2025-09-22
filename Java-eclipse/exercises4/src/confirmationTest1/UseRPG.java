// 実行用クラス

package confirmationTest1;

public class UseRPG {

	public static void main(String[] args) {
		// Human型でHeroクラスのインスタンス生成
		Human h1 = new Hero(); 
		showEscape(h1); // Heroのescape()が呼ばれる
		
		// Human型でCitizenクラスのインスタンス生成
		Human h2 = new Citizen();
		showEscape(h2); // Citizenのescape()が呼ばれる
		
		/*
		 * HeroとCitizenは、Humanクラスを継承している
		 * つまり、「HeroはHumanである」、「CitizenはHumanである」という関係が成立する
		 * ここで、Human型でインスタンス生成する理由
		 * 1. ポリモーフィズムを使用するため
		 * 		・Hero型でインスタンス生成も可能だがHero専用になってしまう
		 * 		・共通インターフェースを使うメソッドに渡せない設計になる可能性がある
		 * 2. 汎用的なメソッドに渡せない
		 * 		・明示的なキャストが必要になるケースがある→再利用性が下がる
		 * 3. 将来的な拡張に弱くなる
		 * 		・他にクラスが増えたときに、Hero型で書いているとメソッドを分ける必要などが出てくる
		 * 4. テストやモックが困難になる
		 * 		・テスト時に他の型を差し替えにくい
		 * 		・モックやスタブで柔軟に差し替え可能
		 * 
		 * メリットまとめ
		 * 拡張性：新しく追加時にメソッド変更不要
		 * 再利用性：共通処理を一括で使える
		 * 保守性：型の分岐が不要で、コードがシンプルになる
		 * テスト容易性：モックやスタブで差し替えやすい
		 */
		
		
		// Heroクラスのbattle()メソッドの使用
		// instaceofはそのインスタンスが指定したクラスのインスタンスであれば
		if (h1 instanceof Hero) {
			// h1はHuman型でインスタンス生成しているので、独自メソッドの使用の際は明示的にキャストする
			Hero hero = (Hero) h1; 
			hero.battle();
		}

	}
	
	// Human型で受け取ることで、継承関係にあるすべてのサブクラスに対応できる抽象的な設計になる
	// ポリモーフィズムな設計
	public static void showEscape(Human human) {
		human.escape();
	}

}
