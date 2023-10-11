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

@WebServlet("/AjaxReplyList2_0911.do")
public class AjaxReplyList2_0911 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjaxReplyList2_0911() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nid = request.getParameter("nid");
		// 추가.
		String param = request.getParameter("param");

		ReplyService dao = new ReplyServiceImpl();
		List<ReplyVO> vo = dao.listReply(Integer.valueOf(nid));

		if (param == null) {

			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(vo); // 리스트 형태 데이터가
																									// json형태 데이터로 바뀜

			response.setContentType("text/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(json);
		} else {
			// {"data": [ ["값], [], .... ] } 이런 형태
			String json = "{\"data\": [";
			int count = 0;
			
			for (ReplyVO rVO : vo) {
				json += "[" 
						+ "\"" + rVO.getReplyId() + "\","
						+ "\"" + rVO.getReply() + "\"," 
						+ "\"" + rVO.getReplyer() + "\"," 
						+ "\"" + rVO.getReplyDate() + "\"" 
						+ "]";
				if( ++count != vo.size()) {	// 마지막 데이터 , 빼기
					json += ",";
				}
			}
			
			json += "]}";
			
			response.setContentType("text/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(json);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
