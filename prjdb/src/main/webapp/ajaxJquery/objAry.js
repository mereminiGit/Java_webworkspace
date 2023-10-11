//objAry.js

const mem1 = {
  name: "홍길동",
  age: 20,
  phone: '010-1111'
}

const mem2 = {
  name: "김길동",
  age: 23,
  phone: '010-2222'
}

const mem3 = {
  name: "박길동",
  age: 26,
  phone: '010-3333'
}

const members = [mem1, mem2, mem3];

let target = document.querySelector('table#memberList tbody'); // > : 자식 , 공백 : 하위요소
//target.innerHTML = ''; // 기존을 값을 지우겟다 / 초기화

members.forEach(addMemberFnc);  // 배열요소, inx, array

document.querySelector('#memberList>tbody>tr>td:nth-child(4)>button').onclick = function (e) {
  e.target.parentElement.parentElement.remove();
}

document.querySelectorAll('#memberList>tbody>tr').forEach(function (tr) {
  tr.addEventListener('mouseover', function () {
    tr.setAttribute('style', 'background-color: yellow');
  })
  tr.addEventListener('mouseout', function () {
    tr.setAttribute('style', 'background-color: null');
  })

})

// 등록버튼 찾기
document.querySelector('.add').addEventListener('click', function (e) {
  let name = document.querySelector('input[name=name]').value;
  let age = document.querySelector('input[name=age]').value;
  let tel = document.querySelector('input[name=phone]').value;

  const member = {
    name, age, phone:tel
  }

  addMemberFnc(member);

  // let newMember = [name, age, tel];

  // tr = document.createElement('tr');

  // newMember.forEach(function(tmp){
  //   td = document.createElement('td');
  //   td.innerText = tmp;
  //   tr.appendChild(td);
  // })

  // td = document.createElement('td');
  // let bnt = document.createElement('button');
  // bnt.addEventListener('click', function (e) {  
  //   this.parentElement.parentElement.remove();
  // });
  // bnt.innerText = "삭제";
  // td.appendChild(bnt);
  // tr.appendChild(td);
  
  // target.appendChild(tr);

});

// document.querySelectorAll('#memberList>tbody>tr').onmouseout = function(e){
//   this.setAttribute('style', 'background-color: white');
// }


function addMemberFnc (member) {     // members의 요소들을 받아오는 member
  let tr = document.createElement('tr');
  let td = document.createElement('td');
  for (let prop in member) {    // member는 오브젝트 타입이라 속성들을 불러올때는 for in
    td = document.createElement('td');
    td.innerText = member[prop];
    tr.appendChild(td);
  }

  td = document.createElement('td');
  let bnt = document.createElement('button');
  bnt.addEventListener('click', function (e) {  // 앞에는 이벤트 뒤에는 이벤트 발생시 실행할 함수  // e가 이벤트 click이라는 이벤트를 메게값으로 전달 받음
    // e.target.parentElement.parentElement.remove();

    //this - 이벤트를 받는 대상을 가르킴 (이벤트 안에서)
    this.parentElement.parentElement.remove();
  });
  bnt.innerText = '삭제';
  td.appendChild(bnt);
  tr.appendChild(td);

  target.appendChild(tr);
}