import java.util.Random;

class Dice extends Random{
  int face = 6;
  public int rollDice() {
    return this.nextInt(face)+1;
  }
}

public class DiceTester {
  public static void main(String[] args) {
     System.out.println("さいころを作って振ります。");
     Dice dice = new Dice();
     System.out.printf("さいころを振る。出目は、%dです。\n",dice.rollDice());
     System.out.printf("さいころを振る。出目は、%dです。\n",dice.rollDice());
     System.out.printf("さいころを振る。出目は、%dです。\n",dice.rollDice());
     System.out.printf("さいころを振る。出目は、%dです。\n",dice.rollDice());
     System.out.printf("さいころを振る。出目は、%dです。\n",dice.rollDice());
  }
}

