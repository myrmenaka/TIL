// 部分一致
// indexOfメソッド : 引数で指定した文字列の位置を返す（存在しない場合は「-1」を返す）

package stringMethod;

public class IndexOfSample2 {

	public static void main(String[] args) {
		String mailAddress = "hoge@example.com";
		
		int pos = mailAddress.indexOf("@");
		String user = mailAddress.substring(0, pos);
		System.out.println(user);

	}

}
