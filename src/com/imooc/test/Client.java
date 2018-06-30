package com.imooc.test;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) throws Exception{
     Socket client=new Socket("localhost", 8888);
      System.out.println("客户端启动");
      Scanner sc=new Scanner(client.getInputStream());
      while(sc.hasNextLine()){
    	  String line=sc.nextLine();
    	  System.out.println(line);
      }
	}
}
