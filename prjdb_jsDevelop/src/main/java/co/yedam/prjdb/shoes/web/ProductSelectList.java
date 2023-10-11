package co.yedam.prjdb.shoes.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.yedam.prjdb.shoes.sevice.ShoesService;
import co.yedam.prjdb.shoes.sevice.ShoesVO;
import co.yedam.prjdb.shoes.seviceImpl.ShoesServiceImpl;


@WebServlet("/ProductSelectList.do")
public class ProductSelectList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ProductSelectList() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoesService dao = new ShoesServiceImpl();
		List<ShoesVO> vo = dao.shoesSelectList();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(vo);
		
		response.setContentType("text/json; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.print(json);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
