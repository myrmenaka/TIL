package com.example.poly2;

// Animalクラスの継承
public class Dog extends Animal {
	// nameフィールド
	private String name;
	// dogTypeフィールド
	private String dogType;
	
	// barkメソッド
	public void bark() {
		System.out.println(this.name + "はワン！ワン！と吠えています");
	}
	
	// アクセッサ
	public void setName(String name) {
		this.name = name;
	}
	public void setDogType(String dogType) {
		this.dogType = dogType;
	}
	public String getName() {
		return name;
	}
	public String getDogType() {
		return dogType;
	}

}
