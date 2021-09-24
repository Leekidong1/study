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
import dto.MeetingMemberDto;

public class MeetingMemberDao {

	private static MeetingMemberDao dao = new MeetingMemberDao();

	
	private MeetingMemberDao() {
		
	}
	
	public static MeetingMemberDao getInstance() {
		return dao;
	}
	
	public boolean addMeetingMember(int seq, String id) {//+++++ 미팅참여
		String sql = " INSERT INTO MEETING_MEM ( MEETING_MEM_SEQ, MEETING_SEQ, ID, JOINDATE, DEL ) "
				   + " VALUES(MEETING_MEM_SEQ.NEXTVAL, ?, ?, SYSDATE, 0) ";
				
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;	
		
		conn = DBConnection.getConnection();
		System.out.println("1/3 addMeetingMember success");
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			psmt.setString(2, id);
			System.out.println("2/3 addMeetingMember success");
			count = psmt.executeUpdate();
			System.out.println("3/3 addMeetingMember success");
		} catch (SQLException e) {
			System.out.println("addMeetingMember failed");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		return count>0?true:false;
	}
	
	public boolean deleteMeetingMember(int meeting_seq,String id) {//+++++ 참여취소
		String sql = " UPDATE MEETING_MEM "
				   + " SET DEL=1 "
			       + " WHERE ID=? "
			       + " AND MEETING_SEQ=?";	
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		System.out.println("1/3 deleteMeetingMember success");
		try {
			conn = DBConnection.getConnection();
			System.out.println("2/3 deleteMeetingMember success");
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setInt(2, meeting_seq);
			System.out.println("3/3 deleteMeetingMember success");
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("deleteMeetingMember failed");
		} finally {
			DBClose.close(conn, psmt, null);
		}
		return count>0?true:false;
	}
	
	public int getCount(int seq) {//+++++ 현재정원 리턴
		String sql = " SELECT COUNT(distinct(ID)) "
				   + " FROM MEETING_MEM "
				   + " WHERE MEETING_SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int meetingMemCount = 0;
		
		try {
			conn= DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			System.out.println("1/3 getCount success");
			psmt.setInt(1, seq);
			rs = psmt.executeQuery();
			System.out.println("2/3 getCount success");
			if(rs.next()) {
				meetingMemCount = rs.getInt(1);
			}
			System.out.println("3/3 getCount success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("getCount failed");
		} finally {
			DBClose.close(conn, psmt, rs);
		}		
		return meetingMemCount;			
	}
	
	public List<MeetingMemberDto> getMeetingMemberList() {
		
		String sql = " SELECT MEETING_MEM_SEQ, MEETING_SEQ, ID, JOINDATE, DEL "
				   + " FROM MEETING_MEM ";
				
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<MeetingMemberDto> list = new ArrayList<MeetingMemberDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getMeetingMemberList success");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/4 getMeetingMemberList success");
			rs = psmt.executeQuery();
			System.out.println("3/4 getMeetingMemberList success");
			while(rs.next()) {
				int i = 1;
				MeetingMemberDto dto = new MeetingMemberDto(rs.getInt(i++), 
															rs.getInt(i++), 
															rs.getString(i++), 
															rs.getString(i++),
															rs.getInt(i++));
				list.add(dto);
			}
			System.out.println("4/4 getMeetingMemberList success");
		} catch (SQLException e) {
			System.out.println("getMeetingMemberList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return list;
	}
	
	public boolean changeAuth(int meeting_seq,String id) {//참여하기 할 시에 del없애준다.
		String sql = " UPDATE MEETING_MEM "
				   + " SET DEL=0 "
			       + " WHERE ID=? "
			       + " AND MEETING_SEQ=?";	
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		System.out.println("1/3 deleteMeetingMember success");
		try {
			conn = DBConnection.getConnection();
			System.out.println("2/3 deleteMeetingMember success");
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setInt(2, meeting_seq);
			System.out.println("3/3 deleteMeetingMember success");
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("deleteMeetingMember failed");
		} finally {
			DBClose.close(conn, psmt, null);
		}
		return count>0?true:false;
	}
}
