// ��̐����l��ǂݍ���ŉ����揜�����l��\��

import java.util.Scanner;
import java.util.InputMismatchException;

class ExceptionSample {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("x��y�������揜���܂��B");

		while (true) {
			try {
				System.out.print("x�̒l�F");  int x = stdIn.nextInt();
				System.out.print("y�̒l�F");  int y = stdIn.nextInt();

				System.out.println("x + y = " + (x + y));
				System.out.println("x - y = " + (x - y));
				System.out.println("x * y = " + (x * y));
				System.out.println("x / y = " + (x / y));
				System.out.println("x % y = " + (x % y));
			} catch (InputMismatchException e) {
				System.out.println("���̓G���[�����B" + e);
				String s = stdIn.next();
				System.out.println(s + "�͖������܂����B");
			} catch (ArithmeticException e) {
				System.out.println("�Z�p�G���[�����B" + e);
				System.out.println("�G���[���o�Ȃ��悤�Ȑ��l�����肢���܂��B");
			} finally {
				System.out.println("--------------------");
				System.out.print("������x�H�i1�cYes�^0�cNo�j�F");
				int retry = stdIn.nextInt();
				if (retry == 0) break;
				System.out.println("--------------------");
			}
		}
	}
}
