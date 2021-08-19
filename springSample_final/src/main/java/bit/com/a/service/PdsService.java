package bit.com.a.service;

import java.util.List;

import bit.com.a.dto.PdsDto;
import bit.com.a.dto.SearchDto;

public interface PdsService {

	List<PdsDto> getPdsList();
	boolean uploadPds(PdsDto dto);
	PdsDto getPds(int seq);
	boolean readcount(int seq);
	boolean downcount(int seq);
	boolean updatePds(PdsDto dto);
	boolean deletePds(int seq);
	List<PdsDto> getTotalList(SearchDto search);
	int allPds(SearchDto search);
	boolean addStep(PdsDto dto);
	boolean answerPds(PdsDto dto);
}
