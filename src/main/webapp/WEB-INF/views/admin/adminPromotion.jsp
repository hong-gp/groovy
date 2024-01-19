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
                        	<a href='<c:url value="/admin/promotion/add" />'>홍보 추가하기</a>
                            <a href='<c:url value="/admin/logout" />'>로그아웃</a>
                        </div>
                        <table>
                            <thead>
                                <tr>
                                    <td>| 번호</td>
                                    <td>| 제목</td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="promotion" items="${ list }">
                                	<tr>
	                                    <td>${ promotion.num }</td>
	                                    <td>${ promotion.title }</td>
	                                    <td><a href='<c:url value="/admin/promotion/modify?num=${ promotion.num }" />'>수정</a></td>
	                                    <td><a href='javascript:;' onclick="deletePromotion(${ promotion.num })">삭제</a></td>
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
    function deletePromotion(num) {
    	if (confirm("정말로 삭제하시겠습니까?")) {
    		location.href = '<c:url value="/admin/promotion/delete?num=' + num +'" />';
    	} else {
    		return;
    	}
    }
    </script>
</body>
</html>