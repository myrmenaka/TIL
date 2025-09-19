// コンストラクタ、フィールド（private）、メソッド（public）

package com.example.poly2;

// Animalクラスの継承
public class Cat2 extends Animal2 {
	// nameフィールド
	private String name;
	// catTypeフィールド
	private String catType;
	
	// コンストラクタ
	Cat2 (String animalType, int age, String name, String catType) {
		super(animalType, age);
		this.name = name;
		this.catType = catType;
	}
	
	// cryメソッド
	public void cry() {
		System.out.println(this.name + "はニャー！ニャー！と吠えた");
	}

	// アクセッサ
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCatType() {
		return catType;
	}
	public void setCatType(String catType) {
		this.catType = catType;
	}
}
