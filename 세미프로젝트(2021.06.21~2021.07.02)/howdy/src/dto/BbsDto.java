package dto;

import java.io.Serializable;

public class BbsDto implements Serializable{
						// 직렬화 : 데이터 날라갔을때 순서대로 찍히도록
	
	/* 6/28 수정 : 추가 8,9줄 */
	private int bbs_seq;		// 작성글번호
	private int groups_seq;
	private String id;		// 작성자
	
	private int ref;		// 그룹번호
	private int setp;		// 행번호
	private int depth;		// 깊이 
	
	private String title;	// 작성 제목
	private String content; // 작성 내용
	private String type;	// 작성 타입(공지사항, 자유글....)
	private String wdate; 	// 작성날짜
	/* 6/28 수정 : 삭제 likeCount */
	
	private int del;			// 글 삭제
	
	public BbsDto() {
	}
	
	/* 6/28 수정 : 삭제 likeCount, 추가 int bbs_seq, int groups_seq */
	public BbsDto(int bbs_seq, int groups_seq, String id, int ref, int setp, int depth, String title, String content,
			String type, String wdate, int del) {
		this.bbs_seq = bbs_seq;
		this.groups_seq = groups_seq;
		this.id = id;
		this.ref = ref;
		this.setp = setp;
		this.depth = depth;
		this.title = title;
		this.content = content;
		this.type = type;
		this.wdate = wdate;
		this.del = del;
	}
	
	/* 6/28 수정 : 추가 int groups_seq, 삭제 int likeCount */
	public BbsDto(int groups_seq, String id, String title, String content, String type ) {
		/* 6/28 수정 : 삭제 super(); likeCount
		 *          : 추가 int groups_seq, this.groups_seq = groups_seq; */
		this.groups_seq = groups_seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.type = type;
	}
	
	public BbsDto(String title, String content, String type) {//0630
		this.title = title;
		this.content = content;
		this.type = type;
	}
	
	public BbsDto(String id, String title, String content, String type) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.type = type;
	}
	
		
	public BbsDto(int groups_seq, String id, String content) {
		this.groups_seq = groups_seq;
		this.id = id;
		this.content = content;
	}

	public int getBbs_seq() {
		return bbs_seq;
	}

	public void setBbs_seq(int bbs_seq) {
		this.bbs_seq = bbs_seq;
	}

	public int getGroups_seq() {
		return groups_seq;
	}

	public void setGroups_seq(int groups_seq) {
		this.groups_seq = groups_seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getSetp() {
		return setp;
	}

	public void setSetp(int setp) {
		this.setp = setp;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

}
	

	
