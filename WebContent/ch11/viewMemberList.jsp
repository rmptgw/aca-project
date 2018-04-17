<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.Statement" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.SQLException" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ȸ�����</title>
</head>
<body>
MEMBER ���̺��� ����
<table width = "100%" border = "1">
<tr>
<td>�̸�</td><td>���̵�</td><td>�̸���</td>
</tr>
<%
// 1. JDBC ����̹� �ε�
Class.forName("oracle.jdbc.OracleDriver");

Connection conn = null;
Statement stmt = null;
ResultSet rs = null;

try{
	String jdbcDriver = "jdbc:oracle:thin:@localhost:1521:xe";
	String dbUser = "system";
	String dbPass = "0000";
	
	String query = "select * from MEMBER order by MEMBERID";
	
	// 2. �����ͺ��̽� Ŀ�ؼ� ����
	conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
	
	// 3. Statement ����
	stmt = conn.createStatement();
	
	// 4. ���� ����
	rs = stmt.executeQuery(query);
	
	// 5. ���� ���� ��� ���
	while(rs.next()){
		%>
		<tr>
		<td><%=rs.getString("NAME") %></td>
		<td><%=rs.getString("MEMBERID") %>
		<input type = "button" value="����� �󼼺���" 
		onclick="lacation=window.open('viewMember.jsp?memberID=<%=rs.getString("MEMBERID")%>')">
		</td>
		<td><%=rs.getString("EMAIL") %></td>
		</tr>
		<%
	}
} catch(SQLException ex){
	out.println(ex.getMessage());
	ex.printStackTrace();
} finally{
	//6. ����� Statement ����
	if(rs != null)try {rs.close();} catch(SQLException ex){}
	if(stmt != null)try {stmt.close();} catch(SQLException ex){}
	
	//7. Ŀ�ؼ� ����
	if(conn != null)try {conn.close();} catch(SQLException ex){}
}
%>
</table>
<input type="button" value="����ڵ��"
onclick="window.open('insertForm.jsp')">
<input type="button" value="����ڼ���"
onclick="window.open('updateForm.jsp')">
</body>
</html>