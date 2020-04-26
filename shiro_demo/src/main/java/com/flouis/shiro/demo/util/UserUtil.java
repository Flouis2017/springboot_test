package com.flouis.shiro.demo.util;

import com.flouis.shiro.demo.entity.User;
import com.google.common.collect.Lists;

import java.util.List;

public class UserUtil {

	private static List<User> defaultUserList = Lists.newArrayList();
	// 虚拟用户数据
	static {
		defaultUserList.add(new User("admin", "123456"));
		defaultUserList.add(new User("test", "123456"));
		defaultUserList.add(new User("software", "123456"));
	}

	public static List<User> getDefaultUserList(){
		return defaultUserList;
	}

	/**
	 * @description 通过用户名获取用户——模拟
	 */
	public static User getByUsername(String username){
		User user = null;
		for (User x : defaultUserList){
			if (username.equals(x.getUsername())){
				user = x;
				break;
			}
		}
		return user;
	}

	/**
	 * @description 通过用户名获取用户角色——模拟
	 */
	public static List<String> getRolesByUsername(String username){
		List<String> roleList = Lists.newArrayList();
		if ("admin".equals(username)){
			roleList.add("admin");
		}
		roleList.add("test");
		return roleList;
	}

	/**
	 * @description 通过用户名获取用户权限——模拟
	 */
	public static List<String> getPermissionsByUsername(String username){
		List<String> permissionList = Lists.newArrayList();
		if ("admin".equals(username)){
			permissionList.add("user:delete");
			permissionList.add("user:add");
		}
		permissionList.add("user:modify");
		permissionList.add("user:list");
		return permissionList;
	}

}
