// 設計図クラス
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
	
	// デフォルトコンストラクタの作成
	Account() {
		
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