package dto;

import java.io.Serializable;

public class MemberDto implements Serializable {
	
	private int seq;
	private String id;
	private String pwd;
	private String name;
	private String birth;
	private String gender;
	private String phone;
	private String email;
	private String category;
	private String location;
	private int auth;	// 0:일반회원  1:관리자
	private int group_auth; // 0:가입대기, 1:모임원, 2:부모임장, 3:모임장
	private String joindate;
	private int del;
	
		public MemberDto() {
	}
		
		

		public MemberDto(int seq, String id, String pwd, String name, String birth, String gender, String phone,
				String email, String category, String location, int auth, int group_auth, String joindate, int del) {
			this.seq = seq;
			this.id = id;
			this.pwd = pwd;
			this.name = name;
			this.birth = birth;
			this.gender = gender;
			this.phone = phone;
			this.email = email;
			this.category = category;
			this.location = location;
			this.auth = auth;
			this.group_auth = group_auth;
			this.joindate = joindate;
			this.del = del;
		}

		public MemberDto(String id, String pwd, String name, String birth, String gender, String phone, String email,
				String category, String location) {
			super();
			this.id = id;
			this.pwd = pwd;
			this.name = name;
			this.birth = birth;
			this.gender = gender;
			this.phone = phone;
			this.email = email;
			this.category = category;
			this.location = location;
		}



		public int getSeq() {
			return seq;
		}



		public void setSeq(int seq) {
			this.seq = seq;
		}



		public String getId() {
			return id;
		}



		public void setId(String id) {
			this.id = id;
		}



		public String getPwd() {
			return pwd;
		}



		public void setPwd(String pwd) {
			this.pwd = pwd;
		}



		public String getName() {
			return name;
		}



		public void setName(String name) {
			this.name = name;
		}



		public String getBirth() {
			return birth;
		}



		public void setBirth(String birth) {
			this.birth = birth;
		}



		public String getGender() {
			return gender;
		}



		public void setGender(String gender) {
			this.gender = gender;
		}



		public String getPhone() {
			return phone;
		}



		public void setPhone(String phone) {
			this.phone = phone;
		}



		public String getEmail() {
			return email;
		}



		public void setEmail(String email) {
			this.email = email;
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



		public int getAuth() {
			return auth;
		}



		public void setAuth(int auth) {
			this.auth = auth;
		}



		public int getGroup_auth() {
			return group_auth;
		}



		public void setGroup_auth(int group_auth) {
			this.group_auth = group_auth;
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

		@Override
		public String toString() {
			return "MemberDto [seq=" + seq + ", id=" + id + ", pwd=" + pwd + ", name=" + name + ", birth=" + birth
					+ ", gender=" + gender + ", phone=" + phone + ", email=" + email + ", category=" + category
					+ ", location=" + location + ", auth=" + auth + ", group_auth=" + group_auth + ", joindate="
					+ joindate + ", del=" + del + "]";
		}

}
