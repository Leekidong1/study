package bit.com.a.dao;

import java.util.List;

import bit.com.a.dto.BbsDto;
import bit.com.a.dto.SearchDto;

public interface BbsDao {

	public List<BbsDto> getList(SearchDto search);
	public int allBbs(SearchDto search);
	public int addBbs(BbsDto bbs);
	public BbsDto getBbs(int seq);
	public int deleteBbs(int seq);
	public int updateBbs(BbsDto bbs);
	public int addStep(BbsDto bbs);
	public int addAnswer(BbsDto bbs);
	public int readcount(int seq);
}
