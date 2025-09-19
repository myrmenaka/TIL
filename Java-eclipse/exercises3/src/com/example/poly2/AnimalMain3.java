// 共通の処理をメソッドにまとめる
// メリット：可読性の向上、拡張性の向上

package com.example.poly2;

public class AnimalMain3 {

	public static void main(String[] args) {
		// 犬オブジェクトを生成
		Dog1 dog = new Dog1();
		// 猫オブジェクトの生成
		Cat1 cat = new Cat1();
		
		// 犬オブジェクトに値の代入
		dog.animalType = "犬";
		dog.age = 8;
		dog.name = "ポチ";
		dog.dogType = "柴犬";
		
		// 猫オブジェクトに値の代入
		cat.animalType = "猫";
		cat.age = 5;
		cat.name = "タマ";
		cat.catType = "マンチカン";
		
		// 表示
		showDetail(dog);
		showAction(dog, "ドックフード");
		
		showDetail(cat);
		showAction(cat, "キャットフード");
		
	}
		
	// 表示するメソッド
	public static void showDetail(Animal1 animal) {
		// 動物の情報を表示
		System.out.println("動物の種類：" + animal.animalType);
		System.out.println("年齢：" + animal.age + "歳");
			
		if (animal instanceof Dog1) {
			Dog1 dog = (Dog1) animal;
			System.out.println("名前：" + dog.name);
			System.out.println("犬種：" + dog.dogType);
		} else {
			Cat1 cat = (Cat1) animal;
			System.out.println("名前：" + cat.name);
			System.out.println("猫種：" + cat.catType);
		}
			
		System.out.println();
		
	}
		
	// メソッドを呼び出すメソッド
	public static void showAction(Animal1 animal, String food) {
			
		animal.sleep();
		animal.eat(food);
			
		if (animal instanceof Dog1) {
			Dog1 dog = (Dog1) animal;
			dog.bark();
		} else {
			Cat1 cat = (Cat1) animal;
			cat.cry();
		}
			
		System.out.println();
		

	}

}

