package com.mis.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CommonDao {

	private Connection conn = null;
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "system";
	String pass = "0000";

	private void init(){
		url = url = "jdbc:oracle:thin:@localhost:1521:xe"; 
		user = "system";
		pass = "0000";

		try{
			Class.forName("oracle.jdbc.OracleDriver");
		} catch(ClassNotFoundException e){
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection(){
		try{
			init();
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("접속성공");
		} catch(SQLException e){
			//TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

	public void dbClose(){
		try{
			conn.close();
		} catch(SQLException e){
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
