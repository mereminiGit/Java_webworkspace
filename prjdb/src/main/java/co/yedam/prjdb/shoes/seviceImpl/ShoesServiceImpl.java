package co.yedam.prjdb.shoes.seviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.prjdb.common.DataSource;
import co.yedam.prjdb.shoes.map.ShoesMapper;
import co.yedam.prjdb.shoes.sevice.ShoesService;
import co.yedam.prjdb.shoes.sevice.ShoesVO;

public class ShoesServiceImpl implements ShoesService{
	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private ShoesMapper map = sqlSession.getMapper(ShoesMapper.class);
	
	@Override
	public List<ShoesVO> shoesSelectList() {
		return map.shoesSelectList();
	}

	@Override
	public ShoesVO shoesSelect(ShoesVO vo) {
		return map.shoesSelect(vo);
	}
	
}
