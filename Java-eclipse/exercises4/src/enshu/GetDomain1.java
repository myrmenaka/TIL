package enshu;

import java.util.*;
public class GetDomain1 {

	public static void main(String[] args) {
		System.out.print("メールアドレスを入力：");
		String mailAddress = inputMailAddress();  // キーボードから文字列入力
		
		String user = getUser(mailAddress); // ユーザー名取得
		
		String domain = getDomain(mailAddress); // ドメイン名取得
		
		System.out.println("ユーザー名：" + user);
		System.out.println("ドメイン名：" + domain);
	}
	
	// キーボードから入力するメソッド
	@SuppressWarnings("resource")
	private static String inputMailAddress() {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}
	
	// ユーザー名を抜き出すメソッド
	private static String getUser(String mailAddress) {
		int pos = mailAddress.indexOf("@");
		return mailAddress.substring(0, pos);
	}
	
	// ドメイン名を抜き出すメソッド
	private static String getDomain(String mailAddress) {
		int pos = mailAddress.indexOf("@");
		return mailAddress.substring(pos + 1);
	}

}


