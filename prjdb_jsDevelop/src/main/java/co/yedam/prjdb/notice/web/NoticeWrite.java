package co.yedam.prjdb.notice.web;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.yedam.prjdb.common.ThumbNail;
import co.yedam.prjdb.common.ViewResolve;
import co.yedam.prjdb.notice.sevice.NoticeService;
import co.yedam.prjdb.notice.sevice.NoticeVO;
import co.yedam.prjdb.notice.seviceImpl.NoticeServiceImpl;


@WebServlet("/noticewrite.do")
public class NoticeWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NoticeWrite() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일 업로드 처리(notice)
		// 어디에 저장할껀지 폴더 잇어야함
		ThumbNail thumbNail = new ThumbNail();  // 썸네일 class
		NoticeService dao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		
		// 저장할 공간
		String saveDir = getServletContext().getRealPath("attech/notice");
		// ServletContext - 프로젝트가 context가 된다. / RealPath - 실제 패스
		
		// 첫번째가 k 두번째 m메가 
		int maxSize = 1024 * 1024 * 100; // 100M byte 최대 사이즈
		
		MultipartRequest multi = new MultipartRequest(request, saveDir, maxSize, "utf-8", new DefaultFileRenamePolicy());
		// DefaultFileRenamePolicy - 파일명이 동일할 때 파일명(1), 파일명(2) 해주는것
		
		String imgFileName = multi.getOriginalFileName("imgfile"); // 원본파일명
		String realImg = multi.getFilesystemName("imgfile"); // 저장되는 파일명
		vo.setNoticeImage(realImg); // vo 객체 이미지 파일 명을 저장한다.
		
		String filePath = saveDir + File.separator + imgFileName;
		
	 	String thumb =thumbNail.makeThumbnail(filePath); //vo.setNoticeThumbnail(thumbNail.makeThumbnail(saveDir, imgFileName, fileExt));
	 	
//	 	System.out.println(thumb + "===================");
	 	int index = thumb.lastIndexOf("t"); // ("\\")+1 해도 됨 / 넘어온 결과에서 파일경로 잘래내고 파일명만 남김
		String thumbName = thumb.substring(index); // 파일 확장자
		
		// 첨부파일 불러오기
		String attech = multi.getOriginalFileName("noticeFile");
		if(attech != null) {
			String attechFile = multi.getFilesystemName("noticeFile");
			vo.setNoticeFile(attechFile);
		}
		
		vo.setNoticeWriter(multi.getParameter("noticeWriter"));
		vo.setNoticeTitle(multi.getParameter("noticeTitle"));
		// 폼에서 넘어오는건 string이라 타입을 바꿔야함
		// integer.valueof int타입일때
		vo.setNoticeDate(LocalDate.parse(multi.getParameter("noticeDate")));
		vo.setNoticeContent(multi.getParameter("noticeContent"));
		vo.setNoticeWriterName(multi.getParameter("noticeWriterName"));
		vo.setNoticeThumbnail(thumbName);
		
		int n = dao.noticeInsert(vo);
		
		if(n != 0) {
			response.sendRedirect("noticeselectlist.do");
		}else {
			request.setAttribute("message", "게시글 등록이 실패했습니다.");
			String page = "notice/noticemessage";
			ViewResolve.forward(request, response, page);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
