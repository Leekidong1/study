package dto;

import java.io.Serializable;

public class GroupMemberDto implements Serializable{
	
	private int group_mem_seq;
	private int groups_seq;
	private String id;
	private String name;
	private String joindate;
	private int del;
	
	public GroupMemberDto() {
		// TODO Auto-generated constructor stub
	}
	
	public GroupMemberDto(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public GroupMemberDto(int group_mem_seq, int groups_seq, String id, String name, String joindate, int del) {
		super();
		this.group_mem_seq = group_mem_seq;
		this.groups_seq = groups_seq;
		this.id = id;
		this.name = name;
		this.joindate = joindate;
		this.del = del;
	}
	
	public int getGroup_mem_seq() {
		return group_mem_seq;
	}

	public void setGroup_mem_seq(int group_mem_seq) {
		this.group_mem_seq = group_mem_seq;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
