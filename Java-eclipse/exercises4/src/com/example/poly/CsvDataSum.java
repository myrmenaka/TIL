package com.example.poly;

public class CsvDataSum {

	public static void main(String[] args) {
		String inputCsv = "65,59,72,85,66";
		String[] csvNumStrs = inputCsv.split(","); 
		
		int sum = calcArraySum(csvNumStrs);
		
		System.out.println("CSVデータ：" + inputCsv);
		System.out.println("合計：" + sum);

	}
	
	// 配列内の文字列を数値に変換して合計を計算するメソッド
	private static int calcArraySum(String[] csvNumStrs) {
		int sum = 0;
		for (String csvNumStr : csvNumStrs) {
			sum += Integer.parseInt(csvNumStr);
		}
		return sum;
	}

}
