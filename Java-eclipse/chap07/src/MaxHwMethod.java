// ３人の身長と体重の最大値を求めて表示
import java.util.Scanner;

public class MaxHwMethod {
	
	// a,b,cの最大値を返却
	static int max(int a, int b, int c) {
		int max = a;
		if (b > max) max =b;
		if (c > max) max = c;
		return max;
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		int[] height = new int[3];
		int[] weight = new int[3];
		
		for (int i = 0; i < 3; i++) {
			System.out.print("[" + (i + 1) + "]");
			System.out.print("身長：");
			height[i] = stdIn.nextInt();
			System.out.print("体重：");
			weight[i] = stdIn.nextInt();
		}
		int maxHeight = max(height[0], height[1], height[2]); // 身長の最大値
		int maxWeight = max(weight[0], weight[1], weight[2]); // 体重の最大値
		System.out.println("身長の最大値は" + maxHeight + "です。");
		System.out.println("体重の最大値は" + maxWeight + "です。");
	}

}
