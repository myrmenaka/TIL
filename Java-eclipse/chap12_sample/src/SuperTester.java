// �X�[�p�[�N���X�ƃT�u�N���X

// �X�[�p�[�N���X
class Base {
	protected int x;	// ������J�i���̃N���X�Ɖ��ʃN���X����A�N�Z�X�ł���j

	Base()      { this.x = 0; }
	Base(int x) { this.x = x; }

	void print() { System.out.println("Base.x = " + x); }
}

// �T�u�N���X
class Derived extends Base {
	int x;		// �X�[�p�[�N���X�Ɠ��ꖼ�̃t�B�[���h

	Derived(int x1, int x2) { super.x = x1; this.x = x2; }

	// �X�[�p�[�N���X�̃��\�b�h���㏑���i�I�[�o���C�h�j
	void print() { super.print(); System.out.println("Derived.x = " + x); }
}

public class SuperTester {

	public static void main(String[] args) {
		Base a = new Base(10);
		System.out.println("-- a --");  a.print();

		Derived b = new Derived(20, 30);
		System.out.println("-- b --");  b.print();
	}
}
