// Accountクラスのサブクラス

// 定期預金付き銀行口座クラス

public class TimeAccount extends Account {
	// メンバ変数
	private long timeBalance;
	
	// コンストラクタ
	TimeAccount(String name, String no, long balance, long timeBalance) {
		// スーパークラスのコンストラクタ呼出
		super(name, no, balance);
		// 預金残高（定期預金）
		this.timeBalance = timeBalance;
	}
	
	// 定期預金残高を調べるゲッターメソッド
	long getTimeBalance() {
		return timeBalance;
	}
	
	// 定期預金を解約して全額を普通預金に移すメソッド
	void cancel() {
		deposit(timeBalance);
		timeBalance = 0;
	}
	
}
