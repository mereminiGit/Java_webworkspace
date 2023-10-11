<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#inputTable label{
	display: inline-block;
	width: 200px;
	text-align: center;
	background-color: lightgray;
}

#inputTable input{
	display: inline-block;
	width: 400px;
}

#bookList td{
	padding: 10px 0 10px 0;
}
</style>
</head>
<body>
	<div align="center">
		<jsp:include page="../menu/topMenu.jsp" />
		<div>
			<h2>도서 관리</h2>
		</div>
		<!-- 도서 입력 -->
		<div>
			<form action="">
				<table id="inputTable">
					<tr>
						<td>
							<label>도서코드</label>
							<input type="text" name="bookIdInput" id="bookIdInput">
						</td>
						<td rowspan="5" style="width: 150px; text-align: center;">
							<button type="button" style="width:80px" onclick="addBookList()">저장</button><br><br>
							<button type="button" style="width:80px" onclick="selectBookDelete()">선택삭제</button>
						</td>
					</tr>
					<tr>
						<td>
							<label>도서명</label>
							<input type="text" name="bookNameInput" id="bookNameInput">
						</td>
					</tr>
					<tr>
						<td>
							<label>저자</label>
							<input type="text" name="bookWriterInput" id="bookWriterInput">
						</td>
					</tr>
					<tr>
						<td>
							<label>출판사</label>
							<input type="text" name="bookCompanyInput" id="bookCompanyInput">
						</td>
					</tr>
					<tr>
						<td>
							<label>금액</label>
							<input type="text" name="bookPriceInput" id="bookPriceInput">
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<br>
		<hr>
		
		<!-- 도서리스트 출력 -->
		<div>
			<table>
				<thead>
					<tr style="background-color: lightgray">
						<th width="70" style="padding: 15px 0 15px 0;"><input type="checkbox" id="allCheck"></th>
						<th width="150" style="padding: 15px 0 15px 0;">도서코드</th>
						<th width="200" style="padding: 15px 0 15px 0;">도서명</th>
						<th width="100" style="padding: 15px 0 15px 0;">저자</th>
						<th width="150" style="padding: 15px 0 15px 0;">출판사</th>
						<th width="100" style="padding: 15px 0 15px 0;">가격</th>
						<th width="70" style="padding: 15px 0 15px 0;">삭제</th>
					</tr>
				</thead>
				<tbody id="bookList" style="text-align: center;">
					
				</tbody>
			</table>
		</div>
		
		<hr>
		
	</div>
	<script src="js/book.js"></script>
	<script type="text/javascript">
		// js 불러오기
		const bookObj = new Book();
		
		const content = ['bookId', 'bookName', 'bookWriter', 'bookCompany', 'bookPrice'];
		
		// 목록 불러오기
		bookObj.bookList(function (data) {
			console.log(data);
			data.forEach(function (book) {
				console.log(book);
				let tr = makeTr(book);
				document.querySelector('#bookList').appendChild(tr);
			});
		});
		
		// makeTr
		function makeTr(book){
			/* console.log(book); */
			let tr = document.createElement('tr');
			tr.setAttribute('id', book.id);
			let td = document.createElement('td');

			let checkbox = document.createElement('input');
			checkbox.type = 'checkbox';
			checkbox.className = 'selectCheckbox';
			td.appendChild(checkbox);
			td.setAttribute('checkid', book.id);
			tr.appendChild(td);
			
			for (let prop of content) {
				let td = document.createElement('td');
				td.innerText = book[prop];
				tr.appendChild(td);
			}
			
			let bnt = document.createElement('button');
			bnt.addEventListener('click', bookDelete);
			bnt.innerText = '삭제';
			let tdEnd = document.createElement('td');
			
			tdEnd.appendChild(bnt);
			tr.appendChild(tdEnd);
			
			let oneCheck = document.querySelectorAll('.selectCheckbox');
			
			oneCheck.forEach(check => {
				check.addEventListener('click', function(){				
					if(document.querySelectorAll('.selectCheckbox').length == document.querySelectorAll('.selectCheckbox:checked').length){
			    		document.querySelector('#allCheck').checked = true;
			    	} else {
			    		document.querySelector('#allCheck').checked = false;
			    	}
				})
			});
			
			return tr;
		}
		
		// 목록 등록하기
		function addBookList() {
			let id = document.querySelector('#bookIdInput').value;
			let name = document.querySelector('#bookNameInput').value;
			let writer = document.querySelector('#bookWriterInput').value;
			let company = document.querySelector('#bookCompanyInput').value;
			let price = document.querySelector('#bookPriceInput').value;
			
			const addContent = { bookId: id, bookName: name, bookWriter: writer, bookCompany: company, bookPrice: price };
			
			bookObj.bookInsert(addContent, function (data) {
				console.log("------" + data);
				const content = ['bookId', 'bookName', 'bookWriter', 'bookCompany', 'bookPrice'];
				if(data.retCode == 'Success') {
					let tr = makeTr(data.data);
					document.querySelector('#bookList').appendChild(tr);
					
					// input 초기화
					document.querySelector('#bookIdInput').value = null;
					document.querySelector('#bookNameInput').value = null;
					document.querySelector('#bookWriterInput').value = null;
					document.querySelector('#bookCompanyInput').value = null;
					document.querySelector('#bookPriceInput').value = null;
				} else if (data.retCode == 'Fail') {
					alert("처리중 에러");
				} else {
					alert("코드 에러");
				}
			})
			
			/* let tr = document.createElement('tr');
			let td = document.createElement('td');

			
			let checkbox = document.createElement('input');
			checkbox.type = 'checkbox';
			checkbox.className = 'selectCheckbox';
			td.appendChild(checkbox);
			tr.appendChild(td);
			
			content.forEach(value => {
				let td = document.createElement('td');
				td.innerText = value;
				tr.appendChild(td);
			});

			// td.innerText = id;
			// td.innerText = name;
			// td.innerText = writer;
			// td.innerText = company;
			// td.innerText = price;
			
			let bnt = document.createElement('button');
			bnt.innerText = '삭제';
			let tdEnd = document.createElement('td');
			
			tdEnd.appendChild(bnt);
			tr.appendChild(tdEnd);

			document.querySelector('#bookList').appendChild(tr); */
			
			
			// 버튼 클릭시 하나 삭제
			/* bnt.addEventListener('click', function (e) {
				e.target.parentElement.parentElement.remove();
			}) */
		}
		
		// 목록 삭제하기
		function bookDelete(e) {
			let id = e.target.parentElement.parentElement.getAttribute('id');
			console.log(id);
			bookObj.bookDelete(id, function (result) {
				if (result.retCode == 'Success') {
					e.target.parentElement.parentElement.remove();
				} else if (result.retCode == 'Fail') {
					alert("처리중 에러");
				} else {
					alert("잘못된 코드 변환");
				}
			});
		}
		
		// 선택 삭제하기
		function selectBookDelete() {
			let id = [];
			const checkboxes = document.querySelectorAll('.selectCheckbox');
			
			for(const checkbox of checkboxes) {
				if(checkbox.checked){
					id.push(checkbox.parentElement.getAttribute('checkid'));
				}
			}
			console.log(id);
			
			id.forEach(function (idData) {
				bookObj.bookDelete(idData, function (result) {
					console.log(result);
					console.log(result.deleteId);
					if (result.retCode == 'Success') {
						document.getElementById(result.deleteId).remove();
					} else if (result.retCode == 'Fail') {
						alert("처리중 에러");
					} else {
						alert("잘못된 코드 변환");
					}
				});
			});
		}
		
		let checkBoxSelect = document.querySelector('#allCheck');
		
		// 전체선택, 전체해제
		checkBoxSelect.addEventListener('click', function(){
			if (checkBoxSelect.checked){
				const checkboxes = document.querySelectorAll('.selectCheckbox');

		        for(const checkbox of checkboxes){
		            checkbox.checked = true;
		        }			
			} else {
				const checkboxes = document.querySelectorAll('.selectCheckbox');

		        for(const checkbox of checkboxes){
		            checkbox.checked = false;
		        }	
				/* document.querySelectorAll('.selectCheckbox').checked = false; */
			}
		});
		
		// 체크박스 하나 선택시
		
		/* let oneCheck = document.querySelectorAll('.selectCheckbox');
		
		oneCheck.forEach(check => {
			check.addEventListener('click', function(){				
				if(document.querySelectorAll('.selectCheckbox').length == document.querySelectorAll('.selectCheckbox:checked').length){
		    		document.querySelector('#allCheck').checked = true;
		    	} else {
		    		document.querySelector('#allCheck').checked = false;
		    	}
			})
		}); */
		
		/* document.querySelectorAll('.selectCheckbox').on('change', function (e) {
			if(document.querySelectorAll('.selectCheckbox').length == document.querySelectorAll('.selectCheckbox:checked').length){
	    		document.querySelector('#allCheck').checked = true;
	    	} else {
	    		document.querySelector('#allCheck').checked = false;
	    	}
		}) */
	</script>
</body>
</html>