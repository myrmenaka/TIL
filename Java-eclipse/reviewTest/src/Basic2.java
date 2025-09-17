
public class Basic2 {

	public static void main(String[] args) {
		int[] array = {1,2,3,4,5};
		int right, tmp;
		
		for (int left = 1; left <= (array.length / 2); left++) {
			// A
			right = array.length - left;
			tmp = array[right];
			array[right] = array[left - 1];
			// B
			array[left - 1] = tmp;
		}
		
		for (int num : array) { // 配列内の全要素表示
			System.out.print(num + " ");
		}
	}
}
