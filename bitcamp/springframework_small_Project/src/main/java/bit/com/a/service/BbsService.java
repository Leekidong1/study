package bit.com.a.service;

import java.util.List;

import bit.com.a.dto.BbsDto;
import bit.com.a.dto.SearchDto;

public interface BbsService {
	
	public List<BbsDto> getList(SearchDto search);
	public int allBbs(SearchDto search);
	public boolean addBbs(BbsDto bbs);
	public BbsDto getBbs(int seq);
	public boolean deleteBbs(int seq);
	public boolean updateBbs(BbsDto bbs);
	public boolean addStep(BbsDto bbs);
	public boolean addAnswer(BbsDto bbs);
	public String readcount(int seq);
}
