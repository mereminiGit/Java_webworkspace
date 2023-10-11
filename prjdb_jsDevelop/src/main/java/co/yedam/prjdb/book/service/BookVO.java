package co.yedam.prjdb.book.service;

import lombok.Data;

@Data
public class BookVO {
	String bookId;
	String bookName;
	String bookWriter;
	String bookCompany;
	int bookPrice;
	int id;
}
