<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
				<form id="search_form" action="${pageContext.request.contextPath }/board?page=1" method="post">
					<input type="text" id="kwd" name="kwd" value="${keyword }">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var='count' value='${fn:length(list) }' />
					<c:choose>
						<c:when test="${param.page >=2 }">
							<c:set var='count' value='${fn:length(list)-(param.page-1)*5 }' />
						</c:when>
						<c:otherwise>
							<c:set var='count' value='${fn:length(list) }' />
						</c:otherwise>
					</c:choose>
					<c:forEach items='${pagingList }' var='vo' varStatus='status'>
						<tr>
							<td>${count-status.index }</td>
							<td style="text-align:left; padding-left:${(vo.depth-1)*10 }px">
								<c:if test="${(vo.depth-1)*10 > 0 }">
									<img
										src="${pageContext.servletContext.contextPath }/assets/images/reply.png" />
								</c:if> <a
								href="${pageContext.request.contextPath }/board?a=view&no=${vo.no }">${vo.title }</a>
							</td>
							<td>${vo.userName }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>
							<c:choose>
								<c:when test="${vo.userNo == authUser.no }">
									<td><a
										href="${pageContext.request.contextPath }/board?a=delete&no=${vo.no }"
										class="del">삭제</a></td>
								</c:when>
								<c:otherwise>
									<td class="del"></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:choose>
							<c:when test="${param.page==1 && empty keyword }">
								<li><a
									href="${pageContext.request.contextPath }/board?page=1">◀</a></li>
							</c:when>
							<c:when test="${param.page==1 && not empty keyword }">
								<li><a
									href="${pageContext.request.contextPath }/board?kwd=${keyword }&page=1">◀</a></li>
							</c:when>
							<c:when test="${param.page!=1 && not empty keyword}">
								<li><a
									href="${pageContext.request.contextPath }/board?kwd=${keyword }&page=${param.page-1 }">◀</a></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="${pageContext.request.contextPath }/board?page=${param.page-1 }">◀</a></li>
							</c:otherwise>
						</c:choose>
						<c:forEach begin='${paging.startPage }' end='${paging.endPage }'
							step='1' var='i'>
							<c:choose>
								<c:when test="${i == param.page }">
									<li class="selected">${i }</li>
								</c:when>
								<c:when test="${i > paging.totalPage }">
									<li>${i }</li>
								</c:when>
								<c:when test="${empty keyword }">
									<li><a
										href="${pageContext.request.contextPath }/board?page=${i }">${i }</a></li>
								</c:when>
								<c:otherwise>
									<li><a
										href="${pageContext.request.contextPath }/board?kwd=${keyword }&page=${i }">${i }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when
								test="${param.page == paging.totalPage && empty keyword }">
								<li><a
									href="${pageContext.request.contextPath }/board?page=${paging.totalPage }">▶</a></li>
							</c:when>
							<c:when
								test="${param.page == paging.totalPage && not empty keyword }">
								<li><a
									href="${pageContext.request.contextPath }/board?kwd=${keyword }&page=${paging.totalPage }">▶</a></li>
							</c:when>
							<c:when
								test="${param.page != paging.totalPage && not empty keyword}">
								<li><a
									href="${pageContext.request.contextPath }/board?kwd=${keyword }&page=${param.page+1 }">▶</a></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="${pageContext.request.contextPath }/board?page=${param.page+1 }">▶</a></li>
							</c:otherwise>
						</c:choose>

					</ul>
				</div>
				<div class="bottom">
					<c:if test='${not empty authUser }'>
						<a href="${pageContext.request.contextPath }/board?a=writeform"
							id="new-book">글쓰기</a>
					</c:if>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>