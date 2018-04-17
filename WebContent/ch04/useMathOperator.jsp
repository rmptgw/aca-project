<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>수치 연산자 사용 예</title>
</head>
<body>
<%
	int operand1 = 10;
	int operand2 = 3;
%>
10 + 3 = <%= operand1 + operand2 %><br>
10 - 3 = <%= operand1 - operand2 %><br>
10 * 3 = <%= operand1 * operand2 %><br>
10 / 3 = <%= operand1 / operand2 %><br>
10 % 3 = <%= operand1 % operand2 %><br>

</body>
</html>