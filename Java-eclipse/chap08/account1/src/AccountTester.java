// 銀行口座クラス【第一版】とそれをテストするクラス

// 銀行口座クラス【第一版】
// 設計図クラス
class Account {
	// メンバ変数（static無しなので、インスタンス変数）
	String name;  // 口座名義
	String no;    // 口座番号
	long balance; // 預金残高
}

// 銀行口座クラスをテストするクラス
// 実行用クラス
public class AccountTester {
	
	public static void main(String[] args) {
		// 足立さんの口座
		Account adachi = new Account();
		// 仲田さんの口座
		Account nakata = new Account();

		// 足立さんの口座情報
		adachi.name = "足立幸一";
		adachi.no = "123456";
		adachi.balance = 1000;
		
		// 仲田さんの口座情報
		nakata.name = "仲田真二";
		nakata.no = "654321";
		nakata.balance = 200;
		
		adachi.balance -= 200; // 足立さんの口座から200円おろす
		nakata.balance += 100; // 仲田さんの口座に100円入金する
		
		System.out.println("■足立さんの口座");
		System.out.println("　口座名義 ： " + adachi.name);
		System.out.println("　口座番号 ： " + adachi.no);
		System.out.println("　預金残高 ： " + adachi.balance);
		
		System.out.println("■仲田さんの口座");
		System.out.println("　口座名義 ： " + nakata.name);
		System.out.println("　口座番号 ： " + nakata.no);
		System.out.println("　預金残高 ： " + nakata.balance);
	}

}
