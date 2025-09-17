
public class MethodOverload {

	public static void main(String[] args) {
		showMark(3, "△");
		showMark();
		showMark("〇");
	}
	
	public static void showMark() {
		System.out.println("☆");
	}
	
	public static void showMark(String mark) {
		System.out.println(mark);
	}
	
	public static void showMark(int num, String mark) {
		for (int i = 0; i < num; i++) {
			System.out.print(mark);
		}
		System.out.println();
	}

}
