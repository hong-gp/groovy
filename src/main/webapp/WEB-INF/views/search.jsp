<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Groovy | 검색</title>
    <link rel="stylesheet" href="css/search.css">
    <link rel="stylesheet" href="css/style.css">
    <script src="https://kit.fontawesome.com/8f808bece4.js" crossorigin="anonymous"></script>
</head>

<body>
    <!-- s : wrap -->
    <div id="wrap">
        <!-- s : header -->
        <%@ include file="header.jsp" %>
        <!-- e : header -->

        <!-- s : main -->
        <div id="main">
            <div id="search">
                <div class="container content_container">
                    <div class="search_box">
                        <div class="search_title">
                            <h2>'${ param.search }' 검색결과</h2>
                        </div>
                        <div class="search_album">
                            <h2>앨범</h2>
                            <div class="search_body">
                                <div class="search_body_header">
                                    <div class="search_body_th">
                                        <div>
                                            <div class="average">평점</div>
                                            <div class="rate">리뷰</div>
                                        </div>
                                    </div>
                                    <div class="search_lists">
                                    	<c:forEach var="album" items="${ albums }" varStatus="status">
											<div class="search_list" onclick="location.href='<c:url value="/chart/review?id=${ album.id }" />'">
												<div class="album_thum">
													<img src="${ album.images[0].url }" alt="">
												</div>
												<div class="album_info">
													<div class="album_title">
														<a href="/groovy/chart/review?id=${ album.id }">${ album.name }</a>
													</div>
													<div class="album_artist">${ album.artists[0].name }</div>
													<div class="album_date">${ album.releaseDate }</div>
													<div class="album_genre">${ album.albumType }</div>
												</div>
												<div class="album_average">
													<c:choose>
														<c:when test="${ empty avgs[status.index].avg }">
															<span>0</span>
														</c:when>
														<c:otherwise>
															<span><fmt:formatNumber value="${ avgs[status.index].avg }" pattern=".00" /></span>
														</c:otherwise>
													</c:choose>
												</div>
												<div class="album_rate">
													<span>${ empty avgs[status.index].count ? 0 : avgs[status.index].count }</span>
												</div>
											</div>
										</c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="page">
							<c:forEach var="i" begin="${ph.beginPage }" end="${ph.endPage }">
								<a href='<c:url value="/search${ ph.sc.getReviewQueryString(i) }"/>'
									class="${ ph.sc.page == i ? 'active' : '' }">${ i }</a>
							</c:forEach>
						</div>
                    </div>

                    <%@ include file="aside.jsp"%>
                </div>
            </div>
        </div>
        <!-- e : main -->

        <!-- s : footer -->
        <%@ include file="footer.jsp" %>
        <!-- e : footer -->
    </div>
    <!-- e : wrap -->
</body>

</html>