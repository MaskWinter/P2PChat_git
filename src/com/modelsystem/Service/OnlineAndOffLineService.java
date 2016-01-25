package com.modelsystem.Service;

//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
import java.net.InetAddress;
//import java.net.MulticastSocket;


import javax.swing.JOptionPane;

import com.modelsystem.controlCenter.ControlCenter;
import com.modelsystem.controlCenter.ViewControl;
import com.modelsystem.model.User;
import com.modelsystem.ui.ActionView;
import com.modelsystem.util.Configuration;
import com.modelsystem.util.DataTransportCenter;
import com.modelsystem.util.PackageType;

/**
 * 上下线通知
 * @Title: OnlineAndOffLineService.java 
 * @Package com.modelsystem.Service 
 * @author lxd <836696016@qq.com>
 * @date 2015年12月30日 下午2:32:19 
 * @version V1.0
 */
public class OnlineAndOffLineService {
	private String localIP;
	private User u;
//	private MulticastSocket multicastSocket;
	private DataTransportCenter dataTransportCenter;
	private ActionView actionView;
	
	public OnlineAndOffLineService(User u,ActionView actionView) {
		try {
			localIP = InetAddress.getLocalHost().getHostAddress().toString();
//			multicastSocket = new MulticastSocket(Configuration.PORT);
			dataTransportCenter = new DataTransportCenter();
			setU(u);
			setActionView(actionView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送上线信息
	 */
	public void online() {
		StringBuilder sendMsg = new StringBuilder();
		sendMsg.append(PackageType.ONLINE);
		sendMsg.append("@");
		sendMsg.append(localIP);
		sendMsg.append("@");
		sendMsg.append(u.getName());
		
		try {
//			multicastSocket.joinGroup(InetAddress.getByName(Configuration.BROADCAST_IP));
			dataTransportCenter.sendData(sendMsg.toString(), Configuration.BROADCAST_IP, Configuration.PORT);
//			InetAddress adds = InetAddress.getByName(Configuration.BROADCAST_IP);
//			DatagramSocket ds = new DatagramSocket();
//			DatagramPacket dp = new DatagramPacket(sendMsg.toString().getBytes(),
//					sendMsg.toString().length(), adds, Configuration.PORT);
//			ds.send(dp);
//			ds.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 接受到本机上线回复，添加对方ip到在线列表。
	 * @param message
	 */
	public void onlineReply(String[] message){
		ControlCenter.onlineIP.add(message[2] + "_" +message[1]);
		ViewControl vc = new ViewControl(actionView);
		vc.change(ControlCenter.onlineIP,"friend");
	}
	
	/**
	 * 收到其他主机上线信息并回复
	 * @param message
	 */
	public void onlineReceiver(String[] message) {
		StringBuilder replyMsg = new StringBuilder();
		
		replyMsg.append(PackageType.ONLINE_REPLY);
		replyMsg.append("@");
		replyMsg.append(localIP);
		replyMsg.append("@");
		replyMsg.append(u.getName());
		
		System.out.println("发送信息：" + replyMsg.toString());
		System.out.println("发送目的地：" + message[1]);
		try {
			dataTransportCenter.sendData(replyMsg.toString(), message[1], Configuration.PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ControlCenter.onlineIP.add(message[2] + "_" + message[1]);
		ViewControl vc = new ViewControl(actionView);
		vc.change(ControlCenter.onlineIP,"friend");
	}
	
	/**
	 * 收到其他主机下线消息。
	 * @param message
	 */
	public void offlineReceiver(String[] message) {
		ControlCenter.onlineIP.remove(message[1]);
		ViewControl vc = new ViewControl(actionView);
		vc.change(ControlCenter.onlineIP,"friend");
		JOptionPane.showMessageDialog(null, "你有一个好友下线", "提示信息",JOptionPane.INFORMATION_MESSAGE);
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
