package bit.com.a;

import javax.servlet.ServletContext;

import org.springframework.http.MediaType;

public class MediaTypeUtiles { //파일업로드셋팅
	
	public static MediaType getMediaTypeForFileName(ServletContext servletContext, String filename) {
		
		String minType = servletContext.getMimeType(filename);	// 파일업로드할 때 필요함
		
		try {
			MediaType mediaType = MediaType.parseMediaType(minType);
			return mediaType;
		}catch (Exception e) {
			return MediaType.APPLICATION_OCTET_STREAM;
		}
		
	}
}
