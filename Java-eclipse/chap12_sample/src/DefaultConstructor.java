// �X�[�p�[�N���X�ƃT�u�N���X�i�f�t�H���g�R���X�g���N�^�̓������m�F�j

// �X�[�p�[�N���X
class A {
	private int a;

	A() { a = 50; }

	int getA() { return a; }
}

// �T�u�N���X
class B extends A {
	// �R���X�g���N�^���`���Ă��Ȃ��i�f�t�H���g�R���X�g���N�^�����������j
}

public class DefaultConstructor {

	public static void main(String[] args) {
		B x = new B();

		System.out.println("x.getA() = " + x.getA());
	}
}
