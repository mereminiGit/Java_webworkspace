<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="css/todoList.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
	</head>

	<body>
		<div id="myDIV" class="header">
			<h2>My To Do List</h2>
			<input type="text" id="myInput" placeholder="Title..." onkeyup="if(window.event.keyCode == 13){newElement()}">
			<input type="date" id="myInput2" placeholder="Due Date..." onkeyup="if(window.event.keyCode == 13){newElement()}">
			<span onclick="newElement()" class="addBtn">Add</span>
		</div>

		<ul id="myUL">
			<li>Hit the gym (Due: always)</li>
			<li class="checked">Pay bills (Due: always)</li>
			<li>Meet George (Due: always)</li>
			<li>Buy eggs (Due: always)</li>
			<li>Read a book (Due: always)</li>
			<li>Organize office (Due: always)</li>
		</ul>

		<script type="text/javascript">
		
			// close (X) 버튼 각 li에 달기
			// Create a "close" button and append it to each list item
			let mylist = $('li'); 
			//var myNodelist = document.getElementsByTagName("LI");
			console.log(mylist);
			
				
			for(let list of mylist) {
				//console.log(list);
				$('<span />').text('\u00D7').attr('class', 'close')
							 .appendTo(list);
			}
			numbering();
			
			// 넘버링
			function numbering(){
				let number = 1;
				let fullList = $('li');
				
				for(let list of fullList) {
					$('<span />').text((number++)+'. ').attr('class', 'num').appendTo(list);
				}
			}
			
			// 넘버링 초기화
			function numberingDel(){
				let fullList = $('li');
				for(let list of fullList) {
					$('.num').remove();
				}
			}
			
			// X클릭하면 사라지게
			// Click on a close button to hide the current list item
			$('#myUL').on('click', 'span',function (e) {
				//console.log(e.currentTarget);
				$(e.currentTarget).parent().remove();
				numberingDel();
				numbering();
			})

			// 체크 모양 표시
			// Add a "checked" symbol when clicking on a list item
			$('#myUL').on('click', 'li',function (e) {	
				// 이벤트 나중처리 위해 DOM에 먼저 있는 요소를 앞에 넣고 위임하는건 나중에 생기는거
				console.log(e.currentTarget.className);
				if ($(e.currentTarget).hasClass("checked")){
					$(e.currentTarget).removeAttr('class');
				} else {
					$(e.currentTarget).attr('class', 'checked');
				}
			})

			// 
			// Create a new list item when clicking on the "Add" button
			function newElement() {
				let title = $('input[id=myInput]').val();
				let dueDate = $('input[id=myInput2]').val();
				console.log(dueDate);
				let li = $('<li />');
				if (title === ''){
					alert("내용을 입력하세요!");
				} else {
					if (dueDate === ''){
						li.text(title + " (Due: TBD)").appendTo($('ul'));
					} else {
						li.text(title + " (Due: " + dueDate + ")").appendTo($('ul'));
					}
					numberingDel();
					numbering();
				}

				 $('<span />').text('\u00D7').attr('class', 'close')
				 			 .appendTo(li);

				$('input[id=myInput]').val('');
				$('input[id=myInput2]').val('');
			}
		</script>
	</body>

	</html>