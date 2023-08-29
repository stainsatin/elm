package com.neusoft.elm.view.impl;

import java.util.Scanner;

import com.neusoft.elm.dao.AdminDao;
import com.neusoft.elm.dao.impl.AdminDaoImpl;
import com.neusoft.elm.po.Admin;
import com.neusoft.elm.view.AdminView;

public class AdminViewImpl implements AdminView{
	
	private Scanner input = new Scanner(System.in);

	@Override
	public Admin login() {
		System.out.println("请输入管理员名称：");
		String adminName = input.nextLine();
		System.out.println("请输入密码：");
		String password = input.nextLine();

		if (adminName.isEmpty() || password.isEmpty()) {
			System.out.println("用户名和密码不能为空");
			return null;
		}
		
		AdminDao dao = new AdminDaoImpl();
		return dao.getAdminByNameByPass(adminName,password);
	}
}
