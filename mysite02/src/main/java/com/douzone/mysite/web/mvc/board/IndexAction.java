package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.pagingVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BoardVo> list = null;
		List<BoardVo> pagingList = null;
		int page = Integer.parseInt(request.getParameter("page"));
		String keyword = request.getParameter("kwd");
		pagingVo paging = new pagingVo();
		
		
		int countList = paging.getCountList(); //출력될 게심루 수
		int countPage = paging.getCountPage(); // 페이지 수


		list = new BoardRepository().findAll(keyword);
		pagingList = new BoardRepository().paging(page, keyword);
		
		int totalPage = list.size() / countList;

		if(list.size() % countList > 0) {
			totalPage++;
			paging.setTotalPage(totalPage);
		} 
		
		int startPage = ((page-1)/countPage) * countPage+1;
		int endPage = startPage + countPage -1;
		

		paging.setStartPage(startPage);
		paging.setEndPage(endPage);

		request.setAttribute("list", list);
		request.setAttribute("keyword", keyword);
		request.setAttribute("pagingList", pagingList);
		request.setAttribute("paging", paging);

		WebUtil.forward(request, response, "board/index");
	}

}