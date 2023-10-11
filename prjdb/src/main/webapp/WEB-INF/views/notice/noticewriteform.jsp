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
		<jsp:include page="../menu/topMenu.jsp"/>
		<div><h1>게시글 작성</h1></div>
		<div>
			<!-- 파일이 있을 때는 multipart/form-data -->
			<form id="frm" action="noticewrite.do" method="post" enctype="multipart/form-data"> 
				<div>
					<table border="1">
						<tr>
							<th width="100">작성자</th>
							<td width="150">
								<input type="text" name="noticeWriterName" id="noticeWriterName" value="${name}" readonly>
							</td>
							<th width="100">작성일자</th>
							<td width="150" align="center">
								<input type="date" id="noticeDate" name="noticeDate" required="required">
							</td>
						</tr>
						<tr>
							<th>이미지</th>
							<td colspan="3">
								<input type="file" id="imgfile" name="imgfile" required="required">
							</td>
						</tr>
						<tr>
							<th>제 목</th>
							<td colspan="3">
								<input type="text" size="67" name="noticeTitle" id="noticeTitle" required="required">
							</td>
						</tr>
						<tr>
							<th>내 용</th>
							<td colspan="3">
								<textarea name="noticeContent"  id="noticeContent" cols="69" rows="7"></textarea>
							</td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td colspan="3">
								<input type="file" name="noticeFile" id="noticeFile">
							</td>
						</tr>
					</table>
				</div><br>
				<div>
					<input type="hidden" name="noticeWriter" value="${id }">
					<input type="submit" value="글등록"> &nbsp;&nbsp;
					<input type="reset" value="취소">
				</div>
			</form>
		</div>
	</div>
</body>
</html>