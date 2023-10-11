<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<div><h1>보내온 데이터</h1></div>
	<%-- <% 
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");	
		String tel = request.getParameter("tel");
	%>
	<%= name %> : <%= tel %> --%>
	<!-- 요청한 객체가 가지고있는 파라메타 -->
	<%-- <%= request.getParameter("name") %> : <%= request.getParameter("tel") %> --%>
	
	<h1>서버에서 처리해서 나한테 보여준다</h1>
	
</div>
</body>
</html>