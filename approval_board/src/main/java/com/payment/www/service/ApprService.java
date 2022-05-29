package com.payment.www.service;

import java.util.List;
import java.util.Map;

public interface ApprService {
	
	Map<String, Object> list(Map<String, Object> map);
	
	int getNextSeq();
	
	List<Map<String, Object>> histList(int seq);
	
	boolean submitAppr(Map<String, Object> map);
}
