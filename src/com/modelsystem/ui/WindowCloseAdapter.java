package com.modelsystem.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;

import com.modelsystem.util.Configuration;
import com.modelsystem.util.DataTransportCenter;
import com.modelsystem.util.PackageType;

/**
 * 窗口监听，关闭窗口后通知其他用户
 * @Title: WindowCloseAdapter.java 
 * @Package com.modelsystem.ui 
 * @author lxd <836696016@qq.com>
 * @date 2015年12月30日 下午2:29:22 
 * @version V1.0
 */
public class WindowCloseAdapter extends WindowAdapter{

	private String name;
	
	public WindowCloseAdapter(String name) {
		super();
		this.setName(name);
	}


	@Override
	public void windowClosing(WindowEvent e) {
		super.windowClosing(e);
		
		try {
			StringBuilder sendMsg = new StringBuilder();
			String localIP = InetAddress.getLocalHost().getHostAddress().toString();
			sendMsg.append(PackageType.OFFLINE);
			sendMsg.append("@");
			sendMsg.append(name + "_" + localIP);
			DataTransportCenter dataTransportCenter = new DataTransportCenter();
			dataTransportCenter.sendData(sendMsg.toString(), 
					Configuration.BROADCAST_IP, Configuration.PORT);
			System.out.println("主机下线");
		} catch (Exception es) {
			es.printStackTrace();
		}
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
}
