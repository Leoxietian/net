package com.newer.chat;

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
			// 输入（接受）流对象
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			// 输出（发送）流对象
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			// 接受控制台的内容 输入流对象
			BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
			String clientmsg = sysin.readLine(); //阻塞
			while(!"bye".equals(clientmsg)){
				pw.println(clientmsg); //发送消息
				pw.flush();
				System.out.println("[client]:"+clientmsg);
				//接受消息
				String servermsg = br.readLine();//阻塞
				System.out.println("[server]:"+servermsg);
				if("bye".equals(servermsg)){
					break;
				}
				clientmsg = sysin.readLine();//阻塞
			}
			pw.println(clientmsg);
			pw.flush();
			pw.close();
			br.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		new Client().talk();;
	}

}
