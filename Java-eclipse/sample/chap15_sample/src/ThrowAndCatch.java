// ��O�����𗝉����邽�߂̃T���v��

import java.util.Scanner;

class ThrowAndCatch {

	//--- sw�̒l�ɉ����ė�O�𔭐� ---//
	static void check(int sw) throws Exception {
		switch (sw) {
		 case 1: throw new Exception("������O����!!"); 
		 case 2: throw new RuntimeException("�񌟍���O����!!");
		}
	}

	//--- check���Ăяo�� ---//
	static void test(int sw) throws Exception {
		check(sw);
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.print("sw�F");
		int sw = stdIn.nextInt();

		try {
			test(sw);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
