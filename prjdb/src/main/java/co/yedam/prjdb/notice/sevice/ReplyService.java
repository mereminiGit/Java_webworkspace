package co.yedam.prjdb.notice.sevice;

import java.util.List;

public interface ReplyService {
	public boolean addReply(ReplyVO vo);
	public boolean editReply(ReplyVO vo);
	public boolean deleteReply(int replyId);
	public ReplyVO getRely(int replyId);
	public List<ReplyVO> listReply(int replyId);	
}