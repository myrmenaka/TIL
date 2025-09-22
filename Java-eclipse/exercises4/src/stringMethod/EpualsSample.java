// 文字列の比較
// equalsメソッド : 指定したオブジェクトと比較する（参照値ではない）

package stringMethod;

public class EpualsSample {

	public static void main(String[] args) {
		String s1 = new String("abc");
		String s2 = new String("abc");
		
		System.out.println(s1.equals(s2));
		System.out.println(s1 == s2);

	}

}
