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
                    <form class="user_content" action='<c:url value="/admin/user/modify" />' method="post">
                        <table>
                            <thead>
                                <tr>
                                    <td>| 구분</td>
                                    <td>| 입력</td>
                                </tr>
                            </thead>
                            <tbody>
                            	<tr>
                            		<td>아이디</td>
                            		<td><input type="text" name="id" value="${ user.id }" readonly="readonly" /></td>
                            	</tr>
                            	<tr>
                            		<td>비밀번호</td>
                            		<td><input type="text" name="password" value="${ user.password }" /></td>
                            	</tr>
                            	<tr>
                            		<td>이름</td>
                            		<td><input type="text" name="name" value="${ user.name }" /></td>
                            	</tr>
                            	<tr>
                            		<td>별명</td>
                            		<td><input type="text" name="nickname" value="${ user.nickname }" /></td>
                            	</tr>
                            	<tr>
                            		<td>휴대폰</td>
                            		<td><input type="text" name="tel" value="${ user.tel }" /></td>
                            	</tr>
                            	<tr>
                            		<td>이메일</td>
                            		<td><input type="text" name="email" value="${ user.email }" /></td>
                            	</tr>
                            	<tr>
                            		<td>생년월일</td>
                            		<td><input type="text" name="birth" value="${ user.birth }" /></td>
                            	</tr>
                            </tbody>
                        </table>
                        <div class="write_footer">
	                        <div>
	                            <button type="submit" class="btn btn1">수정</button>
	                        </div>
	                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>