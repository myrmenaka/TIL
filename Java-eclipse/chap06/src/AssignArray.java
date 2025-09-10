// 配列の代入（誤り）

import java.util.Arrays;

public class AssignArray {

	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4, 5};
		int[] b = {6, 5, 4, 3, 2, 1, 0};
		
		System.out.println("a =" + Arrays.toString(a));
		System.out.println("b =" + Arrays.toString(b));
		
		b = a;
		
		a[0] = 10;
		
		System.out.println("aをbに代入してa[0]に10を入れました。");
		System.out.println("a =" + Arrays.toString(a));
		System.out.println("b =" + Arrays.toString(b));

	}

}
