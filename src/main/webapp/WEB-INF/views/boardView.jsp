<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Groovy | ${ board.title }</title>
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
		<%@ include file="header.jsp"%>
		<!-- e : header -->

		<!-- s : main -->
		<div id="main">
			<div id="view">
				<div class="container content_container">
					<form id="view_box" name="writeFrm">
						<div class="view_content">
							<input type="hidden" name="num" value="${ board.num }" />
							<div class="view_header">
								<div class="view_header_category">
									<c:choose>
										<c:when test="${ board.category == 'review' }">
											<span>후기</span>
										</c:when>
										<c:otherwise>
											<span>음악</span>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="view_header_title">
									<span>${ board.title }</span>
								</div>
								<div class="view_header_info">
									<span>${ board.writer_nickname }</span> <span><fmt:formatDate
											value="${ board.postdate }" type="date" pattern="yyyy.MM.dd" /></span>
									<span>조회수 <span>${ board.view_cnt }</span></span>
								</div>
							</div>
							<div class="view_body">
								<div class="view_body_content">
									<div class="mb-3">
								        <c:if test='${ not empty board.img_src }'>
								         	<img class="img_show" src="${pageContext.request.contextPath}/images/board/${board.img_src }">
								        </c:if>
								       	<div id="content" >${board.content } </div>	
							        </div>
								</div>
								<div class="view_body_likey">
									<button class="likey_button" type="button">
										<div class="like_cnt">
											<i class="fa-regular fa-thumbs-up fa-lg"></i> 
											<span class="like_cnt_text">${ board.like_cnt }</span>
										</div>
										<div class="like_text">좋아요</div>
									</button>
								</div>
							</div>
							<div class="view_footer">
								<c:if test="${ sessionScope.id == board.writer }">
									<button class="btn btn2" type="button" onclick="location.href = '<c:url value="/board/modify?num=${ board.num }" />';">수정</button>
									<button class="btn btn2" type="button" onclick="deletePost()">삭제</button>
								</c:if>
								<button class="btn btn2" type="button"
									onclick="location.href = '<c:url value="list/${ sc.queryString }" />'">목록</button>
							</div>
						</div>
						<div class="view_comment">
							<div class="view_comment_header">
								댓글 <span class="comment_cnt">${ board.comment_cnt }</span>
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
					<%@ include file="aside.jsp"%>
					<!-- e : aside -->
				</div>
			</div>
		</div>
		<!-- e : main -->

		<!-- s : footer -->
		<%@ include file="footer.jsp"%>
		<!-- e : footer -->
	</div>
	<!-- e : wrap -->

	<script>
    let bno = ${ param.num }
    
    let mode = false;
    
    let showComments = function(num) {
    	console.log(num);
    	let comment = $('textarea[name=comment]').val("");
    	
    	$.ajax({
    		type: 'get',
    		url: '/groovy/comments?bno=' + bno,
    		success: function(result) {
    			$(".view_comment_content").html(toHtml(result));
    		},
			error: function() {
				alert('잠시 후 다시 시도해주세요.');
			}
    	});
    }
    
    let checkLike = function(num) {
    	$.ajax({
    		type: 'post',
    		url: '/groovy/check',
    		data: {
    			bno: bno
    		},
    		success: function(result) {
    			if (result != 0) {
    				$('.likey_button').css('color', 'red');
    				$('.likey_button').css('border-color', 'red');
    			} else {
    				$('.likey_button').css('color', '#666');
    				$('.likey_button').css('border-color', '#666');
    			}
    		}
    	});
    };
    
    $(document).ready(function(){
    	showComments(bno);
    	checkLike(bno);
    	$('#comment_send').click(function() {
    		let comment = $('textarea[name=comment]').val();
    		if (comment.trim() == '') {
    			alert("댓글을 입력해주세요.");
    			return;
    		}
    		$.ajax({
    			type: 'post',
    			url: '/groovy/comments/?bno=' + bno,
    			headers: {
    				"content-type": "application/json"
    			},
    			data: JSON.stringify({
    				bno: bno,
    				comment: comment
    			}),
    			success: function(result) {
    				showComments(bno);
    			},
    			error: function() {
    				alert("잠시 후 다시 시도해주세요.");
    			}
    		});
    		
    		$.ajax({
    			type: 'get',
    			url: '/groovy/like',
    			data: {
    				bno: bno
    			},
    			success: function(result) {
    				$(".like_cnt_text").html(result);
    			},
    			error: function() {
    				alert("잠시 후 다시 시도해주세요.");
    			}
    		});
    	});
    	
    	$(".view_comment_content").on("click", ".modBtn", function() {
    		let cno = $(this).parent().parent().parent().attr("data-cno");
    		let bno = $(this).parent().parent().parent().attr("data-bno");
    		
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
    			url: "/groovy/comments/" + cno,
    			headers: {
    				"content-type": "application/json"
    			},
    			data: JSON.stringify({
    				cno: cno,
    				comment: comment
    			}),
    			complete: function(result) {
    				showComments(bno);
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
    			url: '/groovy/comments/' + cno + '?bno=' + bno,
    			success: function(result) {
    				showComments(bno);
    			},
    			error: function() {
    				alert("잠시 후 다시 시도해주세요.");
    			}
    		});
    	});
    	
    	$(".view_comment_content").on("click", ".modBtn", function() {
    		let cno = $(this).parent().parent().parent().attr("data-cno");
    		let bno = $(this).parent().parent().parent().attr("data-bno");
    		
    		let comment = $(this).parent().parent().prev().text();
    		
    		$(this).parent().parent().prev().html('<textarea class="recomment" name="recomment">' + comment + '</textarea>');
    		$(this).parent().html('<a href="javascript:;" class="modBtn">수정</a>');
    		
    		$("#modBtnB").attr('data-cno', cno);
    	});
    	
    	// 답글작성
    	/* $(".view_comment_content").on("click", ".replyBtn", function() {
    		$(".reply_write").detach();
    		let tmp = '<div class="view_comment_write reply_write" style="margin-top: 20px;">';
    		tmp += '<div class="comment_write_header">답글작성</div>';
    		tmp += '<div class="comment_write_body">';
    		tmp += '<textarea name="reply_comment" id="reply_comment"></textarea>';
    		tmp += '</div>';
    		tmp += '<div class="comment_write_footer">';
    		tmp += '<button class="btn btn2" type="button" id="reply_send" style="margin-right: 5px;">등록</button>';
    		tmp += '<button class="btn btn2" type="button" id="reply_reset">취소</button>';
    		tmp += '</div>';
    		tmp += '</div>';
        	$(this).parent().parent().append(tmp);
        }); */
    	
    	
    	$(".view_comment_content").on("click", "#reply_reset", function() {
    		$(".reply_write").detach();
    	});
    	
    	$(".view_comment_content").on("click", "#reply_send", function() {
    		let comment = $("textarea[name=reply_comment]").val();
    		if (comment.trim() == '') {
    			alert("댓글을 입력해주세요.");
    			return;
    		}
    		let pcno = $(this).parent().parent().parent().attr("data-cno");
    		
    		$.ajax({
    			type: 'post',
    			url: '/groovy/reply/?bno=' + bno,
    			headers: {
    				"content-type": "application/json"
    			},
    			data: JSON.stringify({
    				bno: bno,
    				comment: comment,
    				pcno: pcno
    			}),
    			success: function(result) {
    				showComments(bno);
    			},
    			error: function() {
    				alert("잠시 후 다시 시도해주세요.");
    			}
    		});
    	});
    	
    	$(".view_body_likey").on("click", ".likey_button", function() {
    		$.ajax({
    			type: 'post',
    			url: '/groovy/like',
    			data: {
    				bno: bno
    			},
    			success: function(result) {
    				$(".like_cnt_text").html(result);
    				checkLike(bno);
    			},
    			error: function() {
    				alert("잠시 후 다시 시도해주세요.");
    			}
    		});
    	});
    });
    
    let toHtml = function(comments) {
    	let tmp = "<ul class='comment_list'>";
    	
    	comments.forEach(function(comment) {
    		let ml = (comment.re_level * 2);
    		tmp += '<li data-cno=' + comment.cno;
    		/* tmp += ' data-level=' + comment.re_level;
    		tmp += ' data-ref=' + comment.ref;
    		tmp += ' data-step=' + comment.re_step; */
    		tmp += ' data-bno=' + comment.bno + ' style="margin-left: ' + ml + 'em;">';
    		tmp += '<div class="comment_header">';
    		tmp += '<div class="comment_writer">' + comment.commenter_nickname + '</div>';
    		tmp += '<div class="comment_date">' + formatDate(comment.reg_date) + '</div>';
    		tmp += '</div>';
    		tmp += '<div class="comment_body">';
    		if (comment.isdel == 0) {
	    		tmp += comment.comment    			
    		} else {
    			tmp += "수정된 댓글입니다.";
    			console.log(comment.isdel);
    		}
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