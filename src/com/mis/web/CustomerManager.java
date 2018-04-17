package com.mis.web;

import com.mis.web.model.Customer;

public class CustomerManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Customer 파라메터가 있는 생성자를 사용하여 고객객체생성
		Customer oneCus = new Customer("S1001","오소진");
		
		//Customer setter 메서드를 사용하여 고객객체생성
		Customer twoCus = new Customer();
		twoCus.setCusNo("S1002");
		twoCus.setCusName("박지원");
		
		System.out.println("[oneCus] CustomerNo: " + oneCus.getCusNo());
		System.out.println("[oneCus] CustomerName: " + oneCus.getCusName());
		
		System.out.println("[twoCus] CustomerNo: " + twoCus.getCusNo());
		System.out.println("[twoCus] CustomerName: " + twoCus.getCusName());
	}

}
