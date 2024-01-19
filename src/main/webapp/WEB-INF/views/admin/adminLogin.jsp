<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Groovy | 로그인</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="https://kit.fontawesome.com/8f808bece4.js" crossorigin="anonymous"></script>
</head>

<body>
    <!-- s : wrap -->
    <div id="wrap">

        <!-- s : main -->
        <div id="main">
            <div id="login">
                <div class="container content_container">
                    <div class="login_box">
                        <div class="login_content">
                            <div class="login_header">
                                <h2>로그인</h2>
                            </div>
                            <form class="login_body" action='<c:url value="/admin/login" />' method="post">
                            	<div id="msg">${ URLDecoder.decode(param.msg, "utf-8") }</div>
                                <input type="text" name="id" id="id" placeholder="아이디">
                                <input type="password" name="password" id="password" placeholder="비밀번호">
                                <button>로그인</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- e : main -->
        
    </div>
    <!-- e : wrap -->
</body>
</html>