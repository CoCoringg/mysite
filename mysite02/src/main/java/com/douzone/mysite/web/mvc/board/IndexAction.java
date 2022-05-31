package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int totalPageNum;
		String page = request.getParameter("page");
		List<BoardVo> list = new BoardRepository().findAll();
		if(list.size()%5 != 0) {
			totalPageNum = list.size() / 5 +1;
		} else {
			totalPageNum = list.size() / 5;
		}
		
		List<BoardVo> pagingList = new BoardRepository().paging(Integer.parseInt(page));
		
		request.setAttribute("list", list);
		request.setAttribute("totalPageNum", totalPageNum);
		request.setAttribute("pagingList", pagingList);
		
		WebUtil.forward(request, response, "board/index");
	}

}
