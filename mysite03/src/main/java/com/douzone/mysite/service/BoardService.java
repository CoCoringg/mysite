package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.pagingVo;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	public List<BoardVo> getBoardList(int page, String kwd) {
		return boardRepository.findAll(page, kwd);
	}
	
	public pagingVo paging(int page, String kwd) {
		pagingVo vo = new pagingVo();
		vo.setCurrentPage(page);
		vo.setCountList(5); // 출력될 게시물 수
		vo.setCountPage(5); // 페이지 수
		vo.setTotalCount(boardRepository.listCount(kwd)); 
		int totalPage = vo.getTotalCount() / vo.getCountList();
		
		if(vo.getTotalCount() % vo.getCountList() > 0) {
			totalPage++;
		}
		vo.setTotalPage(totalPage);
		int startPage = ((page-1)/vo.getCountPage()) * vo.getCountPage()+1;
		int endPage = startPage + vo.getCountPage() -1;
		
		vo.setStartPage(startPage);
		vo.setEndPage(endPage);
		
		return vo;
	}
	
	public BoardVo getBoard(long no) {
		return boardRepository.findByNo(no);
	}

	public boolean insert(BoardVo vo) {
		return boardRepository.insert(vo);
	}

	public boolean replyUpdate(BoardVo vo) {
		return boardRepository.replyUpdate(vo);
	}

	public boolean modify(BoardVo vo) {
		return boardRepository.update(vo);
	}

	public boolean delete(long no) {
		return boardRepository.delete(no);
	}

	public boolean updateHit(long no) {
		return boardRepository.updateHit(no);
	}
}
