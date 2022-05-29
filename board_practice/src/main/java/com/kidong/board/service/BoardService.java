package com.kidong.board.service;


import java.util.List;
import java.util.Map;

import com.kidong.board.dto.BoardDto;

public interface BoardService {

	Map<String, Object> list(Map<String, Object> map);

	String writeBoard(Map<String, Object> map);
	
	String uploadFile(Map<String, Object> map);
	
	BoardDto boardDetail(int seq);

	List<Map<String,Object>> getFile(int seq);
	
	void deleteBoard(String seq);

	String updateBoard(Map<String, Object> map);

	void mutiDeleteBoard(String[] seqs);

}
