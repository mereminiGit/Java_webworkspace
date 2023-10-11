package co.yedam.prjdb.book.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import co.yedam.prjdb.book.service.BookService;
import co.yedam.prjdb.book.service.BookVO;
import co.yedam.prjdb.book.serviceImpl.BookServiceImpl;

@WebServlet("/bookInsert.do")
public class BookInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookInsert() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		String bookName = request.getParameter("bookName");
		String bookWriter = request.getParameter("bookWriter");
		String bookCompany = request.getParameter("bookCompany");
		String bookPrice = request.getParameter("bookPrice");
		
		BookVO vo = new BookVO();
		vo.setBookId(bookId);
		vo.setBookName(bookName);
		vo.setBookWriter(bookWriter);
		vo.setBookCompany(bookCompany);
		vo.setBookPrice(Integer.parseInt(bookPrice));
		
		BookService dao = new BookServiceImpl();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(dao.bookInsert(vo) != 0) {
			resultMap.put("retCode", "Success");
			resultMap.put("data", vo);
		} else {
			resultMap.put("retCode", "Fail");
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(resultMap);
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
