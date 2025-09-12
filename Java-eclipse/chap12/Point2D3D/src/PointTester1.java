// 実行クラス

public class PointTester1 {

	public static void main(String[] args) {
		
		// スーパークラスのメソッド
		Point2D a = new Point2D(10, 15);
		// サブクラスのコンストラクタ呼出
		Point3D b = new Point3D(20, 30, 40);
		
		// 出力
		System.out.printf("a = (%d, %d)\n", a.getX(), a.getY());
		System.out.printf("b = (%d, %d, %d)\n", b.getX(), b.getY(), b.getZ());

	}

}
