package com.mis.web.model;

public class Customer {
	private String cusNo;
	private String cusName;
	private String cusCEO;
	private String cusHP;
	private String cusEmail;
	private String cusAdd; 
	
	public Customer(){
		//기본형 생성자
	}
	
	public Customer(String cusNo, String cusName){
		//고객번호, 고객이름 파라메터로 받는 생성자
		
		this.cusNo = cusNo;
		this.cusName = cusName;
		
	}
	
	
	public String getCusNo() {
		return cusNo;
	}
	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}
	
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	
	public String getCusCEO() {
		return cusCEO;
	}
	public void setCusCEO(String cusCEO) {
		this.cusCEO = cusCEO;
	}
	
	public String getCusHP() {
		return cusHP;
	}
	public void setCusHP(String cusHp) {
		this.cusHP = cusHP;
	}
	
	public String getCusEmail() {
		return cusEmail;
	}
	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}
	
	public String getCusAdd() {
		return cusAdd;
	}
	public void setCusAdd(String cusAdd) {
		this.cusAdd = cusAdd;
	}
	
	
}
