// 今日の曜日
package com.example.poly;

import java.util.*;
import java.text.*;
public class TodayWeek {

	public static void main(String[] args) {
		// 今日の日付を取得する
		// Dateクラスのインスタンス生成
		Date date = new Date();
		// SimpleDateFomatクラスのインスタンス生成
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日(E)");
		
		String text = sdf.format(date);
		
		System.out.println("現在の日付：" + text);
		
	}

}
