package dto;

public class MeetingMemberDto {

	private int meeting_mem_seq;
	private int calendar_seq;
	private String id;
	private String joindate;
	private int del;
	
	public MeetingMemberDto() {
		// TODO Auto-generated constructor stub
	}
	
	public MeetingMemberDto(String id) {
		super();
		this.id = id;
	}

	public MeetingMemberDto(int meeting_mem_seq, int calendar_seq, String id, String joindate, int del) {
		super();
		this.meeting_mem_seq = meeting_mem_seq;
		this.calendar_seq = calendar_seq;
		this.id = id;
		this.joindate = joindate;
		this.del = del;
	}

	public int getMeeting_mem_seq() {
		return meeting_mem_seq;
	}
	public void setMeeting_mem_seq(int meeting_mem_seq) {
		this.meeting_mem_seq = meeting_mem_seq;
	}
	public int getCalendar_seq() {
		return calendar_seq;
	}
	public void setCalendar_seq(int calendar_seq) {
		this.calendar_seq = calendar_seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJoindate() {
		return joindate;
	}
	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
	
}
