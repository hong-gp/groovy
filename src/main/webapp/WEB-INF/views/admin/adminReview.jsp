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
                                    <td>| 앨범</td>
                                    <td>| 작성자</td>
                                    <td>| 별명</td>
                                    <td>| 작성일</td>
                                    <td>| 좋아요 수</td>
                                    <td>| 평점</td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="review" items="${ list }">
                                	<tr>
	                                    <td>${ review.num }</td>
	                                    <td>${ review.album_id }</td>
	                                    <td>${ review.user_id }</td>
	                                    <td>${ review.user_nickname }</td>
	                                    <td>${ review.reg_date }</td>
	                                    <td>${ review.like_cnt }</td>
	                                    <td>${ review.rate }</td>
	                                    <td><a href='<c:url value="/admin/review/modify?num=${ review.num }" />'>수정</a></td>
	                                    <td><a href='javascript:;' data-num='${ review.num }' data-id='${ review.user_id }' onclick="deleteReview(this);">삭제</a></td>
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
    function deleteReview(review) {
    	let num = review.getAttribute('data-num');
    	let user_id = review.getAttribute('data-id');
    	if (confirm("정말로 삭제하시겠습니까?")) {
    		location.href = '<c:url value="/admin/review/delete?num=' + num + '&id=' + user_id + '" />';
    	} else {
    		return;
    	}
    }
    </script>
</body>
</html>