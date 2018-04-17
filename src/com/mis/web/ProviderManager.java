package com.mis.web;

import com.mis.web.model.Provider;

public class ProviderManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Provider 파라메터가 있는 생성자를 사용하여 납품업체객체생성
		Provider oneProv = new Provider("S1001","오소진");
		
		//Provider setter 메서드를 사용하여 납품업체객체생성
		Provider twoProv = new Provider();
		twoProv.setProvNo("S1002");
		twoProv.setProvName("박지원");
		
		System.out.println("[oneProv] ProviderNo: " + oneProv.getProvNo());
		System.out.println("[oneProv] ProviderName: " + oneProv.getProvName());
		
		System.out.println("[twoProv] ProviderNo: " + twoProv.getProvNo());
		System.out.println("[twoProv] ProviderName: " + twoProv.getProvName());
	}

}
