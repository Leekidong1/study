package bit.com.a.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bit.com.a.MediaTypeUtiles;
import bit.com.a.dto.PdsDto;
import bit.com.a.dto.SearchDto;
import bit.com.a.service.PdsService;
import bit.com.a.util.PdsUtil;

@RestController
public class PdsController {

	@Autowired
	ServletContext servletContext;
	
	@Autowired
	PdsService service;
	
	@RequestMapping(value = "/pdslist", method = {RequestMethod.GET,RequestMethod.POST})
	public Map<String, Object> pdslist(SearchDto search) {
		System.out.println("PdsController pdslist()");
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
	
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public String fileUpload(	@RequestParam("uploadFile")
								MultipartFile uploadFile,
								HttpServletRequest req, PdsDto pds) {
		System.out.println("PdsController fileUpload()");
		System.out.println(pds.toString());
		// 서버경로
		String uploadPath = req.getServletContext().getRealPath("/upload");		
		// String uploadPath = "d:\\tmp";
		
		String filename = uploadFile.getOriginalFilename();
		pds.setFilename(filename);
		
		//일반파일명 -> new파일명 변경
		String newfilename = PdsUtil.getNewFileName(pds.getFilename());
		pds.setNewfilename(newfilename);
		
		String filepath = uploadPath + File.separator + newfilename;
										// '/'
		System.out.println("filepath:" + filepath);
		
		String msg ="";
		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			os.write(uploadFile.getBytes());
			os.close();
			
			// DB
			msg = service.uploadPds(pds);
			
		}catch (Exception e) {
			e.printStackTrace();		
			msg = "fail";
		}
		
		return msg;
	}
	
	@RequestMapping(value = "/fileDownload")
	public ResponseEntity<InputStreamResource> download(int seq, HttpServletRequest req)throws Exception{
		System.out.println("PdsController download()");
		String msg = service.downcount(seq);
		if(msg.equals("success")) {
			System.out.println("다운수 1증가");
		}
		PdsDto pds = service.getPds(seq);
		String newFileName = pds.getNewfilename();
		// 서버경로
		String uploadPath = req.getServletContext().getRealPath("/upload");
		// String uploadPath = "d:\\tmp";
		
		MediaType mediaType = MediaTypeUtiles.getMediaTypeForFileName(this.servletContext, newFileName);
		System.out.println("newFileName:" + newFileName);
		System.out.println("mediaType:" + mediaType);
		
		File file = new File(uploadPath + File.separator + newFileName);
		
		InputStreamResource is = new InputStreamResource(new FileInputStream(file));
		
		return ResponseEntity.ok().	// view에서 다운로드 창이 만들어지는 부분
				header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + pds.getFilename())
				.contentType(mediaType)
				.contentLength(file.length())
				.body(is);
	}
	
	@RequestMapping(value = "/deletePds", method = {RequestMethod.GET,RequestMethod.POST})
	public String deletePds(int seq) {
		System.out.println("PdsController deletePds()");	
		String msg = service.deletePds(seq);
		return msg;
	}

	@RequestMapping(value = "/getPds", method = {RequestMethod.GET,RequestMethod.POST})
	public PdsDto getPds(int seq) {
		System.out.println("PdsController getPds()");
		PdsDto pds = service.getPds(seq);
		return pds;
	}
	
	@RequestMapping(value = "/detailBbs", method = {RequestMethod.GET,RequestMethod.POST})
	public PdsDto detailBbs(int seq) {
		System.out.println("PdsController detailBbs()");
		String msg = service.readcount(seq);
		if(msg.equals("success")) {
			System.out.println("조회수 1증가");
		}
		PdsDto pds = service.getPds(seq);
		return pds;
	}
	
	@RequestMapping(value = "/updatePds", method = RequestMethod.POST)
	public String updatePds(	@RequestParam("uploadFile")
								MultipartFile uploadFile,
								HttpServletRequest req, PdsDto pds) {
		System.out.println("PdsController updatePds()");
		System.out.println(pds.toString());
		String msg ="";
		if(uploadFile != null) {
			// 서버경로
			String uploadPath = req.getServletContext().getRealPath("/upload");		
			// String uploadPath = "d:\\tmp";
			
			String filename = uploadFile.getOriginalFilename();
			pds.setFilename(filename);
			
			//일반파일명 -> new파일명 변경
			String newfilename = PdsUtil.getNewFileName(pds.getFilename());
			pds.setNewfilename(newfilename);
			
			String filepath = uploadPath + File.separator + newfilename;
											// '/'
			System.out.println("filepath:" + filepath);
			
			try {
				BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
				os.write(uploadFile.getBytes());
				os.close();
							
			}catch (Exception e) {
				e.printStackTrace();		
				msg = "fail";
			}
		}	
		msg = service.updatePds(pds);	
		return msg;
	}
	
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String reply(	@RequestParam("uploadFile")
								MultipartFile uploadFile,
								HttpServletRequest req, PdsDto pds) {
		System.out.println("PdsController updatePds()");
		System.out.println(pds.toString());
		String msg ="";
		if(uploadFile != null) {
			// 서버경로
			String uploadPath = req.getServletContext().getRealPath("/upload");		
			// String uploadPath = "d:\\tmp";
			
			String filename = uploadFile.getOriginalFilename();
			pds.setFilename(filename);
			
			//일반파일명 -> new파일명 변경
			String newfilename = PdsUtil.getNewFileName(pds.getFilename());
			pds.setNewfilename(newfilename);
			
			String filepath = uploadPath + File.separator + newfilename;
											// '/'
			System.out.println("filepath:" + filepath);
			
			try {
				BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
				os.write(uploadFile.getBytes());
				os.close();
							
			}catch (Exception e) {
				e.printStackTrace();		
				msg = "fail";
			}
		}	
		msg = service.reply(pds);	
		return msg;
	}
}
