package bit.com.a.poll;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.com.a.dto.MemberDto;

@Controller
public class PollController {
	
	@Autowired
	PollService service;
	
	@RequestMapping(value = "polllist.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String polllist(Model model, HttpServletRequest req) {
		model.addAttribute("doc_title", "투표 목록");
		
		String id = ((MemberDto)req.getSession().getAttribute("login")).getId();
		
		List<PollDto> list = service.getPollAllList(id);
		model.addAttribute("plists", list);
		
		return "polllist.tiles";
	}
	
	@RequestMapping(value = "pollmake.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String pollmake(Model model) {
		model.addAttribute("doc_title", "투표 만들기");	
		return "pollmake.tiles";
	}
	
	@RequestMapping(value = "pollmakeAf.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String pollmakeAf(PollBean pbean) {
		service.makePoll(pbean);
		
		return "redirect:/polllist.do";
	}
	
	@RequestMapping(value = "polldetail.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String polldetail(PollDto poll, Model model) {
		model.addAttribute("doc_title", "투표 내용");
		
		PollDto dto = service.getPoll(poll);
		List<PollSubDto> list = service.getPollSubList(poll);
		
		model.addAttribute("poll", dto);
		model.addAttribute("pollsublist", list);
		return "polldetail.tiles";
	}
	
	@RequestMapping(value = "polling.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String polling(Voter voter) {
		service.polling(voter);
		return "redirect:/polllist.do";
	}
	
	@RequestMapping(value = "pollresult.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String pollresult(PollDto poll, Model model) {
		model.addAttribute("doc_title", "투표 결과");
		
		// 투표주제(Poll) 필요
		PollDto dto = service.getPoll(poll);
		// 투표 보기들(PollSub) 필요
		List<PollSubDto> list = service.getPollSubList(poll);
		
		model.addAttribute("poll", dto);
		model.addAttribute("pollsublist", list);
		
		return "pollresult.tiles";
	}
}
