package com.example.poly2;

// Animalクラスの継承
public class Cat1 extends Animal1 {
	// nameフィールド
	String name;
	// catTypeフィールド
	String catType;
	
	// cryメソッド
	public void cry() {
		System.out.println(this.name + "はニャー！ニャー！と吠えた");
	}
}
