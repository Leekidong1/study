package bit.com.a.dto;

import java.io.Serializable;

public class SearchDto implements Serializable {
	
	private String choice;
	private String search;
	private int pageNumber;
	
	private String startDate;
	private String endDate;
	
	public SearchDto() {
	}

	public SearchDto(String choice, String search, int pageNumber) {
		this.choice = choice;
		this.search = search;
		this.pageNumber = pageNumber;
	}
	
	public SearchDto(String choice, String search, int pageNumber, String startDate, String endDate) {
		this.choice = choice;
		this.search = search;
		this.pageNumber = pageNumber;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "SearchDto [choice=" + choice + ", search=" + search + ", pageNumber=" + pageNumber + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}

}
