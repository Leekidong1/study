package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReviewDao;
import dto.ReviewDto;

public class ReviewController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}

	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = req.getRequestDispatcher("detail_main_groupdetail.jsp");
		String param = req.getParameter("param");
		ReviewDao dao = ReviewDao.getInstance();
		
		System.out.println(param);
		
		if (param.equals("groupreview")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			List<ReviewDto> list = dao.getReviewBbsList(seq);
			System.out.println(list);//null?
			req.setAttribute("param", "detail_groupreview");// 객체명,객체
			rd.forward(req, resp);
		} else if (param.equals("reviewWriteBtn")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
 		resp.sendRedirect("detail_reviewwrite.jsp?seq="+seq); //+++++ 시퀀스날림
		} else if (param.equals("reviewWriteAf")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			String id = req.getParameter("id");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			int score = Integer.parseInt(req.getParameter("score"));

			System.out.println("reviewWriteAf seq는" + seq);
			System.out.println("id:"+id+" title:"+title+" content:"+content+" score:"+score);
					
			boolean isS = dao.addReview(seq, new ReviewDto(id, title, score, content));
			if(isS) {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('성공적으로 리뷰 작성되었습니다'); location.href='ReviewController?param=groupreview&seq=" + seq + "';</script>");			 
				out.flush();
			}else {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('리뷰작성 실패하였습니다'); location.href='ReviewController?param=reviewWriteBtn&seq=" + seq + "';</script>");			 
				out.flush();
			}
			
		} else if (param.equals("deleteReview")) {
			int review_seq = Integer.parseInt(req.getParameter("seq"));
			int group_seq = Integer.parseInt(req.getParameter("group_seq"));
			boolean isS = dao.deleteReview(review_seq);
			if(isS) {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('성공적으로 리뷰를 삭제하였습니다'); location.href='ReviewController?param=groupreview&seq=" + group_seq + "';</script>");			 
				out.flush();
			}else {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('리뷰삭제 실패하였습니다'); location.href='ReviewController?param=reviewWriteBtn&seq=" + group_seq + "';</script>");			 
				out.flush();
			}
		}else if (param.equals("reviewUpdateAf")) {
			int review_seq = Integer.parseInt(req.getParameter("review_seq"));
			int group_seq = Integer.parseInt(req.getParameter("group_seq"));
			String id = req.getParameter("id");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			int score = Integer.parseInt(req.getParameter("score"));
			
			boolean isS = dao.updateReview(review_seq,new ReviewDto(id, title, score, content));
			if(isS) {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('성공적으로 리뷰 작성되었습니다'); location.href='ReviewController?param=groupreview&seq=" + group_seq + "';</script>");			 
				out.flush();
			}else {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('리뷰작성 실패하였습니다'); location.href='ReviewController?param=reviewWriteBtn&seq=" + group_seq + "';</script>");			 
				out.flush();
			}
		}
		
	}
	
}
