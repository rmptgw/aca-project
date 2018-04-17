<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>형 변환시 값 손실 예제</title>
</head>
<body>
<%
	int val = 45000;
	short val2 = (short)val;
%>
<%= val %> 값을 short타입으로 변환한 결과 -> <%= val2 %>
</body>
</html>