package com.mis.web;

import com.mis.web.model.Employee;

public class EmployeeManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Employee �Ķ���Ͱ� �ִ� �����ڸ� ����Ͽ� �����ü����
		Employee oneEmp = new Employee("S1001","������");
		
		//Employee setter �޼��带 ����Ͽ� �����ü����
		Employee twoEmp = new Employee();
		twoEmp.setEmpNo("S1002");
		twoEmp.setEmpName("������");
		
		System.out.println("[oneEmp] EmployeeNo: " + oneEmp.getEmpNo());
		System.out.println("[oneEmp] EmployeeName: " + oneEmp.getEmpName());
		
		System.out.println("[twoEmp] EmployeeNo: " + twoEmp.getEmpNo());
		System.out.println("[twoEmp] EmployeeName: " + twoEmp.getEmpName());
	}

}
