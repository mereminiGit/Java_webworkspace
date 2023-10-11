package co.micol.prj.member.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.micol.prj.common.SHA256;
import co.micol.prj.common.ViewResolve;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.MemberVO;
import co.micol.prj.member.serviceImpl.MemberServiceImpl;


@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 처리
		HttpSession session = request.getSession();
		
		MemberService dao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setMemberId(request.getParameter("memberId"));
		vo.setMemberPassword(SHA256.encrypt(request.getParameter("memberPassword")));
		
		vo = dao.memberSelect(vo);
		String page = null;
		
		if(vo != null) {
			session.setAttribute("id", vo.getMemberId());
			session.setAttribute("check", vo.getMemberCheck());
			
			if(vo.getMemberCheck().equals("ADMIN")) {
				page = "adminhome.do";
			} else {
				page = "home.do";
			}
			response.sendRedirect(page);
		} else {
			request.setAttribute("message", "아이디 패스워드가 틀림");
			String path = "member/membermessage.jsp";
			ViewResolve.forward(request, response, path);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
