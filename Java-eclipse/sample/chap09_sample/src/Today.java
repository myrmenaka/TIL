// �����̓��t��\��

import java.util.GregorianCalendar;
import static java.util.GregorianCalendar.*;

class Today {

	public static void main(String[] args) {
		GregorianCalendar today = new GregorianCalendar();
		System.out.printf("������%04d�N%02d��%02d���ł��B\n",
												today.get(YEAR),				// �N
												today.get(MONTH) + 1,		// ��
												today.get(DATE)					// ��
										 );
	}
}
