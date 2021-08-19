package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GroupMemberDao;
import dao.MeetingDao;
import dao.MeetingMemberDao;
import dao.MemberDao;
import db.DBConnection;
import dto.GroupMemberDto;
import dto.MeetingDto;
import dto.MeetingMemberDto;
import dto.MemberDto;
import net.sf.json.JSONObject;
import util.CalUtil;

public class MeetingController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 doProcess(req, resp);
	}
    private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("MemberController doProcess()");
    	DBConnection.initConnection();
    	req.setCharacterEncoding("utf-8");
    	
    	String param = req.getParameter("param");
    	
    	if(param.equals("meeting")) {
    		resp.sendRedirect("meeting.jsp");
    	}else if(param.equals("meetingwrite")) {
    		resp.sendRedirect("meetingwrite.jsp");
    
    	}else if(param.equals("metwriteAf")) {
    		System.out.println("MeetingController metwriterAf");
    		
    		int groups_seq = Integer.parseInt(req.getParameter("groups_seq"));
    		String latitude = req.getParameter("latitude");
    		String longitude = req.getParameter("longitude");
    		String category = req.getParameter("category");
    		String location = req.getParameter("address").trim() + " " +
    				         req.getParameter("detailAddress").trim();
    		int max_mem = Integer.parseInt(req.getParameter("max_mem").trim());
    		
    		String syear = req.getParameter("syear");
    		String smonth = req.getParameter("smonth");
    		String sday = req.getParameter("sday");
    		String shour = req.getParameter("shour");
    		String smin = req.getParameter("smin");
    		String sdate = syear + CalUtil.two(smonth) + CalUtil.two(sday) + CalUtil.two(shour) + CalUtil.two(smin);
    		
    		String eyear = req.getParameter("eyear");
    		String emonth = req.getParameter("emonth");
    		String eday = req.getParameter("eday");
    		String ehour = req.getParameter("ehour");
    		String emin = req.getParameter("emin");
    		String edate = eyear + CalUtil.two(emonth) + CalUtil.two(eday) + CalUtil.two(ehour) + CalUtil.two(emin);
    		
    		String title = req.getParameter("title");
    		String content = req.getParameter("content");
    		
    		
    		System.out.println(groups_seq);
    		System.out.println(latitude + " " + longitude);
    		System.out.println(category + " " + location);
    		System.out.println(sdate);
    		System.out.println(edate);
    		System.out.println(title + " " + content);
    		
    		
    		MeetingDao dao = MeetingDao.getInstance();
    		boolean isS = dao.addMeeting(new MeetingDto(groups_seq, category, location, sdate, edate, title, content, max_mem, latitude, longitude));
    		if(isS) {
    			resp.setContentType("text/html; charset=UTF-8");			 
    			PrintWriter out = resp.getWriter();			 
    			out.println("<script>alert('성공적으로 일정등록이 되었습니다'); location.href='mypage_manageGroup.jsp';</script>");			 
    			out.flush();
    		}else {
    			resp.setContentType("text/html; charset=UTF-8");			 
    			PrintWriter out = resp.getWriter();			 
    			out.println("<script>alert('일정등록 실패하였습니다'); location.href='mypage_manageGroup.jsp';</script>");			 
    			out.flush();
    		} 	
    	}else if(param.equals("updateMeetingAf")) {
    		int meeting_seq = Integer.parseInt(req.getParameter("meeting_seq"));
    		String latitude = req.getParameter("latitude");
    		String longitude = req.getParameter("longitude");
    		String category = req.getParameter("category");
    		String location = req.getParameter("address").trim() + " " +
    				         req.getParameter("detailAddress").trim();
    		int max_mem = Integer.parseInt(req.getParameter("max_mem").trim());
    		
    		String syear = req.getParameter("syear");
    		String smonth = req.getParameter("smonth");
    		String sday = req.getParameter("sday");
    		String shour = req.getParameter("shour");
    		String smin = req.getParameter("smin");
    		String sdate = syear + CalUtil.two(smonth) + CalUtil.two(sday) + CalUtil.two(shour) + CalUtil.two(smin);
    		
    		String eyear = req.getParameter("eyear");
    		String emonth = req.getParameter("emonth");
    		String eday = req.getParameter("eday");
    		String ehour = req.getParameter("ehour");
    		String emin = req.getParameter("emin");
    		String edate = eyear + CalUtil.two(emonth) + CalUtil.two(eday) + CalUtil.two(ehour) + CalUtil.two(emin);
    		
    		String title = req.getParameter("title");
    		String content = req.getParameter("content");
    		
    		
    		System.out.println(meeting_seq);
    		System.out.println(latitude + " " + longitude);
    		System.out.println(category + " " + location);
    		System.out.println(sdate);
    		System.out.println(edate);
    		System.out.println(title + " " + content);
    		
    		
    		MeetingDao dao = MeetingDao.getInstance();
    		boolean isS = dao.updateMeeting(meeting_seq,new MeetingDto(0, category, location, sdate, edate, title, content, max_mem, latitude, longitude));
    		if(isS) {
    			resp.setContentType("text/html; charset=UTF-8");			 
    			PrintWriter out = resp.getWriter();			 
    			out.println("<script>alert('성공적으로 일정수정 되었습니다'); location.href='mypage_manageGroup.jsp';</script>");			 
    			out.flush();
    		}	
    	}else if(param.equals("deleteMeeting")) {
    		int meeting_seq = Integer.parseInt(req.getParameter("seq"));
    		MeetingDao dao = MeetingDao.getInstance();
    		boolean isS = dao.deleteMeeting(meeting_seq);
    		if(isS) {
    			resp.setContentType("text/html; charset=UTF-8");			 
    			PrintWriter out = resp.getWriter();			 
    			out.println("<script>alert('성공적으로 일정삭제 되었습니다'); location.href='mypage_manageGroup.jsp';</script>");			 
    			out.flush();
    		}else {
    			resp.setContentType("text/html; charset=UTF-8");			 
    			PrintWriter out = resp.getWriter();			 
    			out.println("<script>alert('삭제를 실패하였습니다'); location.href='mypage_manageGroup.jsp';</script>");			 
    			out.flush();
    		}
    	}else if(param.equals("detail_groupdesc_getMeetinglist")) {
    		int group_seq = Integer.parseInt(req.getParameter("group_seq"));
    		String id = req.getParameter("id");
    		
    		MeetingDao dao = MeetingDao.getInstance();
    		GroupMemberDao Gdao = GroupMemberDao.getInstance();
    		
    		List<MeetingDto> list = dao.getMeetingList(group_seq);
    		List<MemberDto> Glist = Gdao.getGroupMemberList(group_seq);
    		
    		JSONObject jobj = new JSONObject();
			jobj.put("list", list);
			jobj.put("Glist", Glist);
			resp.setContentType("application/x-json; charset=utf-8");
			resp.getWriter().print(jobj);
    	}else if(param.equals("MeetingMember")) {
    		String[] values = req.getParameter("value").split(" ");
    		String id = values[0];
    		int meeting_seq = Integer.parseInt(values[1]);
    		String decision = values[2];
    		int group_seq = Integer.parseInt(values[3]);
    		int max_mem = Integer.parseInt(values[4]);
    		System.out.println(id + " " +meeting_seq+" "+decision+" "+group_seq+" "+max_mem);
    		MeetingMemberDao dao = MeetingMemberDao.getInstance();
    		int meetingMemCount = dao.getCount(meeting_seq);
    		if(max_mem != 0) {
	    		if(meetingMemCount < max_mem) {
		    		if(decision.equals("참여하기")) {
		    			dao.changeAuth(meeting_seq, id);
		    			boolean isS = dao.addMeetingMember(meeting_seq, id);	
		    			System.out.println(isS);
			    		if(isS) {
			    			resp.setContentType("text/html; charset=UTF-8");			 
			    			PrintWriter out = resp.getWriter();			 
			    			out.println("<script>alert('" + id +  "님께서 일정참여하셨습니다.'); location.href='mypage_calendar.jsp';</script>");			 
			    			out.flush();
			    		}
		    		}
	    		}else {
	    			resp.setContentType("text/html; charset=UTF-8");			 
	    			PrintWriter out = resp.getWriter();			 
	    			out.println("<script>alert('정원이 모두 찻습니다.다른 일정을 찾아주세요.'); location.href='detail_main_groupdetail.jsp?seq=" + group_seq + "';</script>");			 
	    			out.flush();
	    		}
    		}else {
    			boolean isS = dao.deleteMeetingMember(meeting_seq,id);
	    		if(isS) {
	    			resp.setContentType("text/html; charset=UTF-8");			 
	    			PrintWriter out = resp.getWriter();			 
	    			out.println("<script>alert('" + id +  "님께서 일정취소하셨습니다.'); location.href='detail_main_groupdetail.jsp?seq=" + group_seq + "';</script>");			 
	    			out.flush();
	    		}
    		}
    	}
	}
}
