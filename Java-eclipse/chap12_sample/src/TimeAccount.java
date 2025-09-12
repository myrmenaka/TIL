// ����a���t����s�����N���X�y����Łz

class TimeAccount {
	private String name;				// �������`
	private String no;					// �����ԍ�
	private long balance;				// �a���c��
	private long timeBalance;		// �a���c���i����a���j

	//--- �R���X�g���N�^ ---//
	TimeAccount(String n, String num, long z, long timeBalance) {
		name = n;													// �������`
		no = num;													// �����ԍ�
		balance = z;											// �a���c��
		this.timeBalance = timeBalance;		// �a���c���i����a���j
	}

	//--- �������`�𒲂ׂ� ---//
	String getName() {
		return name;
	}

	//--- �����ԍ��𒲂ׂ� ---//
	String getNo() {
		return no;
	}

	//--- �a���c���𒲂ׂ� ---//
	long getBalance() {
		return balance;
	}

	//--- ����a���c���𒲂ׂ� ---//
	long getTimeBalance() {
		return timeBalance;
	}

	//--- k�~�a���� ---//
	void deposit(long k) {
		balance += k;
	}

	//--- k�~���낷 ---//
	void withdraw(long k) {
		balance -= k;
	}

	//--- ����a������񂵂đS�z�𕁒ʗa���Ɉڂ� ---//
	void cancel() {	
		balance += timeBalance;
		timeBalance = 0;
	}
}
