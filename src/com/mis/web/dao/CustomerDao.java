package com.mis.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mis.web.model.Customer;

public class CustomerDao extends CommonDao {

	private static CustomerDao instance;

	public static CustomerDao getInstance() {
		if (instance == null) {
			instance = new CustomerDao();
		}
		return instance;
	}

	public Customer getCusInfo(String user) {
		Customer cus = new Customer();

		Connection conn = getConnection();

		ResultSet rs = null; // 조회 결과값
		PreparedStatement st;

		try {
			st = conn.prepareStatement("SELECT CUS_NAME, CUS_HP, CUS_EMAIL, CUS_ADD FROM CUSTOMER WHERE CUS_NO=?");
			st.setString(1, user);
			rs = st.executeQuery(); // 쿼리문 실행

			if (rs.next()) {
				cus.setCusName(rs.getString("CUS_NAME"));
				cus.setCusHP(rs.getString("CUS_HP"));
				cus.setCusEmail(rs.getString("CUS_EMAIL"));
				cus.setCusAdd(rs.getString("CUS_ADD"));
				cus.setCusNo(rs.getString("CUS_NO"));
			}
			dbClose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cus;
	}

	public Customer getCusDetailInfo(String no) {
		
		Customer cus = new Customer();
		
		Connection conn = getConnection();
		
		ResultSet rs = null; // 조회 결과값
		PreparedStatement st;
		
		try {
			st = conn.prepareStatement("SELECT * FROM CUSTOMER WHERE CUS_NO=? ");
			st.setString(1, no);
			
			rs = st.executeQuery(); // 쿼리문 실행
			
			if (rs.next()) {
				cus.setCusNo(rs.getString("CUS_NO"));
				cus.setCusName(rs.getString("CUS_NAME"));
				cus.setCusHP(rs.getString("CUS_HP"));
				cus.setCusEmail(rs.getString("CUS_EMAIL"));
				cus.setCusAdd(rs.getString("CUS_ADD"));
			}
		
			dbClose();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cus;
	}

	public ArrayList<Customer> getCusList(int cpp, int page, String srch_type, String srch_keyword) {

		ArrayList<Customer> cuslist = new ArrayList<Customer>();

		Connection conn = getConnection();

		ResultSet rs = null; // 조회 결과값
		PreparedStatement st;
		
		System.out.println("CPP"+cpp);
		System.out.println("page"+page);

		String sql = "SELECT * FROM (SELECT t1.*, ceil(rownum/?) as page FROM " + "(SELECT CUS_NO, CUS_NAME, CUS_HP, CUS_EMAIL, CUS_ADD";
				sql += " ORDER BY TO_NUMBER(CUS_NO) DESC) t1) WHERE page = ?";

		try {
			System.out.println(sql);
			st = conn.prepareStatement(sql);
			st.setInt(1, cpp);
			st.setInt(2, page);

			rs = st.executeQuery(); // 쿼리문 실행

			while (rs.next()) {
				Customer cus = new Customer();
				cus.setCusNo(rs.getString("CUS_NO"));
				cus.setCusName(rs.getString("CUS_NAME"));
				cus.setCusHP(rs.getString("CUS_HP"));
				cus.setCusEmail(rs.getString("CUS_EMAIL"));
				cus.setCusAdd(rs.getString("CUS_ADD"));
				
				cuslist.add(cus);
			}
			
			dbClose();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cuslist;
	}

	public int updateCustomer(Customer cus) {
		int res = 0;
		Connection conn = getConnection();
		PreparedStatement st;
		String sql = "UPDATE EMPLOYEE SET CUS_NAME=?, CUS_HP=?, CUS_EMAIL=?, "
				+ "CUS_ADD=? " + "WHERE CUS_NO=? ";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, cus.getCusName());
			st.setString(2, cus.getCusHP());
			st.setString(3, cus.getCusEmail());
			st.setString(4, cus.getCusAdd());
			st.setString(5, cus.getCusNo());
			res = st.executeUpdate(); // 쿼리문 실행 //res=0은 update 실패, 1은 성공
			dbClose();
		} catch (SQLException e) {
			res = 0;
			e.printStackTrace();
		}
		return res;
	}

	public int deleteCustomer(String no) {
		int res = 0;
		Connection conn = getConnection();
		PreparedStatement st;
		try {
			st = conn.prepareStatement("DELETE FROM CUSTOMER WHERE CUS_NO=? ");
			st.setString(1, no);
			res = st.executeUpdate();
			dbClose();
		} catch (SQLException e) { // TODO Auto-generated catch block
			res = 0;
			e.printStackTrace();
		}
		return res;
	}

	public int createCustomer(Customer cus) {
		
		int res = 0;
		
		Connection conn = getConnection();
		
		PreparedStatement st;
		
		String sql = "INSERT INTO CUSTOMER ( CUS_NO, CUS_NAME, CUS_HP, "
				+ "CUS_EMAIL, CUS_ADD )" + "VALUES( CUS_SEQ.nextval,?,?,?,?,?)";
		
		System.out.println("asdasdasdasdasdasdasd7");
		try {
			st = conn.prepareStatement(sql);
			st.setString(2, cus.getCusName());
			st.setString(3, cus.getCusHP());
			st.setString(4, cus.getCusEmail());
			st.setString(5, cus.getCusAdd());
			
			res = st.executeUpdate(); // 쿼리문 실행 
			//res=0은 update 실패, 1은 성공
			
			dbClose();
		} catch (SQLException e) {
			
			res = 0;
			e.printStackTrace();

			System.out.println("asdasdasdasdasdasdasd9");
		}
		return res;
	}

	public int getCusCnt() {

		int cnt = 0;
		Connection conn = getConnection();
		ResultSet rs = null; // 조회 결과값
		PreparedStatement st;

		try {
			st = conn.prepareStatement("SELECT COUNT(*) AS CNT FROM CUSTOMER");
			rs = st.executeQuery(); // 쿼리문 실행

			if (rs.next()) {
				cnt = rs.getInt("CNT");
				System.out.println("cnt" + cnt);
			}
			dbClose();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
}
