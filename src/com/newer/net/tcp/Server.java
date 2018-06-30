package com.newer.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 服务端
public class Server {

	// 设置端口
	int port = 9000;

	// 服务端套接字
	ServerSocket serverSocket;
	
	// 线程池
	ExecutorService pool;

	public void start() throws IOException {
		serverSocket = new ServerSocket(9000);
		System.out.println("服务器启动...");
		
		pool = Executors.newFixedThreadPool(5);
          
            
		// 接收客户端的连接请求
		while (true) {
			System.out.println("等待...");
			Socket socket = serverSocket.accept();
			System.out.println("建立了一个连接");
			
//			pool.execute(command);
			pool.submit(new DemoServcice(socket));

		}
	}

	public static void main(String[] args) {
		Server server = new Server();
		try {
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
