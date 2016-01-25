package com.modelsystem.util;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 数据发送中心
 * @Title: DataTransportCenter.java 
 * @Package com.modelsystem.util 
 * @author lxd <836696016@qq.com>
 * @date 2015年12月30日 下午2:30:24 
 * @version V1.0
 */
public class DataTransportCenter {
	private DatagramSocket socket;
	
	public DataTransportCenter() {
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送数据
	 * @param data 数据
	 * @param targetIP 目标ip
	 * @param port 目标端口
	 * @return
	 * @throws Exception
	 */
	public boolean sendData(String data, String targetIP, int port) throws Exception {
		DatagramPacket dp = null;
		InetAddress address = InetAddress.getByName(targetIP);
		
		byte[] msg = new byte[1024];
		msg = data.getBytes();

		dp = new DatagramPacket(msg, msg.length, address, port);
		
		socket.send(dp);
		
		return true;
	}
}
