<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Groovy | ${ news.title }</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/boardView.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/x-icon">
<script src="https://kit.fontawesome.com/8f808bece4.js"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
</head>

<body>
	<!-- s : wrap -->
	<div id="wrap">
		<!-- s : header -->
        <%@ include file="header.jsp" %>
        <!-- e : header -->

		<!-- s : main -->
		<div id="main">
			<div id="view">
				<div class="container content_container">
					<form id="view_box" name="writeFrm">
						<div class="view_content">
							<input type="hidden" name="num" value="${ news.num }" />
							<div class="view_header">
								<div class="view_header_category">
									<span>뉴스</span>
								</div>
								<div class="view_header_title">
									<span>${ news.title }</span>
								</div>
								<div class="view_header_info">
									<span>${ news.writer }</span>
                                    <span><fmt:formatDate value="${ news.reg_date }" type="date" pattern="yyyy.MM.dd" /></span>
								</div>
							</div>
							<div class="view_body">
								<div class="view_body_content">
									<div class="mb-3">
								        <img src="${pageContext.request.contextPath}/images/news/${ news.img_src }" alt="뉴스 이미지">
								       	<div id="content" >${ news.content }</div>	
							        </div>
								</div>
							</div>
						</div>
						<div class="view_comment">
							<div class="view_comment_header">
								댓글 <span class="comment_cnt">999</span>
							</div>
							<div class="view_comment_content"></div>
							<div class="view_comment_write">
								<div class="comment_write_header">댓글작성</div>
								<div class="comment_write_body">
									<textarea name="comment" id="comment" placeholder="댓글을 남겨주세요"></textarea>
								</div>
								<div class="comment_write_footer">
									<button class="btn btn2" type="button" id="comment_send">등록</button>
								</div>
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
    let nno = ${ param.num }
    
    let mode = false;
    
    let showComments = function(num) {
    	console.log(num);
    	let comment = $('textarea[name=comment]').val("");
    	
    	$.ajax({
    		type: 'get',
    		url: '/groovy/newsComments?nno=' + nno,
    		success: function(result) {
    			$(".view_comment_content").html(toHtml(result));
    		},
			error: function() {
				alert('잠시 후 다시 시도해주세요.');
			}
    	});
    }
    
    $(document).ready(function(){
    	showComments(nno);
    	$('#comment_send').click(function() {
    		let comment = $('textarea[name=comment]').val();
    		if (comment.trim() == '') {
    			alert("댓글을 입력해주세요.");
    			return;
    		}
    		$.ajax({
    			type: 'post',
    			url: '/groovy/newsComments/?nno=' + nno,
    			headers: {
    				"content-type": "application/json"
    			},
    			data: JSON.stringify({
    				nno: nno,
    				comment: comment
    			}),
    			success: function(result) {
    				showComments(nno);
    			},
    			error: function() {
    				alert("잠시 후 다시 시도해주세요.");
    			}
    		});
    	});
    	
    	$(".view_comment_content").on("click", ".modBtn", function() {
    		let cno = $(this).parent().parent().parent().attr("data-cno");
    		let nno = $(this).parent().parent().parent().attr("data-nno");
    		
    		let comment = $(this).parent().parent().prev().text();
    		
    		$(this).parent().parent().prev().html('<textarea class="recomment" name="recomment">' + comment + '</textarea>');
    		$(this).parent().html('<a href="javascript:;" id="modBtnB">수정</a><a href="javascript:;" id="modBtnR">취소</a>');
    		
    		$("#modBtnB").attr('data-cno', cno);
    	});
    	$(".view_comment_content").on("click", "#modBtnR", function() {
    		let comment = $(this).parent().parent().prev().text();
    		
    		$(this).parent().parent().prev().html('<div class="comment_body">' + comment + '</div>');
    		$(this).parent().html('<a href="javascript:;" class="modBtn">수정</a><a href="javascript:;" class="delBtn">삭제</a>');
    	});
    	
    	$(".view_comment_content").on("click", "#modBtnB", function() {
    		let comment = $('textarea[name=recomment]').val();
    		
    		if (comment.trim() == '') {
    			alert("내용을 입력해주세요.");
    			return;
    		}
    		let cno = $("#modBtnB").attr("data-cno");
    		let del = $(".recomment").detach();
    		let btn = $("#modBtnB").detach();
    		
    		$.ajax({
    			type: "patch",
    			url: "/groovy/newsComments/" + cno,
    			headers: {
    				"content-type": "application/json"
    			},
    			data: JSON.stringify({
    				cno: cno,
    				comment: comment
    			}),
    			complete: function(result) {
    				showComments(nno);
    			},
    			error: function() {
    				alert("잠시 후 다시 시도해주세요.");
    			}
    		});
    		
    	});
    	$(".view_comment_content").on("click", ".delBtn", function() {
    		let cno = $(this).parent().parent().parent().attr("data-cno");
    			
    		$.ajax({
    			type: 'delete',
    			url: '/groovy/newsComments/' + cno + '?nno=' + nno,
    			success: function(result) {
    				showComments(nno);
    			},
    			error: function() {
    				alert("잠시 후 다시 시도해주세요.");
    			}
    		});
    	});
    	
    	$(".view_comment_content").on("click", ".modBtn", function() {
    		let cno = $(this).parent().parent().parent().attr("data-cno");
    		let nno = $(this).parent().parent().parent().attr("data-nno");
    		
    		let comment = $(this).parent().parent().prev().text();
    		
    		$(this).parent().parent().prev().html('<textarea class="recomment" name="recomment">' + comment + '</textarea>');
    		$(this).parent().html('<a href="javascript:;" class="modBtn">수정</a>');
    		
    		$("#modBtnB").attr('data-cno', cno);
    	});
    });
    
    let toHtml = function(comments) {
    	$('.comment_cnt').html(comments.commentCnt);
    	let tmp = "<ul class='comment_list'>";
    	
    	comments.list.forEach(function(comment) {
    		let ml = (comment.re_level * 2);
    		tmp += '<li data-cno=' + comment.cno;
    		tmp += ' data-nno=' + comment.nno + ' style="margin-left: ' + ml + 'em;">';
    		tmp += '<div class="comment_header">';
    		tmp += '<div class="comment_writer">' + comment.commenter_nickname + '</div>';
    		tmp += '<div class="comment_date">' + formatDate(comment.reg_date) + '</div>';
    		tmp += '</div>';
    		tmp += '<div class="comment_body">';
	    	tmp += comment.comment    	
    		tmp += '</div>';
    		tmp += '<div class="comment_footer">';
    		if (comment.commenter == '${ sessionScope.id }') {
	    		tmp += '<div class="comment_footer_mode">';
	    		tmp += '<a href="javascript:;" class="modBtn">수정</a>';
	    		tmp += '<a href="javascript:;" class="delBtn">삭제</a>';
	    		tmp += '</div>';
	    	}
    		// tmp += '<a href="javascript:;" class="replyBtn">답글</a>';
    		tmp += '</div>';
    		tmp += '</li>';
    	});
    	return tmp + "</ul>";
    }
    
    function deletePost() {
		var confirmed = confirm("정말로 삭제하시겠습니까?");
		if (confirmed) {
			var form = document.writeFrm;
			form.method = "post";
			form.action="<c:url value='/board/remove' />${ sc.queryString }";
			form.submit();
		}
	}
    
    function formatDate(date) {
        var d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

        if (month.length < 2) 
            month = '0' + month;
        if (day.length < 2) 
            day = '0' + day;

        return [year, month, day].join('.');
    }
    </script>
</body>
</html>