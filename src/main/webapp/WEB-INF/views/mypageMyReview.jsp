<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Groovy | 마이페이지</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/x-icon">
    <script src="https://kit.fontawesome.com/8f808bece4.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.js"
        integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
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
                    <div class="mypage">
                        <div class="mypage_header">
                            <div class="mypage_header_title">
                                <span>${ user.nickname }님의 마이페이지</span>
                            </div>
                            <div class="mypage_header_nav">
                                <ul>
                                    <li><a href='<c:url value="/mypage/userInfo" />'>회원정보</a></li>
                                    <li><a href='<c:url value="/mypage/myReview" />' class="selected">내가 작성한 리뷰</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="mypage_body">
                            <div class="mypage_body_title">
                                <h2>내가 작성한 리뷰</h2>
                            </div>
                            <div class="mypage_body_content">
                                <div class="review_lists">
                                	<c:choose>
                                		<c:when test="${ empty reviews }">
                                			<div class="review_list">
                                				<div class="empty_list">작성한 리뷰가 존재하지 않습니다.</div>
                                			</div>
                                		</c:when>
                                		<c:otherwise>
                                			<c:forEach var="review" items="${ reviews }" varStatus="status">
			                                    <div class="review_list">
			                                        <div class="review_album">
			                                            <div class="thum">
			                                                <img src="${ albums[status.index].images[0].url }" alt="">
			                                            </div>
			                                        </div>
			                                        <div class="review_review">
			                                            <div class="review_review_header">
			                                                <div class="title">
			                                                    <h2>${ albums[status.index].name }</h2>
			                                                </div>
			                                                <div class="artist">
			                                                    <h3>${ albums[status.index].artists[0].name }</h3>
			                                                </div>
			                                            </div>
			                                            <div class="review_review_body">
			                                                <div class="profile">
			                                                    <p>Review by ${ review.user_nickname }</p>
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
											</c:forEach>
                                		</c:otherwise>
                                	</c:choose>
                                	
                                </div>
                            </div>
                        </div>
                    </div>
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