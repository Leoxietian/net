package com.newer.morechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ServerThread implements Runnable{
    private Socket client = null;//当前处理的客户端对应的socket对象
    private List<Socket> clientList= null;
    private BufferedReader br = null;//输入流对象
    private int num=0;//当前处理的客户端的序号
    public ServerThread(Socket client,List<Socket> clientList,int num) throws IOException {
		this.client = client;
		this.clientList = clientList;
		InputStream is = client.getInputStream();
		br = new BufferedReader(new InputStreamReader(is));
		this.num = num;
	}
	@Override
	public void run() {
		try {
		String msg = "";
		while((msg= readMsg())!=null){ //1、接收消息
			//2、群发消息
			for(Socket s:clientList){
				OutputStream os = s.getOutputStream();
				PrintWriter pw = new PrintWriter(os);
				pw.println("[client_"+num+"]:"+msg+"_(当前在线人数："+clientList.size()+")");
				pw.flush();
				} 
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	//读消息
	public String readMsg(){
		try {
			return br.readLine();
		} catch (IOException e) {
			clientList.remove(client); //移除当前客户端的连接
			return null;
		}
	}

}
