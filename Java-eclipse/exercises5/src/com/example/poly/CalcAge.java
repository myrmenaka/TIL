// 年齢計算
package com.example.poly;

/*
 * 実務でどのバージョンのjavaを使っているかにもよって、使えるクラスなど変わってくる
 * バージョン管理をおろそかにすれば知っている古いクラスでしか対応できなくなる
 * 状況に応じて知識を更新し、どのクラスを使うかはそのプログラマーの裁量によって異なる
 */
import java.util.*; // Scannerクラス
import java.text.SimpleDateFormat;
import java.time.*; // LocalDateTimeクラス
import java.time.format.DateTimeFormatter;
public class CalcAge {

	public static void main(String[] args) {
		System.out.println("現在の日付：" + getDate());
		System.out.println("現在の日付:" + getDateStr("yyyy年M月d日（E）"));
		
		System.out.print("生まれた西暦を入力してください：");
		int birth = inputBirth();
		
		int age = calcAgeNow(birth);
		System.out.println("あなたの年齢は" + age + "歳です");

	}
	
	// 今日の日付を取得する
	public static String getDate() {
		Date date = new Date();
		// SimpleDateFomatクラスのインスタンス生成
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日(E)");
		return sdf.format(date);
	}
	
	// LocalDateTimeバージョン java8から追加
	public static String getDateStr(String format) {
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
		return ldt.format(dtf);
	}
	
	// ユーザーの生まれ年の入力
	@SuppressWarnings("resource")
	public static int inputBirth() {
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}
	
	// 現在の年齢を計算
	public static int calcAgeNow(int yearBirth) {
		// LocalDateクラス
		LocalDateTime ldt = LocalDateTime.now();
		// 現在の西暦を取得
		int year = ldt.getYear();
		// 現在の西暦 - 入力した西暦
		return year - yearBirth;
	}

}
