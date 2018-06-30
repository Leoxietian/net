package com.newer.morechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private int port = 8888;//访问服务器应用的端口号
	private String host = "localhost"; //指定服务器端IP
	private Socket socket = null;
	public Client() {
		try {
			socket = new Socket(host, port);
			System.out.println("客户端连接成功.....");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//客户端消息收发
	public void talk(){
		try {
			//启动接受消息线程
			new Thread(new ClientThread(socket)).start();
		   //发送消息
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			//接受控制台输入消息流
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String msg = "";
			while((msg = br.readLine())!=null){
				pw.println(msg);
				//pw.write(msg+"\n");
				pw.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		new Client().talk();;
	}

}
