// サブクラス

// ３次元座標クラス

public class Point3D extends Point2D {
	// メンバ変数
	private int z;
	
	// コンストラクタ
	Point3D(int x, int y, int z) {
		// スーパークラスのコンストラクタ呼出
		super(x, y);
		// このクラスの
		this.z = z;
	}
	
	// セッターメソッド
	void setZ(int z) {
		this.z = z;
	}
	
	// ゲッターメソッド
	int getZ() {
		return z;
	}
	
	// 出力用メソッド
	public void displayPoint3D() {
		System.out.printf("b = (%d, %d, %d)\n", getX(), getY(), getZ());
	}
}
