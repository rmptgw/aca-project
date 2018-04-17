package com.mis.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mis.web.model.Provider;

public class ProviderDao extends CommonDao {

	private static ProviderDao instance;

	public static ProviderDao getInstance() {
		if (instance == null) {
			instance = new ProviderDao();
		}
		return instance;
	}

	public boolean loginCheck(String no, String pass) {

		boolean res = false;

		Connection conn = getConnection();

		ResultSet rs = null; // 조회 결과값
		PreparedStatement st;

		try {
			st = conn.prepareStatement("SELECT PROV_NAME FROM PROVIDER WHERE PROV_NO=?sn");
			st.setString(1, no);
			st.setString(2, pass);
			rs = st.executeQuery(); // 쿼리문 실행

			System.out.println("----------------");

			if (rs.next()) {

				res = true;
				return res;
			}

			dbClose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;
	}

	public Provider getProvInfo(String user) {
		Provider prov = new Provider();

		Connection conn = getConnection();

		ResultSet rs = null; // 조회 결과값
		PreparedStatement st;

		try {
			st = conn.prepareStatement("SELECT PROV_NAME, PROV_CEO, PROV_HP,PROV_EMAIL, PROV_ADD FROM PROVIDER WHERE PROV_NO=?");
			st.setString(1, user);
			rs = st.executeQuery(); // 쿼리문 실행

			if (rs.next()) {
				prov.setProvName(rs.getString("PROV_NAME"));
				prov.setProvCEO(rs.getString("PROV_CEO"));
				prov.setProvHP(rs.getString("PROV_HP"));
				prov.setProvEmail(rs.getString("PROV_EMAIL"));
				prov.setProvAdd(rs.getString("PROV_ADD"));
				prov.setProvNo(rs.getString("PROV_NO"));
			}
			dbClose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prov;
	}

	public Provider getProvDetailInfo(String no) {
		
		Provider prov = new Provider();
		
		Connection conn = getConnection();
		
		ResultSet rs = null; // 조회 결과값
		PreparedStatement st;
		
		try {
			st = conn.prepareStatement("SELECT * FROM PROVIDER WHERE PROV_NO=? ");
			st.setString(1, no);
			
			rs = st.executeQuery(); // 쿼리문 실행
			
			if (rs.next()) {
				prov.setProvNo(rs.getString("PROV_NO"));
				prov.setProvName(rs.getString("PROV_NAME"));
				prov.setProvCEO(rs.getString("PROV_CEO"));
				prov.setProvHP(rs.getString("PROV_HP"));
				prov.setProvEmail(rs.getString("PROV_EMAIL"));
				prov.setProvAdd(rs.getString("PROV_ADD"));
			}
		
			dbClose();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prov;
	}

	public ArrayList<Provider> getProvList(int cpp, int page, String srch_type, String srch_keyword) {

		ArrayList<Provider> provlist = new ArrayList<Provider>();

		Connection conn = getConnection();

		ResultSet rs = null; // 조회 결과값
		PreparedStatement st;
		
		System.out.println("CPP"+cpp);
		System.out.println("page"+page);

		String sql = "SELECT * FROM (SELECT t1.*, ceil(rownum/?) as page FROM " + "(SELECT PROV_NO, PROV_NAME, PROV_CEO, PROV_HP, PROV_EMAIL, PROV_ADD";
				
		if (srch_keyword != null && !srch_keyword.equals("")) {
			if (srch_type.equals("1")) {// 대표이사
				sql += " WHERE PROV_CEO ='" + srch_keyword + "'";
			} else if (srch_type.equals("2")) {// 연락처
				sql += " WHERE PROV_HP ='" + srch_keyword + "'";
			} else if (srch_type.equals("3")) {// 이름
				sql += " WHERE PROV_NAME ='" + srch_keyword + "'";
			} else {
				sql += " WHERE PROV_CEO ='" + srch_keyword + "' OR PROV_HP ='" + srch_keyword + "' OR PROV_NAME ='"
						+ srch_keyword + "'";
			}
		}

		sql += " ORDER BY TO_NUMBER(PROV_NO) DESC) t1) WHERE page = ?";

		try {
			System.out.println(sql);
			st = conn.prepareStatement(sql);
			st.setInt(1, cpp);
			st.setInt(2, page);

			rs = st.executeQuery(); // 쿼리문 실행

			while (rs.next()) {
				Provider prov = new Provider();
				prov.setProvNo(rs.getString("PROV_NO"));
				prov.setProvName(rs.getString("PROV_NAME"));
				prov.setProvCEO(rs.getString("PROV_CEO"));
				prov.setProvHP(rs.getString("PROV_HP"));
				prov.setProvEmail(rs.getString("PROV_EMAIL"));
				prov.setProvAdd(rs.getString("PROV_ADD"));
				
				provlist.add(prov);
			}
			
			dbClose();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return provlist;
	}

	public int updateProvider(Provider prov) {
		int res = 0;
		Connection conn = getConnection();
		PreparedStatement st;
		String sql = "UPDATE EMPLOYEE SET PROV_NAME=?, PROV_CEO=?, PROV_HP=?, PROV_EMAIL=?, "
				+ "PROV_ADD=? " + "WHERE PROV_NO=? ";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, prov.getProvName());
			st.setString(2, prov.getProvCEO());
			st.setString(3, prov.getProvHP());
			st.setString(4, prov.getProvEmail());
			st.setString(5, prov.getProvAdd());
			st.setString(6, prov.getProvNo());
			res = st.executeUpdate(); // 쿼리문 실행 //res=0은 update 실패, 1은 성공
			dbClose();
		} catch (SQLException e) {
			res = 0;
			e.printStackTrace();
		}
		return res;
	}

	public int deleteProvider(String no) {
		int res = 0;
		Connection conn = getConnection();
		PreparedStatement st;
		try {
			st = conn.prepareStatement("DELETE FROM PROVIDER WHERE PROV_NO=? ");
			st.setString(1, no);
			res = st.executeUpdate();
			dbClose();
		} catch (SQLException e) { // TODO Auto-generated catch block
			res = 0;
			e.printStackTrace();
		}
		return res;
	}

	public int createProvider(Provider prov) {
		
		int res = 0;
		
		Connection conn = getConnection();
		
		PreparedStatement st;
		
		String sql = "INSERT INTO PROVIDER ( PROV_NO, PROV_NAME, PROV_CEO, PROV_HP, "
				+ "PROV_EMAIL, PROV_ADD )" + "VALUES( PROV_SEQ.nextval,?,?,?,?,?)";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, prov.getProvName());
			st.setString(2, prov.getProvCEO());
			st.setString(3, prov.getProvHP());
			st.setString(4, prov.getProvEmail());
			st.setString(5, prov.getProvAdd());
			
			res = st.executeUpdate(); // 쿼리문 실행 
			//res=0은 update 실패, 1은 성공
			
			dbClose();
		} catch (SQLException e) {
			
			res = 0;
			e.printStackTrace();
		}
		return res;
	}

	public int getProvCnt() {

		int cnt = 0;
		Connection conn = getConnection();
		ResultSet rs = null; // 조회 결과값
		PreparedStatement st;

		try {
			st = conn.prepareStatement("SELECT COUNT(*) AS CNT FROM PROVIDER");
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
