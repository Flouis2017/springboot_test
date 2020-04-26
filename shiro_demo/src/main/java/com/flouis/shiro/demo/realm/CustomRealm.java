package com.flouis.shiro.demo.realm;

import com.flouis.shiro.demo.entity.User;
import com.flouis.shiro.demo.util.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.List;

public class CustomRealm extends AuthorizingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		System.out.println("\n开始执行身份认证方法...");
		// 获取用户名
		String username = (String) authenticationToken.getPrincipal();
		// 通过用户名获取用户信息，实际开发中一般是去数据库获取数据，这里直接从虚拟数据中获取
		User userRecord = UserUtil.getByUsername(username);
		if (userRecord == null){
			return null;
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, userRecord.getPassword(), this.getName());
		return authenticationInfo;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		System.out.println("\n开始执行授权认证方法...");
		// 这个就是SimpleAuthenticationInfo(username,password,getName()); 第一个参数
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		System.out.println("第一个参数：" + username);
		username = (String) principalCollection.getPrimaryPrincipal();

		// 获取用户角色数据
		List<String> roleList = UserUtil.getRolesByUsername(username);

		// 获取用户权限数据
		List<String> permissionList = UserUtil.getPermissionsByUsername(username);

		// 创建AuthorizationInfo，并设置角色和权限信息
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addRoles(roleList);
		authorizationInfo.addStringPermissions(permissionList);
		return authorizationInfo;
	}

}
