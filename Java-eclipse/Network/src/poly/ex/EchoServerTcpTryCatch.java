//エコーサーバ（TCP）
// try-catch有りバージョン
package poly.ex;
/*
* ・ServerSocket は TCP通信の受け口として機能し、accept() で接続を受け付ける
* ・Socket はクライアントとの通信路を表し、入出力ストリームを通じてデータをやり取りする
* ・DataInputStream / DataOutputStream は UTF文字列などの高レベルデータを扱える
* ・flush() はバッファの内容を即座に送信する責任を持つ
* ・"quit" を受け取ったらサーバを終了する設計は、制御構造の明確な責任分離になっている
*/
import java.io.*;
import java.net.*;

public class EchoServerTcpTryCatch {
 // サーバのポート番号（クライアントが接続してくる入口）
 private static final int SERVER_PORT = 30000;

 public static void main(String[] args) {
     try {
         // TCP通信の受け口となるサーバソケットを作成（指定ポートで待ち受ける）
         ServerSocket svSocket = new ServerSocket(SERVER_PORT);

         while (true) {
             System.out.println("接続準備中・・・");

             // クライアントからの接続要求を受け付け、通信ソケットを取得
             Socket cSocket = svSocket.accept();

             try {
                 // クライアントからの入力ストリームを取得（バッファ付きで高速化）
                 BufferedInputStream bis = new BufferedInputStream(cSocket.getInputStream());
                 DataInputStream dis = new DataInputStream(bis);

                 // クライアントへの出力ストリームを取得（バッファ付きで高速化）
                 BufferedOutputStream bos = new BufferedOutputStream(cSocket.getOutputStream());
                 DataOutputStream dos = new DataOutputStream(bos);

                 // クライアントから送られてきた文字列を受信
                 String str = dis.readUTF();
                 System.out.println("受信文字列：" + str);

                 // 受信した文字列をそのままクライアントに送り返す（エコー）
                 dos.writeUTF(str);
                 dos.flush(); // バッファの内容を即時送信

                 // 通信終了処理（各ストリームとソケットを閉じる）
                 dis.close();
                 dos.close();
                 cSocket.close();

                 // "quit" が送られてきたらサーバを終了
                 if (str.equals("quit")) {
                     break;
                 }
             } catch (IOException e) {
                 // クライアントとの通信中にエラーが発生した場合の処理
                 System.out.println("クライアントとの通信エラー：" + e);
                 // 通信ソケットを閉じてリソースを解放
                 cSocket.close();
             }
         }

         // サーバソケットを閉じて通信終了（リソースを解放）
         svSocket.close();

     } catch (IOException e) {
         // サーバソケットの作成や接続受付中にエラーが発生した場合の処理
         System.out.println("サーバ起動エラー：" + e);
     }
 }
}
