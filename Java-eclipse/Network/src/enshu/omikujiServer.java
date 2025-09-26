package enshu;

import java.io.*;
import java.net.*;
import java.util.Random;

public class omikujiServer {
    // クライアントからの通信を受け付けるポート番号（0〜1023は予約済みなので避ける）
    public static final int SERVER_PORT = 30000;

    public static void main(String[] args) throws IOException {
        // クライアントからの受信データを格納するバッファ（最大1024バイト）
        byte[] data = new byte[1024];

        // UDP通信の受け口となるソケットを作成（指定ポートで待ち受ける）
        DatagramSocket dSocket = new DatagramSocket(SERVER_PORT);

        // クライアントからの受信データを格納するパケット（バッファを指定）
        DatagramPacket rdp = new DatagramPacket(data, 1024);

        // サーバは常にクライアントからの通信を待ち続ける（終了条件が来るまで）
        while (true) {
            System.out.println("接続受付中…");

            // クライアントからのデータを受信（rdpに格納される）
            dSocket.receive(rdp);

            // 受信したバイト配列を文字列に変換（クライアントが送った名前やコマンド）
            String name = new String(data, 0, rdp.getLength());
            System.out.println("受信者：" + name);

            // おみくじ結果をランダムに生成（名前は使わず結果のみ返す設計）
            String result = getOmikuji();
            System.out.println("おみくじの結果：" + result);
            System.out.println("------------------------");
            
            // メソッドチェーン
//            StringBuilder sb = new StringBuilder();
//            String echoStr = sb.append(str).append("さんの運勢は「").append(omikuji).append("」です").tostring();
//            byte[] sendData = echoStr.getBytes();
            
            // 送信する文字列（結果）をバイト配列に変換
            byte[] sendData = result.getBytes();

            // クライアントのIPとポートを指定して送信用パケットを作成
            DatagramPacket sdp = new DatagramPacket(sendData, sendData.length, rdp.getAddress(), rdp.getPort());

            // クライアントへ結果を送信
            dSocket.send(sdp);

            // クライアントから "quit" が送られてきたらループを抜けて終了
            if (name.equals("quit")) {
                break;
            }
        }

        // ソケットを閉じて通信終了（リソースを解放）
        dSocket.close();
    }

    // おみくじ結果をランダムに返すメソッド（0〜3の乱数で分岐）
    private static String getOmikuji() {
        int random = new Random().nextInt(4);

        return switch (random) {
            case 0 -> "大吉";
            case 1 -> "中吉";
            case 2 -> "小吉";
            default -> "凶";
        };
    }
}
