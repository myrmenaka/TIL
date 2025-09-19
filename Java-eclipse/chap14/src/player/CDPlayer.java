// インターフェースを実装するクラス(具象クラス)

package player;

public class CDPlayer implements IPlayer {
	// IPlayerインターフェースの抽象メソッドのオーバーライド（必須）
	@Override
	public void play() {
		System.out.println("**CD再生開始！");
	}
	@Override
	public void stop() {
		System.out.println("**CD再生停止！");
	}
	
	// クリーニング
	public void cleaning() {
		System.out.println("**ヘッドをクリーニングしました");
	}

}
