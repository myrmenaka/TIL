// 二つの整数値を読み込んで加減乗除した結果を表示するプログラム
// 入力ミスやゼロ除算などの例外にも対応

/*
 * try-catch-finally構文で例外処理を実装し、ユーザー入力の不正やゼロ除算に対応。
 * InputMismatchExceptionは非整数入力（例：文字列）に対する例外。
 * ArithmeticExceptionはゼロ除算などの算術エラーに対応。
 * finallyブロックは、例外の有無に関係なく毎回実行されるため、再試行の確認に最適。
 */

import java.util.*;

public class ExceptionSample {

    @SuppressWarnings("resource") // Scannerのclose警告を抑制
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in); // 標準入力から値を読み取るScannerを生成

        System.out.println("xとyを加減乗除します。"); // 処理開始の案内
        
        while (true) { // ユーザーが終了を選ぶまで繰り返す
            
            // ユーザー入力と演算処理をtryブロックで囲む
            try {
                System.out.print("xの値："); // xの入力を促す
                int x = stdIn.nextInt();   // xに整数値を読み込む

                System.out.print("yの値："); // yの入力を促す
                int y = stdIn.nextInt();   // yに整数値を読み込む

                // 四則演算と剰余を表示
                System.out.println("x + y = " + (x + y)); // 加算
                System.out.println("x - y = " + (x - y)); // 減算
                System.out.println("x * y = " + (x * y)); // 乗算
                System.out.println("x / y = " + (x / y)); // 除算（整数の商）
                System.out.println("x % y = " + (x % y)); // 剰余（余り）

            // 入力が整数でない場合（例：文字列など）
            } catch (InputMismatchException e) {
                System.out.println("入力エラー発生。\n" + e); // エラー内容を表示
                String s = stdIn.next(); // 誤入力を読み飛ばす
                System.out.println(s + "は無視しました。"); // 誤入力の内容を通知
                
            // yに0を入力した場合（除算・剰余で例外発生）
            } catch (ArithmeticException e) {
                System.out.println("算術エラー発生。\n" + e); // エラー内容を表示
                System.out.println("エラーが出ないような数値をお願いします。"); // ゼロ除算の注意喚起
                
            // 例外の有無に関係なく毎回実行される処理
            } finally {
                System.out.println("--------------"); // 区切り線
                System.out.print("もう一度？ (1...Yes/0...No)："); // 続行確認
                int retry = stdIn.nextInt(); // ユーザーの選択を取得
                if (retry == 0) // 0ならループ終了
                    break;
                System.out.println("--------------"); // 次回の処理の区切り
            }
        }
    }
}
