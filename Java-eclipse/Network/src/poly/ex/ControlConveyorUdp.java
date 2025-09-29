// PLC制御プログラム（UDP）
package poly.ex;
/*
 * ・IPアドレス：172.16.110.〇〇
 * ・サブネットマスクパターン：255.255.255.0
 * ・デフォルトルータIPアドレス：172.16.110.254
 * ・ポート番号：49152（16進数ではC000）
 * ・通信方法：ASCIIコード交信
 * 	・TCP,MCプロトコル
 * 	・UDP,MCプロトコル
 */
import java.io.*;
import java.net.*;
import java.util.*;
public class ControlConveyorUdp {
	// PLC IPアドレス
	private static final String IP = "172.16.110.174";
	// PLC ポート番号（0x = 「16進数で～」を表す）
	private static final int PORT = 0xC000;
	
	// MCプロトコル（1Eフレーム）ASCIIコード交信 ： コンベア制御コマンド（末尾2文字が未完）
	private static final String PLC_CONV_COMMAND_HEAD = "02FF00025920000000140200";
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		while (true) {
			
			// キーボードからコンベア制御用の文字列を入力
			Scanner sc = new Scanner(System.in);
			System.out.println("使用法：停止｜左行き｜右行き：[00|10|01]");
			System.out.print("入力待ち-->");
			String inputStr = sc.nextLine();
			
			// 「quit」 入力で無限ループを抜ける
			if (inputStr.equals("quit")) {
				break;
			}
		
			try (
					// データグラム・ソケットの作成
					DatagramSocket dSocket = new DatagramSocket();
			) {
				// 送信先IPアドレスの作成
				InetAddress serverIp = InetAddress.getByName(IP);
				
				// 送信用データを文字列で構成
				String msg = PLC_CONV_COMMAND_HEAD + inputStr;
				System.out.println("送信データ：" + msg);
				
				// 文字列をバイト配列へ変換
				// 送信用データ・バッファ
				byte[] sendData = msg.getBytes();
				
				// 送信用データグラム・パケットの作成
				DatagramPacket sdp = new DatagramPacket(sendData, sendData.length, serverIp, PORT);
				
				// 送信
				dSocket.send(sdp);
				
				// 受信用データ・バッファ
				byte[] receiveData = new byte[1024];
			
				// 受信用データグラム・パケットの作成
				DatagramPacket rdp = new DatagramPacket(receiveData, 1024);
				
				// タイムアウトを0.5秒で設定
				dSocket.setSoTimeout(500);
				
				// 受信待ち → 受信用データグラム・パケットへ格納
				dSocket.receive(rdp);
				
				// 受信用データグラム・パケット内のデータ（バイト配列）から、文字列の取り出し
				String str = new String(receiveData, 0, rdp.getLength());
				
				System.out.println("PLCからの応答：" + str);
				System.out.println();
			
			} catch (IOException e) {
				System.out.println(e);
			}
		}

	}

}
