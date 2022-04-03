package com.payment.www.dao;

import java.util.List;
import java.util.Map;

public interface ApprDao {
	
	List<Map<String, Object>> list(Map<String, Object> map);
	
	int pageNums(Map<String, Object> map);
	
	int getNextSeq();
	
	List<Map<String, Object>> histList(int seq);
	
	int insertAppr(Map<String, Object> map);
	
	int insertHist(Map<String, Object> map);
	
	int updateAppr(Map<String, Object> map);
}
