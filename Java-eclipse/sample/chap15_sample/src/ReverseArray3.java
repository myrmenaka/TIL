// �z��̗v�f�ɒl��ǂݍ���ŕ��т𔽓]����i�o�O����Freverse�ŗ�O��ߑ��j

import java.util.Scanner;

class ReverseArray3 {

	//--- �z��̗v�fa[idx1]��a[idx2]������ ---//
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}

	//--- �z��a�̗v�f�̕��т𔽓]�i���j---//
	static void reverse(int[] a) {
		try {
			for (int i = 0; i < a.length / 2; i++)
				swap(a, i, a.length - i);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.print("�v�f���F");
		int num = stdIn.nextInt();	// �v�f��

		int[] x = new int[num];			// �v�f��num�̔z��

		for (int i = 0; i < num; i++) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}

		reverse(x);						// �z��x�̗v�f�̕��т𔽓]

		System.out.println("�v�f�̕��т𔽓]���܂����B");
		for (int i = 0; i < num; i++)
			System.out.println("x[" + i + "] = " + x[i]);
	}
}
