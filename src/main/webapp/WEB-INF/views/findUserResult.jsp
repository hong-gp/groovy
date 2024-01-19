<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Groovy | ID/PW 찾기</title>
    <link rel="stylesheet" href="css/findUserResult.css">
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
            <div id="find_result">
                <div class="container content_container">
                    <div class="find_result_box find_id_box">
                    	<c:if test="${ find == 'id' }">
	                        <div class="find_result_header">
	                            <h2>아이디 찾기</h2>
	                            <h4>입력하신 정보로 조회된 아이디입니다.</h4>
	                        </div>
	                        <div class="find_result_body">
	                            <div class="find_result_body_title">
	                                <p>${ user.id }</p>
	                            </div>
	                        </div>
	                        <div class="find_result_footer">
	                            <div class="button1">
	                                <button onclick="location.href='<c:url value="/login" />'">로그인</button>
	                            </div>
	                            <div class="button2">
	                                <button onclick="location.href='<c:url value="/findUser" />'">다시 찾기</button>
	                            </div>
	                        </div>
                        </c:if>
                        <c:if test="${ find == 'pw' }">
	                        <div class="find_result_header">
	                            <h2>비밀번호 찾기</h2>
	                            <h4>입력하신 정보로 조회된 비밀번호입니다.</h4>
	                        </div>
	                        <div class="find_result_body">
	                            <div class="find_result_body_title">
	                                <p>${ user.password }</p>
	                            </div>
	                        </div>
	                        <div class="find_result_footer">
	                            <div class="button1">
	                                <button onclick="location.href='<c:url value="/login" />'">로그인</button>
	                            </div>
	                            <div class="button2">
	                                <button onclick="location.href='<c:url value="/findUser" />'">다시 찾기</button>
	                            </div>
	                        </div>
                        </c:if>
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