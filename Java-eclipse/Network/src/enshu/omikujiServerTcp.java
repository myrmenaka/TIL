package enshu;

import java.io.*;
import java.net.*;
import java.util.Random;
public class omikujiServerTcp {
	// サーバのポート番号
	private static final int SERVER_PORT = 30000;

	public static void main(String[] args) throws IOException {
		// サーバソケットの作成
		ServerSocket svSocket = new ServerSocket(SERVER_PORT);
		
		while (true) {
			System.out.println("接続受付中・・・");
			
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
			
			// クライアントから送られてきた名前を受信
			// 受信待ち → 受信データを name に格納
			String name = dis.readUTF();
			System.out.println("受信者：" + name);
			
			// おみくじ結果をランダムに生成（名前は使わず結果のみ返す設計）
            String result = getOmikuji();
            System.out.println("おみくじの結果：" + result);
            System.out.println("------------------------");
            
            // メソッドチェーン
            // TCPではバイト型への変換は不要
            StringBuilder sb = new StringBuilder();
            sb.append(name).append("さんの運勢は「").append(result).append("」です");
            
			// 受信文字列と文字列を連結して送信
			dos.writeUTF(sb.toString());
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
			if (name.equals("quit")) {
				break;
			}
		}
		
		// サーバソケットを閉じて通信終了（リソースを解放）
		// サーバソケットのクローズ
		svSocket.close();
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
