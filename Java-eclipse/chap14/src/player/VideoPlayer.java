// インターフェースを実装するクラス(具象クラス)

package player;

public class VideoPlayer implements IPlayer {
	// フィールド
	private int id; // 製造番号
	private static int count = 0; // 製造番号のカウント
	
	// コンストラクタ
	public VideoPlayer() {
		id = ++count;
	}
	
	// IPlayerインターフェースの抽象メソッドのオーバーライド（必須）
	@Override
	public void play() {
		System.out.println("++ビデオ再生開始！");	
	}

	@Override
	public void stop() {
		System.out.println("++ビデオ再生停止！");
	}
	
	// 製造番号表示
	public void printInfo() {
		System.out.println("++本機の製造番号は[" + id + "]です");
	}
	

}
