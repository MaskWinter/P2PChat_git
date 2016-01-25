package com.modelsystem.ui;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BorderLayout;

/**
 * 主界面
 * @Title: MainView.java 
 * @Package com.modelsystem.ui 
 * @author lxd <836696016@qq.com>
 * @date 2015年12月28日 下午3:34:23 
 * @version V1.0
 */
public class MainView {

	private JFrame frmPp;
	private JPanel panel;
	private JTextField nameText;
	private JPasswordField passwordText;
	
	public MainView() {
		frmPp = new JFrame();
		frmPp.setTitle("P2P通讯系统");
		frmPp.setBounds(100, 100, 720, 500);
		frmPp.setResizable(false);
		frmPp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		frmPp.getContentPane().add(panel, BorderLayout.CENTER);
		initialize();
	}

	public void initialize() {
		
		MyActionListener listener = new MyActionListener(this);
		panel.removeAll();
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JLabel nameL = new JLabel("用户名：");
		nameL.setFont(new Font("宋体", Font.PLAIN, 15));
		sl_panel.putConstraint(SpringLayout.SOUTH, nameL, -147, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, nameL, -246, SpringLayout.EAST, panel);
		panel.add(nameL);
		
		JLabel passwordL = new JLabel("密码：");
		sl_panel.putConstraint(SpringLayout.NORTH, passwordL, 37, SpringLayout.SOUTH, nameL);
		sl_panel.putConstraint(SpringLayout.WEST, passwordL, 0, SpringLayout.WEST, nameL);
		passwordL.setFont(new Font("宋体", Font.PLAIN, 15));
		panel.add(passwordL);
		
		nameText = new JTextField();
		nameText.setFont(new Font("宋体", Font.PLAIN, 14));
		sl_panel.putConstraint(SpringLayout.NORTH, nameText, -1, SpringLayout.NORTH, nameL);
		sl_panel.putConstraint(SpringLayout.WEST, nameText, 6, SpringLayout.EAST, nameL);
		sl_panel.putConstraint(SpringLayout.SOUTH, nameText, -145, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, nameText, 157, SpringLayout.EAST, nameL);
		panel.add(nameText);
		nameText.setColumns(10);
		
		passwordText = new JPasswordField();
		passwordText.setFont(new Font("宋体", Font.PLAIN, 14));
		sl_panel.putConstraint(SpringLayout.NORTH, passwordText, 32, SpringLayout.SOUTH, nameText);
		sl_panel.putConstraint(SpringLayout.WEST, passwordText, 0, SpringLayout.WEST, nameText);
		sl_panel.putConstraint(SpringLayout.EAST, passwordText, 0, SpringLayout.EAST, nameText);
		panel.add(passwordText);
		passwordText.setColumns(10);
		
		JButton sign = new JButton("注册");
		sign.setFont(new Font("宋体", Font.PLAIN, 14));
		sl_panel.putConstraint(SpringLayout.NORTH, sign, 34, SpringLayout.SOUTH, passwordText);
		sl_panel.putConstraint(SpringLayout.WEST, sign, 0, SpringLayout.WEST, nameL);
		panel.add(sign);
		sign.addActionListener(listener);
		
		JButton login = new JButton("登陆");
		login.setFont(new Font("宋体", Font.PLAIN, 14));
		sl_panel.putConstraint(SpringLayout.SOUTH, login, 0, SpringLayout.SOUTH, sign);
		sl_panel.putConstraint(SpringLayout.EAST, login, 0, SpringLayout.EAST, nameText);
		panel.add(login);
		login.addActionListener(listener);
		
		JLabel tip = new JLabel("P2P通讯系统");
		sl_panel.putConstraint(SpringLayout.NORTH, tip, 109, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, tip, 248, SpringLayout.WEST, panel);
		tip.setFont(new Font("宋体", Font.PLAIN, 36));
		panel.add(tip);
		panel.updateUI();
	}

	public JFrame getFrmPp() {
		return frmPp;
	}

	public void setFrmPp(JFrame frmPp) {
		this.frmPp = frmPp;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JTextField getNameText() {
		return nameText;
	}

	public void setNameText(JTextField nameText) {
		this.nameText = nameText;
	}

	public JTextField getPasswordText() {
		return passwordText;
	}

	public void setPasswordText(JPasswordField passwordText) {
		this.passwordText = passwordText;
	}
}
