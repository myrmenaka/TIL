package List;

import java.util.*;
public class ArrayListExample {

	public static void main(String[] args) {
		// ArrayListのインスタンスを生成（空）
//		ArrayList<String> month = new ArrayList<>();
		
		// ArrayListのインターフェースList型でインスタンス生成すると
		// ポリモーフィズム（多態性）なプログラムを記述することができる
		// ※よく使われるのはこっち
		List<String> month = new ArrayList<>();
		
		// addメソッドで"January"、"February"、"March"を要素の末尾に追加
		month.add("January"); // 0
		month.add("February"); // 1
		month.add("March"); // 2
		
		// sizeメソッドで要素数を取得
		System.out.println("要素数" + month.size());
		
		// 配列の走査・全要素の表示
		// getメソッドで引数に渡された番号の要素を取得
		for (int i = 0; i < month.size(); i++) {
			System.out.println(month.get(i));
		}
		
		// removeメソッドで引数番号の要素を削除
		// 削除だけでなく、要素を詰めるまでしてくれる → NULLではないんだよ
		month.remove(1);
		
		System.out.println("要素数" + month.size());
		
		for (int i = 0; i < month.size(); i++) {
			System.out.println(month.get(i));
		}

	}

}

