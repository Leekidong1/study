package bit.com.a.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("DownloadView renderMergedOutputModel()");
		
		//데이터 받기
		File file = (File)model.get("downloadFile");	// == getAttribute
		String originalFile = (String)model.get("originalFile"); // == getAttribute
		int seq = (Integer)model.get("seq");
		
		response.setContentType(this.getContentType()); 		
		response.setContentLength((int)file.length());
		
		System.out.println("originalFile :" + originalFile);
		
		// **이 설정을 안해주면 다운받을 때, 한글 파일명이 정상으로 나오지 않는다.
		originalFile = URLEncoder.encode(originalFile, "utf-8");
		
		//하단에 다운로드 받는 창
		response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFile + "\";");
	    response.setHeader("Content-Transfer-Encoding", "binary;");
	    response.setHeader("Content-Length", "" + file.length());
	    response.setHeader("Pragma", "no-cache;"); 
	    response.setHeader("Expires", "-1;");
	    
	    OutputStream out = response.getOutputStream();
	    FileInputStream fi = new FileInputStream(file);
	    
	    //파일 다운받는 코드
	    FileCopyUtils.copy(fi, out);
	    
	    if(fi != null) {
	    	fi.close();
	    }
	}
	
}
