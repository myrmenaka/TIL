public class Basic2 {

    public static void main(String[] args) {
        // 🔸 初期配列を定義（1〜5の整数）
        int[] array = {1, 2, 3, 4, 5};

        int right, tmp; // 右側のインデックスと一時保存用変数

        // 🔸 配列の前半と後半の要素を入れ替えるループ
        for (int left = 1; left <= (array.length / 2); left++) {
            // A: 左側のインデックスは left - 1（0から始まる）
            //    右側のインデックスは array.length - left（末尾から順に）

            right = array.length - left;     // 右側のインデックスを計算
            tmp = array[right];              // 右側の値を一時保存
            array[right] = array[left - 1];  // 左側の値を右側にコピー
            // B: 左側に一時保存していた右側の値を代入
            array[left - 1] = tmp;           // 左右の値を入れ替え完了
        }

        // 🔸 配列の全要素を表示（反転後の結果）
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}
