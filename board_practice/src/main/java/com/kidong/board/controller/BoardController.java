package com.kidong.board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kidong.board.dto.BoardDto;
import com.kidong.board.service.BoardService;

@Controller
public class BoardController {
	
	@Resource(name = "service")
	private BoardService service;
	
	private static final String FILEPATH = "C:\\Users\\dev\\Desktop\\upload";
	
	@RequestMapping("list")
	public String list(@RequestParam Map<String, Object> map, Model model) {
		if (map == null || map.isEmpty()) {
			map.put("pageNumber", 1);
		}
		Map<String, Object> list = service.list(map);
		model.addAttribute("list", list);
		return "board/list";
	}
	
	@ResponseBody
	@RequestMapping("listAjax")
	public Map<String, Object> listAjax(@RequestParam Map<String, Object> map) {
		System.out.println(map.toString());
		return service.list(map);
	}
	
	@RequestMapping("writeboard")
	public String writeboard(@RequestParam int seq, Model model) {
		if (seq != 0) {
			BoardDto board = service.boardDetail(seq);
			model.addAttribute("board", board);
		}
		model.addAttribute("seq", seq);
		return "board/writeboard";
	}
	
	@RequestMapping(value = "writeBoardAf", method = {RequestMethod.POST})
	public String writeBoardAf(@RequestParam Map<String, Object> map,
							   @RequestParam("fileload") MultipartFile[] files) {
//							   multipartHttpServletRequest files, files.getFile
		String insertBorad = service.writeBoard(map);
		
		if ("success".equals(insertBorad)) {
			for ( MultipartFile file : files) {
				if (!file.getOriginalFilename().isEmpty()) {
					
					System.out.println("Path Fupload:" + FILEPATH);
					
					//??????????????? -> new????????? ??????
					String originalName = file.getOriginalFilename();
					String post = originalName.substring(originalName.indexOf('.'));
					String newfilename = UUID.randomUUID().toString() + post;
					
					map.put("originalName", originalName);
					map.put("newfilename", newfilename);
					map.put("fupload", FILEPATH);
					
					//?????? ????????? ?????????
					File uploadFile = new File(FILEPATH + "/" + newfilename);
					try {
						file.transferTo(uploadFile);
//						FileUtils.writeByteArrayToFile(uploadFile, file.getBytes());
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					// DB??? ??????
					service.uploadFile(map);
				}
			}
		}
		return "redirect:/list";
	}
	
	@RequestMapping("downloadFile")
	public void downloadFile(@RequestParam Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {

		File file = new File(FILEPATH + "/" + map.get("filename"));
		
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		
		String header = request.getHeader("User-Agent");
	    String fileName;
	    
	    if ((header.contains("MSIE")) || (header.contains("Trident")) || (header.contains("Edge"))) {
	        //????????? ??????????????? 10?????? ??????, 11??????, ???????????? ????????? 
	        fileName = URLEncoder.encode(map.get("filename")+"", "UTF-8");
	    } else {
	        //????????? ?????????????????? ?????????
	        fileName = new String((map.get("filename")+"").getBytes("UTF-8"), "iso-8859-1");
	    }
	    //????????? ????????? ??????????????? contentType
	    response.setContentType("application/octet-stream");
	    //??????????????? ??????????????? ????????????
	    response.setHeader("Content-Disposition", "attachment; filename=\""+ fileName + "\"");
	    //????????????
	    FileCopyUtils.copy(in, response.getOutputStream());
	    in.close();
	    response.getOutputStream().flush();
	    response.getOutputStream().close();
		
	}
	
	@ResponseBody
	@RequestMapping(value = "updateBoardAf", method = {RequestMethod.POST})
	public String updateBoardAf(@RequestParam Map<String, Object> map) {
		return service.updateBoard(map);
	}
	
	@RequestMapping("boardDetail")
	public String boardDetail(@RequestParam int seq, Model model) {
		BoardDto board = service.boardDetail(seq);
		List<Map<String, Object>> files = service.getFile(seq);
		model.addAttribute("detail", board);
		model.addAttribute("files", files);
		return "board/detail";
	}
	
	@RequestMapping("deleteBoard")
	public String deleteBoard(String seq) {
		service.deleteBoard(seq);
		return "redirect:/list";
	}
	
	@RequestMapping("mutiDeleteBoard")
	public String mutiDeleteBoard(String[] seqs) {
		
//		List<String> list = Arrays.asList(seqs);
		
		service.mutiDeleteBoard(seqs);
		return "redirect:/list";
	}
}
