package controller;

import java.io.File;
import java.io.IOException;
import org.apache.commons.fileupload.FileItem;

public class ImageLoad {
	
	// 실제 파일 업로드를 실시하는 함수
	public static String processUploadFile(FileItem fileitem,String newfilename, String dir) throws IOException{
		
		String f = fileitem.getName();
		long sizeInBytes = fileitem.getSize(); // 파일넘어오면 사이즈수가 늘어남
		
		String fileName = "";
		String fpost = "";
		
		// 업로드한 파일이 정상일 경우
		if(sizeInBytes > 0){	//업로드경로 예시1 - d:\\tmp\\abc.txt 업로드경로 예시2 - 	d:/tmp/abc.txt => 파일명만 추출하기
			int idx = f.lastIndexOf("\\"); //업로드경로 예시1인 경우에 끝에 있는 \\의 인덱스 찾기
			if(idx == -1){
				idx = f.lastIndexOf("/");	//업로드경로 예시2인 경우에 끝에 있는 /의 인덱스 찾기
			}
			fileName = f.substring(idx + 1);	// 추출값 : abc.txt
			
			try{
				File uploadFile = new File(dir, newfilename);	// 새로운 파일명으로 전환하기
				fileitem.write(uploadFile);	// 실제로 파일 업로드하는 부분
			}catch(Exception e){
				e.printStackTrace();
			};
		}
		return fileName;
	}
}
