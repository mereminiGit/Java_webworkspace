// cart.js 의 시작부분.
console.log('cart.js');

// Intl 객체를 사용하여 포맷 지정.
function number_format(amount) {
	return new Intl.NumberFormat('ko-KR', {
		style: 'currency',
		currency: 'KRW'
	}).format(amount);
}

// prototype에 정의해서 메소드 추가: 숫자 3자리 콤마찍기
Number.prototype.formatNumber = function () {
	if (this == 0) return 0;
	let regex = /(^[+-]?\d+)(\d{3})/;
	let nstr = (this + '');
	while (regex.test(nstr)) nstr = nstr.replace(regex, '$1' + ',' + '$2');
	return nstr;
};

// 1,000,000 => 1000000
//console.log('1,000,000'.replace(/,/g, ''));

let basket = {
	// 속성
	cartCount: 0,
	cartTotal: 0,
	
	// 메소드
	delCheckedItem: function () {
	// 선택된 상품을 삭제....금액을 재계산.
	// 선택 상품 삭제
		let selectProduct = document.querySelectorAll('#checkItem');
		
		selectProduct.forEach(checkbox => {
			if(checkbox.checked){
				console.log(checkbox.parentElement.parentElement.parentElement);
				checkbox.parentElement.parentElement.parentElement.remove();
			}
		});
	},
	delAllItem: function () {
	// 장바구니 비우기 하면 실행되도록..
	// 장바구니 전체 삭제
		let allProduct = document.querySelectorAll('div.row.data');
		
		allProduct.forEach((item) => {
			/*console.log(idx);*/
			item.remove();
		});
	},
	reCalc: function () {
	// 금액을 재계산.
	/*let priceTotal = document.querySelectorAll('#sumPrint');
	priceTotal.forEach()*/
	},
	updateUI: function () {
	// 화면을 refresh.
	let selectProduct = document.querySelectorAll('#checkItem');
	selectProduct.forEach(checkbox => {
		checkbox.checked = true;
	});	
	
	},
	changePNum: function () {
	// 수량변경.
		
		// 총 상품들의 수량
		let count = document.querySelectorAll('#p_num1');
		/*console.log(count);*/
		// 초기화
		this.cartCount = 0;
		count.forEach(eachCount => {
			/*console.log(eachCount.getAttribute('value'));*/
			this.cartCount += Number(eachCount.getAttribute('value'));
		});
		
		document.querySelector('#sum_p_num').innerText = `상품갯수: ${this.cartCount}개`;
		
		// 총 상품들의 가격합계
		let temp = document.querySelectorAll('#sumPrint');
		this.cartTotal = 0;
		temp.forEach(price => {
			this.cartTotal = this.cartTotal + Number(price.getAttribute('value'));
		});
		
		document.querySelector('#sum_p_price').innerText = `합계금액: ${(this.cartTotal).formatNumber()}원`;
	},
	delItem: function () {
	// 삭제버튼 클릭시.
	},
	cartList: function () {
	// 상품목록 출력...아래에 있는 상품정보를 활용해서 수량만큼 출력이 되도록.
		cartItems.forEach((item, idx) => {
			console.log(item.image);
			
			// 이미지 넣기
			document.querySelector('.img img').src = `./img/${item.image}`;
			
			// 이름 넣기
			document.querySelector('.pname span').innerHTML = `${item.productNm}`;
			
			// 가격 넣기
			/*let pricetmp = (item.price).formatNumber();
			console.log(pricetmp);*/
			document.querySelector('#basketPrintPrice').innerText = `${(item.price).formatNumber()}원`;
			document.querySelector('#basketPrintPrice').setAttribute('value', `${item.price}`);
			
			// 총합 넣기
			let price = item.price;
			let count = document.querySelector('#p_num1').value;
			let totalPrice = price * count;
			document.querySelector('#sumPrint').innerText = `${(totalPrice).formatNumber()}원`;
			document.querySelector('#sumPrint').setAttribute('value', `${totalPrice}`);
			
			let template = document.querySelector('div.row.data').cloneNode(true);
			document.querySelector('#basket').append(template);
		});
		
		let temp = document.querySelectorAll('div.row.data');
		console.log(temp);
		temp.forEach((item, idx) => {
			/*console.log(idx);*/
			if(idx == 0){
				/*item.style.display = 'none';*/
				item.remove();
			}
		});

	}
};

var cartItems = [{
		no: 1,
		productNm: 'Samba OG White',
		qty: 2,
		price: 15000,
		image: 'Samba1.png'
	},
	{
		no: 2,
		productNm: 'Samba OG Black',
		qty: 1,
		price: 25000,
		image: 'Samba2.png'
	},
	{
		no: 3,
		productNm: 'Samba OG Navy',
		qty: 1,
		price: 35000,
		image: 'Samba3.png'
	}
]

basket.cartList();
basket.changePNum();

// 1. db를 연결해서 사용하려면 아래의 내용으로 작업을 하면 됨.
//fetch('cartSelectList')
//	.then(resolve => resolve.json())
//	.then(result => {
//		//console.log(result);
//		basket.cartList();
//	})
//	.catch(err => console.log(err))

