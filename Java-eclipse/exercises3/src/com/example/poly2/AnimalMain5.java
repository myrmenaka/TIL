// コンストラクタ、フィールド（private）、メソッド（public）
// sleepメソッドのオーバーライド

package com.example.poly2;

public class AnimalMain5 {

	public static void main(String[] args) {
		// 犬オブジェクトを生成
		Dog3 dog = new Dog3("犬", 8, "ポチ", "柴犬");
		// 猫オブジェクトの生成
		Cat3 cat = new Cat3("猫", 5, "タマ", "マンチカン");
		
		// 表示
		showDetail(dog);
		showAction(dog, "ドックフード");
		
		showDetail(cat);
		showAction(cat, "キャットフード");
		
	}
		
	// 表示するメソッド
	public static void showDetail(Animal2 animal) {
		// 動物の情報を表示
		System.out.println("動物の種類：" + animal.getAnimalType());
		System.out.println("年齢：" + animal.getAge() + "歳");
			
		if (animal instanceof Dog3) {
			Dog3 dog = (Dog3) animal;
			System.out.println("名前：" + dog.getName());
			System.out.println("犬種：" + dog.getDogType());
		} else {
			Cat3 cat = (Cat3) animal;
			System.out.println("名前：" + cat.getName());
			System.out.println("猫種：" + cat.getCatType());
		}
			
		System.out.println();
		
	}
		
	// メソッドを呼び出すメソッド
	public static void showAction(Animal2 animal, String food) {
		
		animal.sleep();
		animal.eat(food);
			
		if (animal instanceof Dog3) {
			Dog3 dog = (Dog3) animal;
			dog.bark();
		} else {
			Cat3 cat = (Cat3) animal;
			cat.cry();
		}
			
		System.out.println();
		

	}

}

