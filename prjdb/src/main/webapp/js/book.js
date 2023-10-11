/**
 *  book.js
 */

class Book {
	
	// 목록 조회 기능.
	bookList(callback, errorcall) {
		fetch('bookList.do')
		.then(function (resolve) {
			/*console.log(resolve);*/
			return resolve.json();
		})
		.then(result => callback(result))
		.catch(err => errorcall(err));
	}
	
	// 등록 기능
	bookInsert(book = {bookId: 'bookId', bookName: 'bookName', bookWriter: 'bookWriter', bookCompany: 'bookCompany', bookPrice: 'bookPrice'}, callback ){
		fetch('bookInsert.do', {
			method: "POST",
			headers: {'content-Type': 'application/x-www-form-urlencoded'},
			body: 'bookId=' + book.bookId + '&bookName=' + book.bookName + '&bookWriter=' + book.bookWriter + '&bookCompany=' + book.bookCompany + '&bookPrice=' + book.bookPrice
		})
		.then(resolve => resolve.json())
		.then(result => callback(result))
		.catch(console.log('err'));
	}
	
	// 삭제 기능
	bookDelete(id, callback) {
		fetch('bookDelete.do?id=' + id)
		.then(resolve => resolve.json())
		.then(result => callback(result))
		.catch(console.log('err'));
	}
}