<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Groovy | 로그인</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
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
            <div id="login">
                <div class="container content_container">
                    <div class="login_box">
                        <div class="login_content">
                            <div class="login_header">
                                <h2>로그인</h2>
                            </div>
                            <form class="login_body" action='<c:url value="/login" />' method="post">
                            	<input type="hidden" name="toUrl" value="${ param.toUrl }" />
                            	<div id="msg">${ URLDecoder.decode(param.msg, "utf-8") }</div>
                                <input type="text" name="id" id="id" placeholder="아이디" autofocus value="${ cookie.id.value }">
                                <input type="password" name="password" id="password" placeholder="비밀번호">
                                <label>
                                    <input type="checkbox" name="rememberId" id="rememberId" ${ empty cookie.id.value ? "" : "checked" }> 
                                    <span>아이디 저장</span>
                                </label>
                                <a href="<c:url value='/findUser' />">ID/PW 찾기</a>
                                <button type="submit">로그인</button>
                            </form>
                            <div class="login_footer">
                                <div class="register_box">
                                    <span>아직 회원이 아니신가요?</span>
                                    <span><a href="<c:url value='/register' />">회원가입</a></span>
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