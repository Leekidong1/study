package bit.com.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.MemberDao;
import bit.com.a.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDao dao;

	@Override
	public boolean idcheck(String id) {
			boolean check = false;
			String idcheck = dao.idcheck(id);
			if(idcheck != null) {
				check = true;
			}
		return check;
	}

	@Override
	public String addMember(MemberDto dto) {
		return dao.addMember(dto)>0?"Good":"Bad";	
	}

	@Override
	public MemberDto login(MemberDto dto) {
		return dao.login(dto);
	}
}
