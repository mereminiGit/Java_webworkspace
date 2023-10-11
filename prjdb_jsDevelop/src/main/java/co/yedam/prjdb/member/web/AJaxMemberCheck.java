package co.yedam.prjdb.member.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.prjdb.member.sevice.MemberService;
import co.yedam.prjdb.member.sevice.MemberVO;
import co.yedam.prjdb.member.seviceImpl.MemberServiceImpl;


@WebServlet("/aJaxmembercheck.do")
public class AJaxMemberCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AJaxMemberCheck() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService dao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setMemberId(request.getParameter("memberId"));
		vo = dao.memberSelect(vo);
		
		String str = "Yes"; // 사용 가능 아이디
		if(vo != null) {
			str = "No"; // 사용 못함
		}
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().append(str);
		return;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
