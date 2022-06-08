package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookRepository guestbookRepositroy;
	
	public List<GuestbookVo> getMessageList() {
		return guestbookRepositroy.findAll();
	}
	
	public boolean deleteMessage(Long no, String password) {
		GuestbookVo vo = new GuestbookVo();
		vo.setNo(no);
		vo.setPassword(password);
		return guestbookRepositroy.delete(vo);
	}
	
	public boolean addMessage(GuestbookVo vo) {
		return guestbookRepositroy.insert(vo);
	}

}
