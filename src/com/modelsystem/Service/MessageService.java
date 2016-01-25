package com.modelsystem.Service;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.modelsystem.controlCenter.ViewControl;
import com.modelsystem.util.Configuration;
import com.modelsystem.util.DataTransportCenter;
import com.modelsystem.util.PackageType;

/**
 * 发送信息的service层
 * @Title: MessageService.java 
 * @Package com.modelsystem.Service 
 * @author lxd <836696016@qq.com>
 * @date 2015年12月30日 下午2:31:50 
 * @version V1.0
 */
public class MessageService {

	private DataTransportCenter dataTransportCenter;
	private ViewControl viewControl;
	private List<String> data = new ArrayList<String>();
	private String localIP;
	
	public MessageService(ViewControl viewControl) {
		try {
			setViewControl(viewControl);
			dataTransportCenter = new DataTransportCenter();
			localIP = InetAddress.getLocalHost().getHostAddress().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送聊天信息
	 */
	public void sendMessage(String[] messages) {
		StringBuilder sendMsg = new StringBuilder();
		
		sendMsg.append(PackageType.MESSAGE);
		sendMsg.append("@");
		sendMsg.append(localIP);
		sendMsg.append("@");
		sendMsg.append(messages[1]);
		sendMsg.append("@");
		sendMsg.append(messages[2]);
		
		try {
			dataTransportCenter.sendData(sendMsg.toString(), messages[0], Configuration.PORT);
			System.out.println("发送成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String chat = "你对 " + messages[3] + " 说: ";
		chat = chat + messages[1] + "\n";
		data.clear();
		data.add(chat);
		viewControl.change(data,"chat");
	}
	
	public void sendFileView(String ip,String name){
		StringBuilder sendMsg = new StringBuilder();
		sendMsg.append(PackageType.FILE);
		sendMsg.append("@");
		sendMsg.append(localIP);
		sendMsg.append("@");
		sendMsg.append(name + "发送文件给你,是否接受？");
		try {
			dataTransportCenter.sendData(sendMsg.toString(), ip, Configuration.PORT);
			System.out.println("发送成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 接受从端口监听程序传过来的数据，并处理数据，在面板上显示。
	 * @param message
	 */
	public void messageReceiver(String[] message) {
		String msg = message[3] + " 对你说：" + message[2] + "\n";
		data.clear();
		data.add(msg);
		viewControl.change(data,"chat");
	}
	
	public void fileMessageReceiver(String[] message){
		int option = JOptionPane.showConfirmDialog(null,message[2], "保存文件？", 
				JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE, null);
		switch (option) {
			case JOptionPane.YES_NO_OPTION: {
				new FileClient(message[1]);
				break;
			}
			case JOptionPane.NO_OPTION: break;

			}
	}
	
	

	public ViewControl getViewControl() {
		return viewControl;
	}

	public void setViewControl(ViewControl viewControl) {
		this.viewControl = viewControl;
	}
}
