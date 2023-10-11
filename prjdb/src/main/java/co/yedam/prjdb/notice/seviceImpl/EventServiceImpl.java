package co.yedam.prjdb.notice.seviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.prjdb.common.DataSource;
import co.yedam.prjdb.notice.map.EventMapper;
import co.yedam.prjdb.notice.sevice.EventService;
import co.yedam.prjdb.notice.sevice.EventVO;

public class EventServiceImpl implements EventService{

	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private EventMapper map = sqlSession.getMapper(EventMapper.class);
	
	@Override
	public List<EventVO> selectListEvent() {
		return map.selectListEvent();
	}

	@Override
	public EventVO selectEvent(EventVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertEvent(EventVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
