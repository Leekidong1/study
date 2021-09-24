package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.BbsDto;

public class BbsDao {
	
	private static BbsDao dao = null;
	
	private BbsDao() {
		DBConnection.initConnection(); /* 추가 */
	}
	
	public static BbsDao getInstance() {
		if(dao == null) {
			dao = new BbsDao();
		}
		return dao; 
	}
	
	public BbsDto getBbs(int seq) {//0630 getbbsdetail
		
		String sql = " SELECT BBS_SEQ, GROUPS_SEQ, ID, REF, STEP, DEPTH, "
				+ "			TITLE, CONTENT, TYPE, WDATE, DEL "
				+ "	   FROM BBS "
				+ "    WHERE BBS_SEQ=? ";
		
		Connection conn = null;			
		PreparedStatement psmt = null;	
		ResultSet rs = null;	
		
		BbsDto dto = null;		
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 getBbs success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/3 getBbs success");
			
			rs = psmt.executeQuery();	
			if(rs.next()) {
				dto = new BbsDto(	rs.getInt(1), 
									rs.getInt(2), 
									rs.getString(3), 
									rs.getInt(4), 
									rs.getInt(5), 
									rs.getInt(6), 
									rs.getString(7), 
									rs.getString(8), 
									rs.getString(9), 
									rs.getString(10), 
									rs.getInt(11));
			}	
			System.out.println("3/3 getBbs success");
			
		} catch (SQLException e) {
			System.out.println("getBbs fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return dto;
	}
	
	// 글 추가하기
	/* 6/28 수정 : 수정 add -> addBbs */
	public boolean addBbs(BbsDto dto) {
		/* 6/28 수정 : 수정 컬럼명변경 + COUNT_LIKEIT삭제 */
		String sql = " INSERT INTO BBS(BBS_SEQ, GROUPS_SEQ, ID, REF, STEP, DEPTH, "
				   + "                 TITLE, CONTENT, TYPE, WDATE, DEL) "
				   + " VALUES(SEQ_BBS.NEXTVAL, ?, ?, " /* 6/28 수정 : 'aaa'-> ?로 변경 */
				   + "					(SELECT NVL(MAX(REF), 0)+1 FROM BBS), 0, 0, "
				   + "					?, ?, ?, SYSDATE, 0 ) ";
		
		Connection conn = null;			
		PreparedStatement psmt = null;	
		
		int count = 0; // 추가되었다
		
		try {
			conn = DBConnection.getConnection(); // 테이블 얻어오기			
			System.out.println("1/3 writeBbs success");
			
			psmt = conn.prepareStatement(sql);
			/* 6/28 수정 : 추가 50,51줄 */
			psmt.setInt(1, dto.getGroups_seq());
			psmt.setString(2, dto.getId());
			psmt.setString(3, dto.getTitle()); // 내가 넘겨받을 부분 작성 즉 ? 인 부분 넣으면됨
			psmt.setString(4, dto.getContent());
			psmt.setString(5, dto.getType());
			System.out.println("2/3 writeBbs success");
			
			count = psmt.executeUpdate(); // 실제로 db에 데이터 넣기
			System.out.println("3/3 writeBbs success");
			
		} catch (SQLException e) {
			System.out.println("writeBbs fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		return count>0?true:false;
	}
	
	// 글목록 다 불러오기
	public List<BbsDto> getBBSList() {
		/* 6/28 수정 : 수정 컬럼명변경 */
		String sql = " SELECT BBS_SEQ, GROUPS_SEQ, ID, REF, STEP, DEPTH, "
				   + "		 	  TITLE, CONTENT, TYPE, WDATE, "
				   + "			   DEL "
				   + " FROM BBS "
				   + " ORDER BY REF DESC, STEP ASC ";
		
		Connection conn = null;				// DB연결	
		PreparedStatement psmt = null;		// Query문 실행
		ResultSet rs = null;				// 결과 취득
		
		List<BbsDto> list = new ArrayList<BbsDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getList success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/4 getList success");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 getList success");
			
			while(rs.next()) { 
				/* 6/28 수정 : 수정 likecount 빠지면서 변경*/
				BbsDto dto = new BbsDto(rs.getInt(1),
										rs.getInt(2),
										rs.getString(3),
										rs.getInt(4),
										rs.getInt(5),
										rs.getInt(6),
										rs.getString(7),
										rs.getString(8),
										rs.getString(9),
										rs.getString(10),
										rs.getInt(11));
				list.add(dto);
			}
			System.out.println("4/4 getBbsList success");
			
		} catch (SQLException e) {
			System.out.println("getList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		System.out.println(list); /* 6/28 수정 : 추가 */
		return list;	
	}
	
	
	// 검색하기
	public List<BbsDto> getBbsSearchList(String choice, int pageNumber) {	// 0- 1~10 ,1- 11~20 ,2- 21~30
				System.out.println(choice);
		String sql = " SELECT BBS_SEQ, GROUPS_SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, TYPE, WDATE, DEL "
				   + " FROM ";
		
		// 1. number설정
		sql += "(SELECT ROW_NUMBER()OVER(ORDER BY REF DESC, STEP ASC) AS RNUM, "
				+ "		BBS_SEQ, GROUPS_SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, TYPE, WDATE, DEL "
				+ "	FROM BBS " ;
				if(!choice.equals("") && !choice.equals("all")) {
					sql += " WHERE TYPE='" + choice + "' " ;
				}
				sql += " ORDER BY REF DESC, STEP ASC) "
				+ " WHERE RNUM >= ? AND RNUM <= ? " ;
		

		int start, end;
		start = 1 + 10 * pageNumber;	// 0 -> 1 ~ 10	1 -> 11 ~ 20
		end = 10 + 10 * pageNumber;

		Connection conn = null;			// DB 연결
		PreparedStatement psmt = null;	// Query문을 실행
		ResultSet rs = null;			// 결과 취득
		
		List<BbsDto> list = new ArrayList<BbsDto>();

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getBbsList success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			System.out.println("2/4 getBbsList success");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 getBbsList success");
			
			while(rs.next()) {
				BbsDto dto = new BbsDto(rs.getInt(1),
										rs.getInt(2),
										rs.getString(3),
										rs.getInt(4),
										rs.getInt(5),
										rs.getInt(6),
										rs.getString(7),
										rs.getString(8),
										rs.getString(9),
										rs.getString(10),
										rs.getInt(11));
				list.add(dto);
			}
			System.out.println("4/4 getBbsList success");
			
		} catch (SQLException e) {
			System.out.println("getBbsList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return list;		
	}
	
	// 수정하기
	public boolean updateBbs(int bbs_seq, BbsDto dto) {
		String sql = " UPDATE BBS "
				   + " SET TITLE=?, CONTENT=?, TYPE=? "
				   + " WHERE BBS_SEQ=? ";//0630
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 S updateBbs");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getType());
			psmt.setInt(4, bbs_seq);
			
			System.out.println("2/3 S updateBbs");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 S updateBbs");
			
		} catch (Exception e) {
			System.out.println("updateBbs fail");
			e.printStackTrace();
		} finally{
			DBClose.close(conn, psmt, null);			
		}		
		
		return count>0?true:false;
	}
	
	// 삭제하기
	public boolean deleteBbs(int bbs_seq) {
		
		String sql = " UPDATE BBS "
					+ " SET DEL=1 "
					+ " WHERE BBS_SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 S deleteBbs");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bbs_seq);
			System.out.println("2/3 S deleteBbs");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 S deleteBbs");
			
		} catch (Exception e) {		
			System.out.println("fail deleteBbs");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);			
		}
		
