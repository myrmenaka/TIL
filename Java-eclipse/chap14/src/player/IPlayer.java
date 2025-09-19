// インターフェース
// インターフェース名は頭文字に「I」を付ける
package player;

public interface IPlayer {
	// 抽象メソッド
	// インターフェースとして宣言しているので、abstractキーワードは不要、アクセス修飾子も不要
	void play(); // 再生
	void stop(); // 停止

}
