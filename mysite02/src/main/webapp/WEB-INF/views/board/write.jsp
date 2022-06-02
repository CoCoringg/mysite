<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form class="board-form" method="post" action="${pageContext.request.contextPath }/board">
					<c:choose>
						<c:when test="${empty vo.gNo }">
							<input type = "hidden" name = "a" value="write">
						</c:when>
						<c:otherwise>
							<input type = "hidden" name = "a" value="reply">
							<input type = "hidden" name = "gNo" value="${vo.gNo }" >
							<input type = "hidden" name = "oNo" value="${vo.oNo }" >
							<input type = "hidden" name = "depth" value="${vo.depth }" >
						</c:otherwise>
					</c:choose>
					<input type = "hidden" name = "userNo" value="${authUser.no }" >
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글쓰기</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value=""></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<textarea id="content" name="content"></textarea>
							</td>
						</tr>
					</table>
					<div class="bottom">
						<c:choose>
							<c:when test="${empty param.no }">
								<a href="${pageContext.request.contextPath }/board?page=1">취소</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath }/board?a=view&no=${vo.no}">취소</a>
							</c:otherwise>
						</c:choose>
						<input type="submit" value="등록">
					</div>
				</form>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>