		return count>0?true:false;
	}
	
	public int getAllBbs() {
		
		String sql = " SELECT COUNT(*) FROM BBS ";
		
		
		Connection conn = null;			// DB 연결
		PreparedStatement psmt = null;	// Query문을 실행
		ResultSet rs = null;			// 결과 취득
		
		int len = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 getAllBbs success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 getAllBbs success");
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				len = rs.getInt(1);
			}
			System.out.println("3/3 getAllBbs success");
			
		} catch (SQLException e) {
			System.out.println("getAllBbs fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return len;
	}
	
	public boolean reply(int bbs_seq, BbsDto dto) {
		
		String sql1 = " UPDATE BBS "
				    + " SET STEP=STEP+1 "
				    + " WHERE REF = (SELECT REF FROM BBS WHERE BBS_SEQ=?) "
				    + "     AND STEP > (SELECT STEP FROM BBS WHERE BBS_SEQ=?)";
		
		String sql2 = " INSERT INTO BBS(BBS_SEQ, GROUPS_SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, TYPE, WDATE, DEL) "
				   + " VALUES(SEQ_BBS.NEXTVAL, ?, ?, (SELECT REF FROM BBS WHERE BBS_SEQ=?), "
				   + "							   (SELECT STEP FROM BBS WHERE BBS_SEQ=?) + 1, "
				   + "							   (SELECT DEPTH FROM BBS WHERE BBS_SEQ=?) + 1, "
				   + " 			'reply', ?, 'reply', SYSDATE, 0) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			System.out.println("1/6 reply success");
			//첫번째 쿼리문 입력
			psmt = conn.prepareStatement(sql1);
			psmt.setInt(1, bbs_seq);
			psmt.setInt(2, bbs_seq);
			System.out.println("2/6 reply success");
			//첫번째 쿼리문 입력완료
			count = psmt.executeUpdate();
			System.out.println("3/6 reply success");
			//쿼리문 입력 초기화
			psmt.clearParameters();
			//두번째 쿼리문 입력
			psmt = conn.prepareStatement(sql2);
			psmt.setInt(1,dto.getGroups_seq());
			psmt.setString(2,dto.getId());
			psmt.setInt(3, bbs_seq);
			psmt.setInt(4, bbs_seq);
			psmt.setInt(5, bbs_seq);
			psmt.setString(6,dto.getContent());
			System.out.println("4/6 reply success");
			//두번째 쿼리문 완료
			count = psmt.executeUpdate();
			System.out.println("5/6 reply success");
			//DB적용 완료
			conn.commit();
			System.out.println("6/6 reply success");
		} catch (SQLException e) {
			System.out.println("reply fail");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBClose.close(conn, psmt, null);
		}
		return count>0?true:false;
	}
}