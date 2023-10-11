package co.yedam.prjdb.shoes.map;

import java.util.List;

import co.yedam.prjdb.shoes.sevice.ShoesVO;

public interface ShoesMapper {
	List<ShoesVO> shoesSelectList();
	ShoesVO shoesSelect(ShoesVO vo);
}
