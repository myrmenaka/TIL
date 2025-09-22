// 部分文字列の取得
// substringメソッド : 引数で指定した位置の文字列を返す
// 引数1つならその位置以降の文字列、引数2つの場合は開始位置から終了位置の一つ手前までの文字列

package stringMethod;

/*
 * 0から始まる文字番号
 * endIndexは終了位置の一つ手前まで
 * もしくは、文字列の間に線を引いた番号で考える
 */

public class SubstringSample {

	public static void main(String[] args) {
		String text = "0123456";
		String sub = text.substring(2,5);
		
		System.out.println(sub);

	}

}
