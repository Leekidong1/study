package bit.com.a.dao;

import bit.com.a.dto.MemberDto;

public interface MemberDao {
	public String idcheck(String id);
	public int addMember(MemberDto dto);
	public MemberDto login(MemberDto dto);
}
