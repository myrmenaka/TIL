// コンストラクタ、フィールド（private）、メソッド（public）

package com.example.poly2;

public class AnimalMain4 {

	public static void main(String[] args) {
		// 犬オブジェクトを生成
		Dog2 dog = new Dog2("犬", 8, "ポチ", "柴犬");
		// 猫オブジェクトの生成
		Cat2 cat = new Cat2("猫", 5, "タマ", "マンチカン");
		
		// 犬オブジェクトのメソッドすべて呼び出す
		dog.sleep();
		dog.eat("ドックフード");
		dog.bark();
		System.out.println();
		
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
			
		if (animal instanceof Dog2) {
			// キャストする理由
			// 上の犬オブジェクトはローカル変数なのでスコープ外→また生成しなおす必要がある
			// 変数animalはAnimal1型なのでDogクラスのメソッドを呼び出せない
			// キャストしてDog型に一時的に変換し、参照できるようにする
			Dog2 dog = (Dog2) animal;
			System.out.println("名前：" + dog.getName());
			System.out.println("犬種：" + dog.getDogType());
		} else {
			Cat2 cat = (Cat2) animal;
			System.out.println("名前：" + cat.getName());
			System.out.println("猫種：" + cat.getCatType());
		}
			
		System.out.println();
		
	}
		
	// メソッドを呼び出すメソッド
	public static void showAction(Animal2 animal, String food) {
		
		animal.sleep();
		animal.eat(food);
			
		if (animal instanceof Dog2) {
			Dog2 dog = (Dog2) animal;
			dog.bark();
		} else {
			Cat2 cat = (Cat2) animal;
			cat.cry();
		}
			
		System.out.println();
		

	}

}

