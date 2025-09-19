package com.example.poly2;

// Animalクラスの継承
public class Dog1 extends Animal1 {
	// nameフィールド
	String name;
	// dogTypeフィールド
	String dogType;
	
	// barkメソッド
	public void bark() {
		System.out.println(this.name + "はワン！ワン！と吠えています");
	}

}

