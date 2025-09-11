// パッケージ管理版


// 実行用クラス

public class AccountTester3 {

	public static void main(String[] args) {
		// コンストラクタの呼出
		// 足立さんの口座
		Account adachi = new Account("足立幸一", "123456", 1000);
		// 仲田さんの口座
		Account nakata = new Account("仲田真二", "654321", 200);
		
		// デフォルトコンストラクタも作成しているのでエラーにならない
		Account maruyama = new Account();
		// この場合、値を入れるには・・・
		// セッターメソッドを作っておく、もしくは
		// メンバ変数がpublicなら直接代入可能（カプセル化的には非推奨）
		// なので、目的が無ければ使わない
		
		
		// 足立さんの口座から200円おろす
		adachi.withdraw(200);
		// 仲田さんの口座に100円入金する
		nakata.deposit(100);
		
		System.out.println("■足立さんの口座");
		
		// 設計図クラスのメソッドの呼出
		System.out.println("　口座名義 ： " + adachi.getName());
		System.out.println("　口座番号 ： " + adachi.getNo());
		System.out.println("　預金残高 ： " + adachi.getBalance());
		
		System.out.println("■仲田さんの口座");
		
		// 設計図クラスのメソッドの呼出
		System.out.println("　口座名義 ： " + nakata.getName());
		System.out.println("　口座番号 ： " + nakata.getNo());
		System.out.println("　預金残高 ： " + nakata.getBalance());
		
	}

}
