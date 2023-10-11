package co.yedam.prjdb.notice.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import co.yedam.prjdb.notice.sevice.ReplyService;
import co.yedam.prjdb.notice.sevice.ReplyVO;
import co.yedam.prjdb.notice.seviceImpl.ReplyServiceImpl;


@WebServlet("/AjaxReplyList.do")
public class AjaxReplyList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxReplyList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nid = request.getParameter("nid");
		// http://localhost/prjdb/AjaxReplyList.do?nid=2
	
		ReplyService dao = new ReplyServiceImpl();
		List<ReplyVO> vo = dao.listReply(Integer.valueOf(nid));
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(vo); // 리스트 형태 데이터가 json형태 데이터로 바뀜
		
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
