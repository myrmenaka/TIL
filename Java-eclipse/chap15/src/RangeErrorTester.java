// 1桁(0~9)の加算を行うプログラム
// 入力値や計算結果が1桁の範囲外なら、独自例外を使って通知する


/*
 * 独自例外の設計 ：　RangeError を基底にして、入力用・結果用の例外を派生
 * 非検査例外の活用 ： RuntimeException を継承して、throws 宣言を省略可能に
 * 入力検証の明確化 ： isValid() で 0〜9 の範囲チェックを一元化
 * 例外による制御 ： 不正な値を検出したら即座に例外で処理を分岐
 */

import java.util.*;

// 🔸 基底の範囲外例外クラス（非検査例外にするため RuntimeException を継承）
class RangeError extends RuntimeException {
    RangeError(int n) {
        // 例外メッセージとして「範囲外の値: n」を設定
        super("範囲外の値:" + n);
    }
}

// 🔸 入力値が範囲外だった場合の例外（仮引数用）
class ParameterRangeError extends RangeError {
    ParameterRangeError(int n) {
        super(n); // 基底クラスに値を渡す
    }
}

// 🔸 計算結果が範囲外だった場合の例外（返却値用）
class ResultRangeError extends RangeError {
    ResultRangeError(int n) {
        super(n); // 基底クラスに値を渡す
    }
}

public class RangeErrorTester {
    
    // 🔸 1桁の整数かどうかを判定するメソッド（0〜9が有効）
    static boolean isValid(int n) {
        return n >= 0 && n <= 9;
    }
    
    // 🔸 1桁の整数 a と b の加算を行う
    // 入力値や結果が範囲外なら、それぞれ対応する例外を投げる
    static int add(int a, int b) throws ParameterRangeError, ResultRangeError {
        if (!isValid(a)) throw new ParameterRangeError(a); // aが範囲外なら例外
        if (!isValid(b)) throw new ParameterRangeError(b); // bが範囲外なら例外
        int result = a + b;
        if (!isValid(result)) throw new ResultRangeError(result); // 結果が範囲外なら例外
        return result;
    }

    @SuppressWarnings("resource") // Scannerのclose警告を抑制
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in); // 標準入力から値を読み取るScannerを生成
        
        System.out.print("整数a:"); int a = stdIn.nextInt(); // aの入力
        System.out.print("整数b:"); int b = stdIn.nextInt(); // bの入力
        
        try {
            // 加算結果を表示（例外が発生する可能性あり）
            System.out.println("それらの和は" + add(a, b) + "です。");
        } catch (ParameterRangeError e) {
            // 入力値が範囲外だった場合の処理
            System.out.println("加える数が範囲外です。\n" + e.getMessage());
        } catch (ResultRangeError e) {
            // 計算結果が範囲外だった場合の処理
            System.out.println("計算結果が範囲外です。\n" + e.toString());
        }
    }
}

