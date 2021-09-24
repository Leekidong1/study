package bit.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import bit.com.a.dto.MemberDto;

@Mapper
@Repository
public interface MemberDao {

	public List<MemberDto> allMember();	// 함수명 Member.xml id와 꼭 같아야됨*****
	public int idcheck(String id);
	public int regi(MemberDto mem);
	public MemberDto login(MemberDto mem);
}
