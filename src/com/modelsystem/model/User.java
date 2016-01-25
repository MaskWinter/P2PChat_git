package com.modelsystem.model;

import java.io.Serializable;

/**
 * 用户类
 * @Title: User.java 
 * @Package com.modelsystem.po 
 * @author lxd <836696016@qq.com>
 * @date 2015年12月28日 下午2:50:10 
 * @version V1.0
 */
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	//用户id
	private String id;
	//用户名
	private String name;
	//密码
	private String password;
	//登陆ip
	private String ip;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
