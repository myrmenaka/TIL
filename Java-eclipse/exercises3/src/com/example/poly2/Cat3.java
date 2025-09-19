// コンストラクタ、フィールド（private）、メソッド（public）
// sleepメソッドのオーバーライド

package com.example.poly2;

// Animalクラスの継承
public class Cat3 extends Animal2 {
	// nameフィールド
	private String name;
	// catTypeフィールド
	private String catType;
	
	// コンストラクタ
	Cat3 (String animalType, int age, String name, String catType) {
		super(animalType, age);
		this.name = name;
		this.catType = catType;
	}
	// sleepメソッドのオーバーライド
	@Override // アナテイション（注釈）：ヒューマンエラーの回避
	public void sleep() {
		System.out.print("「" + this.name + "」は、");
		// スーパークラスのメソッドに記述している内容↓
		super.sleep();
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
