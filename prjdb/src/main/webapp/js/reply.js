/**
 * reply.js
 */
console.log("-----");

class Reply {
	showInfo() {
		console.log('asdf');
	}

	// 목록조회 기능.
	replyList(noticeId, callback, errorcall) {		// id값 하나, callback은 함수
		/*let xhttp = new XMLHttpRequest();	// ajax 비동기 방식으로 요청하는 
		xhttp.open('get', 'AjaxReplyList.do?nid=' + noticeId);
		xhttp.send();	// 실제 서버에 요청
		xhttp.onload = function(e) {
			console.log(xhttp.responseText);
			let data = JSON.parse(xhttp.responseText); // data에 가져온 결과값을 담는다 - 배열
			console.log(data);
			callback(data);	// data 던저주면 처리하는 함수
		}*/
		
		// 정상적으로 되면 then메소드, 에러발생 catch메소드 // promise 객체: 처리중, 완료, 실패 
		fetch('AjaxReplyList.do?nid=' + noticeId)
		.then( function (resolve){ // 처리된 결과값을 함수 매개값으로 담아줌(지금은 json)
			console.log(resolve);
			return resolve.json(); // 문자값 -> 객체. json 포멧으로 바꾸고
		}) // 화살표 함수로 처리해도 됨(function, 한줄일 때 {}생략, return 생략 가능)
		.then(result => callback(result)) // 위의 함수가 실행되면 처리된 값을 가지고 함수
		.catch(err => errorcall(err));
	}
	// 댓글삭제 기능
	replyRemove(replyId, callback, errorcall) {
		/*let xhttp = new XMLHttpRequest();	// ajax 비동기 방식으로 요청하는 
		xhttp.open('get', 'AjaxReplyDelete.do?rid=' + replyId);
		xhttp.send();	
		xhttp.onload = function(e) {
			//console.log(xhttp.responseText);
			let data = JSON.parse(xhttp.responseText); // data에 가져온 결과값을 담는다 - 배열
			//console.log(data);
			callback(data);	// data 던저주면 처리하는 함수
		}*/
		
		fetch('AjaxReplyDelete.do?rid=' + replyId)
		.then(resolve => resolve.json())
		.then(result => callback(result))
		.catch(err => errorcall(err));
		
	}
	// 댓글등록 기능
	replyAdd(reply={nid:1, replyer:'user1', reply:'test'}, callback ){
		/*let xhttp = new XMLHttpRequest();
		xhttp.open('post', 'AjaxReplyAdd.do');
		xhttp.setRequestHeader('content-Type', 'application/x-www-form-urlencoded');	// content-type 지정해줘야함
		xhttp.send('nid=' + reply.nid+'&content=' + reply.reply +'&writer=' + reply.replyer);
		xhttp.onload = function(e){
			let data = JSON.parse(xhttp.responseText);
			callback(data);
		}*/
		
		fetch('AjaxReplyAdd.do', {
			method: "POST",
			headers: {'content-Type': 'application/x-www-form-urlencoded'},
			body: 'nid=' + reply.nid+'&content=' + reply.reply +'&writer=' + reply.replyer
		})
		.then(resolve => resolve.json())
		.then(result => callback(result))
		.catch(console.log);
	}
	
	// 댓글 한건 조회 기능.
	replySearch(replyId, callback) {
		/*let xhttp = new XMLHttpRequest();
		xhttp.open('get', 'AjaxReplySearch.do?rid=' + replyId);
		xhttp.send();
		xhttp.onload = function(e){
			let data = JSON.parse(xhttp.responseText);
			callback(data);
		}*/
		
		fetch('AjaxReplySearch.do?rid=' + replyId)
		.then(resolve => resolve.json())
		.then(result => callback(result))
		.catch(console.log);
	}
	
	// 댓글수정 기능.
	replyModify(reply={rid:10, reply:"변경하겠습니다."}, callback) {
		/*let xhttp = new XMLHttpRequest();
		xhttp.open('post', 'AjaxReplyModify.do');
		xhttp.setRequestHeader('content-Type', 'application/x-www-form-urlencoded');
		xhttp.send('rid=' + reply.rid + '&content=' + reply.reply);
		xhttp.onload = function(e){
			let data = JSON.parse(xhttp.responseText);
			callback(data);
		}*/
		
		fetch('AjaxReplyModify.do', {
			method: "POST",
			headers: {'content-Type': 'application/x-www-form-urlencoded'},
			body: 'rid=' + reply.rid + '&content=' + reply.reply
		})
		.then(resolve => resolve.json())
		.then(result => callback(result))
		.catch(console.log);
	}
	
	// 숫자 => 년월일 시분초로 바꿔줌
	displayDate(millis) {
		// 2023-09-05 13:33:11
		let today = new Date(millis);
		let yyyy = today.getFullYear();
		let mm = ('0' + (today.getMonth() + 1)).slice(-2); // 뒤에서 2개 글자 들고오겠다 slice
		let dd = ('0' + today.getDay()).slice(-2);
		let hh = ('0' + today.getHours()).slice(-2);
		let mi = ('0' + today.getMinutes()).slice(-2);
		let ss = ('0' + today.getSeconds()).slice(-2);

		return yyyy + "-" + mm + "-" + dd + " " + hh + ":" + mi + ":" + ss;
	}
}