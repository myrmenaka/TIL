// �O��̋C����\��

import java.io.*;
import java.util.Scanner;

class LastTime1 {

	//--- �O��̋C����ǂݍ��� ---//
	static void init() {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader("LastTime.txt"));
			String kibun = br.readLine();
			System.out.println("�O��̋C����" + kibun + "�ł����B");
		} catch (IOException e){
			System.out.println("���̃v���O���������s����̂͏��߂Ăł��ˁB");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e){
					System.out.println("�t�@�C���N���[�Y���s�B");
				}
			}
		}
	}

	//--- ����̋C������������ ---//
	static void term(String kibun) {
		FileWriter fw = null;

		try {
			fw = new FileWriter("LastTime.txt");
			fw.write(kibun);
		} catch (IOException e){
			System.out.println("�G���[����!!");
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e){
					System.out.println("�t�@�C���N���[�Y���s�B");
				}
			}
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
