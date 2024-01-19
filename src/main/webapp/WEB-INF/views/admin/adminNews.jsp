<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
                <div class="news_management">
                    <div class="news_content">
                        <div class="news_write">
                            <a href='<c:url value="/admin/news/write" />'>뉴스 작성하기</a>
                            <a href='<c:url value="/admin/logout" />'>로그아웃</a>
                        </div>
                        <table>
                            <thead>
                                <tr>
                                    <td width="10%">| 번호</td>
                                    <td width="60%">| 제목</td>
                                    <td>| 작성일</td>
                                    <td>| 작성자</td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="news" items="${ list }">
                                	<tr>
	                                    <td>${ news.num }</td>
	                                    <td>${ news.title }</td>
	                                    <td><fmt:formatDate value="${ news.reg_date }" type="date" pattern="yyyy-MM-dd" /></td>
	                                    <td>${ news.writer }</td>
	                                    <td><a href='<c:url value="/admin/news/modify?num=${ news.num }" />'>수정</a></td>
	                                    <td><a href='javascript:;' onclick="deleteNews(${ news.num })">삭제</a></td>
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
    function deleteNews(num) {
    	if (confirm("뉴스를 삭제하시겠습니까?")) {
    		location.href = '<c:url value="/admin/news/delete?num=' + num + '" />';
    	} else {
    		return;
    	}
    }
    </script>
</body>
</html>