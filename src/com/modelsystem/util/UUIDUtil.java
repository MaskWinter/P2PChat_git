package com.modelsystem.util;

import java.util.UUID;

/**
 * UUID获取工具类
 * @Title: UUIDUtil.java 
 * @Package com.modelsystem.util 
 * @author lxd <836696016@qq.com>
 * @date 2015年12月28日 下午3:03:07 
 * @version V1.0
 */
public class UUIDUtil {
	/**
	 * 获得UUID的方法
	 * @Title: getUUID 
	 * @return   
	 * @return String
	 */
	public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    } 
}
