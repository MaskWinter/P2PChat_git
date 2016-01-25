package com.modelsystem.ui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * 注册界面
 * @Title: SignView.java 
 * @Package com.modelsystem.ui 
 * @author lxd <836696016@qq.com>
 * @date 2015年12月30日 下午2:29:05 
 * @version V1.0
 */
public class SignView {
	
	private MainView mainView;
	private JTextField nameText;
	private JTextField passwordText;
	private JTextField repasswordText;
	
	public SignView(MainView mainView) {
		this.mainView = mainView;
	}
	
	public void init(){
		MyActionListener listener = new MyActionListener(this);
		JPanel panel = mainView.getPanel();
		panel.removeAll();
		
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		
		JLabel nameL = new JLabel("用户名：");
		sl_panel.putConstraint(SpringLayout.NORTH, nameL, 66, SpringLayout.NORTH, panel);
		nameL.setFont(new Font("宋体", Font.PLAIN, 18));
		panel.add(nameL);
		
		nameText = new JTextField();
		MouseClickAdapter ma = new MouseClickAdapter(nameText);
		nameText.addMouseListener(ma);
		nameText.setText("汉字、数字、字母、下划线，长度1-15");
		sl_panel.putConstraint(SpringLayout.EAST, nameL, -20, SpringLayout.WEST, nameText);
		sl_panel.putConstraint(SpringLayout.EAST, nameText, -126, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.WEST, nameText, 285, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, nameText, 66, SpringLayout.NORTH, panel);
		nameText.setFont(new Font("宋体", Font.PLAIN, 15));
		panel.add(nameText);
		nameText.setColumns(10);
		
		JLabel passwordL = new JLabel("密码：");
		passwordL.setFont(new Font("宋体", Font.PLAIN, 18));
		sl_panel.putConstraint(SpringLayout.NORTH, passwordL, 49, SpringLayout.SOUTH, nameL);
		sl_panel.putConstraint(SpringLayout.WEST, passwordL, 0, SpringLayout.WEST, nameL);
		panel.add(passwordL);
		
		passwordText = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, passwordText, 0, SpringLayout.NORTH, passwordL);
		sl_panel.putConstraint(SpringLayout.WEST, passwordText, 0, SpringLayout.WEST, nameText);
		sl_panel.putConstraint(SpringLayout.EAST, passwordText, 0, SpringLayout.EAST, nameText);
		MouseClickAdapter map = new MouseClickAdapter(passwordText);
		passwordText.addMouseListener(map);
		passwordText.setText("字母,长度6-10");
		passwordText.setFont(new Font("宋体", Font.PLAIN, 15));
		panel.add(passwordText);
		passwordText.setColumns(10);
		
		JLabel repasswordL = new JLabel("重复密码：");
		repasswordL.setFont(new Font("宋体", Font.PLAIN, 15));
		sl_panel.putConstraint(SpringLayout.NORTH, repasswordL, 55, SpringLayout.SOUTH, passwordL);
		sl_panel.putConstraint(SpringLayout.WEST, repasswordL, 0, SpringLayout.WEST, nameL);
		panel.add(repasswordL);
		
		repasswordText = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, repasswordText, -3, SpringLayout.NORTH, repasswordL);
		sl_panel.putConstraint(SpringLayout.WEST, repasswordText, 0, SpringLayout.WEST, nameText);
		sl_panel.putConstraint(SpringLayout.EAST, repasswordText, 0, SpringLayout.EAST, nameText);
		MouseClickAdapter marp = new MouseClickAdapter(repasswordText);
		repasswordText.addMouseListener(marp);
		repasswordText.setFont(new Font("宋体", Font.PLAIN, 15));
		panel.add(repasswordText);
		repasswordText.setColumns(10);
		
		JButton reset = new JButton("重置");
		reset.setFont(new Font("宋体", Font.PLAIN, 18));
		sl_panel.putConstraint(SpringLayout.NORTH, reset, 72, SpringLayout.SOUTH, repasswordL);
		sl_panel.putConstraint(SpringLayout.WEST, reset, 0, SpringLayout.WEST, nameL);
		panel.add(reset);
		reset.addActionListener(listener);
		
		JButton submit = new JButton("提交");
		sl_panel.putConstraint(SpringLayout.NORTH, submit, 0, SpringLayout.NORTH, reset);
		sl_panel.putConstraint(SpringLayout.EAST, submit, 0, SpringLayout.EAST, nameText);
		submit.setFont(new Font("宋体", Font.PLAIN, 18));
		panel.add(submit);
		submit.addActionListener(listener);
		
		panel.updateUI();
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

	public JTextField getRepasswordText() {
		return repasswordText;
	}

	public void setRepasswordText(JPasswordField repasswordText) {
		this.repasswordText = repasswordText;
	}

	public MainView getMainView() {
		return mainView;
	}

	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}
}
