package com.mis.web;

import com.mis.web.model.Provider;

public class ProviderManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Provider �Ķ���Ͱ� �ִ� �����ڸ� ����Ͽ� ��ǰ��ü��ü����
		Provider oneProv = new Provider("S1001","������");
		
		//Provider setter �޼��带 ����Ͽ� ��ǰ��ü��ü����
		Provider twoProv = new Provider();
		twoProv.setProvNo("S1002");
		twoProv.setProvName("������");
		
		System.out.println("[oneProv] ProviderNo: " + oneProv.getProvNo());
		System.out.println("[oneProv] ProviderName: " + oneProv.getProvName());
		
		System.out.println("[twoProv] ProviderNo: " + twoProv.getProvNo());
		System.out.println("[twoProv] ProviderName: " + twoProv.getProvName());
	}

}
