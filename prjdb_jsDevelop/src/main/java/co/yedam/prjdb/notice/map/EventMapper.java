package co.yedam.prjdb.notice.map;

import java.util.List;

import co.yedam.prjdb.notice.sevice.EventVO;

public interface EventMapper {
	public List<EventVO> selectListEvent();
	public EventVO selectEvent(EventVO vo);
	public int insertEvent(EventVO vo);
}
