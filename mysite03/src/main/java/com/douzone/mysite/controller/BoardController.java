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
	public String index(@PathVariable(value="page", required=false) Integer page, 
			@RequestParam(value="kwd", required=false) String kwd, Model model) {
		if(page == null) {
			page = 1;
		}
		List<BoardVo> boardList = boardService.getBoardList(page, kwd);
		pagingVo paging = boardService.paging(page, kwd);
		model.addAttribute("boardList", boardList);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", kwd);
		model.addAttribute("page", page);
		
		return "board/index";
	}
	
//	@RequestMapping(value="/search", method=RequestMethod.POST) 
//	public String search(@RequestParam("kwd") String kwd, Model model) {
//		return "redirect:/board/"+kwd+"/1";
//	}
	
	@RequestMapping(value="/view/{no}", method=RequestMethod.GET)
	public String view(@PathVariable("no") long no, Model model) {
		BoardVo vo = boardService.getBoard(no);
		model.addAttribute("vo", vo);
		
		boardService.updateHit(no);
		
		return "board/view";
	}
	
	@RequestMapping(value={"/write","/write/{userNo}"}, method=RequestMethod.GET)
	public String write(@PathVariable(value="userNo", required=false) Integer userNo, Model model) {
		if(userNo != null) {
			BoardVo vo = boardService.getBoard(userNo);
			model.addAttribute("vo",vo);
		}
		return "board/write";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@RequestParam("page") int page,
			@RequestParam("title") String title,
			@RequestParam("content") String content, @RequestParam("userNo") long userNo,
			@RequestParam(value="gNo", required=false, defaultValue="0") Integer gNo,
			@RequestParam(value="oNo", required=false, defaultValue="0") long oNo,
			@RequestParam(value="depth", required=false, defaultValue="0") long depth) {
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(content);
		vo.setUserNo(userNo);
		
		if(gNo != null) {
			vo.setgNo(gNo);
			vo.setoNo(oNo);
			vo.setDepth(depth);
			boardService.replyUpdate(vo);
		}
		boardService.insert(vo);
		return "redirect:/board/"+page;
	}
	
	@RequestMapping(value="/modify/{no}", method=RequestMethod.GET)
	public String modify(@PathVariable("no") long no, 
			Model model) {
		BoardVo vo = boardService.getBoard(no);
		model.addAttribute("vo",vo);
		return "board/modify";
	}
	
	@RequestMapping(value="/modify/{no}", method=RequestMethod.POST)
	public String modify(@PathVariable("no") long no, 
			@RequestParam("page") int page,
			@RequestParam("title") String title,
			@RequestParam("content") String content, Model model) {
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContents(content);
		boardService.modify(vo);
		
		return "redirect:/board/view/"+no+"?page="+page;
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(@PathVariable("no") long no) {
		boardService.delete(no);
		return "redirect:/board/1";
	}
}
