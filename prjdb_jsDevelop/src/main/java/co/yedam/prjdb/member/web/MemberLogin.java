package co.yedam.prjdb.member.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.yedam.prjdb.common.SHA256;
import co.yedam.prjdb.common.ViewResolve;
import co.yedam.prjdb.member.sevice.MemberService;
import co.yedam.prjdb.member.sevice.MemberVO;
import co.yedam.prjdb.member.seviceImpl.MemberServiceImpl;


@WebServlet("/memberlogin.do")
public class MemberLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberLogin() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService dao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		HttpSession session = request.getSession(); // 세션객체를 호출한다. true - 세션이 존재하지 않으면 만들어줘라
		
		// jsp 파일에 있는 name 변수 memberId
		vo.setMemberId(request.getParameter("memberId"));
		vo.setMemberPassword(SHA256.encrypt(request.getParameter("memberPassword")));
//		System.out.println(SHA256.encrypt("1234"));
		
		vo = dao.memberSelect(vo);
		if(vo != null) {
			// 여기서 session 객체에 필요한 데이터 담아준다.
			session.setAttribute("id", vo.getMemberId());
			session.setAttribute("name", vo.getMemberName());
			
			request.setAttribute("message", vo.getMemberName() + "님 환영합니다.");
		}else {
			request.setAttribute("message", "아이디 또는 패스워드가 일치하지 않습니다.");
		}
		
		String page = "member/membermessage";
		ViewResolve.forward(request, response, page);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
