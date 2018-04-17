<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	int i = 0;
	i++;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>++연산자 사용</title>
</head>
<body>
첫번째 i값 = <%= i %><br>
두번째 i값 = <%= ++i %><br>
세번째 i값 = <%= i++ %><br>
네번째 i값 = <%= i %>
</body>
</html>