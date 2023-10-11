package co.yedam.prjdb.notice.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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


@WebServlet("/AjaxReplyAdd.do")
public class AjaxReplyAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AjaxReplyAdd() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nid = request.getParameter("nid");
		String reply = request.getParameter("content");
		String replyer = request.getParameter("writer");
		
		ReplyVO vo = new ReplyVO();
		vo.setNoticeId(Integer.parseInt(nid));
		vo.setReply(reply);
		vo.setReplyer(replyer);
		
		ReplyService dao = new ReplyServiceImpl();
		
		Map<String, Object> resultMap = new HashMap<>();
		
		if(dao.addReply(vo)) {
			resultMap.put("retCode", "Success");
			resultMap.put("data", vo);
		} else {
			resultMap.put("retCode", "Fail");
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(resultMap);
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(json);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
