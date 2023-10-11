package co.yedam.prjdb.common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewResolve {
	public static void forward(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
		// request, response, page를 받는다 servlet에서 여기로 와서 뷰로 넘어가는 역활
		String prefix = "WEB-INF/views/";
		String suffix = ".jsp";
		String viewPage = prefix + page +suffix;
		
		// 인터페이스라서
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		// html으로 처리
		dispatcher.forward(request, response);
	}
}
