package bit.com.a.dto;

import java.io.Serializable;

public class CalendarPlugData implements Serializable{
	private int id;
	private String start;
	private String end;
	private String title;
	private String constraint;
	
	public CalendarPlugData() {
	}

	public CalendarPlugData(int id, String start, String end, String title, String constraint) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
		this.title = title;
		this.constraint = constraint;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getConstraint() {
		return constraint;
	}

	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}

	@Override
	public String toString() {
		return "CalendarPlugData [id=" + id + ", start=" + start + ", end=" + end + ", title=" + title + ", constraint="
				+ constraint + "]";
	}
	
}
