package com.modelsystem.Service;

import com.modelsystem.dao.UserDao;
import com.modelsystem.model.User;
import com.modelsystem.util.UUIDUtil;

/**
 * 用户Service层
 * @Title: UserService.java 
 * @Package com.modelsystem.Service 
 * @author lxd <836696016@qq.com>
 * @date 2015年12月30日 下午2:32:59 
 * @version V1.0
 */
public class UserService {

	private static UserDao userDao = new UserDao();
	
	/**
	 * 增加用户
	 * @Title: addUser 
	 * @param u 保存用户
	 * @return   
	 * @return boolean
	 */
	public boolean add(User u){
		u.setId(UUIDUtil.getUUID());
		return userDao.addUser(u);
	}
	
	/**
	 * 登陆
	 * @Title: login 
	 * @param name 用户名
	 * @param password 密码
	 * @return   
	 * @return User
	 */
	public User login(String name,String password){
		return userDao.login(name, password);
	}
}
