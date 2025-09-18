package com.example.poly;

public class DogMain2 {

	public static void main(String[] args) {
		// 犬コンストラクタを2つ呼出
		Dog2 pochi = new Dog2("ポチ", 8);
		Dog2 reo = new Dog2("レオ", 2);
		
		// 犬の情報を表示
		System.out.println("---1匹目---");
		System.out.println("名前は" + pochi.getName());
		System.out.println("年齢は" + pochi.getAge());
		System.out.println();
		
		System.out.println("---2匹目---");
		System.out.println("名前は" + reo.getName());
		System.out.println("年齢は" + reo.getAge());
		System.out.println();
		
		// bark()メソッドの呼出
		pochi.bark();
		reo.bark();

	}

}
