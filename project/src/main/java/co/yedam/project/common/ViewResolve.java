package co.yedam.project.common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewResolve {
	// viewName 페이지 명 선택할 jsp 명
	// 넘어온 것의 request, response받고 
	public static void views(HttpServletRequest request, HttpServletResponse response, String viewName) throws ServletException, IOException {
		String jspPage = "WEB-INF/views/" + viewName + ".jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(jspPage);
		dispatcher.forward(request, response);
	}
}
