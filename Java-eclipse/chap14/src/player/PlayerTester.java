// 利用クラス

package player;

public class PlayerTester {

	public static void main(String[] args) {
		IPlayer[] a = new IPlayer[2];
		a[0] = new VideoPlayer();
		a[1] = new CDPlayer();
		
		for (IPlayer p : a) {
			p.play();
			p.stop();
			System.out.println();
		}

	}

}
