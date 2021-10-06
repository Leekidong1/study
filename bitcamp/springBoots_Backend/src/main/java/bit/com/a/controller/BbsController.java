package bit.com.a.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bit.com.a.dto.BbsDto;
import bit.com.a.dto.SearchDto;
import bit.com.a.service.BbsService;

@RestController
public class BbsController {
	
	@Autowired
	BbsService service;
	
	@RequestMapping(value = "/bbslist", method = RequestMethod.POST)
	public Map<String, Object> bbslist(SearchDto search){
		System.out.println("BbsController bbslist()" + new Date());
		System.out.println(search.toString());
		List<BbsDto> list = service.getBbslist(search);
		int totalCount = service.allBbs(search);
		Map<String, Object> map = new HashMap<>();
		map.put("search", search);
		map.put("totalCount", totalCount);
		map.put("bbslist", list);
		return map;
	}
	
	@RequestMapping(value = "/bbswrite", method = {RequestMethod.POST,RequestMethod.GET})
	public String bbswrite(BbsDto bbs) {
		System.out.println("BbsController bbswrite()" + new Date());
		
		String msg = service.addBbs(bbs);
		return msg;
	}
	
	@RequestMapping(value = "/getBbs", method = {RequestMethod.POST,RequestMethod.GET})
	public BbsDto getBbs(int seq) {
		System.out.println("BbsController getBbs()" + new Date());
		System.out.println("seq:"+ seq);		
		BbsDto bbs = service.getBbs(seq);
		String msg = service.readCount(seq);
		System.out.println(msg);
		return bbs;
	}
	
	@RequestMapping(value = "/bbsupdate", method = {RequestMethod.POST,RequestMethod.GET})
	public String bbsupdate(BbsDto bbs) {
		System.out.println("BbsController bbsupdate()" + new Date());
		System.out.println(bbs.toString());
		String msg = service.updateBbs(bbs);
		return msg;
	}
	
	@RequestMapping(value = "/bbsdelete", method = {RequestMethod.POST,RequestMethod.GET})
	public String bbsdelete(int seq) {
		System.out.println("BbsController bbsdelete()" + new Date());
		String msg = service.deleteBbs(seq);
		return msg;
	}
	
	@RequestMapping(value = "/replyBbs", method = {RequestMethod.POST,RequestMethod.GET})
	public String reply(BbsDto bbs) {
		System.out.println("BbsController reply()" + new Date());
		System.out.println(bbs.toString());
		String msg = service.reply(bbs);
		return msg;
	}
}
