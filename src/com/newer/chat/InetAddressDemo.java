package com.newer.chat;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {
	public static void main(String[] args) throws UnknownHostException {
		//获取本机IP信息
		InetAddress  address = InetAddress.getLocalHost();
		//包含主机名+IP
		System.out.println("本机主机信息:"+address);
		InetAddress address2 = InetAddress.getByName("www.163.com");
		System.out.println("网易主机信息："+address2);
		// 通过IP获取主机信息
		InetAddress address3 = InetAddress.getByName("192.168.155.1");
		System.out.println("192.168.155.1的主机信息："+address3);
		// 通过IP地址分段获取主机信息
		InetAddress address4 = InetAddress.getByAddress(new byte[]{127,0,0,1});
        System.out.println(address4);
	}

}
