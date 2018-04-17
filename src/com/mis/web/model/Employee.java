package com.mis.web.model;

public class Employee {
	private String empNo;
	private String empName;
	private String empPosi;
	private String empDept;
	private String empHp;
	private String empEmail;
	private String empPw;
	private String empAuth;
	
	public Employee(){
		//기본형 생성자
	}
	
	public Employee(String empNo, String empName){
		//사원번호, 사원이름 파라메터로 받는 생성자
		
		this.empNo = empNo;
		this.empName = empName;
		
	}
	
	
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	public String getEmpPosi() {
		return empPosi;
	}
	public void setEmpPosi(String empPosi) {
		this.empPosi = empPosi;
	}
	
	public String getEmpDept() {
		return empDept;
	}
	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}
	
	public String getEmpHp() {
		return empHp;
	}
	public void setEmpHp(String empHp) {
		this.empHp = empHp;
	}
	
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	
	public String getEmpPw() {
		return empPw;
	}
	public void setEmpPw(String empPw) {
		this.empPw = empPw;
	}
	
	public String getEmpAuth() {
		return empAuth;
	}
	public void setEmpAuth(String empAuth) {
		this.empAuth = empAuth;
	}
	
	
	
}
