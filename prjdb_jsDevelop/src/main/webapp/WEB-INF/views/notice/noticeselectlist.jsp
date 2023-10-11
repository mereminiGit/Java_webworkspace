<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<jsp:include page="../menu/topMenu.jsp" />
		<div>
			<h2>게시글 목록</h2>
		</div>
		<div>
			<form id="search" action="">
				<table>
					<tr>
						<td><label for="key">Choose a key:</label> 
						<select id="key" name="key">
								<option value="title">제목</option>
								<option value="content">내용</option>
								<option value="writer">작성자</option>
						</select> 
					<!-- 	<input type="text" name="val" id="val">  -->
						<input type="text" name="val" id="val" onkeydown="enterKey(event)"> &nbsp;&nbsp; 
						<input type="button" value="검색" id="btn" onclick="searchList()"></td>
					</tr>
				</table>
			</form>
		</div>
		<br>
		<div>
			<table border="1" id="listtable">
				<thead>
					<tr>
						<th width="50">순번</th>
						<th width="100">이미지</th>
						<th width="200">제목</th>
						<th width="100">작성자</th>
						<th width="100">작성일자</th>
						<th width="50">조회수</th>
						<th width="100">첨부파일</th>
					</tr>
				</thead>
				<tbody id="tlist">
					<c:choose>

						<c:when test="${empty notices }">
							<tr>
								<td colspan="7" align="center">데이터가 존재하지 않습니다.</td>
							</tr>
						</c:when>

						<c:otherwise>
							<c:forEach items="${notices }" var="n">
								<tr onmouseover="this.style.background='#C8FE2E'"
									onmouseout="this.style.background='#FFFFFF'"
									onclick="selectNotice(${n.noticeId})">
									<td align="center">${n.noticeId }</td>
									<td align="center"><img
										src="attech/notice/${n.noticeThumbnail }"></td>
									<td>${n.noticeTitle }</td>
									<td align="center">${n.noticeWriterName }</td>
									<td align="center">${n.noticeDate }</td>
									<td align="center">${n.noticeHit }</td>
									<td align="center">${n.noticeFile }</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<br>
		<div>
			<!-- 로그인이 되어있다면 글쓰기 버튼이 보이게 해라 -->
			<c:if test="${not empty id }">
				<!-- 글쓰기 버튼 -->
				<button type="button" onclick="location.href='noticewriteform.do'">글쓰기</button>
			</c:if>

			<form id="sform" action="noticeselect.do" method="post">
				<input type="hidden" id="noticeId" name="noticeId">
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function selectNotice(id) {
			let form = document.getElementById("sform");
			form.noticeId.value = id;
			form.submit();
		}

		function searchList(){  // ajax post 방식
			let form = document.getElementById("search");  // 폼을 찾고
			let key = form.key.value;
			let val = form.val.value;
//			const formData = new FormData(form);  // 내 폼을 던저주고 / 자바스크립트 FormData class / post로 할 때는 form으로 넣어줘야함
//			let payload = formData; // payload라는 일반변수에 한번더 넣어줘야한다. 그러면 폼 안에 있는 key val를 가져옴
			let payload = "key="+key+"&val="+val;
			let url = "ajaxNoticeSearch.do";
			// post 방식 url다음 몇개 넣어야함
			fetch(url, {
				method: "POST",
				// header : 데이터를 json타입으로 던질때
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				body: payload  // 전달하는 데이터
			}).then(Response=>Response.json())		// 결과받는 callback 함수 / 결과를 .json .xml .text .html 이렇게 받을꺼다 지정
				//.then(json=>console.log(json));		// 처리하는 callback 함수
			  .then(json=>htmlViews(json));
		}
		
		function htmlViews(datas){
				document.querySelector('#tlist').remove();  // 본문에서 <tbody>태그를 삭제하는거
				const tbody = document.createElement('tbody');  // 새롭게 tbody태그 생성해서 담고
				tbody.setAttribute('id', 'tlist'); //tbody 속성 정의
				tbody.innerHTML = datas.map(data=>htmlConvert(data)).join('');  // map 한 행씩 forEach역활 객체 안에서 한개씩 꺼내서 처리
				document.querySelector('#listtable').appendChild(tbody);
		}

		function htmlConvert(n){
				if(n.noticeFile == null){
					n.noticeFile = ' ';
				}
				return `
					<tr onmouseover = "this.style.background='#B4BEC9'"
							onmouseout = "this.style.background='#FFFFFF'"
							onclick = "selectNotice(\${n.noticeId})">
							<td align="center">\${n.noticeId }</td>
							<td align="center">
							<img src="attech/notice/\${n.noticeThumbnail }"></td>
							<td>\${n.noticeTitle }</td>
							<td align="center">\${n.noticeWriterName }</td>
							<td align="center">\${n.noticeDate }</td>
							<td align="center">\${n.noticeHit }</td>
							<td align="center">\${n.noticeFile }</td>
				 </tr>
				`
		} // 자바 스크립트에서는 달러 앞에 \넣어야 인식함
		
		function enterKey(e) {
			if(e.keyCode == 13){
				document.getElementById("btn").focus();
			}
		}
	</script>
</body>
</html>





