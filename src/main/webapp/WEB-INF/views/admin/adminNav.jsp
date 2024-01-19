<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
	<div class="nav">
		<ul>
			<a href='<c:url value="/admin/news/management" />'>
				<li>뉴스 관리</li>
			</a>
			<a href='<c:url value="/admin/user/management" />'>
				<li>회원 관리</li>
			</a>
			<a href='<c:url value="/admin/review/management" />'>
				<li>리뷰 관리</li>
			</a>
			<a href='<c:url value="/admin/board/management" />'>
				<li>커뮤니티 관리</li>
			</a>
			<a href='<c:url value="/admin/promotion/management" />'>
				<li>프로모션 관리</li>
			</a>
		</ul>
	</div>
</body>
</html>