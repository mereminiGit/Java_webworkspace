//

let memberAry = [];
fetch('./MOCK_DATA.json')
.then(resolve => resolve.json()) 
.then(result => {
    //console.log(result);
    memberAry = result;
    //console.log(memberAry);

    let str = "";
    memberAry.forEach((element, idx) => {
        if (idx == 0) {
            str += '<ul>';
        }

        if (idx < 5){
            str += '<li>id: ' + element.id + ', 이름: ' + element.first_name + '</li>';
        }

        if (idx + 1 == memberAry.length){
            str += '</ul>';
        }
    });

    //document.querySelector('#list').innerHTML = str;

    let ary5 = memberAry.filter((member, idx, ary) => {
        return idx < 5;
    });
    //console.log(ary5);

    ary5.reduce((acc, member, idx, ary) => {
        console.log(acc, member, idx, ary);
        return member;  // acc는 전에 return 값
    }, 0);

    result = [0, 1, 2, 3, 4].reduce(function(acc, num, idx) {
        console.log(acc, num, idx);
        return acc > num ? acc : num; // 배열 중에 가장 큰 값 구할때
        //num + acc; // 배열안에 있는 누적값을 구할때 유용
    }, 0); // 0은 초기값을 의미함
    
    console.log(result); 

    result = [3, 2, 6, 9, 5].reduce(function(acc, num, idx, ary) {
        console.log(acc, num, idx);
        if (idx + 1 == ary.length) {
            return (acc + num) / (idx+1);
        }
        return acc + num;
    }, 0); // 0은 초기값을 의미함
    console.log(result);

    $('#list').append($('<ul />').append($('<li />').text(`평균: ${result}`)))
});