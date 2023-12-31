package co.yedam.prjdb.book.service;

import java.util.List;

public interface BookService {
	List<BookVO> bookSelectList();
	BookVO bookSelect(BookVO vo);
	int bookUpdate(BookVO vo);
	int bookInsert(BookVO vo);
	int bookDelete(int id);
}
