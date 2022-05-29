package com.payment.www.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.payment.www.dao.ApprDao;

@Service("apprService")
public class ApprServiceImpl implements ApprService {
	
	@Resource(name = "apprDao")
	private ApprDao dao;

	@Override
	public Map<String, Object> list(Map<String, Object> map) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> list = dao.list(map);
		System.out.println("조회명단----> " + list.toString());
		int totalCount = dao.pageNums(map);
		System.out.println("조회수---> " + totalCount);
		int pageNumber = Integer.parseInt(map.get("pageNumber")+"");
		int displaypage = 5;
		int end = ((int)Math.ceil(pageNumber/(double)displaypage))*displaypage;
		int start = end - (displaypage - 1);
		boolean next, prev;
		int totalPage = (int)Math.ceil(totalCount/(double)10);
		
		if (totalPage < end) {
			end = totalPage;
			next = false;
		} else {
			next = true;
		}
		prev = (start == 1)?false:true;
		
		map.put("totalCount", totalCount);
		map.put("pageNumber", pageNumber);
		map.put("start", start);
		map.put("end", end);
		map.put("next", next);
		map.put("prev", prev);
		map.put("totalPage", totalPage);
		result.put("list", list);
		result.put("paging", map);
		return result;
	}

	@Override
	public int getNextSeq() {
		return dao.getNextSeq();
	}

	@Override
	public List<Map<String, Object>> histList(int seq) {
		return dao.histList(seq);
	}

	@Override
	public boolean submitAppr(Map<String, Object> map) {
		int result = 0;
		if (((String)map.get("prevStatus")).equals("")) {
			// 추가 - 임시저장, 결재 (nullpointexception 조심)
			result = dao.insertAppr(map);
			result += dao.insertHist(map);
		} else {
			// 수정 - 임시저장, 결재, 반려
			result = dao.updateAppr(map);
			result += dao.insertHist(map);
		}

		return result > 1 ? true : false;
	}
}
