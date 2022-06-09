package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/{page}", method=RequestMethod.GET)
	public String index(@PathVariable("page") int page, Model model) {
		List<BoardVo> list = boardService.getBoardList(page, null);
		model.addAttribute("pagingList", list);
		return "board/index";
	}
	
	@RequestMapping(value="/{kwd}/{page}", method=RequestMethod.GET)
	public String index(@PathVariable("kwd") String kwd,
			@PathVariable("page") int page, Model model) {
		List<BoardVo> list = boardService.getBoardList(page, kwd);
		model.addAttribute("pagingList", list);
		return "board/index";
	}
}
