package co.yedam.prjdb.notice.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.prjdb.notice.sevice.ReplyService;
import co.yedam.prjdb.notice.seviceImpl.ReplyServiceImpl;


@WebServlet("/AjaxReplyDelete.do")
public class AjaxReplyDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AjaxReplyDelete() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String replyId = request.getParameter("rid");
		
		ReplyService dao = new ReplyServiceImpl();
		if (dao.deleteReply(Integer.parseInt(replyId))) {
			// {"retCode": "Success"}
			response.getWriter().print("{\"retCode\": \"Success\"}");
		} else {
			// {"retCode": "Fail"}
			response.getWriter().print("{\"retCode\": \"Fail\"}");
		}

		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
