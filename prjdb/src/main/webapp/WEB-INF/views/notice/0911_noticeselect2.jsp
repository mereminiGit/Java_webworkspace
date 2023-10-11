<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
</head>

<body>
	<div align="center">
		<jsp:include page="../menu/topMenu.jsp" />
		<div>
			<h1>게시글 상세보기</h1>
		</div>
		<div>
			<div>
				<table border="1">
					<tr>
						<th width="100">작성자</th>
						<td width="150">${n.noticeWriterName }</td>
						<th width="100">작성일자</th>
						<td width="150" align="center">${n.noticeDate }</td>
						<th width="100">조회수</th>
						<td width="50" align="center">${n.noticeHit }</td>
					</tr>
					<tr>
						<th>이미지</th>
						<td colspan="5"><img height="100"
							src="attech/notice/${n.noticeImage }"></td>
					</tr>
					<tr>
						<th>제 목</th>
						<td colspan="5">${n.noticeTitle }</td>
					</tr>
					<tr>
						<th>내 용</th>
						<td colspan="3"><textarea cols="69" rows="7">${n.noticeContent }</textarea></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td colspan="5">${n.noticeFile }</td>
					</tr>
				</table>
			</div>
		</div>
		<br>
	</div>
	<h3>댓글등록</h3>
	<div>
		<table border="1">
			<tr>
				<th>댓글번호</th>
				<td><input type = "text" name = "rid"></td>
			</tr>
			<tr>
				<th>댓글내용</th>
				<td><input type = "text" name = "content"></td>
			</tr>
			<tr>
				<th>댓글작성자</th>
				<td><input type = "text" name = "writer"></td>
			</tr>
			<tr>
				<th>댓글일자</th>
				<td><input type = "text" name = "replyDate"></td>
			</tr>
			<tr>
				<td colspan="2"><button id="addRow">추가</button></td>
			</tr>
		</table>
	</div>
	<h3>댓글목록</h3>
	<table id="example" class="display" style="width: 100%">
		<thead>
			<tr>
				<th>댓글번호</th>
				<th>댓글내용</th>
				<th>댓글작성자</th>
				<th>댓글날짜</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th>댓글번호</th>
				<th>댓글내용</th>
				<th>댓글작성자</th>
				<th>댓글날짜</th>
			</tr>
		</tfoot>
	</table>
	<script>
		let noticeId = "${n.noticeId}";
		new DataTable('#example', {
			ajax : 'AjaxReplyList2_0911.do?nid=' + noticeId + '&param=jquery'
		});
		
		function addNewRow() {
		    table.row
		        .add([
		            $('input[name=rid]').val(),
		            $('input[name=content]').val(),
		            $('input[name=writer]').val(),
		            $('input[name=replyDate]').val(),
		        ])
		        .draw(false);
		 
		    counter++;
		    $.ajax({
				url: './AjaxReplyAdd.do',	// 웹페이지 주소를 보고 경로를 정해줘야함
				method: 'post',
				data: {nid: noticeId, writer: $('input[name=writer]').val(), content: $('input[name=content]').val()},
				success: function (e) {
					console.log(e);
				},
				error: function (e) {
					console.log(e);
				}
		    })
		}
		 
		const table = new DataTable('#example');
		let counter = 1;
		 
		document.querySelector('#addRow').addEventListener('click', addNewRow);
		 
		// Automatically add a first row of data
		//addNewRow();
	</script>
</body>

</html>