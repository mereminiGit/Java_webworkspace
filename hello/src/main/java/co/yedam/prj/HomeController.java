// 서블릿을 이용한 JSP

package co.yedam.prj;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/home.do")  // 브라우저에서 home.do라고 요청하면 아래 클래스가 동작함
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     *  home.do로 들어오면 해당 영역 실행
     */
    public HomeController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get 방식으로 요청하면 실행되는 메소드
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().append("<h1>나의 이름은 : ")
							.append("홍길동 입니다.</h1>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post 방식으로 요청하면 실행되는 메소드
		doGet(request, response);
	}

}
