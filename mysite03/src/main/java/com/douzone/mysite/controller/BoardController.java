package com.douzone.mysite.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.pagingVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value={"","/{page}"})
	public String index(@PathVariable(value="page", required = false) int page, Model model) {
		List<BoardVo> boardList = boardService.getBoardList(page, null);
		pagingVo paging = boardService.paging(page, null);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("paging", paging);
		model.addAttribute("page", page);
		
		return "board/index";
	}
	
	@RequestMapping(value="/{kwd}/{page}")
	public String index(@PathVariable("kwd") String kwd,
			@PathVariable("page") int page, Model model) {
		List<BoardVo> boardList = boardService.getBoardList(page, kwd);
		pagingVo paging = boardService.paging(page, kwd);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("paging", paging);
		model.addAttribute("page", page);
		model.addAttribute("keyword", kwd);
		
		return "board/index";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST) 
	public String search(@RequestParam("kwd") String kwd, Model model) {
		return "redirect:/board/"+kwd+"/1";
	}
	
	@RequestMapping(value="/view/{no}", method=RequestMethod.GET)
	public String view(@PathVariable("no") long no, Model model) {
		BoardVo vo = boardService.getBoard(no);
		model.addAttribute("vo", vo);
		return "board/view";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@RequestMapping(value="/write/{userNo}", method=RequestMethod.GET)
	public String write(@PathVariable("userNo") long userNo, Model model) {
		BoardVo vo = boardService.getBoard(userNo);
		model.addAttribute("vo",vo);
		return "board/write";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@RequestParam("title") String title,
			@RequestParam("content") String content, @RequestParam("userNo") long userNo) {
		System.out.println(title);
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(content);
		vo.setUserNo(userNo);
		
		boardService.insert(vo);
		return "redirect:/board/1";
	}
	
}
