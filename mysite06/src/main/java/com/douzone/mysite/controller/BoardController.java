package com.douzone.mysite.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;


@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value={"","/{page}"})
	public String index(@PathVariable(value="page", required=false) Integer page, 
			@RequestParam(value="kwd", required=false) String kwd, Model model) {
		if(page == null) {
			page = 1;
		}
		Map<String, Object> map = boardService.getBoardList(page, kwd);
		model.addAllAttributes(map);
		return "board/index";
	}
	
	
	@RequestMapping(value="/view/{no}", method=RequestMethod.GET)
	public String view(@PathVariable("no") long no, Model model) {
		BoardVo vo = boardService.getBoard(no);
		model.addAttribute("vo", vo);
		boardService.updateHit(no);
		return "board/view";
	}
	
	@Auth
	@RequestMapping(value={"/write","/write/{userNo}"}, method=RequestMethod.GET)
	public String write(@PathVariable(value="userNo", required=false) Integer userNo, Model model) {
		if(userNo != null) {
			BoardVo vo = boardService.getBoard(userNo);
			model.addAttribute("vo",vo);
		}
		return "board/write";
	}
	
	@Auth
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@RequestParam("page") int page, BoardVo vo) {
		System.out.println(vo);
		if(vo.getgNo() != 0) {
			boardService.replyUpdate(vo);
		}
		boardService.insert(vo);
		return "redirect:/board/"+page;
	}
	
	@Auth
	@RequestMapping(value="/modify/{no}", method=RequestMethod.GET)
	public String modify(@PathVariable("no") long no, 
			Model model) {
		BoardVo vo = boardService.getBoard(no);
		model.addAttribute("vo",vo);
		return "board/modify";
	}
	
	@Auth
	@RequestMapping(value="/modify/{no}", method=RequestMethod.POST)
	public String modify(@PathVariable("no") long no, 
			@RequestParam("page") int page, BoardVo vo, Model model) {
		boardService.modify(vo);
		return "redirect:/board/view/"+no+"?page="+page;
	}
	
	@Auth
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(@PathVariable("no") long no) {
		boardService.delete(no);
		return "redirect:/board/1";
	}
}
