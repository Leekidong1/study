package bit.com.a.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import bit.com.a.dto.MemberDto;
import bit.com.a.dto.PdsDto;
import bit.com.a.dto.SearchDto;
import bit.com.a.service.PdsService;
import bit.com.a.util.PdsUtil;

@Controller
public class PdsController {

	@Autowired
	PdsService service;
	
	@ResponseBody
	@RequestMapping(value = "pdslist_ajax.do", method = {RequestMethod.GET,RequestMethod.POST})
	public Map<String, Object> pdslist_ajax(SearchDto search) {
		if(search.getStartDate() != null && search.getEndDate() != null) {
			search.setStartDate(search.getStartDate().replace("-", ""));
			search.setEndDate(search.getEndDate().replace("-", ""));
		}
		System.out.println(search.toString());
		List<PdsDto> list = service.getTotalList(search);
		if(list != null) {
			for(int i=0; i< list.size(); i++ ) {
				System.out.println(list.get(i).toString());
			}
		}
		int totalCount = service.allPds(search);
		Map<String, Object> map = new HashMap<>();
		map.put("search", search);
		map.put("totalCount", totalCount);
		map.put("pdslist", list);

		return map;
	}
	
	@RequestMapping(value = "pdslist.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String pdslist(Model model) {
		model.addAttribute("doc_title", "자료실 목록");
		/*
		 * List<PdsDto> list = service.getPdsList(); model.addAttribute("pdslist",
		 * list);
		 */
		return "pdslist.tiles";
	}
	
	@RequestMapping(value = "pdswrite.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String pdswrite(Model model) {
		model.addAttribute("doc_title", "자료 올리기");
		return "pdswrite.tiles";
	}
	
