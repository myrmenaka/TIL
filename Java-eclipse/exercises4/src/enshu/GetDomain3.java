package enshu;

import java.util.*;
public class GetDomain3 {

	public static void main(String[] args) {
		// メールアドレスの入力 inputMailAddress
		String email = inputMailAddress();
		// ドメイン名の取得 getDomain
		// @の判定　if formatMail
		int count = formatMail(email);
		
		if (count != 1) {
			System.out.println("正しいメールアドレスを入力してください");
		} else {
			String domain = getDomain(email);
			System.out.println("ドメイン名：" + domain);
		}

	}
	
	// メールアドレスの入力
	public static String inputMailAddress() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("メールアドレスを入力してください：");
		return sc.nextLine();
	}
	
	// ドメイン名の取得
	public static String getDomain(String mail) {
		return mail.substring(mail.indexOf('@') + 1);
	}
	
	// @の判定　if
	public static int formatMail(String mail) {
		int count = 0;
		for (int i = 0; i < mail.length(); i++) {
			if (mail.charAt(i) == '@') {
				count++;
			}
		}
		return count;
	}

}
