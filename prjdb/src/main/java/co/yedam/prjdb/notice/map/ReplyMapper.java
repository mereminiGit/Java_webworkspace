package co.yedam.prjdb.notice.map;

import java.util.List;

import co.yedam.prjdb.notice.sevice.ReplyVO;

public interface ReplyMapper {
	// 등록, 수정, 삭제, 한건조회, 목록
	public int insert(ReplyVO vo);
	public int update(ReplyVO vo);
	public int delete(int replyId);
	public ReplyVO select(int replyId);
	public List<ReplyVO> list(int noticeId);
}