	@RequestMapping(value = "pdsupload.do", method = {RequestMethod.GET,RequestMethod.POST})	// pds는 form 필드 ,  file은 fileLoad
	public String pdsupload(PdsDto pds, @RequestParam(value = "fileload", required = false) MultipartFile fileload, HttpServletRequest req) {
		//filename(원본) 취득
		String filename = fileload.getOriginalFilename();
		pds.setFilename(filename);	// DB에 저장하기 위해서 Dto setting
		
		// 서버 upload 경로설정
		String fupload = req.getServletContext().getRealPath("/upload"); // 서버에 저장하는 폴더 경로 잡아주기
		
		// 일반폴더
		//String fupload = "d://tmp";
		
		System.out.println("server path fupload:" + fupload);
		
		//일반파일명 -> new파일명 변경
		String newfilename = PdsUtil.getNewFileName(pds.getFilename());
		pds.setNewfilename(newfilename);
		
		//파일업로드
		File file = new File(fupload + "/" + newfilename);
		try {
			//파일 실제로 업로드
			FileUtils.writeByteArrayToFile(file, fileload.getBytes());	// file와 fileload를 바이트단위로 넣기
			
			// DB에 저장
			service.uploadPds(pds);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return "redirect:/pdslist.do";
	}

	@RequestMapping(value = "fileDownload.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String fileDownload(int seq, HttpServletRequest req, Model model) {
		PdsDto dto = service.getPds(seq);
		//경로 설정
		//서버일경우
		String fupload = req.getServletContext().getRealPath("/upload");
		//폴더일경우
		//String fupload = "d://tmp";
		
		File downloadFile = new File(fupload + "/" + dto.getNewfilename());
		
		//다운로드용
		model.addAttribute("downloadFile", downloadFile);
		model.addAttribute("originalFile", dto.getFilename());
		// DB에서 다운로드회수 증가용
		model.addAttribute("seq", seq);
		
		//다운로드 회수
		boolean b = service.downcount(seq);
		if(b) {
			System.out.println("다운로드 회수 1증가~!~!");
		}
		
		return "downloadView";
	}
	
	@RequestMapping(value = "pdsdetail.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String pdsdetail(Model model, int seq) {
		model.addAttribute("doc_title", "자료글 상세정보");
		PdsDto pds = service.getPds(seq);
		boolean b = service.readcount(seq);
		if(b) {
			System.out.println("조회수 1증가~!~!");
		}
		model.addAttribute("pds", pds);
		return "pdsdetail.tiles";
	}
	
	@RequestMapping(value = "pdsupdate.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String pdsupdate(Model model, int seq){
		model.addAttribute("doc_title", "자료글 수정정보");
		PdsDto pds = service.getPds(seq);
		model.addAttribute("pds", pds);
		return "pdsupdate.tiles";
	}
	
	@RequestMapping(value = "pdsupdateAf.do", method = {RequestMethod.GET,RequestMethod.POST})	// pds는 form 필드 ,  file은 fileLoad
	public String pdsupdateAf(PdsDto pds, @RequestParam(value = "fileload", required = false) MultipartFile fileload, HttpServletRequest req) {
		if(fileload != null) {
			//filename(원본) 취득
			String filename = fileload.getOriginalFilename();
			pds.setFilename(filename);	// DB에 저장하기 위해서 Dto setting
			
			// 서버 upload 경로설정
			String fupload = req.getServletContext().getRealPath("/upload"); // 서버에 저장하는 폴더 경로 잡아주기
			
			// 일반폴더
			//String fupload = "d://tmp";
			
			System.out.println("server path fupload:" + fupload);
			
			//일반파일명 -> new파일명 변경
			String newfilename = PdsUtil.getNewFileName(pds.getFilename());
			pds.setNewfilename(newfilename);
			
			//파일업로드
			File file = new File(fupload + "/" + newfilename);
			try {
				//파일 실제로 업로드
				FileUtils.writeByteArrayToFile(file, fileload.getBytes());	// file와 fileload를 바이트단위로 넣기
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// DB에 저장
		service.updatePds(pds);
		
		return "redirect:/pdslist.do";
	}
	
	@RequestMapping(value = "deletePds.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String deletePds(int seq) {
		boolean b = service.deletePds(seq);
		if(b) {
			System.out.println("자료글 삭제성공!");
		}else {
			System.out.println("자료글 삭제실패!");
		}
		return "redirect:/pdslist.do";
	}
	
	@RequestMapping(value = "pdsanswer.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String pdsanswer(Model model, int seq, HttpSession session) {
		MemberDto login = (MemberDto)session.getAttribute("login");
		model.addAttribute("doc_title", "댓글");
		PdsDto pds = service.getPds(seq);
		model.addAttribute("login", login);
		model.addAttribute("pds", pds);
		return "pdsanswer.tiles";
	}
	
	@RequestMapping(value = "pdsanswerAf.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String pdsanswerAf(PdsDto pds, @RequestParam(value = "fileload", required = false) MultipartFile fileload, HttpServletRequest req) {
		if(fileload != null) {
			//filename(원본) 취득
			String filename = fileload.getOriginalFilename();
			pds.setFilename(filename);	// DB에 저장하기 위해서 Dto setting
			
			// 서버 upload 경로설정
			String fupload = req.getServletContext().getRealPath("/upload"); // 서버에 저장하는 폴더 경로 잡아주기
			
			// 일반폴더
			//String fupload = "d://tmp";
			
			System.out.println("server path fupload:" + fupload);
			
			//일반파일명 -> new파일명 변경
			String newfilename = PdsUtil.getNewFileName(pds.getFilename());
			pds.setNewfilename(newfilename);
			
			//파일업로드
			File file = new File(fupload + "/" + newfilename);
			try {
				//파일 실제로 업로드
				FileUtils.writeByteArrayToFile(file, fileload.getBytes());	// file와 fileload를 바이트단위로 넣기
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// DB에 저장
		boolean a = service.addStep(pds);
		boolean b = service.answerPds(pds);
		if(a == true && b == true) {
			System.out.println("댓글작성성공!");
		}
		
		return "redirect:/pdslist.do";
	}
}
