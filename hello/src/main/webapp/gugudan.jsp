<!-- 전통적 JSP -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>구구단</h1>
<% 
	for(int i = 2; i < 10; i++){
		for(int j = 1; j < 10; j++){ %>
			<%= i %> * <%= j %> = <%= i*j %> <br>
<% 		}
	}
%>
<a href="index.jsp">홈 가기</a>
</body>
</html>