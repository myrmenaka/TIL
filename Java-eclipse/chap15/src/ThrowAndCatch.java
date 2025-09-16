// 例外処理を理解するためのサンプルプログラム
// swの値に応じて検査例外または非検査例外を発生させる

/*
 * 検査例外 ： コンパイル時にthrows宣言やtry-catchが必須
 * 非検査例外 ： コンパイル時に強制されない（任意でcatch）
 * 
 * このコードでは、sw = 1 で検査例外、sw = 2 で非検査例外が発生
 * どちらも catch (Exception e) でまとめて処理しているが、設計上は分けて扱うことも重要
 */

import java.util.*;

public class ThrowAndCatch {
    
    // swの値に応じて例外を発生させるメソッド
	// メソッド定義にthrowsを書くことでここでtry-catchを書かなくてもコンパイルが通る
    static void check(int sw) throws Exception {
        switch (sw) {
            case 1 : 
                // 検査例外（checked exception）を明示的にthrow
                throw new Exception("検査例外発生‼");
            case 2 : 
                // 非検査例外（unchecked exception）をthrow
                throw new RuntimeException("非検査例外発生‼");
                
            // ここで発生した例外オブジェクトはメソッドの呼出元でtry-catchする必要がある
        }
    }
    
    // checkメソッドを呼び出すメソッド
    static void test(int sw) throws Exception {
        // checkメソッドが検査例外を投げる可能性があるため、throws宣言が必要
        check(sw);
    }
    
    
    @SuppressWarnings("resource") // Scannerのclose警告を抑制
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in); // 標準入力から値を読み取るScannerを生成
        
        System.out.print("sw:"); // ユーザーにswの入力を促す
        int sw = stdIn.nextInt(); // swの値を読み込む
        
        try {
            // testメソッドを呼び出し、例外が発生する可能性に備える
            test(sw);
        } catch (Exception e) {
            // 検査例外・非検査例外の両方をキャッチしてメッセージを表示
            System.out.println(e.getMessage());
        }
    }
}
