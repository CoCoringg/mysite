package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.web.mvc.Action;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		
		new BoardRepository().delete(Long.parseLong(no));
		
		// 삭제 alert 
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('게시물이 삭제 되었습니다.'); location.href='"+request.getContextPath()+"/board?page=1';</script>");
		out.flush();
		
	}

}
