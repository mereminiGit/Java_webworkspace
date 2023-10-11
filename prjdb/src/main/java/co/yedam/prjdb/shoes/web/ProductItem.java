package co.yedam.prjdb.shoes.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.prjdb.common.ViewResolve;
import co.yedam.prjdb.shoes.sevice.ShoesService;
import co.yedam.prjdb.shoes.sevice.ShoesVO;
import co.yedam.prjdb.shoes.seviceImpl.ShoesServiceImpl;


@WebServlet("/ProductItem.do")
public class ProductItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ProductItem() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		ShoesService dao = new ShoesServiceImpl();
		ShoesVO vo = new ShoesVO();
		vo.setShoesId(id);
		
		vo = dao.shoesSelect(vo);
		request.setAttribute("item", vo);
		
		String page = "shoes/productItem";
		
		ViewResolve.forward(request, response, page);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
