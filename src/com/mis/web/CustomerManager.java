package com.mis.web;

import com.mis.web.model.Customer;

public class CustomerManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Customer �Ķ���Ͱ� �ִ� �����ڸ� ����Ͽ� ����ü����
		Customer oneCus = new Customer("S1001","������");
		
		//Customer setter �޼��带 ����Ͽ� ����ü����
		Customer twoCus = new Customer();
		twoCus.setCusNo("S1002");
		twoCus.setCusName("������");
		
		System.out.println("[oneCus] CustomerNo: " + oneCus.getCusNo());
		System.out.println("[oneCus] CustomerName: " + oneCus.getCusName());
		
		System.out.println("[twoCus] CustomerNo: " + twoCus.getCusNo());
		System.out.println("[twoCus] CustomerName: " + twoCus.getCusName());
	}

}
