package com.newer.morechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

//客户端线程：接收消息
public class ClientThread implements Runnable {
  // private Socket client = null;
   private BufferedReader br = null;
   public ClientThread(Socket client) throws IOException {
	//this.client = client;
	br = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }
	@Override
	public void run() {
		try {
			String msg = br.readLine();
			while(msg!=null){
				System.out.println(msg);
				msg = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
