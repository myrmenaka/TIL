// コンストラクタ、フィールド（private）、メソッド（public）
// sleepメソッドのオーバーライド

package com.example.poly2;

// Animalクラスの継承
public class Dog3 extends Animal2 {
	// nameフィールド
	private String name;
	// dogTypeフィールド
	private String dogType;
	
	// コンストラクタ
	Dog3 (String animalType, int age, String name, String dogType) {
		super(animalType, age);
		this.name = name;
		this.dogType = dogType;
	}
	
	// sleepメソッドのオーバーライド
	@Override // アナテイション（注釈）：ヒューマンエラー回避
	public void sleep() {
		System.out.print("「" + this.name + "」は、");
		// スーパークラスのメソッドに記述している内容↓
		super.sleep();
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
