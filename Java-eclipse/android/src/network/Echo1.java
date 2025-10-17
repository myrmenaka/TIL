package network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Echo1 {
	//ポート番号
	static final int portNo = 49152;
	
	public static void main(String[] args) {
		//コマンドライン引数のチェック
		if(args.length != 1) {
			//実行方法を表示して終了する
			System.out.println("サーバーIPアドレスを指定してください。");
			System.exit(0);
		}

		//SocketChannel
		SocketChannel channel = null;
		
		//受信文字列
		String content;

		
		//キーボード入力
		Scanner stdin = new Scanner(System.in);
		
		//文字列入力
		System.out.print("文字列入力：");
		String msg = stdin.next();
		
		try {
			//InetSockAddress 
			InetSocketAddress address;

			//mainメソッドの引数からargs[0]をサーバーのIPアドレスとする
			InetAddress adr = InetAddress.getByName(args[0]);
			
			//IPアドレスとポート番号からInetSockAddressを作成
			address = new InetSocketAddress(adr, portNo);
			
			//SocketChannelをオープンする
			channel = SocketChannel.open();
			
			//サーバーへ接続
			channel.connect(address);
			
			//デコード用文字コード指定
			Charset charset = Charset.forName("UTF-8");

			//送信用バイトバッファの作成
			ByteBuffer sendData = charset.encode(msg);

			//サーバーへ送信
			channel.write(sendData);

			//受信用バイトバッファの作成
			ByteBuffer receiveData = ByteBuffer.allocate(512);
			
			//サーバーからのデータを受信
			channel.read(receiveData);
			
			//バッファの読み出し位置をゼロへ戻す
			receiveData.flip();
			
			//受信データを指定文字コードでデコードして文字列化
			content = charset.decode(receiveData).toString();
			
			//受信文字列の表示
			System.out.println("エコー文字列：" + content);

			//ソケットをクローズ
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
