package com.modelsystem.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.modelsystem.model.User;

/**
 * 用户dao层
 * @Title: UserDao.java 
 * @Package com.modelsystem.dao 
 * @author lxd <836696016@qq.com>
 * @date 2015年12月30日 下午2:33:16 
 * @version V1.0
 */
public class UserDao {
	
	private List<User> us = null;
	
	/**
	 * 增加用户
	 * @Title: addUser 
	 * @param u 保存用户
	 * @return   
	 * @return boolean
	 */
	public  boolean addUser(User u){
		try {
			readUser();
			File f = new File("UserData", "data.txt");
			if(us == null){
				return false;
			}
			us.add(u);
			FileOutputStream out = new FileOutputStream(f);
			ObjectOutputStream o = new ObjectOutputStream(out);
			o.writeObject(us);
			o.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 创建文件
	 * @Title: createFile    
	 * @return void
	 */
	private void createFile(){
		File file = new File("UserData");
		if(!file.exists()){
			file.mkdir();
		}
		File f = new File(file, "data.txt");
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 登陆
	 * @Title: login 
	 * @param name 用户名
	 * @param password 密码
	 * @return   
	 * @return User
	 */
	public User login(String name, String password) {
		readUser();
		if(us == null){
			return null;
		}
		for(User u : us){
			if(name.equals(u.getName()) && password.equals(u.getPassword())){
				return u;
			}
		}
		return null;
	}
	
	/**
	 * 读取用户信息
	 * @Title: readUser    
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	private void readUser(){
		try{
			createFile();
			File file = new File("UserData", "data.txt");
			FileInputStream input = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(input);
			if(file.length() == 0){
				us = new ArrayList<User>();
				in.close();
			}else{
				us = (List<User>)in.readObject();
				in.close();
			}
		}catch(EOFException ee){
			us = new ArrayList<User>();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
