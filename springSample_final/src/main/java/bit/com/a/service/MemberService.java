package bit.com.a.service;

import bit.com.a.dto.MemberDto;

public interface MemberService {
	public boolean idcheck(String id);
	public String addMember(MemberDto dto);
	public MemberDto login(MemberDto dto);
}
