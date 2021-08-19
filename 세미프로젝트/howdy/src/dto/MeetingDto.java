package dto;

public class MeetingDto {
	
	private int meeting_seq;	//일정번호 0
	private int groups_seq;		//모임번호	-가져오기
    private String category;	//일정종류	-사용자입력
    private String location;	//주소 - 사용자입력
	private String sdate;		//시작일 - 사용자입력
	private String edate;		//종료일 - 사용자입력
	private String rdate;		//등록일(SYSDATE)
	private String title;		//제목 - 사용자입력
	private String content;		//내용 - 사용자입력
	private int max_mem;		//정원 - 사용자입력
	private int del;			//삭제 0
	
	private String latitude;	// 위도 36.xxxxxxxx
	private String longitude;	// 경도 126.xxxxxx
	
	public MeetingDto(int meeting_seq, int groups_seq, String category, String location, String sdate, String edate,
			String rdate, String title, String content, int max_mem, int del, String latitude, String longitude) {
		this.meeting_seq = meeting_seq;
		this.groups_seq = groups_seq;
		this.category = category;
		this.location = location;
		this.sdate = sdate;
		this.edate = edate;
		this.rdate = rdate;
		this.title = title;
		this.content = content;
		this.max_mem = max_mem;
		this.del = del;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public MeetingDto(int groups_seq, String category, String location, String sdate, String edate, String title,
			String content, int max_mem, String latitude, String longitude) {
		super();
		this.groups_seq = groups_seq;
		this.category = category;
		this.location = location;
		this.sdate = sdate;
		this.edate = edate;
		this.title = title;
		this.content = content;
		this.max_mem = max_mem;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getMeeting_seq() {
		return meeting_seq;
	}

	public void setMeeting_seq(int meeting_seq) {
		this.meeting_seq = meeting_seq;
	}

	public int getGroups_seq() {
		return groups_seq;
	}

	public void setGroups_seq(int groups_seq) {
		this.groups_seq = groups_seq;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
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

	public int getMax_mem() {
		return max_mem;
	}

	public void setMax_mem(int max_mem) {
		this.max_mem = max_mem;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "MeetingDto [meeting_seq=" + meeting_seq + ", groups_seq=" + groups_seq + ", category=" + category
				+ ", location=" + location + ", sdate=" + sdate + ", edate=" + edate + ", rdate=" + rdate + ", title="
				+ title + ", content=" + content + ", max_mem=" + max_mem + ", del=" + del + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}
	
}	
