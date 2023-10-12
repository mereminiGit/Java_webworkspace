// reduce_2.js
// 비동기방식. fetch();

// 비동기방식을 동기방식으로 
async function asyncFunc() {
	let memAry = [];

	let promise = await fetch('./MOCK_DATA.json');
	// 결과를 다 받아오고 나서 처리하겠다 -> async에 await 앞에 붙이면 됨
	let json = await promise.json();

	// 동기방식처럼 처리할 수 있다.
	console.log(json);

	memAry = json;
	console.log(memAry);

	// reduce 사용해서
	memAry.reduce((acc, member, idx, ary) => {
		if (idx == 0) {
			$('#list').append($('<ul />'));
		}

		if (idx < 5) {
			$('#list ul').append($('<li />').text(`ID: ${member.id}, First_name: ${member.first_name}`));
			console.log(acc, member);
		}

		return acc;

	}, "");

	memAry.reduce((acc, member, idx, ary) => {
		if (idx + 1 == ary.length) {
			//document.querySelector('#list').append('"'+acc+'"');
			$('#list').append(acc);
		}

		let str = '';
		if (idx == 0) {
			str += '<ul>';
		}
		if (idx == 5) {
			str += '</ul>';
		}
		str += '<li> id: ' + member.id + ' 이름: ' + member.first_name + '</li>';
		return idx < 5 ? acc + str : acc;
	}, "");

	memAry.reduce((acc, member, idx, ary) => {
		if (idx == 0) {
			let ul = document.createElement('ul');
			acc.append(ul);
		}
		let li = document.createElement('li');
		li.innerHTML = 'id: ' + member.id + ', 이름: ' + member.first_name;
		if (member.gender == 'Male') {
			li.style.background = "lightblue";
		} else if (member.gender == 'Female') {
			li.style.background = "lightpink";
		}
		document.querySelector('ul:last-child').append(li);

		// if (idx < 5) {
		//     document.querySelector('ul:last-child').append(li);
		// }

		return acc;
	}, document.querySelector('#list'));
}

asyncFunc();