package co.yedam.prjdb.member.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.prjdb.common.ViewResolve;
import co.yedam.prjdb.member.sevice.MemberService;
import co.yedam.prjdb.member.sevice.MemberVO;
import co.yedam.prjdb.member.seviceImpl.MemberServiceImpl;

@WebServlet("/memberlist.do")
public class MemberList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberList() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService dao = new MemberServiceImpl();
//		MemberVO vo = new MemberVO();
		List<MemberVO> member = new ArrayList<MemberVO>();
		member = dao.memberSelectList();
		// 보여줄 페이지에 돌아온 결과를 넘거줄때 setAttribute 사용
		request.setAttribute("members", member);
		
		String page = "member/memberlist";
		ViewResolve.forward(request, response, page);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
