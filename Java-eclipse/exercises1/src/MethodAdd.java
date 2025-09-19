
public class MethodAdd {

	public static void main(String[] args) {
		int sum = 0;
		add(5, 10);
		
		// addメソッドの処理を実行する場合
//		sum = add(5, 10);
		
		System.out.println(sum);

	}
	
	public static void add(int a, int b) {
		int sum = a + b;
	}
	
	// addメソッドの処理を実行する場合 
//	public static int add(int a, int b) {
//		int sum = a + b;
//		return sum;
//	}

}
