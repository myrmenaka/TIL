import java.util.Scanner;

public class DayComparator {

	  //--- d1とd2の日付は等しいか？ ---//
	  static boolean compDay(Day d1, Day d2) {
	        return d1.getYear()  == d2.getYear()  &&
	               d1.getMonth() == d2.getMonth()  &&
	               d1.getDate()  == d2.getDate();
	  }
	  
	  public static void main(String[] args) {
	        Scanner stdIn = new Scanner(System.in);
	        
	        int y, m, d;
	        System.out.println("day1を入力せよ。");
	        System.out.print("年：");  y = stdIn.nextInt();
	        System.out.print("月：");  m = stdIn.nextInt();
	        System.out.print("日：");  d = stdIn.nextInt();
	         Day day1 = new Day(y, m, d);
	         System.out.println("day2を入力せよ。");
	         System.out.print("年：");  y = stdIn.nextInt();
	         System.out.print("月：");  m = stdIn.nextInt();
	         System.out.print("日：");  d = stdIn.nextInt();
	         Day day2 = new Day(y, m, d);
	         
	         if (compDay(day1, day2))
	              System.out.println("等しいです。");
	         else
	              System.out.println("等しくありません。");
	  }
}
