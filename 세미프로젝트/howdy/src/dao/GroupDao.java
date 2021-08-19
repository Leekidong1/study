package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.GroupDto;

public class GroupDao {
	
	private static GroupDao dao = new GroupDao();
	
	private GroupDao() {
	}
	
	public static GroupDao getInstance() {
		return dao;
	}
	
	public boolean addGroup(GroupDto dto) {
		
		String sql = " INSERT INTO GROUPS(GROUPS_SEQ, CATEGORY, LOCATION, ID, NAME, TITLE, CONTENT, FILENAME, NEWFILENAME, REGIDATE) "
				   + " VALUES(SEQ_GROUPS.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 addGroup success");
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getCategory());
			psmt.setString(2, dto.getLocation());
			psmt.setString(3, dto.getId());
			psmt.setString(4, dto.getGroupName());
			psmt.setString(5, dto.getGroupTitle());
			psmt.setString(6, dto.getGroupContent());
			psmt.setString(7, dto.getFileName());
			psmt.setString(8, dto.getNewfileName());
			System.out.println("2/3 addGroup success");
			count = psmt.executeUpdate();
			System.out.println("3/3 addGroup success");
		} catch (SQLException e) {
			System.out.println("addGroup fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		return count>0?true:false;
	}
	
	public List<GroupDto> getSearchList(String location, String category) {
		
		String sql = " SELECT GROUPS_SEQ, CATEGORY, LOCATION, ID, NAME, TITLE, CONTENT, FILENAME, NEWFILENAME, REGIDATE "
				   + " FROM GROUPS "
				   + " WHERE LOCATION LIKE '%" + location + "%' ";
		if(!category.equals("")) {		    
			sql = sql + " AND CATEGORY='" + category +"' " ;
		}
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<GroupDto> list = new ArrayList<GroupDto>();
		ReviewDao Rdao = ReviewDao.getInstance();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getSearchList success");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/4 getSearchList success");
			rs = psmt.executeQuery();
			System.out.println("3/4 getSearchList success");
			while(rs.next()) {
				String avgScore = star(Rdao.avgScore(rs.getInt(1)));
				int i = 1;
				GroupDto dto = new GroupDto(rs.getInt(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++),
											avgScore);
				list.add(dto);
			}
			System.out.println("4/4 getSearchList success");
		} catch (SQLException e) {
			System.out.println("getSearchList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return list;
	}
	
	public List<GroupDto> getGroupList() {
		
		String sql = " SELECT GROUPS_SEQ, CATEGORY, LOCATION, ID, NAME, TITLE, CONTENT, FILENAME, NEWFILENAME, REGIDATE "
				   + " FROM GROUPS ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<GroupDto> list = new ArrayList<GroupDto>();
		ReviewDao Rdao = ReviewDao.getInstance();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getGroupList success");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/4 getGroupList success");
			rs = psmt.executeQuery();
			System.out.println("3/4 getGroupList success");
			while(rs.next()) {
				String avgScore = star(Rdao.avgScore(rs.getInt(1)));
				int i = 1;
				GroupDto dto = new GroupDto(rs.getInt(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++),
											avgScore);
				list.add(dto);
			}
			System.out.println("4/4 getGroupList success");
		} catch (SQLException e) {
			System.out.println("getGroupList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return list;
	}
	
	public String star(int score){
		String result = "";
		String star = "<img src='lib/images/star-on.png'>";
		for(int i=0; i<score; i++){
			result = result + star;
		}
		return result;
	}
	
	public List<GroupDto> manageGroupList(String category, String searchText, String sdate, String edate ) {//어드민
		
		String sql = " SELECT GROUPS_SEQ, CATEGORY, LOCATION, ID, NAME, TITLE, CONTENT, FILENAME, NEWFILENAME, REGIDATE "
				   + " FROM GROUPS ";
		
		if(!sdate.equals("") && !edate.equals("") && !searchText.equals("")) {// 날짜,검색 둘다 했을 때,
			System.out.println("날짜,검색 둘다");
			sql = sql + " WHERE REGIDATE BETWEEN TO_DATE('" + sdate + "') AND TO_DATE('" + edate + "') ";
				if(category.equals("location")) {
					sql = sql + " AND LOCATION LIKE '%" + searchText + "%' " ;
				}else if(category.equals("manager")) {
					sql = sql + " WHERE ID LIKE '%" + searchText + "%' " ;
				}else if(category.equals("title")) {
					sql = sql + " AND TITLE LIKE '%" + searchText + "%' " ;
				}else if(category.equals("content")) {
					sql = sql + " AND CONTENT LIKE '%" + searchText + "%' " ;
				}
			
		}else if(!searchText.equals("")) {// 검색만 했을 때,
			System.out.println("카테고리검색만");
			if(category.equals("location")) {
				sql = sql + " WHERE LOCATION LIKE '%" + searchText + "%' " ;
			}else if(category.equals("manager")) {
				sql = sql + " WHERE ID LIKE '%" + searchText + "%' " ;
			}else if(category.equals("title")) {
				sql = sql + " WHERE TITLE LIKE '%" + searchText + "%' " ;
			}else if(category.equals("content")) {
				sql = sql + " WHERE CONTENT LIKE '%" + searchText + "%' " ;
			}
		}else if(!sdate.equals("") && !edate.equals("")) {// 날짜검색만 했을때,
			System.out.println("날짜검색만");
			sql = sql + " WHERE REGIDATE BETWEEN TO_DATE('" + sdate + "') AND TO_DATE('" + edate + "') ";
		}
		
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<GroupDto> list = new ArrayList<GroupDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getSearchList success");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/4 getSearchList success");
			rs = psmt.executeQuery();
			System.out.println("3/4 getSearchList success");
			while(rs.next()) {
				int i = 1;
				GroupDto dto = new GroupDto(rs.getInt(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++));
				list.add(dto);
			}
			System.out.println("4/4 getSearchList success");
		} catch (SQLException e) {
			System.out.println("getSearchList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return list;
	}
	
	public GroupDto getGroup(int seq) {
		
		String sql = " SELECT GROUPS_SEQ, CATEGORY, LOCATION, ID, NAME, TITLE, CONTENT, FILENAME, NEWFILENAME, REGIDATE "
				   + " FROM GROUPS "
				   + " WHERE GROUPS_SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		GroupDto dto = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getGroup success");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/4 getGroup success");
			rs = psmt.executeQuery();
			System.out.println("3/4 getGroup success");
			if(rs.next()) {
				int i = 1;
				dto = new GroupDto(rs.getInt(i++), 
									rs.getString(i++), 
									rs.getString(i++), 
									rs.getString(i++), 
									rs.getString(i++), 
									rs.getString(i++), 
									rs.getString(i++), 
									rs.getString(i++), 
									rs.getString(i++), 
									rs.getString(i++));
			}
			System.out.println("4/4 getGroup success");
		} catch (SQLException e) {
			System.out.println("getGroup fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return dto;
	}
	
	public boolean updateGroup(int groups_seq ,GroupDto dto) {
		
		String sql = " UPDATE GROUPS "
				   + " SET CATEGORY=?, LOCATION=?, ID=?, NAME=?, TITLE=?, CONTENT=?, FILENAME=?, NEWFILENAME=? "
				   + " WHERE GROUPS_SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 updateGroup success");
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getCategory());
			psmt.setString(2, dto.getLocation());
			psmt.setString(3, dto.getId());
			psmt.setString(4, dto.getGroupName());
			psmt.setString(5, dto.getGroupTitle());
			psmt.setString(6, dto.getGroupContent());
			psmt.setString(7, dto.getFileName());
			psmt.setString(8, dto.getNewfileName());
			psmt.setInt(9, groups_seq);
			System.out.println("2/3 updateGroup success");
			count = psmt.executeUpdate();
			System.out.println("3/3 updateGroup success");
		} catch (SQLException e) {
			System.out.println("updateGroup fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		return count>0?true:false;
	}
	
	public boolean deleteGroup(int groups_seq) {
		
		String sql = " DELETE FROM GROUPS "
				   + " WHERE GROUPS_SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 deleteGroup success");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, groups_seq);
			System.out.println("2/3 deleteGroup success");
			count = psmt.executeUpdate();
			System.out.println("3/3 deleteGroup success");
		} catch (SQLException e) {
			System.out.println("deleteGroup fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		return count>0?true:false;
	}
	
	public List<GroupDto> getGroupList_Mypage(String id) {
		
		String sql = " SELECT GROUPS_SEQ, CATEGORY, LOCATION, ID, NAME, TITLE, CONTENT, FILENAME, NEWFILENAME, REGIDATE "
				   + " FROM GROUPS "
				   + " WHERE ID='" + id + "' " ;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<GroupDto> list = new ArrayList<GroupDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getGroupList success");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/4 getGroupList success");
			rs = psmt.executeQuery();
			System.out.println("3/4 getGroupList success");
			while(rs.next()) {
				int i = 1;
				GroupDto dto = new GroupDto(rs.getInt(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++));
				list.add(dto);
			}
			System.out.println("4/4 getGroupList success");
		} catch (SQLException e) {
			System.out.println("getGroupList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return list;
	}
	
	public List<GroupDto> getGroupList_Mypage_mylist(String id) {
		
		String sql = " SELECT GROUPS_SEQ, CATEGORY, LOCATION, ID, NAME, TITLE, CONTENT, FILENAME, NEWFILENAME, REGIDATE "
				   + " FROM GROUPS "
				   + " WHERE GROUPS_SEQ IN(SELECT GROUPS_SEQ "
				   + " 			   		   FROM GROUP_MEM "
				   + "			   		   WHERE ID=?) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<GroupDto> list = new ArrayList<GroupDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getGroupList_Mypage_mylist success");
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			System.out.println("2/4 getGroupList_Mypage_mylist success");
			rs = psmt.executeQuery();
			System.out.println("3/4 getGroupList_Mypage_mylist success");
			while(rs.next()) {
				int i = 1;
				GroupDto dto = new GroupDto(rs.getInt(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++));
				list.add(dto);
			}
			System.out.println("4/4 getGroupList_Mypage_mylist success");
		} catch (SQLException e) {
			System.out.println("getGroupList_Mypage_mylist fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return list;
	}
}
