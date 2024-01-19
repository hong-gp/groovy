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
    <link rel="stylesheet" href="css/findUser.css">
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
            <div id="find_user">
                <div class="container content_container">
                    <div class="find_box">
                        <div class="find_content">
                            <div class="find_header">
                                <h2>아이디 찾기</h2>
                            </div>
                            <form class="find_body" method="post" action="<c:url value='/findUserId' />">
                            	<div class="msg">${ id_msg }</div>
                                <input type="text" name="name" id="id_name" placeholder="이름" value="${ findid.name }">
                                <input type="text" name="tel" id="id_tel" placeholder="전화번호" value="${ findid.tel }">
                                <input type="text" name="email" id="id_email" placeholder="이메일" value="${ findid.email }">
                                <button type="submit">아이디 찾기</button>
                            </form>
                        </div>
                    </div>
                    <div class="find_box">
                        <div class="find_content">
                            <div class="find_header">
                                <h2>비밀번호 찾기</h2>
                            </div>
                            <form class="find_body" method="post" action="<c:url value='/findUserPw' />">
                            	<div class="msg">${ pw_msg }</div>
                                <input type="text" name="id" id="pw_id" placeholder="아이디" value="${ findpw.id }">
                                <input type="text" name="name" id="pw_name" placeholder="이름" value="${ findpw.name }">
                                <input type="text" name="tel" id="pw_tel" placeholder="전화번호" value="${ findpw.tel }">
                                <input type="text" name="email" id="pw_email" placeholder="이메일" value="${ findpw.email }">
                                <button type="submit">비밀번호 찾기</button>
                            </form>
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