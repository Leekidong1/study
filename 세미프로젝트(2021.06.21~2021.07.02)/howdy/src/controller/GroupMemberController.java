package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GroupMemberDao;
import dto.GroupMemberDto;
import net.sf.json.JSONObject;

public class GroupMemberController extends HttpServlet {

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
		RequestDispatcher rd = req.getRequestDispatcher("detail_main_groupdetail.jsp");
		String param = req.getParameter("param");
		System.out.println(param);
		
		if (param.equals("groupmember")) {
			req.setAttribute("param", "detail_groupmember");// 객체명,객체
			rd.forward(req, resp);
		}if (param.equals("joingroup")) {//+++++
			int seq = Integer.parseInt(req.getParameter("seq"));
			String id = req.getParameter("id");
			String name = req.getParameter("name");
			System.out.println(id+ " "+ name);
			GroupMemberDao dao = GroupMemberDao.getInstance();
			boolean isS = dao.addGroupMember(seq, new GroupMemberDto(id,name));
			if(isS) {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('성공적으로 모임에 가입하셨습니다'); location.href='detail_main_groupdetail.jsp?seq=" + seq + "';</script>");			 
				out.flush();
			}else {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('모임가입이 실패하였습니다'); location.href='detail_main_groupdetail.jsp?seq=" + seq + "';</script>");			 
				out.flush();
			}
		}else if(param.equals("manageMember")){
			int group_seq = Integer.parseInt(req.getParameter("group_seq"));
			System.out.println("manageMember의 groupseq:"+ group_seq);
			GroupMemberDao dao = GroupMemberDao.getInstance();
			List<GroupMemberDto> Rlist = dao.getManageMemberList_regi(group_seq);
			List<GroupMemberDto> Mlist = dao.getManageMemberList_manage(group_seq);
			
			JSONObject jobj = new JSONObject();
			jobj.put("Rlist", Rlist);	// 가입대기중인회원
			jobj.put("Mlist", Mlist);	// 모임원
			resp.setContentType("application/x-json; charset=utf-8");
			resp.getWriter().print(jobj);
		}else if(param.equals("changeGroupMemAuth")) {
			String[] groupMemAuth = req.getParameter("groupMemAuth").split(" ");
			int group_seq = Integer.parseInt(groupMemAuth[0]);
			String id = groupMemAuth[1];
			int auth = Integer.parseInt(groupMemAuth[2]);
			GroupMemberDao dao = GroupMemberDao.getInstance();
			boolean isS = false;
			if(auth == 1) {//가입허락
				 isS = dao.giveAuthtoGroupMem(auth, id);
			 }else if (auth == 2) {//부모임장권한부여
				 isS = dao.giveAuthtoGroupMem(auth, id);
			 }else if (auth == 3) {//모임장권한부여
				 isS = dao.giveAuthtoGroupMem(auth, id); 
			 }else {// 가입거절
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('가입 거절하였습니다'); location.href='mypage_manageMember_detail.jsp?seq=" + group_seq + "';</script>");			 
				out.flush(); 
			 }
			
			if(isS) {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('권한 변경하였습니다'); location.href='mypage_manageMember_detail.jsp?seq=" + group_seq + "';</script>");			 
				out.flush(); 
			}
			
		}else if(param.equals("deleteGroupMem")) {
			String[] groupMem = req.getParameter("groupMem").split(" ");
			int group_seq = Integer.parseInt(groupMem[0]);
			String id = groupMem[1];
			int auth = Integer.parseInt(groupMem[2]);
			GroupMemberDao dao = GroupMemberDao.getInstance();
			boolean isS = dao.deleteGroupMem(id);
			if(isS) {
				dao.giveAuthtoGroupMem(auth, id);//권한 뺏기
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('모임에서 " + id + "님을 추방하였습니다'); location.href='mypage_manageMember_detail.jsp?seq=" + group_seq + "';</script>");			 
				out.flush(); 
			}else {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('모임에서 추방하지 못하였습니다'); location.href='mypage_manageMember_detail.jsp?seq=" + group_seq + "';</script>");			 
				out.flush(); 
			}
		}
	}
}
