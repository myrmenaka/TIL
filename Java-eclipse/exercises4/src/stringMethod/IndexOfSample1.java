// 部分一致
// indexOfメソッド : 引数で指定した文字列の位置を返す（存在しない場合は「-1」を返す）

package stringMethod;

public class IndexOfSample1 {

	public static void main(String[] args) {
		String s1 = "abcdef";
		
		int p = s1.indexOf("cd");
		System.out.println(p); // 開始位置を出力
		
		int t = s1.indexOf("g");
		System.out.println(t); // 存在しないため「-1」を出力
		
		if (s1.indexOf("cd") != -1) {
			System.out.println("「cd」が含まれます");
		} else {
			System.out.println("「cd」が含まれません");
		}

	}

}
