<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
String s; //���� s�� ���������� �������̴�.
s = "���� ȫ�浿�Դϴ�";
boolean res = false;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%= s %><br> 
//s��?<br>
<% s = "���� JSP �������� �Ǳ� ���մϴ�.<br>"; %><br>
<%= s %><br> 
//s�� �ٲ����?<br>
<%= res %> <br>
// boolean res�� false�� ���Գ�?

</body>
</html>