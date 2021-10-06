package bit.com.a.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dto.PdsDto;
import bit.com.a.dto.SearchDto;

@Repository
public class PdsDaoImpl implements PdsDao {

	@Autowired
	SqlSession session;
	
	String ns = "Pds.";

	@Override
	public List<PdsDto> getPdsList() {
		return session.selectList(ns + "getPdsList");
	}

	@Override
	public boolean uploadPds(PdsDto dto) {
		return session.insert(ns + "uploadPds", dto)>0?true:false;
	}

	@Override
	public PdsDto getPds(int seq) {
		return session.selectOne(ns + "getPds", seq);
	}

	@Override
	public boolean readcount(int seq) {
		return session.update(ns + "readcount", seq)>0?true:false;
	}

	@Override
	public boolean downcount(int seq) {
		return session.update(ns + "downcount", seq)>0?true:false;
	}

	@Override
	public boolean updatePds(PdsDto dto) {
		return session.update(ns + "updatePds", dto)>0?true:false;
	}

	@Override
	public boolean deletePds(int seq) {
		return session.delete(ns + "deletePds", seq)>0?true:false;
	}

	@Override
	public List<PdsDto> getTotalList(SearchDto search) {
		return session.selectList(ns + "getTotalList", search);
	}

	@Override
	public int allPds(SearchDto search) {
		return session.selectOne(ns + "allPds", search);
	}

	@Override
	public boolean addStep(PdsDto dto) {
		return session.update(ns + "addStep", dto)>0?true:false;
	}

	@Override
	public boolean answerPds(PdsDto dto) {
		return session.insert(ns + "answerPds", dto)>0?true:false;
	}
}
