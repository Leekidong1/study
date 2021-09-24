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
import dto.MemberDto;

public class MemberDao {

		private static MemberDao dao = new MemberDao();
		
		private MemberDao() {
			DBConnection.initConnection();
		}
		public static MemberDao getInstance() {
			return dao;
		}
		public boolean addMember(MemberDto dto) {
				String sql = " INSERT INTO MEMBER( MEM_SEQ, ID, PWD, NAME, BIRTH, GENDER, PHONE, "
						   + " 						EMAIL, CATEGORY, LOCATION, AUTH, GROUP_AUTH, JOINDATE, DEL) "
						   + " VALUES(SEQ_MEMBER.NEXTVAL , ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, 0, SYSDATE, 0) ";
				
				Connection conn = null;
				PreparedStatement psmt = null;
				int count = 0;
				
				try {
				conn = DBConnection.getConnection();
				System.out.println("1/3 addMember success");
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getId());
				psmt.setString(2, dto.getPwd());
				psmt.setString(3, dto.getName());
				psmt.setString(4, dto.getBirth());
				psmt.setString(5, dto.getGender());
				psmt.setString(6, dto.getPhone());
				psmt.setString(7, dto.getEmail());
				psmt.setString(8, dto.getCategory());
				psmt.setString(9, dto.getLocation());
				System.out.println("2/3 addMember success");
				
				count = psmt.executeUpdate();
				System.out.println("3/3 addMember success");
				
				}catch (SQLException e) {
					System.out.println("addMember fail");
					e.printStackTrace();
				}finally {
					DBClose.close(conn, psmt, null);
				}
				return count>0?true:false;
		}
		
		public boolean getId(String id) {	//리턴값 boolean
			
			String sql = " SELECT ID " 
					   + " FROM MEMBER " 
					   + " WHERE ID=? ";
			 //count=0 나오면 중복x =1 나오면 중복!
		
			/*String sql = " SELECT ID " 
					   + " FROM MEMBER " 
					   + " WHERE ID=? ";*/
			
			Connection conn = null; 		//DB 연결
			PreparedStatement psmt = null;	//쿼리문을 실행하기 위한 목적
			ResultSet rs = null;			//SELECT 구문 있으면 RS 있음 ->결과값 가져오려고
	
			boolean findLId = false;			
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("1/3 getId success");
					
				psmt = conn.prepareStatement(sql);	//psmt를 쓰면 데이터를 넣어야한다.
				psmt.setString(1,id);	//(String id)

				System.out.println("2/3 getId success"); 
				
				rs = psmt.executeQuery();		//리턴값이 rs
				if(rs.next()) {
					findLId = true;			//id가 있냐 없냐가 중요	
				}
				System.out.println("3/3 getId success");
			} catch (SQLException e) {
				System.out.println("getId fail");
				e.printStackTrace();
			}finally {
				DBClose.close(conn, psmt, rs);
			}
			return findLId;
		}

		public MemberDto login(MemberDto dto) {
			String sql =" SELECT MEM_SEQ, ID, PWD, NAME, BIRTH, GENDER, PHONE, "
					   + "		EMAIL, CATEGORY, LOCATION, AUTH, GROUP_AUTH, JOINDATE, DEL "
					   + " FROM MEMBER "
					   + " WHERE ID=? AND PWD=? ";
			
			
			Connection conn = null; 		
			PreparedStatement psmt = null;	
			ResultSet rs = null;
		
			MemberDto mem = null;	//리턴해줄것
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("1/3 login success");
				
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, dto.getId());
				psmt.setString(2, dto.getPwd());
				System.out.println("2/3 login success");
				
				rs = psmt.executeQuery();
				
				if(rs.next()) {	//찾았을때 여기로 들어온다
					int seq = rs.getInt(1);
					String id= rs.getString(2);
					String pwd= rs.getString(3);
					String name= rs.getString(4);
					String birth= rs.getString(5);
					String gender= rs.getString(6);
					String phone= rs.getString(7);
					String email= rs.getString(8);
					String category= rs.getString(9);
					String location= rs.getString(10);
					int auth = rs.getInt(11);
					int group_auth = rs.getInt(12);
					String joindate = rs.getString(13);
					int del = rs.getInt(14);
					mem = new MemberDto(seq,id,pwd,name,birth,gender,phone,email,category,location,auth,group_auth,joindate,del);
					
				}
				System.out.println("3/3 login success");
				
			} catch (SQLException e) {
				System.out.println("login fail");
				e.printStackTrace();
			} finally {
				DBClose.close(conn, psmt, rs);
			}
			
			return mem;
	}
		public List<MemberDto> getList() {
			String sql = " SELECT * FROM MEMBER ";
			
			Connection conn = DBConnection.getConnection();
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			List<MemberDto> list = new ArrayList<MemberDto>();
			
			try {
				conn = DBConnection.getConnection();
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();
				while(rs.next()) {	//다수의 데이터만 while //한개의 데이터는 if
					int seq = rs.getInt(1);
					String id= rs.getString(2);
					String pwd= rs.getString(3);
					String name= rs.getString(4);
					String birth= rs.getString(5);
					String gender= rs.getString(6);
					String phone= rs.getString(7);
					String email= rs.getString(8);
					String category= rs.getString(9);
					String location= rs.getString(10);
					int auth = rs.getInt(11);
					int group_auth = rs.getInt(12);
					String joindate = rs.getString(13);
					int del = rs.getInt(14);
					
					list.add(new MemberDto(seq, id, pwd, name, birth, gender, phone, email, category, location, auth, group_auth, joindate, del));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBClose.close(conn, psmt, rs);
			}
			return list;
		}
		
		public MemberDto getMember(String searchId) {
			String sql =" SELECT MEM_SEQ, ID, PWD, NAME, BIRTH, GENDER, PHONE, "
					   + "		EMAIL, CATEGORY, LOCATION, AUTH, GROUP_AUTH, JOINDATE, DEL "
					   + " FROM MEMBER "
					   + " WHERE ID=?";
			
			
			Connection conn = null; 		
			PreparedStatement psmt = null;	
			ResultSet rs = null;
		
			MemberDto mem = null;	//리턴해줄것
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("1/3 getMember success");
				
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, searchId);
				System.out.println("2/3 getMember success");
				
				rs = psmt.executeQuery();
				
				if(rs.next()) {	//찾았을때 여기로 들어온다
					int seq = rs.getInt(1);
					String id= rs.getString(2);
					String pwd= rs.getString(3);
					String name= rs.getString(4);
					String birth= rs.getString(5);
					String gender= rs.getString(6);
					String phone= rs.getString(7);
					String email= rs.getString(8);
					String category= rs.getString(9);
					String location= rs.getString(10);
					int auth = rs.getInt(11);
					int group_auth = rs.getInt(12);
					String joindate = rs.getString(13);
					int del = rs.getInt(14);
					
					mem = new MemberDto(seq,id,pwd,name,birth,gender,phone,email,category,location,auth,group_auth,joindate,del);
					
				}
				System.out.println("3/3 getMember success");
				
			} catch (SQLException e) {
				System.out.println("getMember fail");
				e.printStackTrace();
			} finally {
				DBClose.close(conn, psmt, rs);
			}
			
			return mem;
	}
		
		public boolean addGROUP_AUTH(String id) {
			String sql = " UPDATE MEMBER "
					   + " SET GROUP_AUTH = 3 "		//모임장
					   + " WHERE ID=? ";
			
			Connection conn = null;
			PreparedStatement psmt = null;
			int count = 0;
			
			try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 addGROUP_AUTH success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			System.out.println("2/3 addGROUP_AUTH success");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 addGROUP_AUTH success");
			
			}catch (SQLException e) {
				System.out.println("addGROUP_AUTH fail");
				e.printStackTrace();
			}finally {
				DBClose.close(conn, psmt, null);
			}
			return count>0?true:false;
	}
	
		public int getGroup_Auth(String searchId) {
			String sql = " SELECT GROUP_AUTH "
					   + " FROM MEMBER "
					   + " WHERE ID=?";
			
			
			Connection conn = null; 		
			PreparedStatement psmt = null;	
			ResultSet rs = null;
		
			int group_auth = 0;
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("1/3 getGroup_Auth success");
				
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, searchId);
				System.out.println("2/3 getGroup_Auth success");
				
				rs = psmt.executeQuery();
				
				if(rs.next()) {	//찾았을때 여기로 들어온다
					group_auth = rs.getInt(1);
				}
				System.out.println("3/3 getGroup_Auth success");
				
			} catch (SQLException e) {
				System.out.println("getGroup_Auth fail");
				e.printStackTrace();
			} finally {
				DBClose.close(conn, psmt, rs);
			}
			
			return group_auth;
	}
	
		public boolean updateMember(MemberDto dto) {
			String sql = " UPDATE MEMBER "
					   + " SET  PWD=?, NAME=?, BIRTH=?, GENDER=?, PHONE=?, EMAIL=?, CATEGORY=? " ;
					   if(!dto.getLocation().equals("")) {
					   sql += ", LOCATION='" + dto.getLocation() + "' " ;
					   }
					   sql += " WHERE ID=? ";
			
			Connection conn = null;
			PreparedStatement psmt = null;
			int count = 0;
			
			try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 addMember success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getPwd());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getBirth());
			psmt.setString(4, dto.getGender());
			psmt.setString(5, dto.getPhone());
			psmt.setString(6, dto.getEmail());
			psmt.setString(7, dto.getCategory());
			psmt.setString(8, dto.getId());
			System.out.println("2/3 addMember success");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 addMember success");
			
			}catch (SQLException e) {
				System.out.println("addMember fail");
				e.printStackTrace();
			}finally {
				DBClose.close(conn, psmt, null);
			}
			return count>0?true:false;
	}
		
		public boolean deleteMember(String id) {
			String sql = " DELETE FROM MEMBER "
					   + " WHERE ID=? ";
			
			Connection conn = null;
			PreparedStatement psmt = null;
			int count = 0;
			
			try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 deleteMember success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			System.out.println("2/3 deleteMember success");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 deleteMember success");
			
			}catch (SQLException e) {
				System.out.println("deleteMember fail");
				e.printStackTrace();
			}finally {
				DBClose.close(conn, psmt, null);
			}
			return count>0?true:false;
	}	
		
		public List<MemberDto> adminMemberList(String category, String searchText, String sdate, String edate ) {//어드민
			
			String sql = " SELECT MEM_SEQ, ID, PWD, NAME, BIRTH, GENDER, PHONE, EMAIL, CATEGORY, LOCATION, AUTH, GROUP_AUTH, JOINDATE, DEL "
					   + " FROM MEMBER ";
			
			if(!sdate.equals("") && !edate.equals("") && !searchText.equals("")) {
				System.out.println("날짜,카테고리검색 둘다");
				sql = sql + " WHERE JOINDATE BETWEEN TO_DATE('" + sdate + "') AND TO_DATE('" + edate + "') ";
					if(category.equals("id")) {
						sql = sql + " AND ID LIKE '%" + searchText + "%' " ;
					}else if(category.equals("name")) {
						sql = sql + " AND NAME LIKE '%" + searchText + "%' " ;
					}else if(category.equals("address")) {
						sql = sql + " AND LOCATION LIKE '%" + searchText + "%' " ;
					}else if(category.equals("interest")) {
						sql = sql + " AND CATEGORY LIKE '%" + searchText + "%' " ;
					}
				
			}else if(!searchText.equals("")) {
				System.out.println("카테고리검색만");
				if(category.equals("id")) {
					sql = sql + " WHERE ID LIKE '%" + searchText + "%' " ;
				}else if(category.equals("name")) {
					sql = sql + " WHERE NAME LIKE '%" + searchText + "%' " ;
				}else if(category.equals("address")) {
					sql = sql + " WHERE LOCATION LIKE '%" + searchText + "%' " ;
				}else if(category.equals("interest")) {
					sql = sql + " WHERE CATEGORY LIKE '%" + searchText + "%' " ;
				}
			}else if(!sdate.equals("") && !edate.equals("")) {
				System.out.println("날짜검색만");
				sql = sql + " WHERE JOINDATE BETWEEN TO_DATE('" + sdate + "') AND TO_DATE('" + edate + "') ";
			}
			
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			List<MemberDto> list = new ArrayList<MemberDto>();
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("1/4 adminMemberList success");
				psmt = conn.prepareStatement(sql);
				System.out.println("2/4 adminMemberList success");
				rs = psmt.executeQuery();
				System.out.println("3/4 adminMemberList success");
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
				System.out.println("4/4 adminMemberList success");
			} catch (SQLException e) {
				System.out.println("adminMemberList fail");
				e.printStackTrace();
			} finally {
				DBClose.close(conn, psmt, rs);
			}
			return list;
		}	
}
