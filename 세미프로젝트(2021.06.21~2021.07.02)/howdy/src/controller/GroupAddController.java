package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.GroupDao;
import dao.MemberDao;
import dto.GroupDto;

public class GroupAddController extends HttpServlet {
	
	ServletConfig mConfig = null; // 업로드 경로 취득을 위한 변수
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		mConfig = config;	// 업로드한 경로를 취득하기 위해서 설정
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GroupController doGet");
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GroupController doPost");
		doProcess(req, resp);
	}
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		String fupload = mConfig.getServletContext().getRealPath("/imageServer"); // imageServer폴더 저장공간 Ok맞음
		System.out.println("파일업로드:"+ fupload);
		String yourTemDir = fupload;
		
		int yourMaxRepuestSize = 100 * 1024 * 1024; // 1M bite
		int yourMaxMemorySize = 100 * 1024; // 1K bite
		
		String category = "";	// 카테고리
		String addressRegion = ""; // 지역 선택1	(지역권)
		String addressDo = "";	// 지역 선택2	(도)
		String addressSiGunGu = ""; // 지역 선택3	(시,군,구)
		String groupName = "";	// 모임명
		String groupTitle = "";	// 모임주제
		String groupContent = "";	// 모임소개
		String param = "";
		int groups_seq = 0;
		String id = "";
		
		// file data 가져오기
		String filename = "";
		String newfilename = "";
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if(isMultipart) {// image file 업로드
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			factory.setSizeThreshold(yourMaxMemorySize); // 사이즈설정
			factory.setRepository(new File(yourTemDir)); // dir설정
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(yourMaxRepuestSize); // 파일업로드
			
			List<FileItem> items = null;
			try {
				items = upload.parseRequest(req); // createGroup.jsp 에서 form으로 다 묶여서 넘어옴
			} catch (FileUploadException e) {
				e.printStackTrace();
			} 
			Iterator<FileItem> it = items.iterator();
			
			while (it.hasNext()) {
				FileItem item = it.next();
				
				if(item.isFormField()) { // String넘어왔을때
					if(item.getFieldName().equals("category")) {
						category = item.getString("utf-8");
					}
					else if(item.getFieldName().equals("addressRegion")) {
						addressRegion = item.getString("utf-8");
					}
					else if(item.getFieldName().equals("addressDo")) {
						addressDo = item.getString("utf-8");
					}
					else if(item.getFieldName().equals("addressSiGunGu")) {
						addressSiGunGu = item.getString("utf-8");
					}
					else if(item.getFieldName().equals("groupName")) {
						groupName = item.getString("utf-8");
					}
					else if(item.getFieldName().equals("groupTitle")) {
						groupTitle = item.getString("utf-8");
					}
					else if(item.getFieldName().equals("groupContent")) {
						groupContent = item.getString("utf-8");
					}else if(item.getFieldName().equals("param")) {
						param = item.getString("utf-8");
					}else if(item.getFieldName().equals("groups_seq")) {
						groups_seq = Integer.parseInt(item.getString("utf-8"));
					}else if(item.getFieldName().equals("id")) {
						id = item.getString("utf-8");
					}
				}else {// file이 넘어왔을때
					if(item.getFieldName().equals("image")){
						String fileName = item.getName(); // abc.txt
						int lastInNum = fileName.lastIndexOf("."); // abc.txt 에서 .의 위치가져옴
						String exName = fileName.substring(lastInNum); // .txt
						
						newfilename = (new Date().getTime()) + "";
						newfilename = newfilename + exName;
						filename = ImageLoad.processUploadFile(item, newfilename, fupload);
					}
				}
			}
		}else {
			System.out.println("Multipart가 아님");
		}
		
			//DB에 Data저장
		String location = addressRegion +" "+addressDo+" "+addressSiGunGu;
		System.out.println("지역:"+location);
		GroupDao dao = GroupDao.getInstance();
		if(param.equals("createGroupAf")) {	// 모임추가
			boolean isS = dao.addGroup(new GroupDto(category, location, id, groupName, groupTitle, groupContent, filename, newfilename));// id불러오실에 수정할 것
			if(isS) {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('성공적으로 모임등록이 되었습니다'); location.href='main_findGroup_main.jsp';</script>");			 
				out.flush();
			}else {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('모임등록 실패하였습니다'); location.href='main_createGroup.jsp';</script>");			 
				out.flush();
			}
			
			MemberDao mdao = MemberDao.getInstance();
			boolean MisS = mdao.addGROUP_AUTH(id);
			if(MisS) {
				System.out.println("모임장 권한주기 성공!");
			}
		}else if(param.equals("updateGroupAf")) {	// 모임수정
			boolean isS = dao.updateGroup(groups_seq, new GroupDto(category, location, "aaa", groupName, groupTitle, groupContent, filename, newfilename));
			if(isS) {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('성공적으로 모임수정이 되었습니다'); location.href='admin_Group.jsp';</script>");			 
				out.flush();
			}else {
				resp.setContentType("text/html; charset=UTF-8");			 
				PrintWriter out = resp.getWriter();			 
				out.println("<script>alert('모임수정 실패하였습니다'); location.href='admin_Group.jsp';</script>");			 
				out.flush();
			}
		}
	}
}
