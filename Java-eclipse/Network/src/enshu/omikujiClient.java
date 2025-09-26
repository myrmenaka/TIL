package enshu;

import java.io.*;
import java.net.*;
import java.util.*;

public class omikujiClient {
    // サーバのIPアドレス（自PCまたは指定されたIP）
//    private static final String SERVER_IP = "172.16.110.60";
    private static final String SERVER_IP = "localhost";
    
    
    // サーバのポート番号（UDPで待ち受けるポート）
    private static final int SERVER_PORT = 30000;

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        try (
            // UDP通信のためのソケットを作成（ポートはOSが自動割り当て）
            DatagramSocket dSocket = new DatagramSocket();
        ) {
            // ユーザーに名前を入力してもらう（送信内容として使用）
            System.out.print("あなたのお名前は？：");
            String msg = new Scanner(System.in).nextLine();

            // 入力された名前をバイト配列に変換（送信用データ）
            byte[] sendData = msg.getBytes();

            // サーバのIPアドレスを取得
            InetAddress serverIp = InetAddress.getByName(SERVER_IP);

            // 送信用パケットを作成（送信先IP・ポート・データを指定）
            DatagramPacket sdp = new DatagramPacket(sendData, sendData.length, serverIp, SERVER_PORT);

            // サーバへデータを送信（名前を送る）
            dSocket.send(sdp);

            // 受信用バッファを準備（最大1024バイト）
            byte[] receiveData = new byte[1024];

            // 受信用パケットを作成（受信データを格納する入れ物）
            DatagramPacket rdp = new DatagramPacket(receiveData, 1024);

            // 応答待ちのタイムアウトを0.5秒に設定（応答がなければ例外）
            dSocket.setSoTimeout(500);

            // サーバからの応答を受信（おみくじ結果）
            dSocket.receive(rdp);

            // 受信したバイト配列を文字列に変換
            String str = new String(receiveData, 0, rdp.getLength());

            // 結果をコンソールに表示
            System.out.println("------今日の運勢------");
            System.out.println(msg + "さんの運勢は「" + str + "」です");

        } catch (IOException e) {
            // 通信中に送受信エラーやタイムアウトが発生した場合の処理
            System.out.println(e);
        }
    }
}
