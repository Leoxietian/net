package com.newer.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private int port = 8888; // 指定端口号
	private ServerSocket ss = null; //服务器端的socket对象
	public Server() {
		try {
			ss = new ServerSocket(port);
			System.out.println("服务器端启动完毕.....");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//服务器端开始侦听
	public void service(){
		try {
			while(true){
			Socket client =  ss.accept(); //阻塞的
			System.out.println("新的客户请求："+client);
			// 输入（接受）流对象
			InputStream is = client.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			// 输出（发送）流对象
			OutputStream os = client.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			// 接受控制台的内容 输入流对象
			BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
			// 先接受一条消息
			String clientmsg = br.readLine();
			System.out.println("[clientmsg]:"+clientmsg);
			// 接受控制台的消息
			String servermsg = sysin.readLine();
			while(!"bye".equals(servermsg)){
				//向客户端发送消息
				pw.println(servermsg);
				pw.flush();
				System.out.println("[server]:"+servermsg);
				// 接受消息
				clientmsg = br.readLine();
				System.out.println("[client]:"+clientmsg);
				if("bye".equals(clientmsg)){
					break;
				}
				servermsg = sysin.readLine();
			}
			pw.println(servermsg);
			pw.flush();
			pw.close();
			br.close();
			client.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Server().service();
	}

}
