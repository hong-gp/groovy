<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Groovy</title>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/x-icon">
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
            <!-- s : content_review -->
            <div id="content_review">
                <div class="container">
                    <div class="review_header">
                        <h2>리뷰</h2>
                    </div>
                    <div class="review_body">
                    	<div class="review_body_bg"
                    		style="background: url('${ album.images[0].url }') no-repeat center; background-size: cover;"></div>
                    	<div class="review_body_bg_color"></div>
                        <div class="review_album">
                            <div class="thum">
                            	<a href='<c:url value="/chart/review?id=${ album.id }" />'>
                                	<img src="${ album.images[0].url }" alt="">
                                </a>
                            </div>
                        </div>
                        <div class="review_review">
                            <div class="review_review_header">
                                <div class="title">
                                	<a href='<c:url value="/chart/review?id=${ album.id }" />'>
                                    	<h2>${ album.name }</h2>
                                    </a>
                                </div>
                                <div class="artist">
                                    <h3>${ album.artists[0].name }</h3>
                                </div>
                            </div>
                            <div class="review_review_body">
                                <div class="profile">
                                    <p>${ review.user_nickname }</p>
                                </div>
                                <div class="rank">
                                    <c:forEach var="i" begin="1" end="5">
										<c:choose>
											<c:when test="${ review.rate >= i }">
												<i class="fa-solid fa-star fa-sm"></i>
											</c:when>
											<c:when test="${ review.rate == (i - 0.5) }">
												<i class="fa-solid fa-star-half-stroke fa-sm"></i>
											</c:when>
											<c:otherwise>
												<i class="fa-regular fa-star fa-sm"></i>
											</c:otherwise>
										</c:choose>
									</c:forEach>
                                </div>
                                <div class="review_text">
                                    ${ review.comment }
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- e : content_review -->

            <!-- s : latest_album -->
            <div id="latest_album">
                <div class="container">
                    <div class="latest_album_header">
                        <h2>최신 앨범</h2>
                    </div>
                    <div class="latest_album_body">
                        <!-- 데이터 베이스 연동 최신 기준 -->
                        <ul>
                        	<c:forEach var="album" items="${ latestAlbum }">
	                            <a href="<c:url value='/chart/review?id=${ album.id }' />">
	                                <li>
	                                    <div class="thum">
	                                        <img src="${ album.images[0].url }" alt="">
	                                    </div>
	                                    <div class="info">
	                                        <div class="title">${ album.name }</div>
	                                        <div class="artist">${ album.artists[0].name }</div>
	                                    </div>
	                                </li>
	                            </a>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- e : latest_album -->

            <!-- s : news -->
            <div id="news">
                	<div class="news_header">
                		<h2>최신 뉴스</h2>
                	</div>
                <div class="container">
                    <div class="news_main">
                        <a href='<c:url value="/news/view?num=${ news[0].num }" />'>
                            <div class="news_thum">
                                <img src="${pageContext.request.contextPath}/images/news/${ news[0].img_src }" alt="">
                            </div>
                            <div class="news_text">
                                    <div class="title">
                                        <h3>${ news[0].title }</h3>
                                    </div>
                                    <div class="date"><fmt:formatDate value="${ news[0].reg_date }" type="date" pattern="yyyy.MM.dd"/></div>
                            </div>
                        </a>   
                    </div>
                    <div class="news_sub">
                        <ul>
                        	<c:forEach var="newss" items="${ news }" begin="1" end="3">
	                            <a href="<c:url value="/news/view?num=${ newss.num }" />">
	                                <li>
	                                    <div class="news_sub_title">${ newss.title }</div>
	                                    <div class="news_sub_date"><fmt:formatDate value="${ newss.reg_date }" type="date" pattern="yyyy.MM.dd"/></div>
	                                </li>
	                            </a>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- e : news -->
        </div>
        <!-- e : main -->

        <!-- s : footer -->
        <%@ include file="footer.jsp" %>
        <!-- e : footer -->
    </div>
    <!-- e : wrap -->
    
</body>

</html>