package com.kidong.board.dao.Impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidong.board.dao.BoardDao;
import com.kidong.board.dto.BoardDto;

@Repository("dao")
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<Map<String, Object>> list(Map<String, Object> map) {
		return sqlSession.selectList("mapper.list", map);
	}
	
	@Override
	public int pageNums(Map<String, Object> map) {
		return sqlSession.selectOne("mapper.pageNums", map);
	}
	
	@Override
	public int writeBoard(Map<String, Object> map) {
		return sqlSession.insert("mapper.write", map);
	}

	@Override
	public BoardDto boardDetail(int seq) {
		return sqlSession.selectOne("mapper.detail", seq);
	}

	@Override
	public void deleteBoard(String seq) {
		sqlSession.delete("mapper.delete", seq);
	}

	@Override
	public int updateBoard(Map<String, Object> map) {
		return sqlSession.update("mapper.update", map);
	}

	@Override
	public void mutiDeleteBoard(String[] seqs) {
		sqlSession.delete("mapper.mutiDelete", seqs);
	}

	@Override
	public int uploadFile(Map<String, Object> map) {
		return sqlSession.insert("mapper.uploadFile", map);
	}

	@Override
	public List<Map<String, Object>> getFile(int seq) {
		return sqlSession.selectList("mapper.getFile", seq);
	}

}
