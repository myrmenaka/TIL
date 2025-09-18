package com.example.poly2;

public class AnimalMain2 {

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
		System.out.println("年齢：" + dog.age + "歳");
		System.out.println("名前：" + dog.getName());
		System.out.println("犬種：" + dog.getDogType());
		System.out.println();
		
		// 犬オブジェクトのメソッドすべて呼び出す
		dog.sleep();
		dog.eat("ドックフード");
		dog.bark();
		System.out.println();
		
		// 猫オブジェクトの生成
		Cat cat = new Cat();
		
		// 猫オブジェクトに値の代入
		cat.animalType = "猫";
		cat.age = 5;
		cat.name = "タマ";
		cat.catType = "マンチカン";
		
		// 表示
		System.out.println("動物の種類：" + cat.animalType);
		System.out.println("年齢：" + cat.age + "歳");
		System.out.println("名前：" + cat.name);
		System.out.println("犬種：" + cat.catType);
		System.out.println();
		
		// 猫オブジェクトのメソッドすべて表示
		cat.sleep();
		cat.eat("キャットフード");
		cat.cry();

	}

}

