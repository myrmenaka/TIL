
public class MethodDiv {

	public static void main(String[] args) {
		int[] scores = {50, 80, 20};
		calcDiv(scores);
		System.out.println(scores[0]);
	}
	
	public static void calcDiv(int[] scores) {
		scores[0] /= 10;
	}
}
