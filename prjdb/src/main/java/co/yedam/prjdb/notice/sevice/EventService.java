package co.yedam.prjdb.notice.sevice;

import java.util.List;

public interface EventService {
	public List<EventVO> selectListEvent();
	public EventVO selectEvent(EventVO vo);
	public int insertEvent(EventVO vo);
}
