<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Groovy | 추천</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/recommendResult.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/x-icon">
    <script src="https://kit.fontawesome.com/8f808bece4.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>

<body>
    <!-- s : wrap -->
    <div id="wrap">
        <!-- s : header -->
        <%@ include file="header.jsp" %>
        <!-- e : header -->

        <!-- s : main -->
        <div id="main">
            <div id="section">
                <div class="container content_container">
                    <div class="recom_result">
                        <div class="recom_result_title">
                            <h2>추천</h2>
                        </div>
                        <div class="recom_result_tag">
                       		<div class="tag">#${ param.artist_name }</div>
                            <div class="tag">#${ param.genre }</div>
                            <div class="tag">#${ param.track_name }</div>
                        </div>
                        <div class="recom_result_body">
                        	<div class="search_body_th">
								<div>
									<div class="average">평점</div>
									<div class="rate">리뷰</div>
								</div>
							</div>
                        	<c:forEach var="album" items="${ list }" varStatus="status">
	                            <div class="recom_list" onclick="location.href='<c:url value="/chart/review?id=${ album.id }" />'">
	                                <div class="album_thum">
	                                    <img src="${ album.images[0].url }" alt="">
	                                </div>
	                                <div class="album_info">
	                                    <div class="album_title">${ album.name }</div>
	                                    <div class="album_artist">${ album.artists[0].name }</div>
	                                    <div class="album_date">${ album.releaseDate }</div>
	                                    <div class="album_genre">
	                                        <span>${ album.type }</span>
	                                    </div>
	                                </div>
	                                <div class="album_average">
										<c:choose>
											<c:when test="${ empty avgs[status.index].avg }">
												<span>0</span>
											</c:when>
											<c:otherwise>
												<span>${ avgs[status.index].avg }</span>
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

                    <%@ include file="aside.jsp" %>
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