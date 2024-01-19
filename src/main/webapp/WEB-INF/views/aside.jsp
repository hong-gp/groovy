<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
	<div id="aside">
		<div class="promotion">
			<a href="${ promotion.link }"> 
				<img src="${pageContext.request.contextPath}/images/promotion/${ promotion.img_src }" alt="">
			</a>
		</div>
		<div class="today_board">
			<div class="today_board_title">
				<h3>오늘의 인기글</h3>
			</div>
			<div class="today_board_list">
				<ul>
					<c:set var="i" value="0" />
					<c:forEach var="board" items="${ bestBoard }">
						<a href='<c:url value="/board/read?num=${ board.num }" />'>
							<li>${ i = i + 1 }. ${ board.title }</li>
						</a>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>