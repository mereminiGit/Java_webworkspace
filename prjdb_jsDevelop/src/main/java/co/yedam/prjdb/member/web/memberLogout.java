package co.yedam.prjdb.member.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.yedam.prjdb.common.ViewResolve;


@WebServlet("/memberlogout.do")
public class memberLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public memberLogout() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String name = (String) session.getAttribute("name");
		session.invalidate(); // 세션 정보를 완전히 삭제한다.
		request.setAttribute("message", name + "님 로그아웃이 처리되었습니다.");
		String page = "member/membermessage";
		ViewResolve.forward(request, response, page);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
