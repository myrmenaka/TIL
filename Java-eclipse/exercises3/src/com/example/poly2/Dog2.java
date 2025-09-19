// コンストラクタ、フィールド（private）、メソッド（public）

package com.example.poly2;

// Animalクラスの継承
public class Dog2 extends Animal2 {
	// nameフィールド
	private String name;
	// dogTypeフィールド
	private String dogType;
	
	// コンストラクタ
	Dog2 (String animalType, int age, String name, String dogType) {
		super(animalType, age);
		this.name = name;
		this.dogType = dogType;
	}
	
	// barkメソッド
	public void bark() {
		System.out.println(this.name + "はワン！ワン！と吠えています");
	}
	
	// アクセッサ
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDogType() {
		return dogType;
	}
	public void setDogType(String dogType) {
		this.dogType = dogType;
	}
	
	

}
