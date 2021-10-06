package bit.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bit.com.a.dao.BbsDao;
import bit.com.a.dto.BbsDto;
import bit.com.a.dto.SearchDto;

@Service
@Transactional
public class BbsService {
	
	@Autowired
	private BbsDao dao;
	
	public List<BbsDto> getBbslist(SearchDto search){
		return dao.getBbslist(search);
	}
	
	public int allBbs(SearchDto search) {
		return dao.allBbs(search);
	}
	public String addBbs(BbsDto bbs) {
		return dao.addBbs(bbs)>0?"success":"fail";
	}
	
	public BbsDto getBbs(int seq) {
		return dao.getBbs(seq);
	}
	
	public String readCount(int seq) {
		return dao.readCount(seq)>0?"조회수1 증가":"조회수증가실패";
	}
	
	public String updateBbs(BbsDto bbs) {
		return dao.updateBbs(bbs)>0?"success":"fail";
	}
	
	public String deleteBbs(int seq) {
		return dao.deleteBbs(seq)>0?"success":"fail";
	}
	
	public String reply(BbsDto bbs) {
		int step = dao.addStep(bbs);
		int reply = dao.addAnswer(bbs);
		return step+reply>0?"success":"fail";
	}
}
