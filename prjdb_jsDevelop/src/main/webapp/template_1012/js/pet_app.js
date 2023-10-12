
// pet_app js 시작

// json pet 데이터 배열에 넣기
const petData = [];

App = {
	// 입양된 id 값 넣는 배열
	adoptedAry: [],
	
	init: function() {
	// adopted.json 파일의 정보를 읽어서 애완견의 정보를 활용해서 페이지 생성.
	
		// pet json 파일 가져와서 배열에 넣어주기
		fetch("./json/pets.json")
		.then(resolve => resolve.json())
		.then(json => {
			console.log(json.length);
			
			for (let i = 0; i < json.length; i++){
				petData.push(json[i]);
			}
			console.log(petData);
		
			petData.forEach(item => {
				//petName 넣기
				$('#petTitle').text(item.name);
				/*console.log(item.name);*/
				
				// petimg 넣기
				$('#petImg').attr('src', `${item.picture}`);
				
				// petBreed 넣기
				$('#petBreed').text(item.breed);
				
				// petAge 넣기
				$('#petAge').text(item.age);
				
				// pet location 넣기
				$('#petLoc').text(item.location);
				
				// id 값 넣기 버튼의 data-id에
				$('#petBtn').attr('data-id', `${item.id}`);
				
				// div 클론하기
				let template = $('#petList').clone(true);
				$('#petsRow').append(template);
				
			});
		
			$('#petList').remove();
			/*let temp = $('#petList');
			console.log(temp);
			temp.forEach((item, idx) => {
				if (idx == 0){
					item.remove();
				}
			});*/
			
			App.initMarkData();
			
		});
	
	}, // end of init;

	initContract: function() {
	// initMarkData 호출.
	// bindEvents 호출.
	
	}, // end of initContract;

	bindEvents: function() {
		// 입양버튼에 이벤트 등록.	
		$('.btn.btn-default.btn-adopt').each(function(idx, item) {
			/*console.log($(item).attr('data-id'));*/
			App.adoptedAry.forEach(id => {
				if (id == $(item).attr('data-id')){
					$(item).attr("disabled", true);
				}
			});
		});
		
	},

	initMarkData: function() {
		// adopted.json 정보를 활용해서 입양처리하기.
		
		// 이미 입양된 것 버튼 비활성화
		$.ajax({
			url: './json/adopted.json',
			method: 'get',
			success: function (result) {
				//console.log(result);
				/*console.log(App.adoptedAry);*/
				result.forEach(id => {
					/*console.log(id);*/
					App.adoptedAry.push(id);
				});
				console.log(App.adoptedAry);
				App.bindEvents();
				
			},
			error: function () {
				console.log('err');
			}
		});
		
	},

	markAdopted: function() {
		// 입양처리. adoptedAry에 추가.
		
	}, // end of markAdopted;

	handleAdopt: function(event) {
		/*// 배열 초기화
		App.adoptedAry = [];*/
		
		// 사용자화면에서 입양버튼 클릭 시 처리.
		console.log(event.target);
		App.adoptedAry.push(Number($(event.target).attr('data-id')));
		console.log(App.adoptedAry);
		
		App.bindEvents();
	} // end of handleAdopt;

}; // end of App;


$(function() {
	App.init();
});
