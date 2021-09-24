package bit.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bit.com.a.dao.MemberDao;
import bit.com.a.dto.MemberDto;

@Service
@Transactional	// DB바꿔주는 작업
public class MemberService {
	
	@Autowired
	private MemberDao dao;
	
	public List<MemberDto> allMember() {
		return dao.allMember();
	}
	
	public String idcheck(String id) {
		return dao.idcheck(id)>0?"success":"fail";
	}
	
	public String regi(MemberDto mem) {
		return dao.regi(mem)>0?"success":"fail";
	}
	
	public MemberDto login(MemberDto mem) {
		return dao.login(mem);
	}
}
