package co.micol.prj.member.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.common.ViewResolve;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.MemberVO;
import co.micol.prj.member.serviceImpl.MemberServiceImpl;

@WebServlet("/memberlist.do")
public class Memberlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Memberlist() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 멤버목록 보기
		MemberService dao = new MemberServiceImpl();
		List<MemberVO> vo = new ArrayList<MemberVO>();
		
		vo = dao.memberSelectList();
		request.setAttribute("vo", vo);
		
		String page = "admin/management/memberlist";
		ViewResolve.forward(request, response, page);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
