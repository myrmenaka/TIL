// 年齢計算
package com.example.poly;

import java.util.*; // Scannerクラス
import java.text.SimpleDateFormat;
import java.time.*; // LocalDateTimeクラス
public class CalcAge {

	public static void main(String[] args) {
		CalcAge.getDate();
		System.out.print("生まれた西暦を入力してください：");
		int birth = CalcAge.inputBirth();
		int age = CalcAge.calcAgeNow(birth);
		System.out.println("あなたの年齢は" + age + "歳です");

	}
	
	// 今日の日付を取得する
	public static void getDate() {
		Date date = new Date();
		// SimpleDateFomatクラスのインスタンス生成
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日(E)");
		String text = sdf.format(date);
		System.out.println("現在の日付：" + text);
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
