<%-- 지시자 --%>
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
		<div><h1>시작하는 곳</h1></div>
		<div>
		<!-- 어떤방식 get방식으로 어떤 액션 -->
			<form id="frm" action="result.do" onsubmit="return Check()" method="post">
				<label>이름 : </label>
				<!-- form태크 안에 id와 name 속성 반드시 작성 -> id속성 - 자바 스크립트에서 사용하는 변수명 / name속성 - 자바에서 사용하는 변수명 -->
				<input type="text" id="name" name="name"><br>
				<label>전화번호 : </label>
				<input type="tel" id="tel" name="tel"><br>
				<input type="submit" value="전송">
			</form>
		</div>
		
		<script type="text/javascript">
			function Check() {
				let name = document.getElementById("name").value;
				console.log(name);
				alert(name);
				return true;		
			}
		</script>
<%-- 	
	선언문	
	<%!int sum(int a, int b) {
		return a+b;
	}%>
	자바코드
	<%
		int n = 10;
		int m = 100;
		int result = sum(n, m);
	%>
	출력문
	<h1><%= result %></h1> 
		<a href="gugudan.jsp">구구단</a> 
		<a href="Home">Servlet 호출</a>
--%>
	</div>
</body>
</html>