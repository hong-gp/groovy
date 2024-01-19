<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Groovy | 뉴스</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/news.css">
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
            <!-- s : news -->
            <div id="news">
                <!-- s : container -->
                <div class="container content_container">
                    <div class="news_contents">
                    	<c:forEach var="news" items="${ list }">
	                        <div class="news_content">
	                            <div class="news_thum">
	                                <a href='<c:url value="/news/view?num=${ news.num }" />'>
	                                    <img src="${pageContext.request.contextPath}/images/news/${ news.img_src }" alt="">
	                                </a>
	                            </div>
	                            <div class="news_body">
	                                <div class="news_title">
	                                    <a href='<c:url value="/news/view?num=${ news.num }" />'>
	                                        <h2>${ news.title }</h2>
	                                    </a>
	                                </div>
	                                <div class="news_date">
	                                    <fmt:formatDate value="${ news.reg_date }" type="date" pattern="yyyy.MM.dd" />
	                                </div>
	                            </div>
	                        </div>
                        </c:forEach>
                    </div>
                    <%@ include file="aside.jsp" %>
                </div>
                <!-- e : container -->
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