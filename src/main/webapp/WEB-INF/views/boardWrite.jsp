<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Groovy | 글쓰기</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/boardWrite.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/x-icon">
    <script src="https://kit.fontawesome.com/8f808bece4.js" crossorigin="anonymous"></script>
</head>

<body>
    <!-- s : wrap -->
    <div id="wrap">
        <!-- s : header -->
        <%@ include file="header.jsp" %>
        <!-- e : header -->

        <!-- s : main -->
        <div id="main">
            <div id="write">
                <div class="container content_container">
                    <form id="write_box" action='<c:url value="write" />' method="post" enctype="multipart/form-data" onsubmit="return checkForm(this);">
                        <div class="write_header">
                            <h2>글쓰기</h2>
                        </div>
                        <div class="write_category">
                            <select name="category" id="category">
                                <option value="music" ${ board.category == 'music' ? 'selected' : '' }>음악</option>
                                <option value="review" ${ board.category == 'review' ? 'selected' : '' }>후기</option>
                            </select>
                        </div>
                        <div class="write_title">
                            <input type="text" name="title" id="title" placeholder="제목을 입력해주세요." value="${ not empty board.title ? board.title : '' }">
                        </div>
                        <div class="write_body">
                            <div class="write_tool">
                            	<%@ include file="file.jsp" %>
                            </div>
                            <div class="write_content">
                                <textarea name="content" id="content" placeholder="내용을 입력하세요.">${ not empty board.content ? board.content : '' }</textarea>
                            </div>
                        </div>
                        <div class="write_footer">
                        	<div>
                        		<button type="button" class="btn btn2" onclick="location.href = '<c:url value="list${ sc.queryString }" />'">목록</button>
                        	</div>
                        	<div>
	                            <button type="submit" class="btn btn1">등록</button>
                            </div>
                        </div>
                    </form>
                    <!-- s : aside -->
                    <%@ include file="aside.jsp" %>
                    <!-- e : aside -->
                </div>
            </div>
        </div>
        <!-- e : main -->

        <!-- s : footer -->
        <%@ include file="footer.jsp" %>
        <!-- e : footer -->
    </div>
    <!-- e : wrap -->
    
    <script>
    let msg = "${ msg }";
	if (msg == "write_error") alert("게시글 작성에 실패했습니다. 다시 작성해주세요.");
	
	function checkForm(frm) {
		if (frm.title.value.length == 0) {
			alert("제목을 입력해주세요.");
			return false;
		}
		if (frm.content.value.length == 0) {
			alert("내용을 입력해주세요.");
			return false;
		}
	}
    </script>
</body>
</html>