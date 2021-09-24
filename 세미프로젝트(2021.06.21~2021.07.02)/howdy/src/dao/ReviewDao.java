package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.ReviewDto;

public class ReviewDao {
	
	private static ReviewDao dao = new ReviewDao();
	
	private ReviewDao() {
		DBConnection.initConnection();
	}
	
	public static ReviewDao getInstance() {
		return dao;
	}
	
	public ReviewDto getReview(int seq) { //리뷰보기 사용안할듯
		String sql = " SELECT REVIEW_SEQ, GROUPS_SEQ, ID, TITLE, WDATE, SCORE, CONTENT, DEL "
				   + " FROM REVIEW "
				   + " WHERE REVIEW_SEQ=? ";
		
		Connection conn = null;			
		PreparedStatement psmt = null;	
		ResultSet rs = null;	
		
		ReviewDto dto = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 getReview success");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/3 getReview success");
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				 dto = new ReviewDto(rs.getInt(1), 
									 rs.getInt(2), 
									 rs.getString(3), 
									 rs.getString(4), 
									 rs.getString(5), 
									 rs.getInt(6), 
									 rs.getString(7), 
									 rs.getInt(8));
			}
			System.out.println("3/3 getReview success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("getReview failed");
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return dto;
		
	}
	
	public boolean deleteReview(int review_seq) { //리뷰삭제
		String sql = " UPDATE REVIEW "
				   + " SET DEL=1 "
			       + " WHERE REVIEW_SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 deleteReview success");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, review_seq);
			System.out.println("2/3 deleteReview success");
			count = psmt.executeUpdate();
			System.out.println("3/3 deleteReview success");
		} catch (SQLException e) {
			System.out.println("deleteReview fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		return count>0?true:false;
	}
	
	public boolean updateReview(int review_seq, ReviewDto dto) { //리뷰삭제
		String sql = " UPDATE REVIEW "
				   + " SET TITLE=?, SCORE=?, CONTENT=? "
			       + " WHERE REVIEW_SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 updateReview success");
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getTitle());
			psmt.setInt(2, dto.getScore());
			psmt.setString(3, dto.getContent());
			psmt.setInt(4, review_seq);
			System.out.println("2/3 updateReview success");
			count = psmt.executeUpdate();
			System.out.println("3/3 updateReview success");
		} catch (SQLException e) {
			System.out.println("updateReview fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		return count>0?true:false;
	}
	
	public boolean addReview(int seq,ReviewDto dto) {//리뷰추가
		
		String sql = " INSERT INTO REVIEW ( REVIEW_SEQ, GROUPS_SEQ, ID, TITLE, WDATE, SCORE, CONTENT, DEL ) "
				   + " VALUES( SEQ_REVIEW.NEXTVAL, ?, ?, ?, SYSDATE, ?, ?, 0 ) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("addReview 1/3");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			psmt.setString(2, dto.getId());
			psmt.setString(3, dto.getTitle());
			psmt.setInt(4, dto.getScore());
			psmt.setString(5, dto.getContent());
			System.out.println("addReview 2/3");
			
			count = psmt.executeUpdate();
			System.out.println("addReview 3/3");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("addReview failed");
		} finally {
			DBClose.close(conn, psmt, null);
		}
		
		return count>0?true:false;//카운트 == 1 -> true반환
		
	} 
	
	public List<ReviewDto> getReviewBbsList(int seq){//+++++리뷰리스트 시퀀스 파라미터추가
		
		String sql = " SELECT REVIEW_SEQ, GROUPS_SEQ, ID, TITLE, WDATE, SCORE, CONTENT, DEL "
				+ "	   FROM REVIEW "
				+ " WHERE GROUPS_SEQ=? "
				+ " ORDER BY REVIEW_SEQ DESC ";
		
		Connection conn = null;			
		PreparedStatement psmt = null;	
		ResultSet rs = null;	
		
		List<ReviewDto> list = new ArrayList<ReviewDto>();	
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 getReviewBbsList success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 getReviewBbsList success");
			psmt.setInt(1, seq);
			
			rs = psmt.executeQuery();	
			while(rs.next()) {
				ReviewDto dto = new ReviewDto(rs.getInt(1), 
						 rs.getInt(2), 
						 rs.getString(3), 
						 rs.getString(4), 
						 rs.getString(5), 
						 rs.getInt(6), 
						 rs.getString(7), 
						 rs.getInt(8));
				list.add(dto);
			}	
			System.out.println("3/3 getReviewBbsList success");
			
		} catch (SQLException e) {
			System.out.println("getReviewBbsList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return list;
	}
	
	public int avgScore(int seq){//+++++리뷰리스트 시퀀스 파라미터추가
		
		String sql = " SELECT ROUND(AVG(SCORE)) "
				   + " FROM REVIEW "
				   + " WHERE GROUPS_SEQ=? ";
		Connection conn = null;			
		PreparedStatement psmt = null;	
		ResultSet rs = null;	
		
		int avgScore = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 avgScore success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 avgScore success");
			psmt.setInt(1, seq);
			
			rs = psmt.executeQuery();	
			if(rs.next()) {
				avgScore = rs.getInt(1);
			}	
			System.out.println("3/3 avgScore success");
			
		} catch (SQLException e) {
			System.out.println("avgScore fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return avgScore;
	}

}

