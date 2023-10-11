/*
	obj.js
*/
let name = "Hong";  // String
let age = 20; // number

let obj = {		// {} 사용하는게 js에서 객체
	name,	// (key : value)
	age: age,	// (key:value) 값이 이름이 같으면 하나만 써도됨
	phone: '010-1111',
	showInfo: function () {
		return this.name + ", " + this.age;		// 메소드.. name과 age를 반환해주는
	}
}

console.log(obj.name);
console.log(obj.age);
console.log(obj['phone']);
console.log(obj.showInfo());
obj.hobbies = ['reading', 'eating', 'sleeping']; // 선언하는것과 같다
console.log(obj.hobbies[0]);

for (let prop in obj) {
	console.log(prop);	// 속성 키 값들을 가져옴
	console.log(`속성: ${prop}, 값: ${obj[prop]}`);
}

// ------------------------------------------------------------- 

let yourHobbies = ['운동하기', '영화보기', '자전거타기'];

function makeHobbies(hobbies = []) {
	// code here.
	/*txt = document.createTextNode(obj.hobbies[0]);*/
	for (let value of hobbies) {
		li = document.createElement('li');
		document.getElementById('myHobby').appendChild(li);
		txt = document.createTextNode(value);
		li.appendChild(txt);
	}
}

// 함수를 실행하려면 콜을 해줘야함
makeHobbies(yourHobbies);







