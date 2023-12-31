package co.yedam.prjdb.notice.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.prjdb.common.ViewResolve;
import co.yedam.prjdb.notice.sevice.NoticeService;
import co.yedam.prjdb.notice.sevice.NoticeVO;
import co.yedam.prjdb.notice.seviceImpl.NoticeServiceImpl;


@WebServlet("/noticeselectlist.do")
public class NoticeSelectList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NoticeSelectList() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeService dao = new NoticeServiceImpl();
		List<NoticeVO> notices = new ArrayList<>();
		
		notices = dao.noticeSelectList();
		request.setAttribute("notices", notices);
		
		String page = "notice/noticeselectlist";
		ViewResolve.forward(request, response, page);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
