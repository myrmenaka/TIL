// 動物、犬クラスの利用

package com.example.poly2;

public class AnimalMain1 {

	public static void main(String[] args) {
		// 犬オブジェクトを生成
		Dog1 dog = new Dog1();
		
		// 犬オブジェクトに値の代入
		dog.animalType = "犬";
		dog.age = 8;
		dog.name = "ポチ";
		dog.dogType = "柴犬";
		
		// 表示
		System.out.println("動物の種類：" + dog.animalType);
		System.out.println("年齢：" + dog.age + "歳");
		System.out.println("名前：" + dog.name);
		System.out.println("犬種：" + dog.dogType);
		System.out.println();
		
		// 犬オブジェクトのメソッドすべて呼び出す
		dog.sleep();
		dog.eat("ドックフード");
		dog.bark();
		System.out.println();

	}

}

