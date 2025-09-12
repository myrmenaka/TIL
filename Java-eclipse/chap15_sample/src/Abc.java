import java.util.Scanner;

//---- ����̌�����O ---//
class CheckedException extends Exception {
	CheckedException(String s, Throwable e) { super(s, e); }
}

//---- ����̔񌟍���O ---//
class UncheckedException extends RuntimeException {
	UncheckedException(String s, Throwable e) { super(s, e); }
}

public class Abc {

	//--- sw�̒l�ɉ����ė�O�𔭐� ---//
	static void work(int sw) throws Exception {
		switch (sw) {
		 case 1: throw new RuntimeException("�񌟍���O����!!"); 
		 case 2: throw new Exception("������O����!!");
		}
	}

	//--- work���Ăяo�� ---//
	static void test(int sw) throws CheckedException {
		try {
			work(sw);
		} catch (RuntimeException e) {
			/* �Ώ������݂����Ώ�������Ȃ����� */
			throw new UncheckedException("�񌟍���O�Ώ��s�\!!", e);
		} catch (Exception e) {
			/* �Ώ������݂����Ώ�������Ȃ����� */
			throw new CheckedException("������O�Ώ��s�\!!", e);
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.print("sw�F");
		int sw = stdIn.nextInt();

		try {
			test(sw);
		} catch (Exception e) {
			System.out.println("��O�@�@�@�F" + e);
			System.out.println("��O�̌����F" + e.getCause());
			e.printStackTrace();
		}
	}
}
