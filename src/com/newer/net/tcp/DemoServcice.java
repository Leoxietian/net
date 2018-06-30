package com.newer.net.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 服务器提供的服务
 * 
 * @author wtao
 *
 */
public class DemoServcice implements Runnable {
	
	Socket socket;

	public DemoServcice(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println(name + "开始响应: " + socket.getPort());
		
		// 0 <= r < 1
		int score = (int) (Math.random() * 750);
		
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
		
		// 发送
		OutputStream out;
		try {
			out = socket.getOutputStream();
			String msg = "高考成绩" + score;
			out.write(msg.getBytes());
			out.flush();
			System.out.println(socket.getPort() + " " + msg);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(name + "完成响应: " + socket.getPort());
	}

}
