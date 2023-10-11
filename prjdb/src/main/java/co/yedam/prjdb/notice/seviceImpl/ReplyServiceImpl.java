package co.yedam.prjdb.notice.seviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.prjdb.common.DataSource;
import co.yedam.prjdb.notice.map.ReplyMapper;
import co.yedam.prjdb.notice.sevice.ReplyService;
import co.yedam.prjdb.notice.sevice.ReplyVO;

public class ReplyServiceImpl implements ReplyService {
	
	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private ReplyMapper map = sqlSession.getMapper(ReplyMapper.class);
	
	@Override
	public boolean addReply(ReplyVO vo) {
		return map.insert(vo) == 1;
	}

	@Override
	public boolean editReply(ReplyVO vo) {
		return map.update(vo) == 1;
	}

	@Override
	public boolean deleteReply(int replyId) {
		return map.delete(replyId) == 1;
	}

	@Override
	public ReplyVO getRely(int replyId) {
		return map.select(replyId);
	}

	@Override
	public List<ReplyVO> listReply(int replyId) {
		return map.list(replyId);
	}

}
