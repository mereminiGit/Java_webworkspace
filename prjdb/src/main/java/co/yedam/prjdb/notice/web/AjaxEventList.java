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

import co.yedam.prjdb.notice.sevice.EventService;
import co.yedam.prjdb.notice.sevice.EventVO;
import co.yedam.prjdb.notice.seviceImpl.EventServiceImpl;


@WebServlet("/AjaxEventList.do")
public class AjaxEventList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AjaxEventList() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EventService dao = new EventServiceImpl();
		List<EventVO> vo = dao.selectListEvent();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(vo); // 리스트 형태 데이터가 json형태 데이터로 바뀜
		
		response.setContentType("text/json;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.print(json);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
