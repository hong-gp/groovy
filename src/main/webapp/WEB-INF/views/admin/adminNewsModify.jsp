<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Groovy | 뉴스 작성</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/boardWrite.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <script src="https://kit.fontawesome.com/8f808bece4.js" crossorigin="anonymous"></script>
</head>

<body>
    <!-- s : wrap -->
    <div id="wrap">
        <div class="container">
            <div class="section">
                <%@ include file="adminNav.jsp" %>
                <form id="write_box" action='<c:url value="/admin/news/modify" />' method="post" enctype="multipart/form-data">
                	<input type="hidden" name="num" value="${ news.num }" />
                    <div class="write_header">
                        <h2>뉴스 수정</h2>
                    </div>
                    <div class="write_title">
                        <input type="text" name="title" id="title" placeholder="제목을 입력해주세요." value="${ news.title }">
                    </div>
                    <div class="write_body">
                        <div class="write_tool">
							<%@ include file="../file.jsp" %>
                        </div>
                        <div class="write_content">
                            <textarea name="content" id="content"
                                placeholder="내용을 입력하세요.">${ news.content }</textarea>
                        </div>
                    </div>
                    <div class="write_footer">
                        <div>
                            <button type="submit" id="modifyBtn" class="btn btn1">수정</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- e : main -->
    </div>
    <!-- e : wrap -->

    <script>
        let msg = "${ msg }";
        if (msg == "write_error") alert("게시글 작성에 실패했습니다. 다시 작성해주세요.");
    </script>
</body>

</html>