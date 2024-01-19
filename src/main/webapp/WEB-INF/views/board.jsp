<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<%@ page import="com.project.groovy.util.Time" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Groovy | 커뮤니티</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/board.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/x-icon">
<script src="https://kit.fontawesome.com/8f808bece4.js"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
</head>

<body>
	<!-- s : wrap -->
	<div id="wrap">
		<!-- s : header -->
		<%@ include file="header.jsp"%>
		<!-- e : header -->

		<!-- s : main -->
		<div id="main">
			<!-- s : contents -->
			<div id="contents">
				<div class="container content_container">
					<!-- s : board -->
					<div id="board">
						<h2>커뮤니티</h2>
						<form id="board_nav" name="form" method="get"
							action="<c:url value='/board/list' />">
							<div id="category">
								<label for="all"
									class="${ ph.sc.optionCategory == 'A' || empty ph.sc.optionCategory ? 'checked' : '' }">전체</label>
								<input type="radio" name="optionCategory" id="all" value="A" class="hidden"
									${ ph.sc.optionCategory == 'A' || empty ph.sc.optionCategory ? 'checked' : '' }>

								<label for="music" class="${ ph.sc.optionCategory == 'M' ? 'checked' : '' }">음악</label>
								<input type="radio" name="optionCategory" id="music" value="M" class="hidden"
									${ ph.sc.optionCategory == 'M' || ph.sc.optionCategory == '' ? 'checked' : '' }>

								<label for="review"
									class="${ ph.sc.optionCategory == 'R' ? 'checked' : '' }">후기</label>
								<input type="radio" name="optionCategory" id="review" value="R" class="hidden"
									${ ph.sc.optionCategory == 'R' || ph.sc.optionCategory == '' ? 'checked' : '' }>

							</div>
							<div id="search_area">
								<select name="optionSearch" id="searchField">
									<option value="A"
										${ ph.sc.optionSearch == 'A' || ph.sc.optionSearch == '' ? 'selected' : '' }>제목+내용</option>
									<option value="T"
										${ ph.sc.optionSearch == 'T' ? 'selected' : '' }>제목</option>
									<option value="N"
										${ ph.sc.optionSearch == 'N' ? 'selected' : '' }>작성자</option>
								</select> <input type="text" name="keyword" id="searchWord"
									placeholder="검색어를 입력하세요." value="${ ph.sc.keyword }" />
								<button id="searchBtn" type="button" onclick="form.submit();">
									<i class="fa-solid fa-magnifying-glass fa-lg"></i>
								</button>
							</div>
						</form>
						<div id="list">
							<table>
								<tr class="theader">
									<td width="5%">번호</td>
									<td width="10%">카테고리</td>
									<td width="50%">제목</td>
									<td width="10%">작성자</td>
									<td width="10%">날짜</td>
									<td width="10%">조회수</td>
									<td width="5%">좋아요</td>
								</tr>
								<c:choose>
									<c:when test="${ empty list }">
										<tr class="tbody">
											<td id="empty_board" colspan="7">등록된 게시물이 없습니다.</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach var="board" items="${ list }">
											<tr class="tbody">
												<td>${ board.num }</td>
												<c:choose>
													<c:when test="${ board.category == 'review' }">
														<td>후기</td>
													</c:when>
													<c:otherwise>
														<td>음악</td>
													</c:otherwise>
												</c:choose>
												<td><a style="text-align: left;"
													href='<c:url value="/board/read${ ph.sc.queryString }&num=${ board.num }" />'>
														${ board.title } <span>${ board.comment_cnt == 0 ? '' : board.comment_cnt }</span></a></td>
												<td>${ board.writer_nickname }</td>
												<fmt:formatDate value="${ board.postdate }" type="date"
													pattern="yyyy-MM-dd" var="postdate" />
												<fmt:formatDate value="${ now }" type="date"
													pattern="yyyy-MM-dd" var="today" />
												<c:choose>
													<c:when test="${ postdate == today }">
														<td><fmt:formatDate value="${ board.postdate }" type="time" pattern="HH:mm" /></td>
													</c:when>
													<c:otherwise>
														<td>${ postdate }</td>
													</c:otherwise>
												</c:choose>
												<td>${ board.view_cnt }</td>
												<td>${ board.like_cnt }</td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</table>
						</div>
						<div id="write_btn_area">
							<button type="button" id="write_btn" class="btn btn1"
								onclick="location.href = '<c:url value="/board/write${ ph.sc.queryString }" />'">글쓰기</button>
						</div>
						<div id="page">
							<c:if test="${ ph.showPrev }">
								<a href="<c:url value='/board/list${ ph.sc.getQueryString(1) }' />">&laquo;</a>
								<a
									href="<c:url value='/board/list${ ph.sc.getQueryString(ph.beginPage - 1) }' />">&lt;</a>
							</c:if>
							<c:forEach var="i" begin="${ph.beginPage }" end="${ph.endPage }">
								<a
									href='<c:url value="/board/list${ ph.sc.getQueryString(i) }"/>'
									class="${ ph.sc.page == i ? 'active' : '' }">${ i }</a>
							</c:forEach>
							<c:if test="${ ph.showNext }">
								<a
									href="<c:url value='/board/list${ ph.sc.getQueryString(ph.endPage + 1) }' />">&gt;</a>
								<a href="<c:url value='/board/list${ ph.sc.getQueryString(ph.totalPage) }' />">&raquo;</a>
							</c:if>
						</div>
					</div>
					<!-- e : board -->
					<!-- s : aside -->
					<%@ include file="aside.jsp" %>
					<!-- e : aside -->
				</div>
			</div>
			<!-- e : contents -->
		</div>
		<!-- e : main -->

	<!-- s : footer -->
	<%@ include file="footer.jsp"%>
	<!-- e : footer -->
	</div>
	<!-- e : wrap -->

	<script>
    let labels = document.querySelectorAll("#category label");
	let c = document.querySelectorAll("input[name='optionCategory']");
    for (let i=0; i<c.length; i++) {
		c[i].addEventListener("click", function() {
			document.querySelector("#board_nav").submit();
		});
	}
    
    let msg = "${ msg }";
    if (msg == "write_ok") alert("성공적으로 등록되었습니다.");
    if (msg == "modify_ok") alert("성공적으로 수정되었습니다");
    if (msg == "del") alert("성공적으로 삭제되었습니다.");
    if (msg == "error") alert("삭제를 실패했습니다.");
    </script>
</body>

</html>