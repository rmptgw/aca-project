<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
int ia[]; //int형 변수의 배열을 선언하였다.
float fa[] = {1.0f, 3.0f, 5.0f, 7.0f}; //생성과 초기화 동시에
ia = new int[5]; //배열을 생성한다.
ia[3] = 7;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>array</title>
</head>
<body>
배열 ia의 3번째 방의 값 : <%= ia[3] %><br>
배열 fa의 3번째 방의 값 : <%= fa[3] %><br>
<br>
배열 ia의 0번째 방의 값 : <%= ia[0] %><br>
<br>
배열 ia의 크기 : <%= ia.length %><br>
배열 fa의 크기 : <%= fa.length %><br>
</body>
</html>