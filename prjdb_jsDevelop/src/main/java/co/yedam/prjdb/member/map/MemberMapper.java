package co.yedam.prjdb.member.map;

import java.util.List;

import co.yedam.prjdb.member.sevice.MemberVO;

public interface MemberMapper {
	List<MemberVO> memberSelectList();
	MemberVO memberSelect(MemberVO vo);
	int memberUpdate(MemberVO vo);
	int memberInsert(MemberVO vo);
	int memberDelete(MemberVO vo);
}
