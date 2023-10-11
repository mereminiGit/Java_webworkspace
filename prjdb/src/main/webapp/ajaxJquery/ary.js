// ary.js

// 상수 배열
const myNumbers = [20, 30, 45, 66, 88, 22];
let sum = 0;


for (let i = 0; i < myNumbers.length; i++) {
  console.log(myNumbers[i]);
  sum += myNumbers[i];
}

sum = 0;
for (let num of myNumbers) {
  sum += num;
}
console.log(sum);

sum = 0;
myNumbers.forEach(function (ele, idx) {
  console.log(`index: ${idx}, value: ${ele}`);
  sum += ele;
});

let fruits = ['Apple', 'Banana', 'Cherry'];
document.querySelector('.fruits').innerHTML = '';

fruits.forEach(function (ele) {
  li = document.createElement('li');
  txt = document.createTextNode(ele);
  let bnt = document.createElement('button');
  bnt.onclick = function () {
    // alert('버튼 선택');
    console.log(bnt);
    bnt.parentElement.remove();   // 버튼의 parentElement는 부모요소 = li
  }
  // let txtbtn = document.createTextNode('Delete');
  bnt.innerText = 'Delete';   // 버튼 태그 안에 넣는 텍스트라서

  li.appendChild(txt);

  // bnt.appendChild(txtbtn);
  li.appendChild(bnt);

  document.querySelector('.fruits').appendChild(li);
})