// エコーサーバ（TCP）
// try-catch無しバージョン
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
public class EchoServerTcp {
	// サーバのポート番号
	private static final int SERVER_PORT = 30000;

	public static void main(String[] args) throws IOException {
		// サーバソケットの作成
		ServerSocket svSocket = new ServerSocket(SERVER_PORT);
		
		while (true) {
			System.out.println("接続準備中・・・");
			
			// クライアントからの接続要求待ち → 接続後、クライアントソケットの作成
			Socket cSocket = svSocket.accept();
			
			// クライアントからの入力ストリームを取得（バッファ付きで高速化）
			// 入力ストリームの作成 → バッファ付き入力ストリーム作成
			BufferedInputStream bis = new BufferedInputStream(cSocket.getInputStream());
			// データ入力ストリームの作成
			DataInputStream dis = new DataInputStream(bis);
			
			// クライアントへの出力ストリームを取得（バッファ付きで高速化）
			// 出力ストリームの作成 → バッファ付き出力ストリーム作成
			BufferedOutputStream bos = new BufferedOutputStream(cSocket.getOutputStream());
			// データ出力ストリームの作成
			DataOutputStream dos = new DataOutputStream(bos);
			
			// クライアントから送られてきた文字列を受信
			// 受信待ち → 受信データを str に格納
			String str = dis.readUTF();
			System.out.println("受信文字列：" + str);
			
			// 受信した文字列をそのままクライアントに送り返す（エコー）
			// 受信文字列をそのまま送信
			dos.writeUTF(str);
			// 出力ストリームのフラッシュ
			// バッファの内容を即時送信
			dos.flush();
			
			// 通信終了処理（各ストリームとソケットを閉じる）
			// 入力ストリームのクローズ
			dis.close();
			// 出力ストリームのクローズ
			dos.close();
			// クライアントソケットのクローズ
			cSocket.close();
			
			// クライアントから "quit" が送られてきたらループを抜けて終了
			if (str.equals("quit")) {
				break;
			}
		}
		
		// サーバソケットを閉じて通信終了（リソースを解放）
		// サーバソケットのクローズ
		svSocket.close();
	}

}
