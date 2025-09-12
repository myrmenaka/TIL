// �Q�������W�N���X�ƂR�������W�N���X

// �Q�������W�N���X
class Point2D {
	int x;	// �w���W
	int y;	// �x���W

	// �R���X�g���N�^
	Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	void setX(int x) {
		this.x = x;
	}	// �w���W��ݒ�

	void setY(int y) {
		this.y = y;
	}	// �x���W��ݒ�

	int getX() {
		return x;
	}					// �w���W���擾
	int getY() {
		return y;
	}					// �x���W���擾
}

// �R�������W�N���X
class Point3D extends Point2D {
	int z;	// �y���W
	// �R���X�g���N�^
	Point3D(int x, int y, int z) {
		super(x, y);	// �X�[�p�[�N���X�̃R���X�g���N�^�ďo
		this.z = z;
	}

	void setZ(int z) {
		this.z = z;
	} // �y���W��ݒ�

	int getZ() {
		return z;
	}						// �y���W���擾
}

public class PointTester {

	public static void main(String[] args) {

		Point2D a = new Point2D(10, 15);
		Point3D b = new Point3D(20, 30, 40);

		System.out.printf("a = (%d, %d)\n",     a.getX(), a.getY());
		System.out.printf("b = (%d, %d, %d)\n", b.getX(), b.getY(), b.getZ());
	}
}
