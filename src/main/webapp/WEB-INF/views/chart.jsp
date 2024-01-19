<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Groovy | 리뷰</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/chart.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/x-icon">
<script src="https://kit.fontawesome.com/8f808bece4.js"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>

<body>
	<!-- s : wrap -->
	<div id="wrap">
		<!-- s : header -->
		<%@ include file="header.jsp"%>
		<!-- e : header -->

		<!-- s : main -->
		<div id="main">
			<div id="review">
				<!-- s : container -->
				<div class="container content_container">
					<div class="contents">
						<div class="review_header">
							<div class="review_header_title">
								<h2>차트</h2>
							</div>
						</div>
						<div class="review_body">
							<div class="review_body_header">
								<form name="sortForm" class="review_body_sort" action='<c:url value="/chart/list" />'>
									<input type="hidden" value="latest" name="order" />
									<div>
										<ul>
											<li class="${ order == 'latest' ? 'selected' : '' }" data-order="latest">최신음악순</li>
											<li class="${ order == 'review' ? 'selected' : '' }" data-order="review">리뷰순</li>
											<li class="${ order == 'rating' ? 'selected' : '' }" data-order="rating">평점순</li>
										</ul>
									</div>
									<div>
										<div class="average">평점</div>
										<div class="rate">리뷰</div>
									</div>
								</form>
								<div class="review_lists">
									<c:forEach var="album" items="${ latestAlbums }" varStatus="status">
										<div class="review_list" onclick="location.href='<c:url value="/chart/review?id=${ album.id }" />'">
											<div class="album_thum">
												<img src="${ album.images[0].url }" alt="">
											</div>
											<div class="album_info">
												<div class="album_title">
													<a href='<c:url value="/chart/review?id=${ album.id }" />'>${ album.name }</a>
												</div>
												<div class="album_artist">${ album.artists[0].name }</div>
												<div class="album_date">${ album.releaseDate }</div>
												<div class="album_genre">${ album.albumType }</div>
											</div>
											<div class="album_average">
												<c:choose>
													<c:when test="${ empty rateList[status.index].avg }">
														<span>0</span>
													</c:when>
													<c:otherwise>
														<span><fmt:formatNumber value="${ rateList[status.index].avg }" pattern=".00" /></span>
													</c:otherwise>
												</c:choose>
											</div>
											<div class="album_rate">
												<span>${ empty rateList[status.index].count ? 0 : rateList[status.index].count }</span>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
						<div id="page">
							<c:forEach var="i" begin="${ph.beginPage }" end="${ph.endPage }">
								<a href='<c:url value="/chart/list${ ph.sc.getQueryString(i) }"/>'
									class="${ ph.sc.page == i ? 'active' : '' }">${ i }</a>
							</c:forEach>
						</div>
					</div>
					
					<%@ include file="aside.jsp"%>
				</div>
				<!-- e : container -->
			</div>
		</div>
		<!-- e : main -->

		<!-- s : footer -->
		<%@ include file="footer.jsp"%>
		<!-- e : footer -->
	</div>
	<!-- e : wrap -->
	<script>
        const radioButtons = document.querySelectorAll('input[type="radio"]');

        radioButtons.forEach(radioButton => {
            radioButton.addEventListener('change', function () {
                document.querySelectorAll('label').forEach(label => {
                    label.classList.remove('checked-label');
                });

                if (this.checked) {
                    document.querySelector(`label[for="$(this.id)"]`).classList.add('checked-label');
                }
            });
        });
        const listItems = document.querySelectorAll('.review_body_sort li');

        listItems.forEach(item => {
            item.addEventListener('click', function () {
                this.classList.add('selected');

                listItems.forEach(otherItem => {
                    if (otherItem !== this) {
                        otherItem.classList.remove('selected');
                    }
                });
                
                let order = $(this).attr('data-order');
                console.log(order);
                
                $("input[name=order]").val(order);
                sortForm.submit();
            });
        });
    </script>
</body>

</html>