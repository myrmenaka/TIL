//--- �D�҉���N���X ---//
public class SpecialMember extends Member {
	private String privilege;		// ���T

	public SpecialMember(String name, int no, int age, String privilege) {
		super(name, no, age);  this.privilege = privilege; 
	}

	@Override public void print() {
		super.print();
		System.out.println("���T�F" + privilege);
	}
}
