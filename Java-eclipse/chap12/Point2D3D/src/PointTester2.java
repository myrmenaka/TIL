// 実行クラス

public class PointTester2 {

	public static void main(String[] args) {
		
		// スーパークラスのコンストラクタ
		Point2D a = new Point2D(10, 15);
		// 出力メソッド
		a.displayPoint2D();
		
		
		
		// サブクラスのコンストラクタ
		Point3D b = new Point3D(20, 30, 40);
		// 出力メソッド
		b.displayPoint3D();

	}

}
