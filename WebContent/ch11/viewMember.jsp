<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.Statement" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.SQLException" %>
<%
String memberID = request.getParameter("memberID");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원정보</title>
</head>
<body>
<%
// 1. JDBC 드라이버 로딩
Class.forName("oracle.jdbc.OracleDriver");

Connection conn = null;
Statement stmt = null;
ResultSet rs = null;

try{
	String jdbcDriver = "jdbc:oracle:thin:@localhost:1521:xe";
	String dbUser = "system";
	String dbPass = "0000";
	
	String query = "select * from MEMBER where MEMBERID = '"+memberID+"'";
	
	conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
	stmt = conn.createStatement();
	
	rs = stmt.executeQuery(query);
	if(rs.next()){
%>
<table border="1">
<tr>
<td>아이디</td><td><%=memberID %></td>
</tr>
<tr>
<td>암호</td><td><%= rs.getString("PASSWORD") %></td>
</tr>
<tr>
<td>아름</td><td><%=rs.getString("NAME") %></td>
</tr>
<tr>
<td>이메일</td><td><%=rs.getString("EMAIL") %></td>
</tr>
</table>

<input type="button" value="사용자 리스트 보기"
onclick="window.open('viewMemberList.jsp')">
<%
	} else{
%>
<%= memberID %>에 해당하는 정보가 존재하지 안습니다.
<%
	}
}catch(SQLException ex){
%>
에러 발생 : <%= ex.getMessage() %>
<%
}finally{
	if(rs != null) try{rs.close();}catch(SQLException ex){}
	if(stmt != null) try{stmt.close();}catch(SQLException ex){}
	if(conn != null) try{conn.close();}catch(SQLException ex){}
}
%>
}	
</body>
</html>