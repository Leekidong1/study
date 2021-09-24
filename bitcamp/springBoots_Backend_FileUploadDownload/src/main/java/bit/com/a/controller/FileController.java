package bit.com.a.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

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

@RestController
public class FileController {

	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public String fileUpload(	@RequestParam("uploadFile")
								MultipartFile uploadFile,
								HttpServletRequest req) {
		System.out.println("FileController fileUpload");
		
		// 서버경로
		String uploadPath = req.getServletContext().getRealPath("/upload");		
		// String uploadPath = "d:\\tmp";
		
		String filename = uploadFile.getOriginalFilename();
		String filepath = uploadPath + File.separator + filename;
										// '/'
		System.out.println("filepath:" + filepath);
		
		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			os.write(uploadFile.getBytes());
			os.close();
			
			// DB
			
		}catch (Exception e) {
			e.printStackTrace();
			
			return "file upload fail";
		}
		
		return "file upload success";
	}
	
	@RequestMapping(value = "/fileDownload")
	public ResponseEntity<InputStreamResource> download(String fileName, HttpServletRequest req)throws Exception{
		System.out.println("FileController download");
		
		// 서버경로
		String uploadPath = req.getServletContext().getRealPath("/upload");
		// String uploadPath = "d:\\tmp";
		
		MediaType mediaType = MediaTypeUtiles.getMediaTypeForFileName(this.servletContext, fileName);
		System.out.println("fileName:" + fileName);
		System.out.println("mediaType:" + mediaType);
		
		File file = new File(uploadPath + File.separator + fileName);
		
		InputStreamResource is = new InputStreamResource(new FileInputStream(file));
		
		return ResponseEntity.ok().	// view에서 다운로드 창이 만들어지는 부분
				header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
				.contentType(mediaType)
				.contentLength(file.length())
				.body(is);
	}
	
	
	
}
