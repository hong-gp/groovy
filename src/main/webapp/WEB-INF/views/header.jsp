<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<body>
	<!-- s : header -->
	<div id="header">
		<div class="container">
			<div class="header_logo">
				<div class="logo">
					<a href='<c:url value="/" />'>
						<h2>Groovy</h2>
					</a>
				</div>
				<form name="searchForm" class="input_search_box" action='<c:url value="/search" />'>
					<input type="text" name="search" id="input_search" placeholder="앨범을 검색하세요"
						value="${ param.search }">
					<a href="javascript:;" class="search_icon">
						<i class="fa-solid fa-magnifying-glass"></i>
					</a>
				</form>
			</div>
			<div class="header_menu">
				<div class="nav">
					<ul>
						<a href="<c:url value='/news/list' />">
							<li>뉴스</li>
						</a>
						<a href="<c:url value='/chart/list' />">
							<li>차트</li>
						</a>
						<a href="<c:url value='/recommend/recommend' />">
							<li>추천</li>
						</a>
						<a href="<c:url value='/board/list' />">
							<li>커뮤니티</li>
						</a>
					</ul>
				</div>
				<div class="account">
					<ul>
						<c:choose>
							<c:when test="${ empty sessionScope.id }">
								<a href='<c:url value="/login" />'>
									<li>로그인</li>
								</a>
								<a href='<c:url value="/register" />'>
									<li>회원가입</li>
								</a>
							</c:when>
							<c:otherwise>
								<a href='<c:url value="/mypage/userInfo" />'>
									<li>${ sessionScope.nickname }</li>
								</a>
								<a href='<c:url value="/logout" />'>
									<li>로그아웃</li>
								</a>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- e : header -->
	
	<script>
		document.querySelector(".search_icon").addEventListener('click', function() {
			let search = document.querySelector("#input_search").value;
			
			if (search == '' || search == null) {
				alert("검색어를 입력해주세요.");
				return;
			}
			searchForm.submit();
		});
	</script>
</body>
</html>