package com.newer.morechat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	private int port = 8888; // 指定端口号
	private ServerSocket ss = null; //服务器端的socket对象
	private List<Socket> clientList = null; //存放所有已连接的客户端集合
	private int num=0;//标识客户端的序号
	public Server() {
		try {
			ss = new ServerSocket(port);
			clientList = new ArrayList<Socket>();
			System.out.println("服务器端启动完毕.....");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//服务器端开始侦听
	public void service(){
		try {
			while(true){
			Socket client =  ss.accept(); //阻塞的 与客户端请求对应的socket对象
			System.out.println("新的客户请求："+client);
			clientList.add(client);
			num++;
			//当前客户端通信交给线程处理
			new Thread(new ServerThread(client, clientList, num) ).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Server().service();
	}

}
