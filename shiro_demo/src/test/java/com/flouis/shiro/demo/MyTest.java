package com.flouis.shiro.demo;

import com.flouis.shiro.demo.entity.User;
import com.flouis.shiro.demo.realm.CustomRealm;
import com.flouis.shiro.demo.util.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.junit.Test;

import java.util.Scanner;

public class MyTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入用户名：");
		String usernameInput = scanner.nextLine();
		System.out.println("请输入密码：");
		String passwordInput = scanner.nextLine();

		System.out.println("username: " + usernameInput + "; password: " + passwordInput);
	}

	/**
	 * @description 身份认证测试
	 */
	@Test
	public void authenticationTest(){
		String username = "Flouis";
		String password = "123456";

		// 构建SecurityManager环境
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

		// 创建一个SimpleAccountRealm
		SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

		// 添加一个测试账号
		simpleAccountRealm.addAccount(username, password);

		// 设置Realm
		defaultWebSecurityManager.setRealm(simpleAccountRealm);
		SecurityUtils.setSecurityManager(defaultWebSecurityManager);

		// 获取主体
		Subject subject = SecurityUtils.getSubject();

		// 用户名/密码生成token
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入用户名：");
		String usernameInput = scanner.nextLine();
		System.out.println("请输入密码：");
		String passwordInput = scanner.nextLine();
		UsernamePasswordToken token = new UsernamePasswordToken(usernameInput, passwordInput);

		try {
			// 登录（提交认证）
			subject.login(token);
		} catch (IncorrectCredentialsException exception){
			System.out.println("用户名密码错误！");
		} catch (LockedAccountException exception){
			System.out.println("账号已被冻结！");
		} catch (DisabledAccountException exception){
			System.out.println("账号已被禁用！");
		} catch (UnknownAccountException exception){
			System.out.println("用户不存在！");
		}

		System.out.println("\n用户认证状态：isAuthenticated = " + subject.isAuthenticated());
		System.out.println("\n执行登出logout()方法...");
		subject.logout();
		System.out.println("\n用户认证状态：isAuthenticated = " + subject.isAuthenticated());
	}

	/**
	 * @description 授权认证测试：先身份认证再授权认证
	 */
	@Test
	public void authorizationTest(){
		String username = "Flouis";
		String password = "123456";
		String[] roleList = new String[]{"admin", "user"};

		// 构建SecurityManager环境
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		// 创建一个SimpleAccountRealm域
		SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
		// 添加一个账号和所拥有的角色
		simpleAccountRealm.addAccount(username, password, roleList);

		// 设置realm
		defaultWebSecurityManager.setRealm(simpleAccountRealm);
		SecurityUtils.setSecurityManager(defaultWebSecurityManager);

		// 获取主体
		Subject subject = SecurityUtils.getSubject();

		// 输入用户名和密码生成token
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入用户名：");
		String usernameInput = scanner.nextLine();
		System.out.println("请输入密码：");
		String passwordInput = scanner.nextLine();
		UsernamePasswordToken token = new UsernamePasswordToken(usernameInput, passwordInput);

		try {
			// 登录（提交认证）
			subject.login(token);

			// 授权校验
			subject.checkRoles("admin", "user");

		} catch (IncorrectCredentialsException exception){
			System.out.println("用户名密码错误！");
		} catch (LockedAccountException exception){
			System.out.println("账号已被冻结！");
		} catch (DisabledAccountException exception){
			System.out.println("账号已被禁用！");
		} catch (UnknownAccountException exception){
			System.out.println("用户不存在！");
		} catch (UnauthorizedException exception){
			System.out.println("用户没有角色权限");
		}

		System.out.println("\n用户认证状态：isAuthenticated = " + subject.isAuthenticated());
		System.out.println("\n执行登出logout()方法...");
		subject.logout();
		System.out.println("\n用户认证状态：isAuthenticated = " + subject.isAuthenticated());
	}

	@Test
	public void customRealmTest(){
//		User user = UserUtil.getByUsername("admin");
//		System.out.println(user);

		// 构建SecurityManager环境
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

		// 设置自定义realm
		CustomRealm customRealm = new CustomRealm();
		defaultSecurityManager.setRealm(customRealm);
		SecurityUtils.setSecurityManager(defaultSecurityManager);

		// 获取主体
		Subject subject = SecurityUtils.getSubject();

		// 输入用户名和密码生成token
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入用户名：");
		String usernameInput = scanner.nextLine();
		System.out.println("请输入密码：");
		String passwordInput = scanner.nextLine();
		UsernamePasswordToken token = new UsernamePasswordToken(usernameInput, passwordInput);

		try {
			// 登录（提交认证）
			subject.login(token);
			System.out.println("认证状态：isAuthenticated = " + subject.isAuthenticated());

			// 授权校验
			subject.checkRoles("admin");
			System.out.println("当前用户拥有admin角色");

			subject.checkPermissions("user:delete", "user:add", "user:modify", "user:list");
			System.out.println("当前用户拥有 user:delete, user:add, user:modify, user:list 操作权限");

		} catch (IncorrectCredentialsException exception){
			System.out.println("用户名密码错误！");
		} catch (LockedAccountException exception){
			System.out.println("账号已被冻结！");
		} catch (DisabledAccountException exception){
			System.out.println("账号已被禁用！");
		} catch (UnknownAccountException exception){
			System.out.println("用户不存在！");
		} catch (UnauthorizedException exception){
			System.out.println("用户没有角色权限");
		}

	}


}
