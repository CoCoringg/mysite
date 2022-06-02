package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class replyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gNo = request.getParameter("gNo");
		String oNo = request.getParameter("oNo");
		String depth = request.getParameter("depth");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String userNo = request.getParameter("userNo");
		
		BoardVo vo = new BoardVo();
		vo.setgNo(Long.parseLong(gNo));
		vo.setoNo(Long.parseLong(oNo));
		vo.setDepth(Long.parseLong(depth));
		vo.setTitle(title);
		vo.setContents(content);
		vo.setUserNo(Long.parseLong(userNo));
		
		new BoardRepository().replyUpdate(vo);
		new BoardRepository().insert(vo);
		
		
		
		WebUtil.redirect(request, response, request.getContextPath()+"/board?page=1");
		
	}

}
