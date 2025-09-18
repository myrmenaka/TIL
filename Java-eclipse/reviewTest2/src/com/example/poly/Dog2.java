package com.example.poly;

public class Dog2 {
	private String name;
	private int age;
	
	// コンストラクタ
	Dog2 (String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	// セッターメソッド
	// 今回は無くてもいい
	// なぜか→飼い主や犬の名前は変わらない
	// セッターメソッドがあることで値の書き換えが不本意に起きてしまうこともあり得る→安全性に関わる
	// 設計に応じた記述が必要→値の書き換えがある可能性があるならセッターメソッドも用意する
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//	public void setAge(int age) {
//		this.age = age;
//	}
	
	// ゲットメソッド
	public String getName() {
		return this.name;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void bark() {
		System.out.println(this.name + "はワン！ワン！と吠えた");
	}
}
