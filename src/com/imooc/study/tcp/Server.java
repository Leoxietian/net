package com.imooc.study.tcp;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
//服务器
public class Server {
public static void main(String[] args) throws Exception {
	String data ="你来了";
	
	//服务端准备就绪，端口号为8888
	ServerSocket server=new ServerSocket(8888);
	System.out.println("服务端准备就绪。。。。。");
	//接受连接的客户端对象
	boolean accept=true;
	while(accept){
	Socket client=server.accept();
	System.out.println("连接过来的客户端："+client.getInetAddress());
	//获取该客户端的输出对象，给客户端输出数据，将输出流包装为打印流 [打印流可以打印任何输出类型]
	PrintStream out=new PrintStream(client.getOutputStream());
	out.print(data);
	//记得关流
	out.close();}
	server.close();
}
}
