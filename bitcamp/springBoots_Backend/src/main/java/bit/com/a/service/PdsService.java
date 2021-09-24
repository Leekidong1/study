package bit.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bit.com.a.dao.PdsDao;
import bit.com.a.dto.PdsDto;
import bit.com.a.dto.SearchDto;

@Service
@Transactional
public class PdsService {
	
	@Autowired
	private PdsDao dao;
	
	public List<PdsDto> getTotalList(SearchDto search){
		return dao.getTotalList(search);
	}
	
	public int allPds(SearchDto search) {
		return dao.allPds(search);
	}
	
	public String uploadPds(PdsDto pds) {
		return dao.uploadPds(pds)>0?"success":"fail";
	}
	
	public PdsDto getPds(int seq) {
		return dao.getPds(seq);
	}
	public String deletePds(int seq) {
		return dao.deletePds(seq)>0?"success":"fail";
	}
	public String updatePds(PdsDto pds) {
		return dao.updatePds(pds)>0?"success":"fail";
	}
	public String reply(PdsDto pds) {
		int step = dao.addStep(pds);
		int addReply = dao.answerPds(pds);
		return step+addReply>0?"success":"fail";
	}
	public String downcount(int seq) {
		return dao.downcount(seq)>0?"success":"fail";
	}
	public String readcount(int seq) {
		return dao.readcount(seq)>0?"success":"fail";
	}
}
