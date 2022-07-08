package com.douzone.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JSONResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@RestController("guestbookApiController")
@RequestMapping("/api/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@GetMapping("")
	public JSONResult list() {
		List<GuestbookVo> list = guestbookService.getMessageList();
		return JSONResult.success(list);
	}
	
	@PostMapping("")
	public JSONResult post(@RequestBody GuestbookVo vo) {
		guestbookService.addMessage(vo);
		vo.setPassword("");
		return JSONResult.success(vo);
	}
	
	@DeleteMapping("/{no}")
	public JSONResult delete(@PathVariable("no") Long no, String password) {
		boolean result = guestbookService.deleteMessage(no, password);
		
		return JSONResult.success(result?no:-1);
	}
}
