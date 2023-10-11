package co.yedam.prjdb.notice.sevice;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReplyVO {
	private int replyId;
	private int noticeId;
	private String replyer;
	private String reply;
	private LocalDate replyDate;
	private LocalDate updateDate;
}
