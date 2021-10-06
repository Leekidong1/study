package bit.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.BbsDao;
import bit.com.a.dto.BbsDto;
import bit.com.a.dto.SearchDto;

@Service
public class BbsServiceImpl implements BbsService {

	@Autowired
	BbsDao dao;

	@Override
	public List<BbsDto> getList(SearchDto search) {
		return dao.getList(search);
	}

	@Override
	public int allBbs(SearchDto search) {
		return dao.allBbs(search);
	}

	@Override
	public boolean addBbs(BbsDto bbs) {
		return dao.addBbs(bbs)>0?true:false;
	}

	@Override
	public BbsDto getBbs(int seq) {
		return dao.getBbs(seq);
	}

	@Override
	public boolean deleteBbs(int seq) {
		return dao.deleteBbs(seq)>0?true:false;
	}

	@Override
	public boolean updateBbs(BbsDto bbs) {
		return dao.updateBbs(bbs)>0?true:false;
	}

	@Override
	public boolean addStep(BbsDto bbs) {
		return dao.addStep(bbs)>0?true:false;
	}

	@Override
	public boolean addAnswer(BbsDto bbs) {
		return dao.addAnswer(bbs)>0?true:false;
	}

	@Override
	public String readcount(int seq) {
		return dao.readcount(seq)>0?"조회수 1증가!":"";
	}
	
	
}
