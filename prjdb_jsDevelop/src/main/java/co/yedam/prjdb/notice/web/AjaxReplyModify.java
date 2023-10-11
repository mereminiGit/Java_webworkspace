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

@WebServlet("/AjaxReplyModify.do")
public class AjaxReplyModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AjaxReplyModify() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rid = request.getParameter("rid");
		String reply = request.getParameter("content");
		
		ReplyVO vo = new ReplyVO();
		vo.setReplyId(Integer.parseInt(rid));
		vo.setReply(reply);
		
		ReplyService dao = new ReplyServiceImpl();
		Map<String, Object> resultMap = new HashMap<>();
		
		if (dao.editReply(vo)) {
			vo = dao.getRely(Integer.parseInt(rid));
			
			resultMap.put("retCode", "Success");
			resultMap.put("data", vo);
		} else {
			resultMap.put("retCode", "Fail");
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(resultMap);
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().append(json);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
