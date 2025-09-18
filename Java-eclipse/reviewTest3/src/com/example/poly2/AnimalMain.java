package com.example.poly2;

public class AnimalMain {

	public static void main(String[] args) {
		// 犬オブジェクトを生成
		Dog dog = new Dog();
		
		// 犬オブジェクトに値の代入
		dog.animalType = "犬";
		dog.age = 8;
		dog.setName("ポチ");
		dog.setDogType("柴犬");
		
		// 表示
		System.out.println("動物の種類：" + dog.animalType);
		System.out.println("年齢：" + dog.age);
		System.out.println("名前：" + dog.getName());
		System.out.println("犬種：" + dog.getDogType());
		System.out.println();
		
		// 犬オブジェクトのメソッドすべて呼び出す
		dog.sleep();
		dog.eat("ドックフード");
		dog.bark();
		
	}

}
