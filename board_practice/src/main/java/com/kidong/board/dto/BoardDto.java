package com.kidong.board.dto;

public class BoardDto {
	
	private String seq;
	private String memId;
	private String memName;
	private String boardSubject;
	private String boardContent;
	private String regDate;
	private String uptDate;
	private String viewCnt;
	
	public BoardDto() {
		
	}
	
	public BoardDto(String seq, String memId, String memName, String boardSubject, String boardContent,
			String uptDate, String viewCnt, String regDate) {
		super();
		this.seq = seq;
		this.memId = memId;
		this.memName = memName;
		this.boardSubject = boardSubject;
		this.boardContent = boardContent;
		this.uptDate = uptDate;
		this.viewCnt = viewCnt;
		this.regDate = regDate;
	}
	
	public BoardDto(String memId, String memName, String boardSubject, String boardContent) {
		super();
		this.memId = memId;
		this.memName = memName;
		this.boardSubject = boardSubject;
		this.boardContent = boardContent;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getBoardSubject() {
		return boardSubject;
	}

	public void setBoardSubject(String boardSubject) {
		this.boardSubject = boardSubject;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getUptDate() {
		return uptDate;
	}

	public void setUptDate(String uptDate) {
		this.uptDate = uptDate;
	}

	public String getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(String viewCnt) {
		this.viewCnt = viewCnt;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "BoardDto [seq=" + seq + ", memId=" + memId + ", memName=" + memName + ", boardSubject=" + boardSubject
				+ ", boardContent=" + boardContent + ", regDate=" + regDate + ", uptDate=" + uptDate + ", viewCnt="
				+ viewCnt + "]";
	}
	
}
