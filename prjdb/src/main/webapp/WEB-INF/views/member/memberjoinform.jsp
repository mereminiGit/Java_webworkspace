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
		<jsp:include page="../menu/topMenu.jsp"></jsp:include>
		<div>
			<h1>회원가입</h1>
		</div>
		<div>
			<form id="frm" action="memberjoin.do" method="post"
				onsubmit="return formCheck()">
				<div>
					<table border="1">
						<tr style="text-align: center">
							<th width="150">아이디</th>
							<td width="260"><input type="text" id="memberId"
								name="memberId" required="required">
								<button type="button" id="idCheck" onclick="memberIdCheck()"
									value="No">중복체크</button> <!-- <input id="idCheck" type="button" onclick="memberIdCheck()" value="No"> -->
							</td>
						</tr>
						<tr>
							<th width="150">패스워드</th>
							<td width="200"><input type="password" id="memberPassword"
								name="memberPassword" required="required"></td>
						</tr>
						<tr>
							<th width="150">패스워드 확인</th>
							<td width="200"><input type="password" id="passwordCheck"
								name="passwordCheck" required="required"></td>
						</tr>
						<tr>
							<th width="150">이름</th>
							<td width="200"><input type="text" id="memberName"
								name="memberName"></td>
						</tr>
						<tr>
							<th width="150">전화번호</th>
							<td width="200"><input type="text" id="memberTel"
								name="memberTel"></td>
						</tr>
					</table>
				</div>
				<br>
				<div>
					<input type="submit" value="가입">&nbsp;&nbsp; <input
						type="reset" value="취소">
				</div>
			</form>
		</div>

	</div>

	<script type="text/javascript">

		function formCheck(){
			let form = document.getElementById("frm");
			
			if(form.idcheck.value == 'No'){
				alert("아이디 중복체크를 하세요");
				return false;
			}
			
			if(form.memberPassword.value != form.passwordCheck.value){
				alert("패스워드가 일치하지 않습니다.");
				form.memberPassword.value = "";
				form.PasswordCheck.value = "";
				form.memberPassword.focus();
				return false;
			}
			return true;
		}
		
		function memberIdCheck(){ // aJax 사용	
			let url = "aJaxmembercheck.do"
			let payload = document.getElementById("memberId").value;
			
			url = url+"?memberId="+payload;
			
			fetch(url)
				.then(response => response.text())
				.then(text => membercheck(text));
		}
		
		function membercheck(str){
			if(str == 'Yes'){
				alert("사용가능한 아이디");
				document.getElementById("idCheck").value = "Yes";
				document.getElementById("idCheck").disabled = true;
			}else{
				alert("이미 존재하는 아이디");
				document.getElementById("memberId").value="";
				document.getElementById("memberId").focus();
			}
		}
	</script>
</body>
</html>