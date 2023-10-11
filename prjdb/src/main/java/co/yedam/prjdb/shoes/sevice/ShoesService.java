package co.yedam.prjdb.shoes.sevice;

import java.util.List;

public interface ShoesService {
	List<ShoesVO> shoesSelectList();
	ShoesVO shoesSelect(ShoesVO vo);
}
