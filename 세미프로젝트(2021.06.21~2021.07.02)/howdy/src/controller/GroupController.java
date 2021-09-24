package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GroupDao;
import dao.MeetingDao;
import db.DBConnection;
import dto.GroupDto;
import dto.MeetingDto;
import net.sf.json.JSONObject;

public class GroupController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");	// 한글깨짐방지
		DBConnection.initConnection();
		String param = req.getParameter("param");
		
		if(param.equals("grouplist")) {
			
			String location = req.getParameter("location");
			String category = req.getParameter("category");
			String sdate = req.getParameter("startdate").replace("-", "");	// 작성않하면 빈값으로 넘어옴
			String edate = req.getParameter("enddate").replace("-", "");	// 작성않하면 빈값으로 넘어옴
			
			System.out.println("location :"+ location);
			System.out.println("category :"+ category);
			System.out.println("startdate :"+ sdate);
			System.out.println("enddate :"+ edate);
			
			GroupDao Gdao = GroupDao.getInstance();
			MeetingDao Mdao = MeetingDao.getInstance();
			
			List<GroupDto> Glist = null;
			List<MeetingDto> Mlist = null;
			
			// 모임리스트 찾기
			if(location.equals("") && category.equals("")) {	// 지역검색이 빈값일 때,
				Glist = Gdao.getGroupList();
			}else { // 지역검색이 빈값이 아닐 때,
				Glist = Gdao.getSearchList(location, category);
			}
			
			// 지도에서 일정찾기
			if(!sdate.equals("") || !edate.equals("") || !location.equals("") || !category.equals("")) {	// 해당일정 모임찾기
				System.out.println("일정카테고리 있음");
				Mlist = Mdao.getSearchingList(location, sdate, edate, category);
			}else {
				System.out.println("일정카테고리 모두없음");
				Mlist = Mdao.getMeetingList(-1);
			}
			
			JSONObject jobj = new JSONObject();
			jobj.put("Glist", Glist);
			jobj.put("Mlist", Mlist);
			resp.setContentType("application/x-json; charset=utf-8");
			resp.getWriter().print(jobj);
		}else if(param.equals("manageGroups")) {
			
			String category = req.getParameter("category");
			if(category == null) {
				category = "";
			}
			String searchText = req.getParameter("searchText");
			String sdate = req.getParameter("startdate").replace("-", "");	// 작성않하면 빈값으로 넘어옴
			String edate = req.getParameter("enddate").replace("-", "");	// 작성않하면 빈값으로 넘어옴

			
			System.out.println("category :"+ category);
			System.out.println("searchText :"+ searchText);
			System.out.println("sdate :"+ sdate);
			System.out.println("edate :"+ edate);
			
			GroupDao dao = GroupDao.getInstance();
			List<GroupDto> list = dao.manageGroupList(category, searchText, sdate, edate);
			
			JSONObject jobj = new JSONObject();
			jobj.put("list", list);
			resp.setContentType("application/x-json; charset=utf-8");
			resp.getWriter().print(jobj);
		}else if(param.equals("deleteGroupAf")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			GroupDao dao = GroupDao.getInstance();
			boolean isS = dao.deleteGroup(seq);
			if(isS) {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('성공적으로 모임이 삭제되었습니다'); location.href='admin_Group.jsp';</script>");			 
				out.flush();
			}else {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('모임삭제 실패하였습니다'); location.href='admin_Group.jsp';</script>");			 
				out.flush();
			}
		}else if(param.equals("groupdesc")) {
			int group_seq = Integer.parseInt(req.getParameter("seq"));
			RequestDispatcher rd = req.getRequestDispatcher("detail_main_groupdetail.jsp");
			req.setAttribute("param", "detail_groupdesc");// 객체명,객체
			req.setAttribute("group_seq", group_seq);
			rd.forward(req, resp);
		// 메인화면에 내 근처 인기모임 리스트
		}else if(param.equals("mainlist")) {	
    		System.out.println("mainlist 들어왔어요");
    		String location = req.getParameter("location");
    		
    		System.out.println(location);
    		
    		GroupDao dao = GroupDao.getInstance();
    		List<GroupDto> list = null;
    		if(!location.equals("")) {	// 고객 지역정보가 있을 때,
    			list = dao.getSearchList(location, "");
    		}else { // 고객 지역정보가 없을 때, 인기 모임 6개 보여주기
    			
    		}
    		
    		JSONObject jobj = new JSONObject();
    		jobj.put("list", list);
    		resp.setContentType("application/x-json; charset=utf-8");
			resp.getWriter().print(jobj);
    	}else if(param.equals("mypage_group_manage")) {
    		System.out.println("mypage_group_manage");
    		String id = req.getParameter("id");
    		GroupDao dao = GroupDao.getInstance();
    		List<GroupDto> list = dao.getGroupList_Mypage(id);
    		
    		JSONObject jobj = new JSONObject();
    		jobj.put("list", list);
    		resp.setContentType("application/x-json; charset=utf-8");
			resp.getWriter().print(jobj);
    	}else if(param.equals("mypage_mylist")) {
    		System.out.println("mypage_mylist");
    		GroupDao dao = GroupDao.getInstance();
    		String id = req.getParameter("id");
    		List<GroupDto> list = dao.getGroupList_Mypage_mylist(id);  		
    		JSONObject jobj = new JSONObject();
    		jobj.put("list", list);
    		resp.setContentType("application/x-json; charset=utf-8");
			resp.getWriter().print(jobj);
    	}
	}
}
