<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <%
		String idR = request.getParameter("id");
		String pwR = request.getParameter("password");
	%> --%>
	<!-- EL 표현식 -->
	<%-- <h1>로그인 아이디 : ${id}</h1> --%>
	<%-- <h1>패스워드 : ${password}</h1> --%>
	<h1>${massage}</h1>
	<%-- <%= idR %> : <%= pwR %> --%>
</body>
</html>