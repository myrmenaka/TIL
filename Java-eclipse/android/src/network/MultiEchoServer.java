package network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class MultiEchoServer {
	//ポート番号
	static final int portNo = 30000;
	
	public static void main(String [] args) {
		//ServerSocketChannel
		ServerSocketChannel svSocket = null;
		
		//SocketChannel
		SocketChannel cSocket = null;
		
		try {
			//ServerSocketChannelをオープン
			svSocket = ServerSocketChannel.open();
			
			//PCのIPアドレスと指定ポート番号にバインドする
			svSocket.bind(new InetSocketAddress(portNo));
			
			//接続中受付表示
			System.out.println("接続受付中.....");
			
			//クライアントからの接続待ちループ
			while(true) {
				//接続待ち
				cSocket = svSocket.accept();

				//接続クライアントの表示
				System.out.println(cSocket.getRemoteAddress().toString());
				
				//スレッドクラスインスタンスの作成
				EchoThread th = new EchoThread(cSocket);
				
				//スレッドの開始
				th.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//ServerSocketChannelをクローズ
			try {
				svSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
