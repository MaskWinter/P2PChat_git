package com.modelsystem.ui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

/**
 * 聊天界面
 * @Title: ActionView.java 
 * @Package com.modelsystem.ui 
 * @author lxd <836696016@qq.com>
 * @date 2015年12月30日 下午2:26:29 
 * @version V1.0
 */
public class ActionView {
	
	private MainView mainView;
	private JList<String> friendList;
	private JTextArea chatArea;
	private JTextArea chatContextArea;
	private JLabel userName = new JLabel("");
	
	public ActionView(MainView mainView) {
		this.mainView = mainView;
	}
	
	public void init(){
		JPanel panel = mainView.getPanel();
		panel.removeAll();
		MyActionListener listener = new MyActionListener(this);
		
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JScrollPane chatScrollPane = new JScrollPane();
		chatContextArea = new JTextArea();
		chatContextArea.setEditable(false);
		chatScrollPane.setViewportView(chatContextArea);
		sl_panel.putConstraint(SpringLayout.NORTH, chatScrollPane, 44, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, chatScrollPane, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, chatScrollPane, -136, SpringLayout.SOUTH, panel);
		panel.add(chatScrollPane);
		
		JScrollPane friendScrollPane = new JScrollPane();
		sl_panel.putConstraint(SpringLayout.SOUTH, friendScrollPane, -136, SpringLayout.SOUTH, panel);
		friendList = new JList<>();
		friendScrollPane.setViewportView(friendList);
		sl_panel.putConstraint(SpringLayout.EAST, chatScrollPane, -6, SpringLayout.WEST, friendScrollPane);
		sl_panel.putConstraint(SpringLayout.WEST, friendScrollPane, 555, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, friendScrollPane, -10, SpringLayout.EAST, panel);
		panel.add(friendScrollPane);
		
		JLabel tip = new JLabel("聊天记录");
		sl_panel.putConstraint(SpringLayout.NORTH, tip, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, tip, 256, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, tip, -6, SpringLayout.NORTH, chatScrollPane);
		sl_panel.putConstraint(SpringLayout.EAST, tip, 362, SpringLayout.WEST, panel);
		tip.setFont(new Font("宋体", Font.PLAIN, 25));
		panel.add(tip);
		
		JLabel friendL = new JLabel("好友列表");
		sl_panel.putConstraint(SpringLayout.NORTH, friendScrollPane, 8, SpringLayout.SOUTH, friendL);
		sl_panel.putConstraint(SpringLayout.NORTH, friendL, 6, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, friendL, -32, SpringLayout.EAST, panel);
		friendL.setFont(new Font("宋体", Font.PLAIN, 25));
		panel.add(friendL);
		
		JButton chatButton = new JButton("发送");
		sl_panel.putConstraint(SpringLayout.NORTH, chatButton, 17, SpringLayout.SOUTH, friendScrollPane);
		chatButton.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(chatButton);
		chatButton.addActionListener(listener);
		
		
		chatArea = new JTextArea();
		sl_panel.putConstraint(SpringLayout.WEST, chatButton, 40, SpringLayout.EAST, chatArea);
		sl_panel.putConstraint(SpringLayout.NORTH, chatArea, 6, SpringLayout.SOUTH, chatScrollPane);
		sl_panel.putConstraint(SpringLayout.WEST, chatArea, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, chatArea, 126, SpringLayout.SOUTH, chatScrollPane);
		sl_panel.putConstraint(SpringLayout.EAST, chatArea, 0, SpringLayout.EAST, chatScrollPane);
		panel.add(chatArea);
		
		userName.setFont(new Font("宋体", Font.PLAIN, 12));
		sl_panel.putConstraint(SpringLayout.NORTH, userName, 0, SpringLayout.NORTH, tip);
		sl_panel.putConstraint(SpringLayout.WEST, userName, 0, SpringLayout.WEST, chatScrollPane);
		panel.add(userName);
		
		JButton fileButton = new JButton("文件传输");
		sl_panel.putConstraint(SpringLayout.NORTH, fileButton, 23, SpringLayout.SOUTH, chatButton);
		sl_panel.putConstraint(SpringLayout.WEST, fileButton, 0, SpringLayout.WEST, friendL);
		fileButton.setFont(new Font("宋体", Font.PLAIN, 20));
		panel.add(fileButton);
		fileButton.addActionListener(listener);
		
		panel.updateUI();
	}

	public JList<String> getFriendList() {
		return friendList;
	}


	public JTextArea getChatArea() {
		return chatArea;
	}


	public JTextArea getChatContextArea() {
		return chatContextArea;
	}

	public JLabel getUserName() {
		return userName;
	}

	public void setUserName(JLabel userName) {
		this.userName = userName;
	}

	public MainView getMainView() {
		return mainView;
	}

	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}
}
