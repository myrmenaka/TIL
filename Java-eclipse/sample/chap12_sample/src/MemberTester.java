//--- ����N���X�̃e�X�g ---//
public class MemberTester {

	public static void main(String[] args) {
		Member[] m = {
			new Member("����", 101, 27),
			new SpecialMember("����", 102, 31, "����"),
			new SpecialMember("����", 103, 52, "���z�Ə�"),
		};

		for (Member k : m) {
			k.print();
			System.out.println();
		}
	}
}
