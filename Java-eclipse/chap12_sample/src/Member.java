//--- ����N���X ---//
public class Member {
	private String name;	// ���O
	private int no;				// ����ԍ�
	private int age;			// �N��

	public Member(String name, int no, int age) {
		this.name = name;  this.no = no;  this.age = age;
	}

	public String getName() {
		return name;
	}

	public void print() {
		System.out.println("No." + no + "�F" + name +
											 "�i" + age + "�΁j");
	}
}
