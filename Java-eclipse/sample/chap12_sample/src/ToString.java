// toString���ԋp���镶�����\�����郁�\�b�h�i���ׂẴN���X�^�ɑΉ��j

class X {
	public String toString() {
		return "Class X";
	}
}

class Y extends X {
	public String toString() {
		return "Class Y";
	}
}

public class ToString {

	//--- toString���\�b�h���ԋp���镶�����\�� ---//
	static void print(Object obj) {
		System.out.println(obj);
	}

	public static void main(String[] args) {
		X x = new X();
		Y y = new Y();
		int[] c = new int[5];

		print(x);
		print(y);
		print(c);
	}
}
