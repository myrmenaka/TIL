package network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.security.CodeSource;

public class EchoThread extends Thread{
	//SocketChannel
	private SocketChannel cSocket;
		
	//受信用バイトバッファの作成
	private ByteBuffer r_data;

	//デコード用文字コード指定
	private Charset charset;

	//コンストラクタ
	public EchoThread(SocketChannel cSC) {
					//SocketChannelの代入
		this.cSocket = cSC;
			
		//受信用ByteBufferの作成
		r_data = ByteBuffer.allocate(1024);
			
		//文字コード指定
		charset = Charset.forName("UTF-8");
	}

	//スレッドメソッドrun()
	public void run() {
		//受信文字列
		String content;
		
		try {
			//クライアントからのデータを受信
			cSocket.read(r_data);
			
			//バッファの読み出し位置をゼロへ戻す
			r_data.flip();
			
			//受信データを指定文字コードでデコードして文字列化
			content = charset.decode(r_data).toString();
			
			//受信文字列の表示
			System.out.println("受信文字列：" + content);
			
			//受信文字列を指定文字コードでバイト列へエンコードする
			ByteBuffer s_data = charset.encode(content);
			
			//クライアントへ送信する
			cSocket.write(s_data);
			
			//受信バッファのクリア
			r_data.clear();
				
			//クライアント用ソケットをクローズ
			cSocket.close();

			//次の接続待ち表示
			System.out.println("\n接続待ち.....");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
