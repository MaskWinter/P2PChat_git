package com.modelsystem.controlCenter;

import java.util.List;

import javax.swing.DefaultListModel;

import com.modelsystem.ui.ActionView;

/**
 * 聊天界面控制工具
 * @Title: ViewControl.java 
 * @Package com.modelsystem.controlCenter 
 * @author lxd <836696016@qq.com>
 * @date 2015年12月30日 下午2:35:51 
 * @version V1.0
 */
public class ViewControl {
	//运行主界面
	private ActionView actionView;
	
	public ViewControl(ActionView actionView) {
		this.actionView = actionView;
	}

	
	/**
	 * 根据标志来更新聊天列表还是好友列表
	 * @Title: change 
	 * @param data 更新数据
	 * @param sign   标志
 	 * @return void
	 */
	public void change(List<String> data,String sign){
		switch(sign){
			case "friend" : {
				DefaultListModel<String> dlm = new DefaultListModel<String>();
				for(String s : data){
					dlm.addElement(s);
				}
				actionView.getFriendList().setModel(dlm);
				break;
			}
			case "chat" :{
				actionView.getChatArea().setText("");
				for(String s : data){
					actionView.getChatContextArea().append(s);
				}
				break;
			}
			default : break;
		}
	}
	
	
	
	
	
	
	
	
	
	public ActionView getActionView() {
		return actionView;
	}

	public void setActionView(ActionView actionView) {
		this.actionView = actionView;
	}

}
