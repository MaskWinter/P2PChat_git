package com.modelsystem.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 检查字段工具类
 * @Title: CheckUtil.java 
 * @Package com.modelsystem.util 
 * @author lxd <836696016@qq.com>
 * @date 2015年12月28日 下午3:04:11 
 * @version V1.0
 */
public class CheckUtil {
	/**
	 * 检查注册信息
	 * @Title: check 
	 * @param name
	 * @param password
	 * @param rePassword
	 * @return   
	 * @return List<String>
	 */
	public static List<String> check(String name,String password,
			String rePassword){
		
		List<String> result = new ArrayList<String>();
		if(name == null || password == null || rePassword == null){
			result.add("姓名或密码不能为空");
			return result;
		}
		if("".equals(name) || "".equals(password) || "".equals(rePassword)){
			result.add("姓名或密码不能为空");
			return result;
		}
		if(!name.matches("^[a-zA-Z0-9_\u4e00-\u9fa5]{1,15}$")){
			result.add("用户名不符合");
		}
		if(!password.matches("^[a-zA-Z]{5,9}") || !password.equals(rePassword)){
			result.add("密码不符合规范或密码不一致");
		}
		return result;
	}
	
	/**
	 * 检查登录信息
	 * @Title: checkLogin 
	 * @param name
	 * @param password
	 * @return   
	 * @return boolean
	 */
	public static boolean checkLogin(String name,String password){
		
		if("".equals(name) | "".equals(password)){
			return false;
		}
		return true;
	}
}
