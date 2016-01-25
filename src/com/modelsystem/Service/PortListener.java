package com.modelsystem.Service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import com.modelsystem.controlCenter.ViewControl;
import com.modelsystem.model.User;
import com.modelsystem.ui.ActionView;
import com.modelsystem.util.Configuration;

/**
 * 接收信息中心
 * @Title: PortListener.java 
 * @Package com.modelsystem.Service 
 * @author lxd <836696016@qq.com>
 * @date 2015年12月30日 下午2:32:43 
 * @version V1.0
 */
public class PortListener implements Runnable{
	private DatagramPacket datagramPacket;
	private MulticastSocket broadcastSocket;
	private String localIP;
	private ActionView actionView;
	private User u;
	
	public PortListener(User u,ActionView actionView) {
		setU(u);
		setActionView(actionView);
		datagramPacket = new DatagramPacket(new byte[2048], 2048);
		try {
			broadcastSocket = new MulticastSocket(Configuration.PORT);
			broadcastSocket.joinGroup(InetAddress.getByName(Configuration.BROADCAST_IP));
			localIP = InetAddress.getLocalHost().getHostAddress().toString();
			System.out.println("用户登录ip为：" + localIP);
			u.setIp(localIP);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		String[] message;
		ViewControl vc;
		
		while (true) {
			try {
				broadcastSocket.receive(datagramPacket);
				String data = new String(datagramPacket.getData(),0,datagramPacket.getLength());
				message = data.split("@"); 
				
				
				System.out.println("消息为：" + data);
				System.out.println(message[1]);
				
				if (message[1].equals(localIP))
					continue; // 忽略自身消息
				
				if (message[0].equals("MESSAGE")) { // 聊天信息
					System.out.println("message!");
					vc = new ViewControl(actionView);
					new MessageService(vc).messageReceiver(message);
					
				} else if (message[0].equals("ONLINE")) {// 主机上线通知
					new OnlineAndOffLineService(u,actionView).onlineReceiver(message);
					
					
				} else if (message[0].equals("ONLINE_REPLY")) { // 主机上线回复
					new OnlineAndOffLineService(u,actionView).onlineReply(message);
					
				} else if (message[0].equals("OFFLINE")) { // 主机离线
					new OnlineAndOffLineService(u,actionView).offlineReceiver(message);
				}else if(message[0].equals("FILE")){	//传送文件
					vc = new ViewControl(actionView);
					new MessageService(vc).fileMessageReceiver(message);
				}

			} catch (Exception e) {
				System.out.println("监听程序出错！ " + e);
			}
		}
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public ActionView getActionView() {
		return actionView;
	}

	public void setActionView(ActionView actionView) {
		this.actionView = actionView;
	}
}
