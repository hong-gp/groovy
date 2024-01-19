<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Groovy | ${ album.name }</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/reviewDetail.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/x-icon">
<script src="https://kit.fontawesome.com/8f808bece4.js"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>

<body>
	<!-- s : wrap -->
	<div id="wrap">
		<!-- s : header -->
		<%@ include file="header.jsp"%>
		<!-- e : header -->

		<!-- s : main -->
		<div id="main">
			<div id="review">
				<!-- s : container -->
				<div class="container content_container">
					<div class="review_container">
						<div class="review_header">
							<div class="album_thum">
								<img src="${ album.images[0].url }" alt="">
							</div>
							<div class="album_track_list">
								<div class="trck_list_title">수록곡</div>
								<div class="track_list">
									<ul>
										<c:forEach var="track" items="${ album.tracks.items }">
											<li>
												<div class="num">${ track.trackNumber }</div>
												<div class="title">${ track.name }</div>
											</li>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
						<div class="review_body">
							<div class="album_title">
								<h2>${ album.name }</h2>
							</div>
							<div class="album_info">
								<table>
									<tr>
										<td>Artist</td>
										<td>${ album.artists[0].name }</td>
									</tr>
									<tr>
										<td>Type</td>
										<td>${ album.albumType }</td>
									</tr>
									<tr>
										<td>Release date</td>
										<td>${ album.releaseDate }</td>
									</tr>
									<tr>
										<td>Genres</td>
										<td><c:if test="${ empty album.genres[0] }">
                                        		분류중
                                        	</c:if> <c:forEach var="genre"
												items="${ album.genres }">
												${ genre },
											</c:forEach></td>
									</tr>
									<tr>
										<td>Rating</td>
										<td>
											<c:choose>
												<c:when test="${ empty map.avg }">0</c:when>
												<c:otherwise>
													<fmt:formatNumber value="${ map.avg }" pattern=".00"></fmt:formatNumber>
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</table>
							</div>
							<form class="review_write"
								action='<c:url value="/chart/write" />' method="post" onsubmit="return reviewWriteCheck(this)">
								<div class="review_write_title">리뷰 작성</div>
								<div class="review_write_rating">
									<span class="star"> ★★★★★ <span>★★★★★</span>
									<input type="range" name="rate" oninput="drawStar(this)" value="0"
										step="1" min="0" max="10" id="rate">
									</span>
								</div>
								<div class="review_write_write">
									<textarea name="comment" id="review_textarea" placeholder="리뷰를 작성해주세요"></textarea>
								</div>
								<div class="review_write_footer">
									<button class="btn btn2" type="submit">등록</button>
								</div>
								<input type="hidden" name="album_id" value="${ album.id }" />
							</form>
							<div class="review_list">
								<div class="review_list_title">리뷰 ${ cnt }</div>
								<div class="review_list_body">
									<ul>
										<c:choose>
											<c:when test="${ empty reviews }">
												<div class="list_empty">등록된 리뷰가 없습니다.</div>
											</c:when>
											<c:otherwise>
												<c:forEach var="review" items="${ reviews }">
													<li class="list_list" data-num='${ review.num }'>
														<form id="frm" method="post" action='<c:url value="/chart/modify" />'>
															<input type="hidden" value="${ review.num }" name="num" />
															<input type="hidden" value="${ review.album_id }" name="album_id" />
															<div class="list_title">
																<div>
																	<span>${ review.user_nickname }</span>
																	<span style="font-size: 14px;">
																		<fmt:formatDate value="${ review.reg_date }" type="date" pattern="yyyy.MM.dd" />
																	</span>
																</div>
																<div>
																	<c:forEach var="i" begin="1" end="5">
																		<c:choose>
																			<c:when test="${ review.rate >= i }">
																				<i class="fa-solid fa-star fa-sm"></i>
																			</c:when>
																			<c:when test="${ review.rate == (i - 0.5) }">
																				<i class="fa-solid fa-star-half-stroke fa-sm"></i>
																			</c:when>
																			<c:otherwise>
																				<i class="fa-regular fa-star fa-sm"></i>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>
																</div>
															</div>
															<div class="list_content">${ review.comment }</div>
															<div class="list_footer">
																<div>
																	<c:if test="${ sessionScope.id == review.user_id }">
																		<a href="javascript:;" class="modBtn">수정</a>
																		<a href="javascript:;" type="button" class="delBtn">삭제</a>
																	</c:if>
																</div>
																<div>
																	<a href="javascript:;" class="likeBtn"> 추천 
																		<i class="fa-regular fa-thumbs-up"></i>
																		<span class="likeCnt">${ review.like_cnt }</span>
																	</a>
																</div>
															</div>
														</form>
													</li>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</ul>
								</div>
							</div>
							<div id="page">
								<c:if test="${ ph.showPrev }">
									<a href="<c:url value='/chart/review${ ph.sc.getQueryString(1) }&id=${ album.id }' />">&laquo;</a>
									<a
										href="<c:url value='/chart/review${ ph.sc.getQueryString(ph.beginPage - 1) }&id=${ album.id }' />">&lt;</a>
								</c:if>
								<c:forEach var="i" begin="${ph.beginPage }" end="${ph.endPage }">
									<a
										href='<c:url value="/chart/review${ ph.sc.getQueryString(i) }&id=${ album.id }"/>'
										class="${ ph.sc.page == i ? 'active' : '' }">${ i }</a>
								</c:forEach>
								<c:if test="${ ph.showNext }">
									<a
										href="<c:url value='/chart/review${ ph.sc.getQueryString(ph.endPage + 1) }&id=${ album.id }' />">&gt;</a>
									<a href="<c:url value='/chart/review${ ph.sc.getQueryString(ph.totalPage) }&id=${ album.id }' />">&raquo;</a>
								</c:if>
							</div>
						</div>
					</div>
				</div>
				<!-- e : container -->
			</div>
		</div>
		<!-- e : main -->

		<!-- s : footer -->
		<%@ include file="footer.jsp"%>
		<!-- e : footer -->
	</div>
	<!-- e : wrap -->

	<script>
    const drawStar = (target) => {
        document.querySelector('.star span').style.width = (target.value * 10) + `%`;
    }
    let msg = "${ msg }";
    if (msg == "write_ok") alert("성공적으로 등록되었습니다.");
    if (msg == "write_error") alert("잠시 후 다시 시도해주세요.");
    
    function resetModComment(event) {
    	console.log(event.val());
    	let comment = event.text();
    	comment = comment.replaceAll('\n', '<br>');
    	let btn = "<a href='javascript:;' class='modBtn'>수정</a>";
		btn += "<a href='javascript:;' class='delBtn'>삭제</a>";
		
		event.parent().next().children("div:eq(0)").html(btn);
    	event.parent().html(comment);
    	
    }
    
    $(document).ready(function() {
    	$('.list_list').on('click', '.modBtn', function() {
    		resetModComment($('.modComment'));
    		let comment = $(this).parent().parent().prev().html();
    		comment = comment.replaceAll("<br>", "\n");
    		let tmp = "<textarea name='comment' class='modComment'>";
    		tmp += comment;
    		tmp += "</textarea>";
    		$(this).parent().parent().prev().html(tmp);
    		
    		let btn = "<a href='javascript:;' id='modCompBtn'>수정</a>";
    		btn += "<a href='javascript:;' id='delCompBtn'>취소</a>";
    		$(this).parent().html(btn);
    	});
    	
    	$('.list_list').on('click', '.delBtn', function() {
    		let con = confirm("정말로 삭제하시겠습니까?");
    		if (!con) return;
    		$(this).parent().parent().parent().attr("action", '<c:url value="/chart/delete" />').submit();
    	});
    	
    	$('.list_list').on('click', '#delCompBtn', function() {
    		let comment = $(".modComment").text();
    		comment = comment.replaceAll("\n","<br>");
    		$(this).parent().parent().prev().html(comment);
    		
    		let btn = "<a href='javascript:;' class='modBtn'>수정</a>";
    		btn += "<a href='javascript:;' class='delBtn'>삭제</a>";
    		$(this).parent().html(btn);
    	});
    	
    	$('.list_list').on('click', '#modCompBtn', function() {
    		let comment = $(".modComment").text();
    		if (comment.trim() == '') {
    			alert("리뷰를 입력해주세요.");
    			return;
    		}
    		console.log($(".modComment").val());
    		
    		$(this).parent().parent().parent().submit();
    	});
    	
    	$('.list_list').on('click', '.likeBtn', function() {
    		let review_num = $(this).parent().parent().parent().parent().attr("data-num");
    		let likeCnt = $(this).children('span');
    		
    		$.ajax({
    			type: 'post',
    			url: '/groovy/chart/like',
    			data: {
    				review_num: review_num
    			},
    			success: function(result) {
    				if (result != -1) {
    					likeCnt.html(result);
    				}
    				else {
    					alert("잠시 후 다시 시도해주세요.");
    				}
    			},
    			error: function() {
    				alert("잠시 후 다시 시도해주세요.");
    			}
    		});
    	});
    });
    
    function reviewWriteCheck(frm) {
    	let rate = $("#rate").val();
    	let comment = $("textarea[name=comment]").val();
    	
    	if (rate == 0) {
    		alert("평점을 선택해주세요.");
    		return false;
    	}
    	if (comment.trim() == '') {
    		alert("리뷰를 작성해주세요.");
    		return false;
    	}
    }
    </script>
</body>

</html>