// エコーサーバ（UDP）
// try-catch有りバージョン

/*
 * ・UDPは接続レス型通信 → ソケットとパケットでやり取り
 * ・receive() → 通信の入口、send() → 通信の出口
 * ・DatagramPacket は送受信の入れ物
 * ・"quit" で終了する制御設計
 * ・try-catchで通信(IOException)とソケット(SocketException)の責任を分離している
 */
package poly.ex;

import java.io.*;
import java.net.*;

public class EchoServerUdpTryCatch {
	// サーバのポート番号（定数） 任意の数字（０～１０２３以外）
    public static final int SERVER_PORT = 30000;

    public static void main(String[] args) {
    	// 送受信用データ・バッファ
        byte[] data = new byte[1024];

        try {
        	// 1. データグラム・ソケットの作成
        	// UDPで通信するためのソケットを作成
    		// SERVER_PORTで待ち受ける
            DatagramSocket dSocket = new DatagramSocket(SERVER_PORT);
            // 2. 受信用データグラム・パケットの作成
            // クライアントからデータを受け取るための入れ物
    		// data はバイト配列（最大1024バイト）
            DatagramPacket rdp = new DatagramPacket(data, 1024);
            
            // 無限ループ
            // サーバは常にクライアントからの接続を待ち続ける
    		// "quit"が来たら終了
            while (true) {
                System.out.println("接続受付中…");

                try {
                	// 3. 受信待ち　→　受信用データグラム・パケットへ格納
                	// クライアントからのデータを受信
                	dSocket.receive(rdp);
                	// 受信用データグラム・パケット内のデータ（バイト配列）から、文字列に変換
                    String str = new String(data, 0, rdp.getLength());
                    // 取り出してコンソール上に表示
                    System.out.println("受信文字列：" + str);

                    // 4. 送信用データグラム・パケットの作成
                    // 受け取ったデータをそのまま送り返す（エコー）
        			// 宛先は受信元のIPアドレスとポート番号
                    DatagramPacket sdp = new DatagramPacket(data, rdp.getLength(), rdp.getAddress(), rdp.getPort());
                    
                    // 5. 送信
                    dSocket.send(sdp);
                    
                    // 終了判定
        			// クライアントから"quit"が送られてきたら、ループを抜けて終了
                    if (str.equals("quit")) {
                        break;
                    }
                } catch (IOException e) { // 通信処理（送受信） → 受信処理（入口）：receive();　送信処理(出口)：send();
                    System.out.println(e);
                }
            }
            // データグラム・ソケットのクローズ
            // 通信を終了し、リソースを解放
            dSocket.close();

        } catch (SocketException e) { // DatagramSocketの作成
            System.out.println(e);
        }
    }
}

