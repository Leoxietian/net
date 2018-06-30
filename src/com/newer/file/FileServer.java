package com.newer.file;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



/*
 * 服务器：基于 TCP 套接字建立链接进行文件传输
 */
public class FileServer {

	// 服务器
	ServerSocket serverSocket;

	int port = 9000;

	ExecutorService pool;

	String filePath = "D:/files";

	public void start(){
		try {
			serverSocket = new ServerSocket(port);
			pool = Executors.newCachedThreadPool();
			while(true){
				Socket  socket = serverSocket.accept();
				
				pool.execute(new Runnable(){

	public void run(){
		ByteArrayInputStream data =new ByteArrayInputStream();
					
					try (InputStream in=socket.getInputStream()){
					
						byte[] buf = new byte[1024 * 4];
					int size;
					while (-1 != (size = in.read(buf))) {
						data.write(buf, 0, size);
					}
					} catch (Exception e) {
						e.printStackTrace();
					}
					//接收到的数据
					byte[] info = data.toByteArray();
					String file = "";
					
					try {
						// 获得文件的散列值
						byte[] hash = MessageDigest.getInstance("SHA-256").digest(info);
						file = new BigInteger(1, hash).toString(16);
					} catch (NoSuchAlgorithmException e) {
					
						e.printStackTrace();
					}

					
					try {
						FileOutputStream out = new FileOutputStream(new File(filePath, file))) {
							out.write(info);
							System.out.println("上传完成");
					} catch (Exception e) {
						System.out.println("上传失败");
					}
				}
				
					});
					}

	}catch(IOException e)
	{
		e.printStackTrace();
	}

}

	public static void main(String[] args) {
		FileServer server = new FileServer();
		server.start();
	}
}