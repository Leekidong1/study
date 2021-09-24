package bit.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.PdsDao;
import bit.com.a.dto.PdsDto;
import bit.com.a.dto.SearchDto;

@Service
public class PdsServiceImpl implements PdsService {

	@Autowired
	PdsDao dao;

	@Override
	public List<PdsDto> getPdsList() {
		return dao.getPdsList();
	}

	@Override
	public boolean uploadPds(PdsDto dto) {
		return dao.uploadPds(dto);
	}

	@Override
	public PdsDto getPds(int seq) {
		return dao.getPds(seq);
	}

	@Override
	public boolean readcount(int seq) {
		return dao.readcount(seq);
	}

	@Override
	public boolean downcount(int seq) {
		return dao.downcount(seq);
	}

	@Override
	public boolean updatePds(PdsDto dto) {
		return dao.updatePds(dto);
	}

	@Override
	public boolean deletePds(int seq) {
		return dao.deletePds(seq);
	}

	@Override
	public List<PdsDto> getTotalList(SearchDto search) {
		return dao.getTotalList(search);
	}

	@Override
	public int allPds(SearchDto search) {
		return dao.allPds(search);
	}

	@Override
	public boolean addStep(PdsDto dto) {
		return dao.addStep(dto);
	}

	@Override
	public boolean answerPds(PdsDto dto) {
		return dao.answerPds(dto);
	}
}
