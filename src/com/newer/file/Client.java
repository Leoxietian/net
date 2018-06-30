package com.newer.file;
/*
 * 客户端
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import org.omg.CORBA_2_3.portable.OutputStream;

public class Client {
	//从文件读取数据
      FileInputStream fileIn;
      
      //套接字
      Socket socket;
      
      java.io.OutputStream out;
      
      String serverAddress="";
      int serverPort = 9000;
      
      public void start(){
    	  //获得一个要上传的文件
    	  Scanner sc = new Scanner(System.in);
    	  System.out.println("输入要上传的文件:");
    	  String file =sc.next();
    	  sc.close();
    	  
    	  //建立链接
    	  try {
			socket=new Socket(serverAddress, serverPort);
			//从套接字获得输出，发送数据
			out = socket.getOutputStream();
			
			byte[] buf=new byte[1024 * 4];
			int size;
			//
			fileIn=new FileInputStream(file);
			 
			while(-1!=(size=fileIn.read(buf))){
				out.write(buf,0,size);
				out.flush();
			}
			
			System.out.println("发送完成");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    	  try {
			fileIn.close();
		} catch (IOException e) {
			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
    	  
      }
 
     private void mian() {
    	 Client client =new Client();
    	client.start();
		// TODO Auto-generated method stub

	}
}
