package com.mis.web.model;

public class Provider {
	private String provNo;
	private String provName;
	private String provCEO;
	private String provHP;
	private String provEmail;
	private String provAdd; 
	
	public Provider(){
		//�⺻�� ������
	}
	
	public Provider(String provNo, String provName){
		//��ǰ��ü��ȣ, ��ǰ��ü�̸� �Ķ���ͷ� �޴� ������
		
		this.provNo = provNo;
		this.provName = provName;
		
	}
	
	
	public String getProvNo() {
		return provNo;
	}
	public void setProvNo(String provNo) {
		this.provNo = provNo;
	}
	
	public String getProvName() {
		return provName;
	}
	public void setProvName(String provName) {
		this.provName = provName;
	}
	
	public String getProvCEO() {
		return provCEO;
	}
	public void setProvCEO(String provCEO) {
		this.provCEO = provCEO;
	}
	
	public String getProvHP() {
		return provHP;
	}
	public void setProvHP(String provHp) {
		this.provHP = provHP;
	}
	
	public String getProvEmail() {
		return provEmail;
	}
	public void setProvEmail(String provEmail) {
		this.provEmail = provEmail;
	}
	
	public String getProvAdd() {
		return provAdd;
	}
	public void setProvAdd(String provAdd) {
		this.provAdd = provAdd;
	}
	
	
}
