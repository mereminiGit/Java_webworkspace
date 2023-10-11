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
import co.yedam.prjdb.book.serviceImpl.BookServiceImpl;

@WebServlet("/bookDelete.do")
public class BookDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		BookService dao = new BookServiceImpl();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(dao.bookDelete(Integer.parseInt(id)) != 0) {
			/*
			 * response.getWriter().print("[" + "{\"retCode\": \"Success\"}");
			 * response.getWriter().print(", {\"deleteId\": " + "\"" + id + "\"" + "}" +
			 * "]");
			 */
			resultMap.put("retCode", "Success");
			resultMap.put("deleteId", id);
		} else {
			/* response.getWriter().print("{\"retCode\": \"Fail\"}"); */
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
