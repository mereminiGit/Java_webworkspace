package co.yedam.prjdb.notice.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import co.yedam.prjdb.notice.sevice.NoticeService;
import co.yedam.prjdb.notice.sevice.NoticeVO;
import co.yedam.prjdb.notice.seviceImpl.NoticeServiceImpl;


@WebServlet("/ajaxNoticeSearch.do")
public class AjaxNoticeSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AjaxNoticeSearch() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeService dao = new NoticeServiceImpl();
		List<NoticeVO> notices = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper(); // json 형태의 데이터로 반환
		
		String key = request.getParameter("key");
		String val = request.getParameter("val");
		System.out.println(key +"===================");
		System.out.println(val +"===================");
		
		notices = dao.noticeSelectList(key, val);
		
		String list = objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(notices); // 리스트 형태 데이터가 json형태 데이터로 바뀜
		// java 8부터는 date다룰때 localdate, localdatetilme를 사용 권장
		// 라이브러리에 위를 충족하기 위해서 jsr310라이브러리도 넣음
		// registerModule(new JavaTimeModule()) - 요구사항 310을 충족시키기 위해서
		// String 형태의 json객체로 담음
		
		response.setContentType("text/html; charset=UTF-8"); // 한글깨짐 방지 / html폼 데어터라는 인식
		response.getWriter().append(list);
		return;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
