package com.mis.web.bean;

import com.mis.web.dao.EmployeeDao;
import com.mis.web.model.Employee;

public class LoginBean {

	private static LoginBean instance;
	
	public static LoginBean getInstance(){
		if(instance == null){
			instance = new LoginBean();
		}
		return instance;
	}
	
	public boolean loginCheck(String no, String pass){
		EmployeeDao memberDao = EmployeeDao.getInstance();
		return memberDao.loginCheck(no, pass);
	}
	public Employee getEmpInfo(String user){
		EmployeeDao memberDao = EmployeeDao.getInstance();
		return memberDao.getEmpInfo(user);
	}
}
