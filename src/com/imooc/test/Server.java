package com.imooc.test;


import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
public static void main(String[] args) throws Exception {
	String date="??过来了";
	ServerSocket server=new ServerSocket(8888);
	System.out.println("服务端启动");
	boolean accept=true;
	while(accept){
	Socket client= server.accept();
	System.out.println("新机器加入"+client.getInetAddress());
	PrintStream pr=new PrintStream(client.getOutputStream());
	pr.print(date);
	pr.close();}
	server.close();
	
}
}
