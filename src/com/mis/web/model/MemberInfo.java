package com.mis.web.model;

public class MemberInfo {

	//ȸ����ȣ
	//�̸�
	//����⵵
	//�ּ�
	
	private String memberNo;
	private String memberName;
	private String birtheYear;
	private String address;
	
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getBirtheYear() {
		return birtheYear;
	}
	public void setBirtheYear(String birtheYear) {
		this.birtheYear = birtheYear;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public MemberInfo(){
		
	}
	
	public MemberInfo(String no, String name){
		this.memberNo = no;
		this.memberName = name;
	}
	
	
}
