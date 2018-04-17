<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
String s; //변수 s의 데이터형은 참조형이다.
s = "나는 홍길동입니다";
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
//s는?<br>
<% s = "나는 JSP 전문가가 되길 원합니다.<br>"; %><br>
<%= s %><br> 
//s가 바뀌었나?<br>
<%= res %> <br>
// boolean res는 false로 나왔나?

</body>
</html>