// エコークライアント(UDP)

/*
 * SocketExceptionの親クラスにIOExceptionがあるので
 * 本来、別々にcatchを記述するのが適切ではあるが
 * IOExceptionだけのcatchでもコンパイルエラーにならない
 * 
 * ・UDPは接続レス型通信 → ソケットとパケットでやり取り
 * ・send() → 通信の出口、receive() → 通信の入口
 * ・タイムアウト設定で受信待ちの制御が可能
 * ・IOException で通信全体の例外をまとめて処理
 * ・ユーザー入力 → バイト配列 → パケット → 送信 → 受信 → 表示 の流れ
 */
package poly.ex;

import java.io.*;
import java.net.*;
import java.util.*;
public class EchoClientUdp {
	// サーバのIPアドレス（定数）
//	private static final String SERVER_IP = "172.16.110.87";
	private static final String SERVER_IP = "localhost"; // 自PC
	// サーバのポート番号（定数）
	private static final int SERVER_PORT = 30000;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// try-catch
		try (
				// 1. データグラム・ソケットの作成
				// UDPで通信するためのソケットの作成
				// ポート番号は指定せず、OSが自動割り当て
				DatagramSocket dSocket = new DatagramSocket();
				
		) {
			// ユーザー入力の取得
			System.out.println("文字列入力：");
			// キーボードから文字列を入力
			// msg に格納
			String msg = new Scanner(System.in).nextLine();
			
			// 入力文字列をバイト配列へ変換
			byte[] sendData = msg.getBytes(); // 送信用データ・バッファ
			
			// 送信先IPアドレスの作成
			InetAddress serverIp = InetAddress.getByName(SERVER_IP);
			
			// 2. 送信用データグラム・パケットの作成
			// サーバIPとポート番号を指定して送信パケットを作成
			DatagramPacket sdp = new DatagramPacket(sendData, sendData.length, serverIp, SERVER_PORT);
			
			// 3. 送信
			// 通信の出口
			dSocket.send(sdp);
			
			// 受信用バッファの用意
			byte[] receiveData = new byte[1024];
			
			// 受信用データグラム・パケットの作成
			DatagramPacket rdp = new DatagramPacket(receiveData, 1024);
			
			// タイムアウトを0.5秒で設定（応答が無ければ例外）
			dSocket.setSoTimeout(500);
			
			// 受信待ち　→　受信用データグラム・パケットへ格納
			// 通信の入口
			dSocket.receive(rdp);
			
			// 受信用データグラム・パケット内のデータ（バイト配列）から、文字列に変換
			String str = new String(receiveData, 0, rdp.getLength());
			// コンソールに表示
			System.out.println("エコー文字列：" + str);
			
		} catch (IOException e) { // 通信処理（送受信） → 受信処理（入口）：receive();　送信処理(出口)：send();
			// 通信失敗時
			// 通信中にエラーが起きた場合（送信失敗、受信タイムアウトなど）
			// SocketException は IOException の子クラスなので、まとめてcatch可能
			System.out.println(e);
		}
		

	}

}
