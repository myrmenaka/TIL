package com.example.poly2;

// Animalクラスの継承
public class Cat extends Animal {
	// nameフィールド
	String name;
	// catTypeフィールド
	String catType;
	
	// cryメソッド
	public void cry() {
		System.out.println(this.name + "はニャー！ニャー！と吠えた");
	}
}
