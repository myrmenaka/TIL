// エコークライアント(TCP)
package poly.ex;
/*
 * ・クライアントの責任は「接続 → 送信 → 受信 → 表示」の4ステップに分かれる
 * ・Socket は TCP通信の起点であり、connect() でサーバと接続される
 * ・DataOutputStream / DataInputStream は UTF文字列などの高レベルデータを扱える
 * ・flush() は送信の即時性を保証し、setSoTimeout() は受信の待機時間を制限する
 * ・表示は「ユーザー体験の設計責任」に含まれ、受信結果を明確に伝える役割を持つ
 */
import java.io.*;
import java.net.*;
import java.util.*;
public class EchoClientTcp {
	// サーバのIPアドレス
	private static final String SERVER_IP = "localhost";
	// サーバのポート番号
	private static final int SERVER_PORT = 30000;
	

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// InetSocketAddressの作成（サーバのIPアドレスとポート番号をセット）
		// サーバのIPとポートを指定して接続先情報を構築
		InetSocketAddress endpoint = new InetSocketAddress(SERVER_IP, SERVER_PORT);
		
		try (
				// TCP通信のためのクライアントソケットを作成（まだ接続はしない）
				// クライアントソケットの作成
				Socket socket = new Socket();
		) {
			// タイムアウト１秒でサーバへ接続
			socket.connect(endpoint, 1000);
			
			try (
					// サーバからの入力ストリームを取得（バッファ付きで高速化）
					// 入力ストリームの作成　→ バッファ付き入力ストリームの作成
					BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
					// データ入力ストリームの作成
					DataInputStream dis = new DataInputStream(bis);
					
					// サーバへの出力ストリームを取得（バッファ付きで高速化）
					// 出力ストリームの作成 → バッファ付き入力ストリームの作成
					BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
					// データ出力ストリームの作成
					DataOutputStream dos = new DataOutputStream(bos);
					
			) {
				// ユーザーに文字列を入力してもらう（送信内容として使用）
				System.out.print("文字列入力：");
				// キーボード入力
				String msg = new Scanner(System.in).nextLine();
				
				// 入力された文字列をサーバへ送信
				dos.writeUTF(msg);
				// 出力ストリームのフラッシュ
				dos.flush();
				
				// サーバからの応答待ち（タイムアウトは0.5秒）
				// タイムアウトを0.5秒で設定
				socket.setSoTimeout(500);
				// 受信待ち → 受信データを msg に格納
				msg = dis.readUTF();
				
				// サーバからの応答を表示（エコー結果）
				System.out.println("エコー文字列：" + msg);
			}
		} catch (IOException e) {
			// 通信中に送受信エラーやタイムアウトが発生した場合の処理
			System.out.println(e);
		}
		

	}

}
