package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BbsDao;
import dao.MeetingDao;
import db.DBConnection;
import dto.BbsDto;

public class BbsController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberController doProcess()"); //들어왔는지 확인
		DBConnection.initConnection();
		req.setCharacterEncoding("utf-8");
		
		String param = req.getParameter("param");
		
		if(param.equals("write")) { // write parameter값 가지고오면 이동해라
			resp.sendRedirect("detail_bbsWrite.jsp"); // 수정 BoardWrite.jsp -> detail_bbsWrite.jsp
		}
		else if(param.equals("save")) {				// submit으로 넘기니까 지워도 되는건가
			System.out.println("detail_bbs.jsp"); // 수정 BoardList.jsp -> detail_bbs.jsp
		}else if(param.equals("groupbbs")) {
			RequestDispatcher rd = req.getRequestDispatcher("detail_main_groupdetail.jsp");
			req.setAttribute("param", "detail_bbs");// 객체명,객체
			req.setAttribute("seq", req.getParameter("seq"));
			rd.forward(req, resp);
		} else if (param.equals("bbsWriteAf")) { /* 추가 */
			
			String id = req.getParameter("id");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String type = req.getParameter("type");
			
			BbsDao dao = BbsDao.getInstance(); /* 추가 */
			boolean isS = dao.addBbs(new BbsDto(1, id, title, content, type));
			if(isS) {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('성공적으로 글이 작성되었습니다'); location.href='main_findGroup_main.jsp';</script>");			 
				out.flush();
			}else {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('글작성 실패하였습니다'); location.href='main_createGroup.jsp';</script>");			 
				out.flush();
			}
		} else if (param.equals("bbsEditAf")) { //0630
			BbsDao dao = BbsDao.getInstance();
			int b_seq = Integer.parseInt(req.getParameter("b_seq"));
			int g_seq = Integer.parseInt(req.getParameter("g_seq"));
			
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String section = req.getParameter("section");
			
			boolean isS = dao.updateBbs(b_seq,(new BbsDto(title, content, section)));
			
			if(isS) {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('성공적으로 게시글을 수정했습니다'); location.href='Bbs?param=groupbbs&seq=" + g_seq + "';</script>");			 
				out.flush();
			}else {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('게시글 수정 실패하였습니다'); location.href='Bbs?param=groupbbs&seq=" + g_seq + "';</script>");			 
				out.flush();
			}
			
		} else if (param.equals("bbsDeleteAf")) {
			int b_seq = Integer.parseInt(req.getParameter("b_seq"));
			int g_seq = Integer.parseInt(req.getParameter("g_seq"));
			System.out.println("bbsDeleteAf 들어옴");
			System.out.println("b_s 들어옴");
			System.out.println("bbsDeleteAf 들어옴");
			
			BbsDao dao = BbsDao.getInstance();
			boolean isS = dao.deleteBbs(b_seq);
			System.out.println();
			if(isS) {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('성공적으로 게시글이 삭제되었습니다'); location.href='Bbs?param=groupbbs&seq=" + g_seq + "';</script>");			 
				out.flush();
			}else {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('게시글삭제 실패하였습니다'); location.href='Bbs?param=groupbbs&seq=" + g_seq + "';</script>");			 
				out.flush();
			}
		}else if(param.equals("addReply")) {
			String id = req.getParameter("id");
			String answer = req.getParameter("answer");
			int bbs_seq = Integer.parseInt(req.getParameter("bbs_seq"));
			int group_seq = Integer.parseInt(req.getParameter("group_seq"));
			BbsDao dao = BbsDao.getInstance();
			boolean isS = dao.reply(bbs_seq, new BbsDto(group_seq, id, answer));
			if(isS) {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('댓글작성완료'); location.href='Bbs?param=groupbbs&seq=" + group_seq + "';</script>");			 
				out.flush();
			}else {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('댓글작성실패'); location.href='Bbs?param=groupbbs&seq=" + group_seq + "';</script>");			 
				out.flush();
			}
		}else if(param.equals("pagging")) {
			RequestDispatcher rd = req.getRequestDispatcher("detail_main_groupdetail.jsp");
			String seq = req.getParameter("seq");
			String choice = req.getParameter("choice");
			String pageNumber = req.getParameter("pageNumber");
			System.out.println("group_seq:"+ seq+" choice:"+choice+" pageNumber:"+ pageNumber);
			req.setAttribute("param", "detail_bbs");// 객체명,객체
			req.setAttribute("seq", seq);
			req.setAttribute("choice", choice);
			req.setAttribute("pageNumber", pageNumber);
			rd.forward(req, resp);
		}
	}
}
