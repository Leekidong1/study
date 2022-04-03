package com.payment.www.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("apprDao")
public class ApprDaoImpl implements ApprDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<Map<String, Object>> list(Map<String, Object> map) {
		return sqlSession.selectList("approval.list", map);
	}

	@Override
	public int pageNums(Map<String, Object> map) {
		return sqlSession.selectOne("approval.pageNums", map);
	}

	@Override
	public int getNextSeq() {
		return sqlSession.selectOne("approval.getNextSeq");
	}

	@Override
	public List<Map<String, Object>> histList(int seq) {
		return sqlSession.selectList("approval.histList", seq);
	}

	@Override
	public int insertAppr(Map<String, Object> map) {
		return sqlSession.insert("approval.insertAppr", map);
	}

	@Override
	public int insertHist(Map<String, Object> map) {
		return sqlSession.insert("approval.insertHist", map);
	}

	@Override
	public int updateAppr(Map<String, Object> map) {
		return sqlSession.update("approval.updateAppr", map);
	}
	
}
