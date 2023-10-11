package co.yedam.prjdb.common;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.prjdb.notice.map.ReplyMapper;
import co.yedam.prjdb.notice.sevice.ReplyVO;

public class mainExe {
	public static void main(String[] args) {
		SqlSession sqlSession = DataSource.getInstance().openSession(true);
		ReplyMapper map = sqlSession.getMapper(ReplyMapper.class);
		
		ReplyVO vo = new ReplyVO();
//		vo.setNoticeId(1);
//		vo.setReply("테스트 ing...");
//		vo.setReplyer("userJava");
		vo.setReplyId(5);
		System.out.println(map.select(vo.getReplyId()));
		
		List<ReplyVO> list = map.list(1);
		
		for(ReplyVO voTmp : list) {
			System.out.println(voTmp.toString());
		}
	}
}
