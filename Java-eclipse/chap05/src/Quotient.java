// 二つの数値の商を求める

public class Quotient {

	public static void main(String[] args) {
		// int / int = int
		System.out.println("15 / 2" + 15 / 2);
		// double / double = double
		System.out.println("15.0 / 2.0 =" + 15.0 / 2.0);
		
		// int型とdouble型の計算では、int型がdouble型へ昇格する（変換される）
		//　小さいほうの型が、より大きいほうの型のオペランドへ変換される
		
		// double / int → double / double = double
		System.out.println("15.0 / 2 =" + 15.0 / 2);
		// int / double → double / double = double
		System.out.println("15 / 2.0 =" + 15 / 2.0);

	}

}
