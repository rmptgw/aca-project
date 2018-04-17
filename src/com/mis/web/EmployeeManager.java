package com.mis.web;

import com.mis.web.model.Employee;

public class EmployeeManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Employee 파라메터가 있는 생성자를 사용하여 사원객체생성
		Employee oneEmp = new Employee("S1001","오소진");
		
		//Employee setter 메서드를 사용하여 사원객체생성
		Employee twoEmp = new Employee();
		twoEmp.setEmpNo("S1002");
		twoEmp.setEmpName("박지원");
		
		System.out.println("[oneEmp] EmployeeNo: " + oneEmp.getEmpNo());
		System.out.println("[oneEmp] EmployeeName: " + oneEmp.getEmpName());
		
		System.out.println("[twoEmp] EmployeeNo: " + twoEmp.getEmpNo());
		System.out.println("[twoEmp] EmployeeName: " + twoEmp.getEmpName());
	}

}
