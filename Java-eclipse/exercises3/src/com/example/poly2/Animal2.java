// コンストラクタ、フィールド（private）、メソッド（public）

package com.example.poly2;

public class Animal2 {
	// animalTypeフィールド
	private String animalType;
	// ageフィールド
	private int age;
	
	// コンストラクタ
	Animal2(String animalType, int age) {
		this.animalType = animalType;
		this.age = age;
	}
	
	// sleepメソッド
	public void sleep() {
		System.out.println("スヤスヤ寝ています");
	}
	
	// eatメソッド
	public void eat(String food) {
		System.out.println(food + "を食べています");
	}
	
	// アクセッサ
	public  String getAnimalType() {
		return animalType;
	}
	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}
	public  int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
