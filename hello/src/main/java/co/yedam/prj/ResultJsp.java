package co.yedam.prj;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/result.do")
public class ResultJsp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ResultJsp() {
        super();
    }

    // 지금은 WEB-INF 밑에 있는 jsp를 어케 보여줄까
    // result.jsp를 보여주기 위해 result.do 서블릿
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 서버에서 동작하니깐 getRequestDispatcher ()안으로 가서 / forward 돌려보내라
		request.getRequestDispatcher("WEB-INF/result.jsp").forward(request, response);
		// -> 뷰 리절브라고 함
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
