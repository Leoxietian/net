package com.imooc.study.tcp;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

//客户端
public class Client {
public static void main(String[] args) throws Exception {
	//创建客户端对象，并连接服务器端地址与端口号
	Socket client=new Socket("localhost", 8888);
	//获取客户端的输入流对象(inputstream)  
	Scanner sc=new Scanner(client.getInputStream());
	while(sc.hasNextLine()){
		String line=sc.nextLine();
		System.out.println(line);
	}
	sc.close();
	client.close();
}
}
