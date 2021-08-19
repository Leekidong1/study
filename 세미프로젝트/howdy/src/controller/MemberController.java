package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import dao.GroupDao;
import dao.MemberDao;
import db.DBConnection;
import dto.GroupDto;
import dto.MemberDto;
import net.sf.json.JSONObject;
import util.CalUtil;

public class MemberController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req,resp);
	}
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberController doProcess()실행됨");
		DBConnection.initConnection();
		req.setCharacterEncoding("UTF-8");
		String param = req.getParameter("param");
		
		if(param.equals("login")) {	//로그인 페이지로 가는~
			System.out.println("MemberController login.jsp 실행됨");
			resp.sendRedirect("login.jsp");
		}
		if(param.equals("loginAf")) {	//로그인 페이지로 가는~
			System.out.println("MemberController loginAf.jsp 실행됨");
			
			String id = req.getParameter("id");
			String pwd = req.getParameter("pwd"); 
		
			
			System.out.println(id+" "+pwd);
			
			MemberDao dao = MemberDao.getInstance(); 
			MemberDto dto = dao.login(new MemberDto(id, pwd, null, null, null, null, null, null, null));
			if(dto != null && !dto.getId().equals("")) {
				HttpSession session = req.getSession();
				session.setAttribute("login", dto);
				
				resp.setContentType("text/html; charset=UTF-8");			 
    			PrintWriter out = resp.getWriter();			 
    			out.println("<script>alert('" + dto.getId() + "님 안녕하세요. 반갑습니다^^'); location.href='main.jsp';</script>");			 
    			out.flush();	
			}else {
				resp.setContentType("text/html; charset=UTF-8");			 
    			PrintWriter out = resp.getWriter();			 
    			out.println("<script>alert('아이디나 비밀번호가 틀렸습니다. 다시한번 확인해 주세요'); location.href='member?param=login';</script>");			 
    			out.flush();
			}
			
		}
		else if(param.equals("findId")) {
			System.out.println("MemberController findId.jsp 실행됨");
			resp.sendRedirect("findId.jsp");
		}
		else if(param.equals("findPwd")) {
			System.out.println("MemberController findId.jsp 실행됨");
			resp.sendRedirect("findPwd.jsp");
		}
		else if(param.equals("regi")) {
			System.out.println("MemberController regi.jsp 실행됨");
			resp.sendRedirect("regi_agreement.jsp");
		}

		else if(param.equals("regiAf")) {
			System.out.println("MemberController regiAf.jsp 실행됨");

			String id = req.getParameter("id");
			String pwd = req.getParameter("pwd"); 
			String name = req.getParameter("name");
			//생년월일
			String year = req.getParameter("year");
			String month = req.getParameter("month"); 
			String day = req.getParameter("day"); 
			String birth = year + CalUtil.two(month) + CalUtil.two(day);
			
			String gender = req.getParameter("gender");
			//주소
			String address = req.getParameter("address");
			String detailAddress = req.getParameter("detailAddress");
			String location = address + " " + detailAddress;
			
			String phone = req.getParameter("phone"); 
			String email = req.getParameter("email"); 
			String category = req.getParameter("category"); 
			
			System.out.println(id+" "+pwd+" "+name+" "+birth+" "+gender+" "+phone+" "+email+" " +category+" "+location); 
			
			
			MemberDao dao =MemberDao.getInstance(); 
			boolean isS = dao.addMember(new MemberDto(id, pwd, name, birth, gender, phone, email, category, location));
			if(isS) {
    			resp.setContentType("text/html; charset=UTF-8");			 
    			PrintWriter out = resp.getWriter();			 
    			out.println("<script>alert('저희와 함께 해주셔서 감사합니다 \\n 꼭 좋은 모임 찾으세요!'); location.href='member?param=login';</script>");			 
    			out.flush();
    		}else {
    			resp.setContentType("text/html; charset=UTF-8");			 
    			PrintWriter out = resp.getWriter();			 
    			out.println("<script>alert('뭔가 놓치셨어요!다시 기입해 주세요'); location.href='member?param=regi';</script>");			 
    			out.flush();
    		} 	
			
		}
		else if(param.equals("idcheck")) {	//jsp 사용하는거 아님 ajax를 사용해서 
			System.out.println("MemberController idcheck");
			
			String id = req.getParameter("id");
			System.out.println("id:"+id);
			
			MemberDao dao = MemberDao.getInstance();
			boolean b = dao.getId(id); // id 있을시 true 리턴
			
			String str = "idnotfound";
			
			if(b) {
				str = "idfound";
			}
			JSONObject obj = new JSONObject();
			obj.put("str", str);
			
			resp.setContentType("application/x-json; charset=utf-8");
			resp.getWriter().print(obj);
			
		}
		else if(param.equals("updateMember")) {
			
			String id = req.getParameter("id");
			String pwd = req.getParameter("pwd"); 
			String name = req.getParameter("name");
			//생년월일
			String year = req.getParameter("year");
			String month = req.getParameter("month"); 
			String day = req.getParameter("day"); 
			String birth = year + CalUtil.two(month) + CalUtil.two(day);
			
			String gender = req.getParameter("gender");
			//주소
			String address = req.getParameter("address");
			String detailAddress = req.getParameter("detailAddress");
			String location = address + " " + detailAddress;
			
			String phone = req.getParameter("phone"); 
			String email = req.getParameter("email"); 
			String category = req.getParameter("category"); 
			
			System.out.println(id+" "+pwd+" "+name+" "+birth+" "+gender+" "+phone+" "+email+" " +category+" "+location); 
			
			
			MemberDao dao =MemberDao.getInstance(); 
			boolean isS = dao.updateMember(new MemberDto(id, pwd, name, birth, gender, phone, email, category, location.trim()));
			if(isS) {
    			resp.setContentType("text/html; charset=UTF-8");			 
    			PrintWriter out = resp.getWriter();			 
    			out.println("<script>alert('성공적으로 정보수정되었습니다'); location.href='mypage_info.jsp';</script>");			 
    			out.flush();
    		}else {
    			resp.setContentType("text/html; charset=UTF-8");			 
    			PrintWriter out = resp.getWriter();			 
    			out.println("<script>alert('정보수정 실패하셨습니다'); location.href='mypage_info.jsp';</script>");			 
    			out.flush();
    		} 	
		}else if(param.equals("admin_Member")) {
			System.out.println("admin_Member 입성성공~!");
			
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
			
			MemberDao dao = MemberDao.getInstance();
			List<MemberDto> list = dao.adminMemberList(category, searchText, sdate, edate);
			
			JSONObject jobj = new JSONObject();
			jobj.put("list", list);
			resp.setContentType("application/x-json; charset=utf-8");
			resp.getWriter().print(jobj);
		}else if(param.equals("deleteMemberAf")) {
			String id = req.getParameter("id");
			
			MemberDao dao =MemberDao.getInstance(); 
			boolean isS = dao.deleteMember(id);
			if(isS) {
    			resp.setContentType("text/html; charset=UTF-8");			 
    			PrintWriter out = resp.getWriter();			 
    			out.println("<script>alert('성공적으로 회원삭제 되었습니다'); location.href='admin_Member.jsp';</script>");			 
    			out.flush();
    		}else {
    			resp.setContentType("text/html; charset=UTF-8");			 
    			PrintWriter out = resp.getWriter();			 
    			out.println("<script>alert('회원삭제 실패하셨습니다'); location.href='admin_Member.jsp';</script>");			 
    			out.flush();
    		} 	
		}
	}
}