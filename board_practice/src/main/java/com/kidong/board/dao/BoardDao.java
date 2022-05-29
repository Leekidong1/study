package com.kidong.board.dao;

import java.util.List;
import java.util.Map;

import com.kidong.board.dto.BoardDto;

public interface BoardDao {

	List<Map<String, Object>> list(Map<String, Object> map);
	
	int pageNums(Map<String, Object> map);

	int writeBoard(Map<String, Object> map);
	
	int uploadFile(Map<String, Object> map);
	
	BoardDto boardDetail(int seq);
	
	List<Map<String,Object>> getFile(int seq);
	
	void deleteBoard(String seq);

	int updateBoard(Map<String, Object> map);

	void mutiDeleteBoard(String[] seqs);
	
}
