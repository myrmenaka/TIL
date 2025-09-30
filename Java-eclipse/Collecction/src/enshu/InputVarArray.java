package enshu;

/*
 * ユーザー入力は意図した値以外も入力できるので、
 * 意図した入力以外の時のプログラムも必要
 * 
 * Switch文は式にオブジェクトも入れられる
 * 
 * 今回のケースでは、最低でも１回はユーザー入力してもらわないといけないので
 * 「do-while」が最適である
 * 
 */

import java.util.*;
public class InputVarArray {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		List<String> List = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		
		while (true) {		
			System.out.print("[使用法] 追加：1｜削除：2｜入力終了：quit ->");
			String inputStr = sc.nextLine();
			
			if (inputStr.equals("1")) {
				// 追加
				System.out.print("追加データを入力 ->");
				String item = sc.nextLine();
				List.add(item);
				System.out.println("「" + item + "」を追加しました");
				
			} else if (inputStr.equals("2")) {
				// 削除
				System.out.println("現在のリスト");
				for (int i = 0; i < List.size(); i++) {
					System.out.println("[" + i + "]" + List.get(i));
				}
				System.out.print("削除データの要素番号を入力 ->");
				int item = sc.nextInt();
				System.out.println("要素番号「" + item + "」、「" + List.get(item) + "」を削除しました");
				List.remove(item);
				
			} else if (inputStr.equals("quit")) {
				// 終了
				break;
			}
		}
		// 全要素表示
		System.out.println("全要素");
		for (int i = 0; i < List.size(); i++) {
			System.out.println("[" + i + "]" + List.get(i));
		}

	}

}
