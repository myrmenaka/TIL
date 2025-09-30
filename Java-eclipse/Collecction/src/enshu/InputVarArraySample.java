// 先生の回答
package enshu;

import java.util.*;
public class InputVarArraySample {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		List<String> inputDataArrays = new ArrayList<>();
		
		// デフォルト値 null では困るので空文字で初期化している
		String input = "";
		do {
			System.out.println("[使用法] 追加：1｜削除：2｜入力終了：quit ->");
			input = new Scanner(System.in).nextLine();
			
			switch(input) {
			case "1":
				addData(inputDataArrays);
				break;
			case "2":
				deleteData(inputDataArrays);
				break;
			case "quit":
				break;
			default :
				System.out.println("!!!使用法に従い再度入力してください!!!");
				System.out.println();
			}
		} while (!input.equals("quit"));
		
		System.out.println("----------------------");
		
		for (String output : inputDataArrays) {
			System.out.println(output);
		}

	}
	
	// 引数で配列を私、ユーザー入力値を元に配列からデータを追加するメソッド
	public static void addData(List<String> inputDataArrays) {
		System.out.println("追加データを入力 -> ");
		String data = new Scanner(System.in).nextLine();
		System.out.println("「" + data + "を追加しました");
		inputDataArrays.add(data);
		
		System.out.println();
	}
	
	// 引数で配列を私、ユーザー入力値を元に配列からデータを削除するメソッド
	public static void deleteData(List<String> inputDataArrays) {
		System.out.println("削除データの要素番号を入力 -> ");
		int data = new Scanner(System.in).nextInt();
		System.out.println("要素番号「" + data + "」、「" + inputDataArrays.get(data) + "」を削除しました");
		inputDataArrays.remove(data);
		
		System.out.println();
	}

}
