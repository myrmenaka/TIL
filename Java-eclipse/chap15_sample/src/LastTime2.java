// �O��̋C����\���i�����t��try���j

import java.io.*;
import java.util.Scanner;

class LastTime2 {

	//--- �O��̋C����ǂݍ��� ---//
	static void init() {
		try (BufferedReader br = new BufferedReader(new FileReader("LastTime.txt"));
		) {
			String kibun = br.readLine();
			System.out.println("�O��̋C����" + kibun + "�ł����B");
		} catch (IOException e){
			System.out.println("���̃v���O���������s����̂͏��߂Ăł��ˁB");
		}
	}

	//--- ����̋C������������ ---//
	static void term(String kibun) {
		try (
			FileWriter fw = new FileWriter("LastTime.txt");
		) {
			fw.write(kibun);
		} catch (IOException e){
			System.out.println("�G���[����!!");
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		init();				// �O��̋C����\��

		System.out.print("���̋C���́F");
		String kibun = stdIn.next();

		term(kibun);
	}
}
