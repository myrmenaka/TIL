// プレイヤーの動作確認用クラス（利用クラス）
package player;

public class PlayerTester {

    public static void main(String[] args) {
        // IPlayer型の配列を2つ分用意（インターフェース型で定義）
        // → VideoPlayerやCDPlayerなど、IPlayerを実装したクラスを共通の型で扱える
        IPlayer[] a = new IPlayer[2]; // VideoPlayerとCDPlayerの分（要素数2の配列）

        // IPlayerインターフェースを実装した具象クラスのインスタンスを配列に格納
        // → 多態性（ポリモーフィズム）により、同じ操作で異なる動作を実現
        a[0] = new VideoPlayer(); // ビデオプレイヤーの生成(配列に直接格納するので、戻り値の型の明示は不要)
        a[1] = new CDPlayer();    // CDプレイヤーの生成

        // 配列内の各プレイヤーに対して、共通の操作（playとstop）を実行
        // → 実際の動作は各クラスのオーバーライドされたメソッドに委ねられる
        for (IPlayer p : a) {
            p.play();  // 再生開始（VideoPlayerならビデオ、CDPlayerならCD）
            p.stop();  // 再生停止（それぞれのクラスで異なる実装）
            System.out.println(); // 改行して出力を見やすくする
        }
    }
}
