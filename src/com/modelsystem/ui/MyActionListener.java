package com.modelsystem.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.modelsystem.Service.FileSender;
import com.modelsystem.Service.MessageService;
import com.modelsystem.Service.OnlineAndOffLineService;
import com.modelsystem.Service.PortListener;
import com.modelsystem.Service.UserService;
import com.modelsystem.controlCenter.ViewControl;
import com.modelsystem.model.User;
import com.modelsystem.util.CheckUtil;

/**
 * 按钮监听类
 * @Title: MyActionListener.java 
 * @Package com.modelsystem.ui 
 * @author lxd <836696016@qq.com>
 * @date 2015年12月30日 下午2:27:27 
 * @version V1.0
 */
public class MyActionListener implements ActionListener{

	private MainView mainView;
	private SignView signView;
	private ActionView actionView;
	
	public MyActionListener(MainView mainView) {
		this.setMainView(mainView);
	}
	
	public MyActionListener(SignView signView) {
		this.setSignView(signView);
	}
	
	public MyActionListener(ActionView actionView) {
		this.setActionView(actionView);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		System.out.println(action);
		switch(action){
			case "注册" : sign();
						 break;
			case "重置" : reset();
						 break;
			case "提交" : submit();
			 			 break;
			case "返回" : back();
						 break;
			case "登陆" : login();
						 break;
			case "发送" : send();
						 break;
			case "文件传输" : sendFile();
							break;
		}
	}
	
	//注册方法
	public void sign(){
		SignView sign = new SignView(mainView);
		sign.init();
	}
	
	//提交按钮响应
	public void submit(){
		String name = signView.getNameText().getText();
		String password = signView.getPasswordText().getText();
		String repassword = signView.getRepasswordText().getText();
		List<String> check = CheckUtil.check(name, password, repassword);
		if(check.size() == 0){
			UserService userService = new UserService();
			User u = new User();
			u.setName(name);
			u.setPassword(password);
			boolean result = userService.add(u);
			if(result){
				JOptionPane.showMessageDialog(null, "成功,返回登录", "注册信息",JOptionPane.INFORMATION_MESSAGE);
				signView.getMainView().initialize();
			}else{
				JOptionPane.showMessageDialog(null, "失败，请检查注册信息", "注册信息",JOptionPane.ERROR_MESSAGE);
			}
		}else{
			int wight = 60;
			int hight = 25;
			JPanel panel = signView.getMainView().getPanel();
			panel.removeAll();
			SpringLayout layout = new SpringLayout();
			panel.setLayout(layout);
			for(int i = 0; i < check.size(); i++){
				JLabel massage = new JLabel();
				massage.setFont(new Font("宋体", Font.PLAIN, 15));
				massage.setText(check.get(i));
				layout.putConstraint(SpringLayout.NORTH, massage, hight*(i+1), SpringLayout.NORTH, panel);
				layout.putConstraint(SpringLayout.WEST, massage, wight, SpringLayout.WEST,panel );
				panel.add(massage);
			}
			JButton back = new JButton("返回");
			back.setFont(new Font("宋体", Font.PLAIN, 18));
			layout.putConstraint(SpringLayout.SOUTH, back, -36, SpringLayout.SOUTH, panel);
			layout.putConstraint(SpringLayout.EAST, back, -66, SpringLayout.EAST, panel);
			panel.add(back);
			back.addActionListener(this);
			panel.updateUI();
		}
		
	}
	
	//返回按钮
	public void back(){
		signView.init();
	}
	
	//重置按钮
	public void reset(){
		signView.getNameText().setText("");
		signView.getPasswordText().setText("");
		signView.getRepasswordText().setText("");
	}
	
	//登陆按钮
	public void login(){
		String name = mainView.getNameText().getText();
		String password = mainView.getPasswordText().getText();
		boolean check = CheckUtil.checkLogin(name, password);
		if(check){
			UserService userService = new UserService();
			User u = userService.login(name, password);
			if(u == null){
				JOptionPane.showMessageDialog(null, "用户名或密码错误", "登陆信息",JOptionPane.ERROR_MESSAGE);
				return;
			}
			ActionView action = new ActionView(mainView);
			action.init();
			action.getUserName().setText(u.getName());
			//启动监听程序
			new Thread(new PortListener(u,action)).start();
			//发送上线信息
			new OnlineAndOffLineService(u,action).online();
			WindowCloseAdapter windowCloseAdapter = new WindowCloseAdapter(u.getName());
			action.getMainView().getFrmPp().addWindowListener(windowCloseAdapter);
		}else{
			JOptionPane.showMessageDialog(null, "登陆失败", "登陆信息",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//发送按钮
	public void send(){
		String ip = actionView.getFriendList().getSelectedValue();
		System.out.println(ip);
		if(ip == null){
			JOptionPane.showMessageDialog(null, "请选择一个好友!!", "聊天信息",JOptionPane.ERROR_MESSAGE);
			return;
		}
		String context = actionView.getChatArea().getText();
		if(context == null || "".equals(context)){
			JOptionPane.showMessageDialog(null, "发送内容不能为空!!", "聊天信息",JOptionPane.ERROR_MESSAGE);
			return;
		}
		ViewControl vc = new ViewControl(actionView);
		MessageService messageService = new MessageService(vc);
		String[] messages = {ip.split("_")[1],context,
				actionView.getUserName().getText(),ip.split("_")[0]};
		messageService.sendMessage(messages);
	}
	
	//发送文件按钮
	public void sendFile(){
		String ip = actionView.getFriendList().getSelectedValue();
		System.out.println(ip);
		if(ip == null){
			JOptionPane.showMessageDialog(null, "请选择一个好友!!", "聊天信息",JOptionPane.ERROR_MESSAGE);
			return;
		}
		FileSender f = new FileSender();
		new Thread(f).start();
		ViewControl vc = new ViewControl(actionView);
		MessageService messageService = new MessageService(vc);
		messageService.sendFileView(ip.split("_")[1], actionView.getUserName().getText());
		
	}
	
	
	
	
	
	
	
	

	public MainView getMainView() {
		return mainView;
	}

	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}

	public SignView getSignView() {
		return signView;
	}

	public void setSignView(SignView signView) {
		this.signView = signView;
	}

	public ActionView getActionView() {
		return actionView;
	}

	public void setActionView(ActionView actionView) {
		this.actionView = actionView;
	}

}
