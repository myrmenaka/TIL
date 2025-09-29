// PLC制御プログラム（TDP）
package poly.ex;
/*
 * ・IPアドレス：172.16.110.〇〇
 * ・サブネットマスクパターン：255.255.255.0
 * ・デフォルトルータIPアドレス：172.16.110.254
 * ・ポート番号：49153（16進数ではC000）
 * ・通信方法：ASCIIコード交信
 * 	・TCP,MCプロトコル
 * 	・UDP,MCプロトコル
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
public class ControlConveyorTcp {
	//PLCのIPアドレス
	private static final String IP = "172.16.110.174";
	// PLCポート番号
	private static final int PORT = 0xC001; 

	// MCプロトコル（1Eフレーム）ASCIIコード交信：コンベア制御コマンド（末尾2文字が未完）
	private static final String PLC_CONV_COMMAND_HEAD = "02FF00025920000000140200";

	public static void main(String[] args) {

		while (true) {
			// キーボードからコンベア制御用の文字列を入力
			Scanner sc = new Scanner(System.in);
			System.out.println("使用法：停止｜左行き｜右行き: [00|10|01]");
			System.out.print("入力待ち-->");
			String inputStr = sc.nextLine();

			// 「quit」入力で無限ループを抜ける
			if (inputStr.equals("quit")) {
				break;
			}

			// InetSocketAddressの作成（PLCのIPアドレスとポート番号をセット）
			InetSocketAddress endpoint = new InetSocketAddress(IP, PORT);

			try (
					// クライアントソケットの作成
					Socket socket = new Socket();

			) {
				// タイムアウト1秒でサーバへ接続
				socket.connect(endpoint, 1000);

				try (
						// 入力ストリームの作成 → バッファ付き入力ストリーム作成
						BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());

						// データ入力ストリームの作成
						DataInputStream dis = new DataInputStream(bis);

						// 出力ストリームの作成 → バッファ付き出力ストリーム作成
						BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());

						// データ出力ストリームの作成
						DataOutputStream dos = new DataOutputStream(bos);

				) {
					// 送信データを文字列で構成
					String msg = PLC_CONV_COMMAND_HEAD + inputStr;
					System.out.println("送信データ: " + msg);

					// 送信
					// dos.writeUTF(msg); // 先頭に余計な2バイト(00 1a)が付くので×
					// 文字列をバイト配列にして送信
					dos.writeBytes(msg);
					// 出力ストリームのフラッシュ
					dos.flush();

					// 受信
					// msg = dis.readUTF(); // バイト配列の受けだと、文字列の終わりを判断できないので×
					// 受信データ用バイト配列
					byte[] receiveMsg = new byte[1024];
					// タイムアウトを0.5秒で設定
					socket.setSoTimeout(500);
					// 受信待ち → 受信データをreceiveMsgに格納。receiveMsg[5]-[1023] ← 0 (8200の場合)
					int n = dis.read(receiveMsg);

					System.out.print("PLCからの応答: ");
					// byte[]の0番目の要素からn個取り出す
					System.out.print(new String(receiveMsg, 0, n));
					// n ← 4 (8200の場合)
					System.out.println(" (" + n + "文字)");
					System.out.println();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
}