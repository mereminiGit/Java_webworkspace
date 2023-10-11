package co.yedam.prjdb.notice.map;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.yedam.prjdb.notice.sevice.NoticeVO;

public interface NoticeMapper {
	List<NoticeVO> noticeSelectList();

	NoticeVO noticeSelect(NoticeVO vo);

	int noticeInsert(NoticeVO vo);

	int noticeUpdate(NoticeVO vo);

	int noticeDelete(NoticeVO vo);

	void noticeHitUpdate(int id);

//	매게변수 두개이면 @param을 넣어줘야한다.
	List<NoticeVO> noticeSelectList(@Param("key") String key, @Param("val") String val);
}
