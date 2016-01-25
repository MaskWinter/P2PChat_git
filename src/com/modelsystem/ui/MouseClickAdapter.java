package com.modelsystem.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

/**
 * 监听鼠标，以便注册时清空提示信息
 * @Title: MouseClickAdapter.java 
 * @Package com.modelsystem.ui 
 * @author lxd <836696016@qq.com>
 * @date 2015年12月30日 下午2:26:50 
 * @version V1.0
 */
public class MouseClickAdapter extends MouseAdapter{

	private JTextField textField;
	
	public MouseClickAdapter(JTextField textField) {
		super();
		this.textField = textField;
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		textField.setText("");
	}
}
