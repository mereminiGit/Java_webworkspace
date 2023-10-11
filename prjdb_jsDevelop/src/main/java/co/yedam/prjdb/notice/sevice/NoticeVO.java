package co.yedam.prjdb.notice.sevice;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeVO {
	private int noticeId;
	private String noticeWriter;
	private String noticeTitle;
	private String noticeContent;
	private LocalDate noticeDate;
	private int noticeHit;
	private String noticeFile;
	private String noticeImage;
	private String noticeWriterName;
	private String noticeThumbnail;
}
