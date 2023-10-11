/**
 * dom.js
 */

let ul = document.createElement('ul'); // DOM
let li = document.createElement('li'); // DOM
li.setAttribute('class', 'second');
let txt = document.createTextNode('Apple');
li.appendChild(txt); //li하위 요소에 txt를 li태그로 붙이겟다
ul.appendChild(li);

li = document.createElement('li'); // DOM
txt = document.createTextNode('Banana');
li.appendChild(txt); //
ul.appendChild(li); // li태그를 ul태그 하위에 넣는다


console.log(ul);



document.getElementById('clone').appendChild(ul); 
		// id값으로 clone이라는 값을 찾아 거기 하위에 넣는것
		// 여기에서 getElementById('clone') - div태그 / appendChild - 하위요소로 넣는