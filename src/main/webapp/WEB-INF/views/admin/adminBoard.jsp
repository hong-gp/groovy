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
                                    <td>| 번호</td>
                                    <td>| 카테고리</td>
                                    <td>| 제목</td>
                                    <td>| 작성자</td>
                                    <td>| 별명</td>
                                    <td>| 작성일</td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="board" items="${ list }">
                                	<tr>
	                                    <td>${ board.num }</td>
	                                    <td>${ board.category }</td>
	                                    <td>${ board.title }</td>
	                                    <td>${ board.writer }</td>
	                                    <td>${ board.writer_nickname }</td>
	                                    <td>${ board.postdate }</td>
	                                    <td><a href='<c:url value="/admin/board/modify?num=${ board.num }" />'>수정</a></td>
	                                    <td><a href='javascript:;' data-num='${ board.num }' data-writer='${ board.writer }' onclick="deleteBoard(this)">삭제</a></td>
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
    function deleteBoard(board) {
    	let num = board.getAttribute('data-num');
    	let id = board.getAttribute('data-writer');
    	if (confirm("정말로 삭제하시겠습니까?")) {
    		location.href = '<c:url value="/admin/board/delete?num=' + num + '&id=' + id + '" />';
    	} else {
    		return;
    	}
    }
    </script>
</body>
</html>