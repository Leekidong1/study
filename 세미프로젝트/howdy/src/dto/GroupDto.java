package dto;

import java.io.Serializable;

public class GroupDto implements Serializable {
	private int groups_seq;	// 내가지정
	private String category;
	private String location;
	private String id;
	private String groupName;
	private String groupTitle;
	private String groupContent;
	
	private String fileName;
	private String newfileName;
	
	private String regidate;	// 내가지정
	private String starScore;

	public GroupDto(int groups_seq, String category, String location, String id, String groupName, String groupTitle, String groupContent,
			String fileName, String newfileName, String regidate) {
		this.groups_seq = groups_seq;
		this.category = category;
		this.location = location;
		this.id = id;
		this.groupName = groupName;
		this.groupTitle = groupTitle;
		this.groupContent = groupContent;
		this.fileName = fileName;
		this.newfileName = newfileName;
		this.regidate = regidate;
	}
	
	public GroupDto(int groups_seq, String category, String location, String id, String groupName, String groupTitle, String groupContent,
			String fileName, String newfileName, String regidate, String starScore) {
		this.groups_seq = groups_seq;
		this.category = category;
		this.location = location;
		this.id = id;
		this.groupName = groupName;
		this.groupTitle = groupTitle;
		this.groupContent = groupContent;
		this.fileName = fileName;
		this.newfileName = newfileName;
		this.regidate = regidate;
		this.starScore = starScore;
	}

	public GroupDto(String category, String location, String id, String groupName, String groupTitle, String groupContent,
			String fileName, String newfileName) {
		this.category = category;
		this.location = location;
		this.id = id;
		this.groupName = groupName;
		this.groupTitle = groupTitle;
		this.groupContent = groupContent;
		this.fileName = fileName;
		this.newfileName = newfileName;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLocation() {
		return location;
	}

	public String getStarScore() {
		return starScore;
	}

	public void setStarScore(String starScore) {
		this.starScore = starScore;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupTitle() {
		return groupTitle;
	}

	public void setGroupTitle(String groupTitle) {
		this.groupTitle = groupTitle;
	}

	public String getGroupContent() {
		return groupContent;
	}

	public void setGroupContent(String groupContent) {
		this.groupContent = groupContent;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getNewfileName() {
		return newfileName;
	}

	public void setNewfileName(String newfileName) {
		this.newfileName = newfileName;
	}

	public String getRegidate() {
		return regidate;
	}

	public void setRegidate(String regidate) {
		this.regidate = regidate;
	}

	@Override
	public String toString() {
		return "GroupDto [groups_seq=" + groups_seq + ", category=" + category + ", location=" + location + ", id=" + id
				+ ", groupName=" + groupName + ", groupTitle=" + groupTitle + ", groupContent=" + groupContent
				+ ", fileName=" + fileName + ", newfileName=" + newfileName + ", regidate=" + regidate + ", starScore="
				+ starScore + "]";
	}

}
