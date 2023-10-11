<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
								<td colspan="5"><img height="100" src="attech/notice/${n.noticeImage }"></td>
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
				<!-- 등록화면 -->
				<table border="1">
					<tr>
						<th>댓글내용</th>
						<td><input type="text" name="content"></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><input type="text" name="writer"></td>
					</tr>
					<tr>
						<td colspan="2">
							<button id="addReply">댓글등록</button>
						</td>
					</tr>
				</table>
				<h3>댓글목록</h3>
				<table border="1">
					<tbody id="replyList">
						<tr>
							<td>1</td>
							<td>1번에 댓글입니다.</td>
							<td>user1</td>
							<td>2023-09-05</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- modal창 -->
			<style>
				body {
					font-family: Arial, Helvetica, sans-serif;
				}

				/* The Modal (background) */
				.modal {
					display: none;
					/* Hidden by default */
					position: fixed;
					/* Stay in place */
					z-index: 1;
					/* Sit on top */
					padding-top: 100px;
					/* Location of the box */
					left: 0;
					top: 0;
					width: 100%;
					/* Full width */
					height: 100%;
					/* Full height */
					overflow: auto;
					/* Enable scroll if needed */
					background-color: rgb(0, 0, 0);
					/* Fallback color */
					background-color: rgba(0, 0, 0, 0.4);
					/* Black w/ opacity */
				}

				/* Modal Content */
				.modal-content {
					background-color: #fefefe;
					margin: auto;
					padding: 20px;
					border: 1px solid #888;
					width: 80%;
				}

				/* The Close Button */
				.close {
					color: #aaaaaa;
					float: right;
					font-size: 28px;
					font-weight: bold;
				}

				.close:hover,
				.close:focus {
					color: #000;
					text-decoration: none;
					cursor: pointer;
				}
			</style>
			<!-- modal창 -->

			<!-- The Modal -->
			<div id="myModal" class="modal">

				<!-- Modal content -->
				<div class="modal-content">
					<span class="close">&times;</span>
					<p>23</p>
					<p><input class="content"></p>
					<p><button id="editBtn"> 수정</button></p>
				</div>

			</div>

			<script src='js/reply.js'></script>
			<script>
				const replyObj = new Reply();
				replyObj.showInfo();
				let today = new Date(2023);
				console.log(today);


				let noticeId = '<c:out value="${n.noticeId }"/>';
				const field = ['replyId', 'reply', 'replyer', 'replyDate'];

				// 댓글목록
				replyObj.replyList(noticeId, function (data) {
					console.log(data);
					// 출력할 필드 선언.
					data.forEach(function (reply){
						let tr = makeTr(reply);
						document.querySelector('#replyList').appendChild(tr);
					});
					
				});

				// 댓글정보를 매개값으로 해서 tr요소 생성. makeTr
				function makeTr(reply) {
					console.log("---------" + reply);
					let tr = document.createElement('tr');
					// tr 더블클릭 시 수정 할 수 있게 이벤트
					tr.addEventListener('dblclick', showEditForm);
					tr.setAttribute('rid', reply.replyId);
					for (let prop of field) {
						let td = document.createElement('td');
						if (prop == 'replyDate') {
							td.innerText = replyObj.displayDate(reply[prop]);
						} else {
							td.innerText = reply[prop];
						}
						tr.appendChild(td);
					}
					let td = document.createElement('td');
					let bnt = document.createElement('button');
					bnt.addEventListener('click', deleteReplyFnc)
					bnt.innerText = '삭제';
					td.appendChild(bnt);
					tr.appendChild(td);
					// document.querySelector('#replyList').appendChild(tr);
					return tr;
				}

				// 댓글 삭제 기능
				function deleteReplyFnc(e) {
					//e.target = bnt
					let rid = e.target.parentElement.parentElement.getAttribute('rid');
					replyObj.replyRemove(rid, function (result) {
						if (result.retCode == 'Success') {
							e.target.parentElement.parentElement.remove();
						} else if (result.retCode == 'Fail') {
							alert("처리중 에러");
						} else {
							alert("잘못된 코드 변환");
						}
					});
				}

				// 댓글등록 기능
				document.querySelector('#addReply').addEventListener('click', function (e) {
					let content = document.querySelector('input[name=content]').value;
					let writer = document.querySelector('input[name=writer]').value;
					const r = { nid: noticeId, replyer: writer, reply: content };

					replyObj.replyAdd(r, function (data) {
						console.log(data);

						// code here..
						const field = ['replyId', 'reply', 'replyer', 'replyDate'];
						if (data.retCode == 'Success') {
							let tr = makeTr(data.data);
							document.querySelector('#replyList').appendChild(tr);
							fieldinit();
							// let tr = document.createElement('tr');
							// tr.setAttribute('rid', data.data.replyId);

							// for (let prop of field) {
							// 	let td = document.createElement('td');
							// 	if (prop == 'replyDate') {
							// 		td.innerText = replyObj.displayDate(data.data[prop]);
							// 	} else {
							// 		td.innerText = data.data[prop];
							// 	}
							// 	tr.appendChild(td);
							// }
							// let td = document.createElement('td');
							// let bnt = document.createElement('button');
							// bnt.addEventListener('click', deleteReplyFnc)
							// bnt.innerText = '삭제';
							// td.appendChild(bnt);
							// tr.appendChild(td);
							// document.querySelector('#replyList').appendChild(tr);
						} else if (data.retCode == 'Fail') {
							alert("처리중 에러");
						} else {
							alert("잘못된 코드 변환");
						}

					});
				});

				/*
								let xhttp = new XMLHttpRequest();	// ajax 비동기 방식으로 요청하는 
								xhttp.open('get', 'AjaxReplyList.do?nid=' + noticeId);
								xhttp.send();	// 실제 서버에 요청
								xhttp.onload = function (e) {
									console.log(xhttp.responseText);
									let data = JSON.parse(xhttp.responseText); // data에 가져온 결과값을 담는다 - 배열
									console.log(data);
									// 출력할 배열 선언
									const field = ['replyId', 'reply', 'replyer', 'replyDate'];
									data.forEach(function (reply) {
										let tr = document.createElement('tr');
										for (let prop of field) {
											let td = document.createElement('td');
											if(prop == 'replyDate'){
												td.innerText = displayDate(reply[prop]);
											}else{
												td.innerText = reply[prop];
											}
											tr.appendChild(td);
										}
										document.querySelector('#replyList').appendChild(tr);
									})
								}
				
								function getJson() {
									let data = JSON.parse(xhttp.responseText); // data에 가져온 결과값을 담는다 - 배열
									let target = document.querySelector('#replyList');
				
				
									for (let i = 0; i < data.length; i++) {
										let tr = document.createElement('tr');
										data.forEach(function (tmp) {
											let td = document.createElement('td');
											td.innerText = tmp;
											tr.appendChild(td);
										});
										target.appendChild(tr);
									}
				
								}
				*/

				// 등록을 하면 input에 입력된거 초기화
				function fieldinit() {
					document.querySelector('input[name=content]').value = '';
					document.querySelector('input[name=writer]').value = '';
				}


				// Get the modal ===============================================================================
				// 수정화면 open.
				function showEditForm(e) {
					var modal = document.getElementById("myModal");		// 모달을 가져와서
					modal.style.display = "block";		// 더블클릭시 보여지게 

					//
					let rid = this.getAttribute('rid');		// tr의 속성값이라서
					replyObj.replySearch(rid, function(data) {
						console.log(data);
						document.querySelector('#myModal p').innerText = rid;
						document.querySelector('#myModal input.content').value = data.reply;
					});
				}

				// modal 창의 닫기 버튼 클릭 이벤트 발생.
				document.querySelector('span.close').addEventListener('click', function(){
					var modal = document.getElementById("myModal");
					modal.style.display = "none";
				});


				// When the user clicks anywhere outside of the modal, close it / 윈도우의 타겟부분이 모달 영역이거나 아니거나
				window.onclick = function (event) {
					var modal = document.getElementById("myModal");	
					if (event.target == modal) {
						modal.style.display = "none";
					}
				}
				
				//수정버튼 이벤트.
				document.querySelector('#editBtn').addEventListener('click', editReplyHandler);

				function editReplyHandler(e){
					//Ajax 호출(db변경)/ 화면수정
					let rid = document.querySelector('#myModal p').innerText;
					let content = document.querySelector('#myModal input.content').value;

					const r = {rid: rid, reply: content};
					replyObj.replyModify(r, function(data) {
						console.log(data);
						let oldTr = document.querySelector('tr[rid="'+rid+'"]');
						let newTr = makeTr(data.data);
						document.querySelector('#replyList').replaceChild(newTr, oldTr);
						
						//modal 닫기.
						var modal = document.getElementById("myModal");
						modal.style.display = "none";
					});
				}

			</script>
		</body>

		</html>