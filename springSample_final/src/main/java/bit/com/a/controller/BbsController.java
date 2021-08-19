package bit.com.a.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.a.dto.BbsDto;
import bit.com.a.dto.SearchDto;
import bit.com.a.service.BbsService;

@Controller
public class BbsController {

	
	@Autowired
	BbsService service;
	
	@RequestMapping(value = "bbslist.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String bbslist(Model model,String msg, SearchDto search) {
		List<BbsDto> list = service.getList(search);
		int totalCount = service.allBbs(search);
		model.addAttribute("doc_title", "글목록");
		model.addAttribute("search", search);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("bbslist", list);
		model.addAttribute("msg", msg);
		return "bbslist.tiles";
	}
	
	@ResponseBody
	@RequestMapping(value = "bbslistAjax.do", method = {RequestMethod.GET,RequestMethod.POST})
	public Map<String, Object> bbslistAjax(Model model, SearchDto search) {
		System.out.println(search.toString());
		List<BbsDto> list = service.getList(search);
		int totalCount = service.allBbs(search);
		Map<String, Object> map = new HashMap<>();
		map.put("search", search);
		map.put("totalCount", totalCount);
		map.put("bbslist", list);
		return map;
	}
	
		
	@RequestMapping(value = "bbswrite.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String bbswrite(Model model, String msg) {
		model.addAttribute("doc_title", "글쓰기");
		model.addAttribute("msg", msg);
		return "bbswrite.tiles";
	}
	
	@RequestMapping(value = "bbswriteAf.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String bbswriteAf(BbsDto bbs, Model model) {
		System.out.println(bbs.toString());
		boolean b = service.addBbs(bbs);
		if(b) {
			model.addAttribute("msg", "글작성 완료하였습니다");
			return "redirect:/bbslist.do";
		}
		model.addAttribute("msg", "글작성 실패하였습니다");
		return "redirect:/bbswrite.do";
	}
	
	@RequestMapping(value = "bbsdetail.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String bbsdetail(int seq,Model model) {
		BbsDto bbs = service.getBbs(seq);
		//조회수
		String msg = service.readcount(seq);
		System.out.println("조회수결과 :" + msg);
		model.addAttribute("doc_title", "상세글보기");
		model.addAttribute("bbs", bbs);
		return "bbsdetail.tiles";
	}
	
	@RequestMapping(value = "bbsupdate.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String bbsupdate(int seq,Model model) {
		BbsDto bbs = service.getBbs(seq);
		model.addAttribute("doc_title", "글수정");
		model.addAttribute("bbs", bbs);
		return "bbsupdate.tiles";
	}
		
	@RequestMapping(value = "bbsupdateAf.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String bbsupdateAf(BbsDto bbs,Model model) {
		boolean b = service.updateBbs(bbs);
		if(b) {
			model.addAttribute("msg", "성공적으로 글수정되었습니다");
			return "redirect:/bbslist.do";
		}
		model.addAttribute("seq", bbs.getSeq());
		return "redirect:/bbsdetail.do";
	}
	
	@RequestMapping(value = "bbsdelete.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String bbsdelete(int seq,Model model) {
		boolean b = service.deleteBbs(seq);
		if(b) {
			model.addAttribute("msg", "성공적으로 글삭제되었습니다");
			return "redirect:/bbslist.do";
		}
		model.addAttribute("seq", seq);
		return "redirect:/bbsdetail.do";
	} 
	
	@RequestMapping(value = "answer.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String answer(int seq,String msg,Model model) {
		BbsDto bbs = service.getBbs(seq);
		model.addAttribute("bbs", bbs);
		model.addAttribute("doc_title", "댓글작성");
		model.addAttribute("msg", msg);
		return "answer.tiles";
	} 
	
	@RequestMapping(value = "answerAf.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String answerAf(BbsDto bbs,Model model) {
		System.out.println(bbs.toString());
		boolean step = service.addStep(bbs);
		boolean answer = service.addAnswer(bbs);
		if(step == true && answer == true) {
			model.addAttribute("msg", "성공적으로 댓글 작성되었습니다");
			return "redirect:/bbslist.do";
		}
		model.addAttribute("msg", "댓글작성 실패하였습니다");
		model.addAttribute("seq", bbs.getSeq());
		return "redirect:/answer.do";
	} 
}
