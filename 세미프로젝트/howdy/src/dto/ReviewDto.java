package dto;

import java.io.Serializable;

public class ReviewDto implements Serializable {

	private int review_seq; //리뷰시퀀스
	private int groups_seq; //그룹시퀀스
	private String id; //사용자아이디
	private String title; //리뷰제목
	private String wdate; //리뷰작성일
	private int score; //평점
	private String content; //리뷰내용
	private int del;//삭제
	
	public ReviewDto() {
		// TODO Auto-generated constructor stub
	}
	
	public ReviewDto(String id, String title, int score, String content) {
	super();
	this.id = id;
	this.title = title;
	this.score = score;
	this.content = content;
}

	public ReviewDto(int review_seq, int groups_seq, String id, String title, String wdate, int score, String content,
			int del) {
		super();
		this.review_seq = review_seq;
		this.groups_seq = groups_seq;
		this.id = id;
		this.title = title;
		this.wdate = wdate;
		this.score = score;
		this.content = content;
		this.del = del;
	}

	public int getReview_seq() {
		return review_seq;
	}

	public void setReview_seq(int review_seq) {
		this.review_seq = review_seq;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	
	
}
