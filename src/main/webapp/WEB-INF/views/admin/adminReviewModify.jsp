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
                    <form class="user_content" action='<c:url value="/admin/review/modify" />' method="post">
                        <div class="user_write">
                            <a href='<c:url value="/admin/logout" />'>로그아웃</a>
                        </div>
                        <table>
                            <thead>
                                <tr>
                                    <td>| 구분</td>
                                    <td>| 입력</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                	<td>번호</td>
                                	<td><input type="text" value="${ review.num }" name="num" readonly="readonly" /></td>
                                </tr>
                                <tr>
                                	<td>앨범아이디</td>
                                	<td>
                                		<input type="text" value="${ review.album_id }" name="album_id" readonly="readonly" />
                                	</td>
                                </tr>
                                <tr>
                                	<td>내용</td>
                                	<td>
                                		<textarea name="comment" placeholder="내용을 입력해주세요">${ review.comment }</textarea>
                                	</td>
                                </tr>
                                <tr>
                                	<td>작성자</td>
                                	<td><input type="text" value="${ review.user_id }" name="user_id" readonly="readonly" /></td>
                                </tr>
                                <tr>
                                	<td>별명</td>
                                	<td>
                                		<input type="text" name="user_nickname" value="${ review.user_nickname }" readonly="readonly" />
                                	</td>
                                </tr>
                                <tr>
                                	<td>추천수</td>
                                	<td><input type="text" value="${ review.like_cnt }" readonly="readonly" name="like_cnt" /></td>
                                </tr>
                                <tr>
                                	<td>별점</td>
                                	<td><input type="text" value="${ review.rate }" readonly="readonly" name="rate" /></td>
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