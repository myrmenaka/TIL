package com.example.poly;

public class DogMain {

	public static void main(String[] args) {
//		Dog pochi = new Dog("ポチ", 8);
//		Dog reo = new Dog("レオ", 2);
		
		// 犬オブジェクトを2つ生成
		Dog pochi = new Dog();
		Dog reo = new Dog();
		
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
