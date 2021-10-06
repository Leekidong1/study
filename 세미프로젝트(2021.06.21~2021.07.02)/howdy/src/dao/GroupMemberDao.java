package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.GroupMemberDto;
import dto.MeetingDto;
import dto.MemberDto;

public class GroupMemberDao {

	private static GroupMemberDao dao = new GroupMemberDao();
	
	public static GroupMemberDao getInstance() {
		return dao;
	}
	
	public boolean addGroupMember(int seq, GroupMemberDto gmd) {//+++++ 가입하기 클릭시
		String sql = " INSERT INTO GROUP_MEM ( GROUP_MEM_SEQ, GROUPS_SEQ, ID, NAME, JOINDATE, DEL ) "
				   + " VALUES(SEQ_GROUP_MEM.NEXTVAL, ?, ?, ?, SYSDATE, 0 ) ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		conn = DBConnection.getConnection();
		System.out.println("1/3 addMember success");
		
		try {
			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 addMember success");
			psmt.setInt(1, seq);
			psmt.setString(2, gmd.getId());
			psmt.setString(3, gmd.getName());
			System.out.println("3/3 addMember success");
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("addMember failed");
		} finally {
			DBClose.close(conn, psmt, null);
		}
		return count>0?true:false;
	}
	
	public List<MemberDto> getGroupMemberList(int seq){
		
	String sql = " SELECT MEM_SEQ, ID, PWD, NAME, BIRTH, GENDER, PHONE, EMAIL, CATEGORY, LOCATION, AUTH, GROUP_AUTH, JOINDATE, DEL "
			   + " FROM MEMBER "  
			   + " WHERE ID IN(SELECT ID "
			   + " 				FROM GROUP_MEM "
			   + " 				WHERE GROUPS_SEQ=?) ";//+++++ seq값 
		
	Connection conn = null;			
	PreparedStatement psmt = null;	
	ResultSet rs = null;	
	
	List<MemberDto> list = new ArrayList<MemberDto>();	
	
	try {
		conn = DBConnection.getConnection();
		System.out.println("1/3 getGroupMemberList success");
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, seq);
		System.out.println("2/3 getGroupMemberList success");
		
		rs = psmt.executeQuery();	
		while(rs.next()) {
			int i = 1;
			MemberDto dto = new MemberDto(rs.getInt(i++), 
										rs.getString(i++), 
										rs.getString(i++), 
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
										rs.getInt(i++));
			list.add(dto);
		}	
		System.out.println("3/3 getGroupMemberList success");
		
	} catch (SQLException e) {
		System.out.println("getReviewBbsList fail");
		e.printStackTrace();
	} finally {
		DBClose.close(conn, psmt, rs);
	}
	System.out.println(list);
	return list;
	}
	
	public List<GroupMemberDto> getManageMemberList_regi(int seq){
		
		String sql = " SELECT GROUP_MEM_SEQ, GROUPS_SEQ, ID, NAME, JOINDATE, DEL "
				   + " FROM GROUP_MEM "
				   + " WHERE ID IN(SELECT ID "
				   + " 	 		   FROM MEMBER "
				   + "     		   WHERE GROUP_AUTH = 0) "	// 가입대기회원 리스트가져오기
				   + " AND GROUPS_SEQ=? ";
			
		Connection conn = null;			
		PreparedStatement psmt = null;	
		ResultSet rs = null;	
		
		List<GroupMemberDto> list = new ArrayList<GroupMemberDto>();	
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 getManageMemberList_regi success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/3 getManageMemberList_regi success");
			
			rs = psmt.executeQuery();	
			while(rs.next()) {
				GroupMemberDto dto = new GroupMemberDto(rs.getInt(1), 
														rs.getInt(2), 
														rs.getString(3), 
														rs.getString(4),  
														rs.getString(5), 
														rs.getInt(6));					
				list.add(dto);
			}	
			System.out.println("3/3 getManageMemberList_regi success");
			
		} catch (SQLException e) {
			System.out.println("getManageMemberList_regi fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		System.out.println(list);
		return list;
	}
	public List<GroupMemberDto> getManageMemberList_manage(int seq){
		
		String sql = " SELECT GROUP_MEM_SEQ, GROUPS_SEQ, ID, NAME, JOINDATE, DEL "
				   + " FROM GROUP_MEM "
				   + " WHERE ID IN(SELECT ID "
				   + " 	 		   FROM MEMBER "
				   + "     		   WHERE GROUP_AUTH = 1 OR GROUP_AUTH = 2 ) "	// 모인원 리스트 가져오기
				   + " AND GROUPS_SEQ=? ";
			
		Connection conn = null;			
		PreparedStatement psmt = null;	
		ResultSet rs = null;	
		
		List<GroupMemberDto> list = new ArrayList<GroupMemberDto>();	
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 getManageMemberList_regi success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/3 getManageMemberList_regi success");
			
			rs = psmt.executeQuery();	
			while(rs.next()) {
				GroupMemberDto dto = new GroupMemberDto(rs.getInt(1), 
														rs.getInt(2), 
														rs.getString(3), 
														rs.getString(4),  
														rs.getString(5), 
														rs.getInt(6));					
				list.add(dto);
			}	
			System.out.println("3/3 getManageMemberList_regi success");
			
		} catch (SQLException e) {
			System.out.println("getManageMemberList_regi fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		System.out.println(list);
		return list;
	}

	public boolean giveAuthtoGroupMem(int auth, String id) {//+++++ 가입하기 클릭시
		String sql = " UPDATE MEMBER "
				   + " SET GROUP_AUTH=?"
				   + " WHERE ID=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		conn = DBConnection.getConnection();
		System.out.println("1/3 giveAuthtoGroupMem success");
		
		try {
			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 giveAuthtoGroupMem success");
			psmt.setInt(1, auth);
			psmt.setString(2, id);
			System.out.println("3/3 giveAuthtoGroupMem success");
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("giveAuthtoGroupMem failed");
		} finally {
			DBClose.close(conn, psmt, null);
		}
		return count>0?true:false;
	}
	
	public boolean deleteGroupMem(String id) {//+++++ 가입하기 클릭시
		String sql = " DELETE FROM GROUP_MEM "
				   + " WHERE ID=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		conn = DBConnection.getConnection();
		System.out.println("1/3 deleteGroupMem success");
		
		try {
			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 deleteGroupMem success");
			psmt.setString(1, id);
			System.out.println("3/3 deleteGroupMem success");
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("deleteGroupMem failed");
		} finally {
			DBClose.close(conn, psmt, null);
		}
		return count>0?true:false;
	}
}
