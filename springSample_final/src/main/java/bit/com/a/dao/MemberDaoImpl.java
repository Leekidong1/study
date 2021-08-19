package bit.com.a.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dto.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	SqlSession session;
	
	String ns = "Member.";

	@Override
	public String idcheck(String id) {
		String idcheck = session.selectOne(ns + "idcheck", id);
		return idcheck;
	}

	@Override
	public int addMember(MemberDto dto) {
		int count = session.insert(ns + "addMember", dto);
		return count;
	}

	@Override
	public MemberDto login(MemberDto dto) {	
		return session.selectOne(ns + "login", dto);
	}

}
