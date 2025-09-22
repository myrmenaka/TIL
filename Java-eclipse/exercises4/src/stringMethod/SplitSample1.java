// 区切り文字で分割
// splitメソッド : 指定した区切り文字で分割（配列に格納する）

package stringMethod;

public class SplitSample1 {

	public static void main(String[] args) {
		String s1 = "2023/10/14";
		String[] date = s1.split("/");
		
		for (int i = 0; i < date.length; i++) {
			System.out.println(date[i]);
		}

	}

}
