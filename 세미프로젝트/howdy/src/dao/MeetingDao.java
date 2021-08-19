package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.MeetingDto;


public class MeetingDao {
	
	private static MeetingDao dao = new MeetingDao();

	
	private MeetingDao() {
		
	}
	public static MeetingDao getInstance() {
		return dao;
	}
	
	public boolean addMeeting(MeetingDto dto) {
		
		String sql = " INSERT INTO MEETING(MEETING_SEQ, GROUPS_SEQ, CATEGORY, LOCATION, SDATE, EDATE, RDATE, TITLE, CONTENT, MAX_MEM, DEL, LATITUDE, LONGITUDE) "
				   + " VALUES(SEQ_MEETING.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE, ?, ?, ?, 0, ?, ?) ";
		
		Connection conn = null;			
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 addMeeting success");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto.getGroups_seq());
			psmt.setString(2, dto.getCategory());
			psmt.setString(3, dto.getLocation());
			psmt.setString(4, dto.getSdate());
			psmt.setString(5, dto.getEdate());
			psmt.setString(6, dto.getTitle());
			psmt.setString(7, dto.getContent());
			psmt.setInt(8, dto.getMax_mem());
			psmt.setString(9, dto.getLatitude());
			psmt.setString(10, dto.getLongitude());
			System.out.println("2/3 addMeeting success");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 addMeeting success");
			
		} catch (SQLException e) {
			System.out.println("addMeeting fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		return count>0?true:false;
	}
	
	public List<MeetingDto> getSearchingList(String location, String sdate, String edate, String category) {
		
		String sql = " SELECT MEETING_SEQ, GROUPS_SEQ, CATEGORY, LOCATION, SDATE, EDATE, RDATE, TITLE, CONTENT, MAX_MEM, DEL, LATITUDE, LONGITUDE "
				   + " FROM MEETING ";
			
			String sword = "";
			if(!location.equals("") && !sdate.equals("") && !edate.equals("") && !category.equals("")) {	// 모두검색한 경우
				System.out.println("모두 검색한 경우");
				sword = sword + " WHERE LOCATION LIKE '%" + location + "%' "
						      + " AND SDATE BETWEEN '" + sdate + "%' AND '" + edate + "%' "
							  + " AND GROUPS_SEQ IN (SELECT GROUPS_SEQ "
					      	  + "					 FROM GROUPS "
					      	  + "					 WHERE CATEGORY= '" + category + "') "; 
			}else if(!location.equals("") && !sdate.equals("") && !edate.equals("")) {	// 지역, 일정검색 경우
				System.out.println("지역, 일정검색 경우");
				sword = sword + " WHERE LOCATION LIKE '%" + location + "%' "
							  + " AND SDATE BETWEEN '" + sdate + "%' AND '" + edate + "%' ";		
			}else if(!location.equals("") && !category.equals("")) { // 지역, 카테고리검색 경우
				System.out.println("지역, 카테고리검색 경우");
				sword = sword + " WHERE LOCATION LIKE '%" + location + "%' "
							  + " AND GROUPS_SEQ IN (SELECT GROUPS_SEQ "
					      	  + "					 FROM GROUPS "
					      	  + "					 WHERE CATEGORY= '" + category + "') "; 
			}else if(!sdate.equals("") && !edate.equals("") && !category.equals("")) {	// 일정, 카테고리검색 경우
				System.out.println("일정, 카테고리검색 경우");
				sword = sword + " WHERE SDATE BETWEEN '" + sdate + "%' AND '" + edate + "%' "
							  + " AND GROUPS_SEQ IN (SELECT GROUPS_SEQ "
					      	  + "					 FROM GROUPS "
					      	  + "					 WHERE CATEGORY= '" + category + "') "; 
			}else if(!sdate.equals("") && !edate.equals("")) {	// 일정만 검색한 경우
				System.out.println("일정만 검색한 경우");
				sword = sword + " WHERE SDATE BETWEEN '" + sdate + "%' AND '" + edate + "%' ";
			}else if(!category.equals("")) {	// 카테고리만 검색한 경우
				System.out.println("카테고리만 검색한 경우");
				sword = sword + " WHERE GROUPS_SEQ IN (SELECT GROUPS_SEQ "
					      	  + "					 FROM GROUPS "
					      	  + "					 WHERE CATEGORY= '" + category + "') "; 
			}else if(!location.equals("")) {	// 지역만 검색한 경우
				System.out.println("지역만 검색한 경우");
				sword = sword + " WHERE LOCATION LIKE '%" + location + "%' ";
			}
			
			sql = sql + sword;
			
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<MeetingDto> list = new ArrayList<MeetingDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getSearchingList success");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/4 getSearchingList success");
			rs = psmt.executeQuery();
			System.out.println("3/4 getSearchingList success");
			while(rs.next()) {
				int i = 1;
				MeetingDto dto = new MeetingDto(rs.getInt(i++), 
											rs.getInt(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getInt(i++),
											rs.getInt(i++),
											rs.getString(i++),
											rs.getString(i++));
				list.add(dto);
			}
			System.out.println("4/4 getSearchingList success");
		} catch (SQLException e) {
			System.out.println("getSearchingList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return list;
	}
	
	public MeetingDto getMeeting(int seq) {
		
		String sql = " SELECT MEETING_SEQ, GROUPS_SEQ, CATEGORY, LOCATION, SDATE, EDATE, RDATE, TITLE, CONTENT, MAX_MEM, DEL, LATITUDE, LONGITUDE "
				   + " FROM MEETING "
				   + " WHERE MEETING_SEQ=? ";
				
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		MeetingDto dto = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getMeetingList success");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/4 getMeetingList success");
			rs = psmt.executeQuery();
			System.out.println("3/4 getMeetingList success");
			if(rs.next()) {
				int i = 1;
				dto = new MeetingDto(rs.getInt(i++), 
									rs.getInt(i++), 
									rs.getString(i++), 
									rs.getString(i++), 
									rs.getString(i++), 
									rs.getString(i++), 
									rs.getString(i++), 
									rs.getString(i++), 
									rs.getString(i++), 
									rs.getInt(i++),
									rs.getInt(i++),
									rs.getString(i++),
									rs.getString(i++));
			}
			System.out.println("4/4 getMeetingList success");
		} catch (SQLException e) {
			System.out.println("getMeetingList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return dto;
	}
	
	public List<MeetingDto> getMeetingList(int group_seq) {
		
		String sql = " SELECT MEETING_SEQ, GROUPS_SEQ, CATEGORY, LOCATION, SDATE, EDATE, RDATE, TITLE, CONTENT, MAX_MEM, DEL, LATITUDE, LONGITUDE "
				   + " FROM MEETING ";
			if(group_seq != -1) {
				sql += " WHERE GROUPS_SEQ=" + group_seq;
			}
				
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<MeetingDto> list = new ArrayList<MeetingDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getMeetingList success");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/4 getMeetingList success");
			rs = psmt.executeQuery();
			System.out.println("3/4 getMeetingList success");
			while(rs.next()) {
				int i = 1;
				MeetingDto dto = new MeetingDto(rs.getInt(i++), 
											rs.getInt(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											toDates(rs.getString(i++)), //sdate
											toDates(rs.getString(i++)), //edate
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getInt(i++),
											rs.getInt(i++),
											rs.getString(i++),
											rs.getString(i++));
				list.add(dto);
			}
			System.out.println("4/4 getMeetingList success");
		} catch (SQLException e) {
			System.out.println("getMeetingList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return list;
	}
	
	public String toDates(String mdate){
		String s = mdate.substring(0, 4) + "-" // yyyy
				 + mdate.substring(4, 6) + "-" // mm
				 + mdate.substring(6, 8) + "  " // dd
				 + mdate.substring(8, 10) + ":" // hh
				 + mdate.substring(10, 12); // mm:ss
		return s;
	}
	
	public List<MeetingDto> getCalendarList(int group_seq, String yyyyMM){
		
		String sql = " SELECT MEETING_SEQ, GROUPS_SEQ, CATEGORY, LOCATION, SDATE, EDATE, RDATE, TITLE, CONTENT, MAX_MEM, DEL, LATITUDE, LONGITUDE "
				   + " FROM (SELECT ROW_NUMBER()OVER(PARTITION BY SDATE ORDER BY SDATE ASC) AS RNUM, "
				   + " 		 MEETING_SEQ, GROUPS_SEQ, CATEGORY, LOCATION, SDATE, EDATE, RDATE, TITLE, CONTENT, MAX_MEM, DEL, LATITUDE, LONGITUDE "
				   + " 		 FROM MEETING "
				   + "		 WHERE GROUPS_SEQ=? AND SUBSTR(SDATE,1,6)=?) "
				   + " WHERE RNUM BETWEEN 1 AND 5 ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<MeetingDto> list = new ArrayList<MeetingDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getCalendarList success");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, group_seq);
			psmt.setString(2, yyyyMM);
			System.out.println("2/4 getCalendarList success");
			rs = psmt.executeQuery();
			System.out.println("3/4 getCalendarList success");
			while(rs.next()) {
				int i = 1;
				MeetingDto dto = new MeetingDto(rs.getInt(i++), 
											rs.getInt(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getInt(i++),
											rs.getInt(i++),
											rs.getString(i++),
											rs.getString(i++));
				list.add(dto);
			}
			System.out.println("4/4 getCalendarList success");
		} catch (SQLException e) {
			System.out.println("getCalendarList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return list;
	}
	
	public List<MeetingDto> getMyList(String id, String yyyyMM){
		System.out.println("id:"+id+" "+"yyyyMM:"+yyyyMM);
		String sql = "SELECT MEETING_SEQ, GROUPS_SEQ, CATEGORY, LOCATION, SDATE, EDATE, RDATE, TITLE, CONTENT, MAX_MEM, DEL, LATITUDE, LONGITUDE "
				   + "FROM (SELECT ROW_NUMBER()OVER(PARTITION BY SDATE ORDER BY SDATE ASC) AS RNUM,"
				   + "	  MEETING_SEQ, GROUPS_SEQ, CATEGORY, LOCATION, SDATE, EDATE, RDATE, TITLE, CONTENT, MAX_MEM, DEL, LATITUDE, LONGITUDE"
				   + "	  FROM MEETING"
				   + "	  WHERE SUBSTR(SDATE,1,6)=?"
				   + "	  	AND MEETING_SEQ IN(SELECT MEETING_SEQ"
				   + "	  						FROM MEETING_MEM"
				   + "	  						WHERE ID=? AND DEL=0))"
				   + "WHERE RNUM BETWEEN 1 AND 5 ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<MeetingDto> list = new ArrayList<MeetingDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getMyList success");
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, yyyyMM);
			psmt.setString(2, id);
			System.out.println("2/4 getMyList success");
			rs = psmt.executeQuery();
			System.out.println("3/4 getMyList success");
			while(rs.next()) {
				int i = 1;
				MeetingDto dto = new MeetingDto(rs.getInt(i++), 
											rs.getInt(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getString(i++), 
											rs.getInt(i++),
											rs.getInt(i++),
											rs.getString(i++),
											rs.getString(i++));
				list.add(dto);
			}
			System.out.println("4/4 getMyList success");
		} catch (SQLException e) {
			System.out.println("getMyList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return list;
	}
	
	public boolean updateMeeting(int meeting_seq,MeetingDto dto) {
		
		String sql = " UPDATE MEETING"
				   + " SET CATEGORY=?, LOCATION=?, SDATE=?, EDATE=?, TITLE=?, CONTENT=?, MAX_MEM=?, LATITUDE=?, LONGITUDE=? "
				   + " WHERE MEETING_SEQ=" + meeting_seq;
		
		Connection conn = null;			
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 updateMeeting success");
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getCategory());
			psmt.setString(2, dto.getLocation());
			psmt.setString(3, dto.getSdate());
			psmt.setString(4, dto.getEdate());
			psmt.setString(5, dto.getTitle());
			psmt.setString(6, dto.getContent());
			psmt.setInt(7, dto.getMax_mem());
			psmt.setString(8, dto.getLatitude());
			psmt.setString(9, dto.getLongitude());
			System.out.println("2/3 updateMeeting success");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 updateMeeting success");
			
		} catch (SQLException e) {
			System.out.println("updateMeeting fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		return count>0?true:false;
	}
	public boolean deleteMeeting(int meeting_seq) {

		String sql = " DELETE FROM MEETING "
				   + " WHERE MEETING_SEQ=? ";
		
		Connection conn = null;			
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 deleteMeeting success");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, meeting_seq);
			System.out.println("2/3 deleteMeeting success");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 deleteMeeting success");
			
		} catch (SQLException e) {
			System.out.println("deleteMeeting fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		return count>0?true:false;
	}
}



