package com.example.poly;

public class DogForMain {

	public static void main(String[] args) {
		Dog1 pochi = new Dog1();
		Dog1 reo = new Dog1();
		
		String[] name = {"ポチ", "レオ"};
		int[] age = {8, 2};
		
		for (int i = 0; i < 2; i++) {
			System.out.println("---" + ( i + 1 ) + "匹目---");
			System.out.println("名前は" + name[i]);
			System.out.println("年齢は" + age[i]);
			System.out.println();
		}
		
		pochi.name = "ポチ";
		reo.name = "レオ";
		
		pochi.bark();
		reo.bark();
		
	}

}
