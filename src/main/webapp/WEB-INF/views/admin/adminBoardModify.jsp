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
                    <form class="user_content" action='<c:url value="/admin/board/modify" />' method="post" enctype="multipart/form-data">
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
                                	<td><input type="text" value="${ board.num }" name="num" readonly="readonly" /></td>
                                </tr>
                                <tr>
                                	<td>카테고리</td>
                                	<td>
                                		<select name="category" id="category">
			                                <option value="music" ${ board.category == 'music' ? 'selected' : '' }>음악</option>
			                                <option value="review" ${ board.category == 'review' ? 'selected' : '' }>후기</option>
			                            </select>
                                	</td>
                                </tr>
                                <tr>
                                	<td>제목</td>
                                	<td><input type="text" value="${ board.title }" name="title" /></td>
                                </tr>
                                <tr>
                                	<td>내용</td>
                                	<td>
                                		<textarea name="content">${ board.content }</textarea>
                                	</td>
                                </tr>
                                <tr>
                                	<td>작성자</td>
                                	<td><input type="text" value="${ board.writer }" name="writer" readonly="readonly" /></td>
                                </tr>
                                <tr>
                                	<td>별명</td>
                                	<td><input type="text" value="${ board.writer_nickname }" readonly="readonly" name="writer_nickname" /></td>
                                </tr>
                                <tr>
                                	<td>이미지</td>
                                	<td><%@ include file="../file.jsp" %></td>
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