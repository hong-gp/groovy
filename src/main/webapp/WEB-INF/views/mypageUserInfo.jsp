<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                    <div class="mypage">
                        <div class="mypage_header">
                            <div class="mypage_header_title">
                                <span>${ user.nickname }님의 마이페이지</span>
                            </div>
                            <div class="mypage_header_nav">
                                <ul>
                                    <li><a href='<c:url value="/mypage/userInfo" />' class="selected">회원정보</a></li>
                                    <li><a href='<c:url value="/mypage/myReview" />'>내가 작성한 리뷰</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="mypage_body">
                            <div class="mypage_body_title">
                                <h2>회원정보</h2>
                            </div>
                            <div class="mypage_body_content">
                                <table>
                                    <tr>
                                        <td>아이디</td>
                                        <td>${ user.id }</td>
                                    </tr>
                                    <tr>
                                        <td>이름</td>
                                        <td>${ user.name }</td>
                                    </tr>
                                    <tr>
                                        <td>별명</td>
                                        <td>${ user.nickname }</td>
                                    </tr>
                                    <tr>
                                        <td>휴대폰</td>
                                        <td>${ user.tel }</td>
                                    </tr>
                                    <tr>
                                        <td>이메일</td>
                                        <td>${ user.email }</td>
                                    </tr>
                                    <tr>
                                        <td>생일</td>
                                        <td>${ user.birth }</td>
                                    </tr>
                                </table>
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