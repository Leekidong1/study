package bit.com.a.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dto.BbsDto;
import bit.com.a.dto.SearchDto;

@Repository
public class BbsDaoImpl implements BbsDao {
	
	@Autowired
	SqlSession session;
	//SqlSession session말고 sqlSessionTemplate session 사용가능 (import org.mybatis.spring.SqlSessionTemplate)
	
	String ns = "Bbs.";

	@Override
	public List<BbsDto> getList(SearchDto search) {
		
		List<BbsDto> list = session.selectList(ns + "getlist", search);
		return list;
	}

	@Override
	public int allBbs(SearchDto search) {
		int allbbs = session.selectOne(ns + "allBbs", search);
		return allbbs;
	}

	@Override
	public int addBbs(BbsDto bbs) {
		int count = session.insert(ns + "addBbs", bbs);
		return count;
	}

	@Override
	public BbsDto getBbs(int seq) {
		return session.selectOne(ns + "getBbs", seq);
	}

	@Override
	public int deleteBbs(int seq) {
		return session.update(ns + "deleteBbs", seq);
	}

	@Override
	public int updateBbs(BbsDto bbs) {
		return session.update(ns + "updateBbs", bbs);
	}

	@Override
	public int addStep(BbsDto bbs) {
		return session.update(ns + "addStep", bbs);
	}

	@Override
	public int addAnswer(BbsDto bbs) {
		return session.insert(ns + "addAnswer", bbs);
	}

	@Override
	public int readcount(int seq) {
		return session.update(ns + "readCount", seq);
	}
}
