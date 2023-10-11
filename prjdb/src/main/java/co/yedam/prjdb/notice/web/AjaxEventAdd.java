package co.yedam.prjdb.notice.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.prjdb.notice.sevice.EventService;
import co.yedam.prjdb.notice.sevice.EventVO;
import co.yedam.prjdb.notice.seviceImpl.EventServiceImpl;


@WebServlet("/AjaxEventAdd")
public class AjaxEventAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AjaxEventAdd() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		EventVO vo = new EventVO();
		vo.setTitle(title);
		vo.setStartDate(startDate);
		vo.setEndDate(endDate);
		
		EventService dao = new EventServiceImpl();
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
