package bit.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import bit.com.a.dto.BbsDto;
import bit.com.a.dto.SearchDto;

@Mapper
@Repository
public interface BbsDao {
	
	public List<BbsDto> getBbslist(SearchDto dto);
	public int allBbs(SearchDto dto);
	public int addBbs(BbsDto bbs);
	public BbsDto getBbs(int seq);
	public int readCount(int seq);
	public int updateBbs(BbsDto bbs);
	public int deleteBbs(int seq);
	public int addStep(BbsDto bbs);
	public int addAnswer(BbsDto bbs);
}
