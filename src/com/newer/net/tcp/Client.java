package com.newer.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	// localhost qq.com
	// 127.0.0.1
	String serverHost = "127.0.0.1";

	int serverPort = 9000;

	Socket socket;

	public void start() throws UnknownHostException, IOException {
		System.out.println("客户端启动");
		socket = new Socket(serverHost, serverPort);
		System.out.println("建立连接");
		System.out.println("客户端"+socket.getInetAddress()+"加入");
		// 接收成绩
		InputStream in = socket.getInputStream();

		byte[] buf = new byte[1024 * 4];
		int size = in.read(buf);
		String data = new String(buf, 0, size);

		System.out.println(data);
	}

	public static void main(String[] args) {

		for (int i = 0; i < 20; i++) {
			Client client = new Client();
			try {
				client.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
