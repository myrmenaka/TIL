// �P���i0�`9�j�̉��Z���s��

import java.util.Scanner;

//---- �͈͊O��O ---//
class RangeError extends RuntimeException {
	RangeError(int n) { super("�͈͊O�̒l�F" + n); }
}

//---- �͈͊O��O�i�������j---//
class ParameterRangeError extends RangeError {
	ParameterRangeError(int n) { super(n); }
}

//---- �͈͊O��O�i�ԋp�l�j---//
class ResultRangeError extends RangeError {
	ResultRangeError(int n) { super(n); }
}

public class RangeErrorTester {

	/*--- n�͂P���i0�`9�j���H ---*/
	static boolean isValid(int n) {
		return n >= 0 && n <= 9;
	}

	/*--- �P���i0�`9�j�̐���a��b�̘a�����߂� ---*/
	static int add(int a, int b) throws ParameterRangeError, ResultRangeError {
		if (!isValid(a)) throw new ParameterRangeError(a);
		if (!isValid(b)) throw new ParameterRangeError(b);
		int result = a + b;
		if (!isValid(result)) throw new ResultRangeError(result);
		return result;
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.print("����a�F");  int a = stdIn.nextInt();
		System.out.print("����b�F");  int b = stdIn.nextInt();

		try {
			System.out.println("�����̘a��" + add(a, b) + "�ł��B");
		} catch (ParameterRangeError e) {
			System.out.println("�����鐔���͈͊O�ł��B" + e.getMessage());
		} catch (ResultRangeError e) {
			System.out.println("�v�Z���ʂ��͈͊O�ł��B" + e.toString());
		}
	}
}
