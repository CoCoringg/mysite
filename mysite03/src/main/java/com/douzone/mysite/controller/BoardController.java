package com.douzone.mysite.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.pagingVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping({"","/{page}","/{kwd}/{page}"})
	public String index(@PathParam("kwd") String kwd,
			@PathVariable("page") int page, Model model) {
		List<BoardVo> boardList = boardService.getBoardList(page, kwd);
		pagingVo paging = boardService.paging(page, kwd);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("paging", paging);
		model.addAttribute("page", page);
		model.addAttribute("keyword", kwd);
		return "board/index";
	}
	
	
}
