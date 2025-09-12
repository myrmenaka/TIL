//--- —D‘Ò‰ïˆõƒNƒ‰ƒX ---//
public class SpecialMember extends Member {
	private String privilege;		// “Á“T

	public SpecialMember(String name, int no, int age, String privilege) {
		super(name, no, age);  this.privilege = privilege; 
	}

	@Override public void print() {
		super.print();
		System.out.println("“Á“TF" + privilege);
	}
}
