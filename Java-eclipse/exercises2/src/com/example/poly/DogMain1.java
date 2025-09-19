package com.example.poly;

public class DogMain1 {

	public static void main(String[] args) {
		
		// 犬オブジェクトを2つ生成
		Dog1 pochi = new Dog1();
		Dog1 reo = new Dog1();
		
		// 犬オブジェクトに代入
		pochi.name = "ポチ";
		pochi.age = 8;
		
		reo.name = "レオ";
		reo.age = 2;
		
		// 犬の情報を表示
		System.out.println("---1匹目---");
		System.out.println("名前は" + pochi.name);
		System.out.println("年齢は" + pochi.age);
		System.out.println();
		
		System.out.println("---2匹目---");
		System.out.println("名前は" + reo.name);
		System.out.println("年齢は" + reo.age);
		System.out.println();
		
		// bark()メソッドの呼出
		pochi.bark();
		reo.bark();
	}

}
