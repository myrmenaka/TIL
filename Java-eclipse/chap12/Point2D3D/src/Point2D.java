// スーパークラス

// ２次元座標クラス

public class Point2D {
	// メンバ変数
	private int x;
	private int y;
	
	// コンストラクタ
	Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// セッターメソッド
	void setX(int x) {
		this.x = x;
	}
	void setY(int y) {
		this.y = y;
	}
	
	// ゲッターメソッド
	int getX() {
		return x;
	}
	int getY() {
		return y;
	}
	
	// 出力用メソッド
	public void displayPoint2D() {
		System.out.printf("a = (%d, %d)\n", getX(), getY());
	}
}
