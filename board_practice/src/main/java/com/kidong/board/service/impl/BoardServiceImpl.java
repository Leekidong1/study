package com.kidong.board.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kidong.board.dao.BoardDao;
import com.kidong.board.dto.BoardDto;
import com.kidong.board.service.BoardService;

@Service("service")
public class BoardServiceImpl implements BoardService{
	
	@Resource(name = "dao")
	private BoardDao dao;
	
	@Override
	public Map<String, Object> list(Map<String, Object> map) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> list = dao.list(map);
		System.out.println(list.toString());
		int totalCount = dao.pageNums(map);
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
	public String writeBoard(Map<String, Object> map) {
		return dao.writeBoard(map) == 1 ? "success" : "fail";
	}

	@Override
	public BoardDto boardDetail(int seq) {
		return dao.boardDetail(seq);
	}

	@Override
	public void deleteBoard(String seq) {
		dao.deleteBoard(seq);
	}

	@Override
	public String updateBoard(Map<String, Object> map) {
		return dao.updateBoard(map) == 1 ? "success" : "fail";
	}

	@Override
	public void mutiDeleteBoard(String[] seqs) {
		dao.mutiDeleteBoard(seqs);
	}

	@Override
	public String uploadFile(Map<String, Object> map) {
		return dao.uploadFile(map) == 1 ? "success" : "fail";
	}

	@Override
	public List<Map<String, Object>> getFile(int seq) {
		return dao.getFile(seq);
	}	
	
}
