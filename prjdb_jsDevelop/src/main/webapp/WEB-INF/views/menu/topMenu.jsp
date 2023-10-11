<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <style>
#container {
	width: 800px;
	/* height: 70px; */
	margin: 10px auto;
	background-color: #F8F8F8;
}

nav {
	/* float: right; */
	text-align: center;
}

nav>ul {
	margin: 22px 0px 0 0;
}

nav>ul>li {
	float: left;
	margin-left: 50px;
	list-style: none;
	position: relative;
}

nav a {
	padding: 15px 30px;
	display: block;
	text-decoration: none;
	color: #222222;
	font-size: larger;
	font-weight: bolder;
}

nav>ul>li:hover>a {
	background-color: #4C4C4B;
	color: #F8F8F8;
}
</style> -->
<link  rel="stylesheet" href="css/menu.css">
</head>
<body>
	<div id="container">
		<nav>
			<ul>
				<li><a href="home.do">Home</a></li>
				<li><a href="bookmain.do">Book</a></li>
				<li><a href="noticeselectlist.do">Content</a></li>
				<c:if test="${empty id }">
				<li><a href="memberloginform.do">Login</a></li>	
				<li><a href="memberjoinform.do">Join</a></li>			
				</c:if>
				<c:if test="${ not empty id }">
				<li><a href="memberlist.do">Member</a></li>
				<li><a href="memberlogout.do">Logout</a></li>
				<li>${name }님 접속중</li>
				</c:if>
			</ul>
		</nav>
	</div>
</body>
</html>