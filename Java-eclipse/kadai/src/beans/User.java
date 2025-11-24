package beans;

import java.io.Serializable;

public class User implements Serializable {
	// フィールド
	private int id;
	private String fName;
	private String gName;
	private String fName_kana;
	private String gName_kana;
	private int birth_year;
	private int birth_month;
	private int birth_day;
	
	// デフォルトコンストラクタ
	public User() {}
	
	// コンストラクタ　引数あり
	public User(int id, String fName, String gName, String fName_kana, String gName_kana, int birth_year,
			int birth_month, int birth_day) {
		this.id = id;
		this.fName = fName;
		this.gName = gName;
		this.fName_kana = fName_kana;
		this.gName_kana = gName_kana;
		this.birth_year = birth_year;
		this.birth_month = birth_month;
		this.birth_day = birth_day;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public String getfName_kana() {
		return fName_kana;
	}
	public void setfName_kana(String fName_kana) {
		this.fName_kana = fName_kana;
	}
	public String getgName_kana() {
		return gName_kana;
	}
	public void setgName_kana(String gName_kana) {
		this.gName_kana = gName_kana;
	}
	public int getBirth_year() {
		return birth_year;
	}
	public void setBirth_year(int birth_year) {
		this.birth_year = birth_year;
	}
	public int getBirth_month() {
		return birth_month;
	}
	public void setBirth_month(int birth_month) {
		this.birth_month = birth_month;
	}
	public int getBirth_day() {
		return birth_day;
	}
	public void setBirth_day(int birth_day) {
		this.birth_day = birth_day;
	}
	
	

}
