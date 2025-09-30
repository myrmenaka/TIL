package List;

import java.util.*;
public class ArrayListExample {

	public static void main(String[] args) {
		// monthという可変長配列を生成
		ArrayList<String> month = new ArrayList<>();
		
		// addメソッドで"January"、"February"、"March"を要素の末尾に追加
		month.add("January");
		month.add("February");
		month.add("March");
		
		// sizeメソッドで要素数を取得して表示
		System.out.println("要素数" + month.size());
		
		// getメソッドで引数に渡された番号の要素を取得
		for (int i = 0; i < month.size(); i++) {
			System.out.println(month.get(i));
		}
		
		// removeメソッドで引数番号の要素を削除
		month.remove(1);
		
		System.out.println("要素数" + month.size());
		
		for (int i = 0; i < month.size(); i++) {
			System.out.println(month.get(i));
		}

	}

}
