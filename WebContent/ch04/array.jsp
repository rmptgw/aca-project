<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
int ia[]; //int�� ������ �迭�� �����Ͽ���.
float fa[] = {1.0f, 3.0f, 5.0f, 7.0f}; //������ �ʱ�ȭ ���ÿ�
ia = new int[5]; //�迭�� �����Ѵ�.
ia[3] = 7;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>array</title>
</head>
<body>
�迭 ia�� 3��° ���� �� : <%= ia[3] %><br>
�迭 fa�� 3��° ���� �� : <%= fa[3] %><br>
<br>
�迭 ia�� 0��° ���� �� : <%= ia[0] %><br>
<br>
�迭 ia�� ũ�� : <%= ia.length %><br>
�迭 fa�� ũ�� : <%= fa.length %><br>
</body>
</html>