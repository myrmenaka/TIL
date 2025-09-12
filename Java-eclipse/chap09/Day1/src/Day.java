// 日付クラスDay

public class Day {
	// カプセル化したメンバ変数
	private int year;
	private int month;
	private int date;
	
	// Dayクラスのコンストラクタ
	Day(int year, int month, int date) {
		// Day.year はアクセスできない（カプセル化）し、「今日の年」だとおかしい
		// staticをつければDay.yearとして使えるけど、staticを付ける意味がそもそもない
		// Dayクラスは「１つ１つのオブジェクトが異なる日付を持つ」
		// year,month,dateはインスタンスごとに違う値を持つべき　→　staticでは設計が崩れる
		// thisキーワードは「このインスタンス自身」を指す
		// thisを使うことで「誰のyear」かを明確にする
		this.year = year;
		this.month = month;
		this.date = date;
	}
	
	// 年月日を取得する ゲッターメソッド（アクセッサ）
	// 外部からprivate指定したメンバ変数にアクセス（値を取得）するためのメソッド
	int getYear() {
		return year;
	}
	
	int getMonth() {
		return month;
	}
	
	int getDate() {
		return date;
	}
	
	// 年月日を設定する セッターメソッド（アクセッサ）
	// 外部からprivate指定したメンバ変数に値を代入するためのメソッド
	// 個別に代入用
	void setYear(int year) {
		this.year = year;
	}
	
	void setMonth(int month) {
		this.month = month;
	}
	
	void setDate(int date) {
		this.date = date;
	}
	
	// セッターメソッドをまとめた便利メソッド
	// コンストラクタと似た形で使えるように設計（オーバーロード風）
	// まとめて代入するとき用
	void set(int year, int month, int date) {
		this.year = year;
		this.month = month;
		this.date = date;
	}
	
	// 曜日を決める（日曜日～土曜日を、0~6で返却）メソッド
	int dayOfWeek() {
		int y = year;
		int m = month;
		if (m == 1 || m == 2) {
			y--;
			m += 12;
		}
		return (y + y / 4 - y / 100 + y / 400 + (13 * m + 8) / 5 + date) % 7;
	}
	
}
