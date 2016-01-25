package com.modelsystem.controlCenter;

import java.awt.EventQueue;
import java.util.LinkedList;
import java.util.List;

import com.modelsystem.ui.MainView;

/**
 * 控制中心
 * @Title: ControlCenter.java 
 * @Package com.modelsystem.controlCenter 
 * @author lxd <836696016@qq.com>
 * @date 2015年12月30日 下午2:35:25 
 * @version V1.0
 */
public class ControlCenter {

	public static List<String> onlineIP = new LinkedList<String>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.getFrmPp().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
