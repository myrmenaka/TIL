package com.example.poly;

public class Dog2 {
	private String name;
	private int age;
	
	// コンストラクタ
	Dog2 (String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	// ゲットメソッド
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void bark() {
		System.out.println(this.name + "はワン！ワン！と吠えた");
	}
}
