<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.SQLException"%>
<%
	request.setCharacterEncoding("EUC-KR");
	String memberID = request.getParameter("memberID");
	String name = request.getParameter("name");

	int updateCount = 0;

	Class.forName("oracle.jdbc.OracleDriver");

	Connection conn = null;
	Statement stmt = null;

	try {
		String jdbcDriver = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbUser = "system";
		String dbPass = "0000";
		String query = "UPDATE MEMBER SET NAME = " + name + "" + "where MEMBERID = " + "";
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		stmt = conn.createStatement();
		updateCount = stmt.executeUpdate(query);
	} finally {
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException ex) {
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException ex) {
			}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�̸�����</title>
</head>
<body>
	<%
		if (updateCount > 0) {
	%>
	<%=memberID%>�� �̸���
	<%=name%>(��)�� ����

	<%
		} else {
	%>
	<%=memberID%>���̵� �������� ����
	<%
		}
	%>
	<input type="button" value="����� ����Ʈ ����"
		onclick="window.open('viewMemberList.jsp')">

</body>
</html>