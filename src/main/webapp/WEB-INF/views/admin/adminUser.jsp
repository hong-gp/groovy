<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Groovy | 관리자</title>
    <link href="${pageContext.request.contextPath}/css/admin.css" rel="stylesheet">
</head>
<body>
    <div id="wrap">
        <div class="container">
            <div class="section">
                <%@ include file="adminNav.jsp" %>
                <div class="user_management">
                    <div class="user_content">
                        <div class="user_write">
                            <a href='<c:url value="/admin/logout" />'>로그아웃</a>
                        </div>
                        <table>
                            <thead>
                                <tr>
                                    <td>| 아이디</td>
                                    <td>| 비밀번호</td>
                                    <td>| 이름</td>
                                    <td>| 별명</td>
                                    <td>| 휴대폰</td>
                                    <td>| 이메일</td>
                                    <td>| 생년월일</td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="user" items="${ list }">
                                	<tr>
	                                    <td>${ user.id }</td>
	                                    <td>${ user.password }</td>
	                                    <td>${ user.name }</td>
	                                    <td>${ user.nickname }</td>
	                                    <td>${ user.tel }</td>
	                                    <td>${ user.email }</td>
	                                    <td>${ user.birth }</td>
	                                    <td><a href='<c:url value="/admin/user/modify?id=${ user.id }" />'>수정</a></td>
	                                    <td><a href='javascript:;' onclick="deleteUser('${ user.id }')">삭제</a></td>
	                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script>
    function deleteUser(id) {
    	if (confirm("정말로 삭제하시겠습니까?")) {
    		location.href = '<c:url value="/admin/user/delete?id=' + id + '" />';
    	} else {
    		return;
    	}
    }
    </script>
</body>
</html>