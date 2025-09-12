// 設計図クラス
// 銀行口座クラス【第2版】とテスト用クラス

// 銀行口座クラス【第2版】
class Account {
	private String name;
	private String no;
	private long balance;
	
	// コンストラクタ(Accountクラスの初期化)
	Account (String n, String num, long z) {
		name = n;
		no = num;
		balance = z;
	}
	
	// 口座名義を調べる
	String getName() {
		return name;
	}
	
	// 口座番号を調べる
	String getNo() {
		return no;
	}
	
	// 預金残高を調べる
	long getBalance() {
		return balance;
	}
	
	// k円預ける
	void deposit (long k) {
		balance += k;
	}
	
	// k円おろす
	void withdraw (long k) {
		balance -= k;
	}
	
}

// 銀行口座クラス【第2版】をテストするクラス
// 実行用クラス
public class AccountTester {

	public static void main(String[] args) {
		// 足立さんの口座
		Account adachi = new Account("足立幸一", "123456", 1000);
		// 仲田さんの口座
		Account nakata = new Account("仲田真二", "654321", 200);
		
		// 足立さんの口座から200円おろす
		adachi.withdraw(200);
		// 仲田さんの口座に100円入金する
		nakata.deposit(100);

		
		// コンストラクタを生成した場合、以下はコンパイルエラーとなる
		
		// コンストラクタを生成しているので、引数無しでインスタンスは作れない
//		Account adachi = new Account();
		
//		// 足立さんの口座情報
//		adachi.name = "足立幸一";
//		adachi.no = "123456";
//		adachi.balance = 1000;
//		
//		// 仲田さんの口座情報
//		nakata.name = "仲田真二";
//		nakata.no = "654321";
//		nakata.balance = 200;
		
//		adachi.balance -= 200; // 足立さんの口座から200円おろす
//		nakata.balance += 100; // 仲田さんの口座に100円入金する
		
		
		
		System.out.println("■足立さんの口座");
		
		// 設計図クラスのメソッドの呼出
		System.out.println("　口座名義 ： " + adachi.getName());
		System.out.println("　口座番号 ： " + adachi.getNo());
		System.out.println("　預金残高 ： " + adachi.getBalance());
		
//		System.out.println("　口座名義 ： " + adachi.name);
//		System.out.println("　口座番号 ： " + adachi.no);
//		System.out.println("　預金残高 ： " + adachi.balance);
		
		System.out.println("■仲田さんの口座");
		
		// 設計図クラスのメソッドの呼出
		System.out.println("　口座名義 ： " + nakata.getName());
		System.out.println("　口座番号 ： " + nakata.getNo());
		System.out.println("　預金残高 ： " + nakata.getBalance());
		
//		System.out.println("　口座名義 ： " + nakata.name);
//		System.out.println("　口座番号 ： " + nakata.no);
//		System.out.println("　預金残高 ： " + nakata.balance);

	}

}
