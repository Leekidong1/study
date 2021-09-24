package bit.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import bit.com.a.dto.PdsDto;
import bit.com.a.dto.SearchDto;

@Mapper
@Repository
public interface PdsDao {
	
	public List<PdsDto> getTotalList(SearchDto search);
	public int allPds(SearchDto search);
	public int uploadPds(PdsDto pds);
	public PdsDto getPds(int seq);
	public int deletePds(int seq);
	public int updatePds(PdsDto pds);
	public int addStep(PdsDto pds);
	public int answerPds(PdsDto pds);
	public int downcount(int seq);
	public int readcount(int seq);
}
