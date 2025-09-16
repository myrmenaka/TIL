// 実行用クラス
import java.util.*;

public class PetTester {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		// スーパークラス Pet のインスタンス生成
		Pet kurt = new Pet("kurt", "アイ");
		// スーパークラスのオブジェクトのintoroduceメソッド
		kurt.introduce(); // Petクラスのintroduceメソッド呼出
		System.out.println(); // 出力
		
		// サブクラスのインスタンス生成
		RobotPet r2d2 = new RobotPet("R2D2", "ルーク");
		r2d2.introduce(); // RobotPetクラスのintroduceメソッド呼出（オーバーライド）
		System.out.println();
		
		// ユーザー入力によって参照先を切り替える
		System.out.print("(1)kurt (2)R2D2:");
		// swをキーボード入力
		int sw = stdIn.nextInt();
		
		// Petクラスの参照変数（スーパークラス型）
		Pet p; 
		
		// swが1であればPetを参照、2であればRobotPetを参照する
		// 条件に応じて参照先を切り替える（ポリモーフィズムの活用）
		if (sw == 1) {
			p = kurt; // Pet型インスタンスを参照
		} else {
			p = r2d2; // RobotPet型インスタンスを参照（Pet型として扱う）
		}
		// 実行時に参照先の型に応じてintroduceメソッドが呼ばれる（動的バインディング）
		p.introduce();
		// RobotPetクラスに固有のメソッド work を呼び出し（swを引数に渡す）
		r2d2.work(sw); // p.work()はコンパイルエラーとなる（Pet型にはwork()が存在しないため）
		
		
		// RobotPetクラスのメソッドが呼び出される
		// RobotPetはPetを継承している（is-a関係）
		// Pet型の参照変数にRobotPet型のインスタンスを代入 → アップキャスト（暗黙的な型変換）
		// 参照型はPetだが、実体型はRobotPet → 実行時にRobotPetのintroduce()が呼ばれる（動的バインディング）
		// rの実体はRobotPetなので、オーバーライドされたメソッドが優先される
		Pet r = new RobotPet("あい", "えお");
		r.introduce(); // 実体がRobotPetなので、RobotPetのintroduce()が呼ばれる
		System.out.println();
	}

}
