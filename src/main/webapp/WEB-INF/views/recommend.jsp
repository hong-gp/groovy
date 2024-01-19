<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Groovy | 추천</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/recommend.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/x-icon">
    <script src="https://kit.fontawesome.com/8f808bece4.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>

<body>
    <!-- s : wrap -->
    <div id="wrap">
        <!-- s : header -->
        <%@ include file="header.jsp" %>
        <!-- e : header -->

        <!-- s : main -->
        <div id="main">
            <div id="section">
                <div class="container content_container">
                    <div class="recommend">
                        <div class="recom_title">
                            <h2>추천</h2>
                        </div>
                        <div class="selected_box">
                        	<h2>원하는 조건을 선택하면 음악을 추천해드립니다</h2>
	                        <div class="selected_recommend">
	                        	<div class="selected_artist">
	                        		<h2>아티스트</h2>
	                        		<div class="selected_no"></div>
	                        	</div>
	                        	<div class="selected_genre">
	                        		<h2>장르</h2>
	                        		<div class="selected_no"></div>
	                        	</div>
	                        	<div class="selected_track">
	                        		<h2>트랙</h2>
	                        		<div class="selected_no"></div>
	                        	</div>
	                        </div>
                        </div>
                        <form class="recom_header" action='<c:url value="/recommend/result" />' method='get'>
                        	<div class="choose_artist">
                        		<div class="choose_artist_header">
                        			<h2>아티스트를 선택해주세요</h2>
                        		</div>
                        		<div class="choose_artist_body">
                        			<div class="search_artist_box">
                        				<input type="text" name="artist_search" class="input_artist" />
                        				<button type="button" class="searchBtn" data-type='artist'>검색</button>
                        			</div>
                        			<div class="search_artist_result">
                        				
                        			</div>
                        		</div>
                        		<div class="recom_footer">
		                        	<button class="nextBtn" type="button">다음</button>
		                        </div>
                        	</div>
                        	
                            <div class="choose_genre" style="display: none;">
                                <div class="choose_genre_header">
                                    <h2>장르를 선택해주세요</h2>
                                </div>
                                <div class="choose_genre_body">
                                    
                                </div>
                                <div class="recom_footer">
		                        	<button class="prevBtn" type="button">이전</button>
		                        	<button class="nextBtn" type="button">다음</button>
		                        </div>
                            </div>
                            
                            <div class="choose_track" style="display: none;">
                            	<div class="choose_track_header">
                            		<h2>트랙을 선택해주세요</h2>
                            	</div>
                            	<div class="choose_track_body">
                            		<div class="search_track_box">
                        				<input type="text" name="track_search" class="input_track" />
                        				<button type="button" class="search_track_btn" data-type='track'>검색</button>
                        			</div>
                        			<div class="search_track_result">
                        				
                        			</div>
                            	</div>
                            	<div class="recom_footer">
		                        	<button class="prevBtn" type="button">이전</button>
		                            <button class="recomBtn" type="button">확인</button>
		                        </div>
                            </div>

                            <input type="hidden" name="genre" value="">
                            <input type="hidden" name="artist" value="">
                            <input type="hidden" name="searchTrack" value="">
                            <input type="hidden" name="artist_name" value="">
                            <input type="hidden" name="track_name" value="">
                        </form>
                    </div>

                    <%@ include file="aside.jsp" %>
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
        $(document).on('click', '.genre', function() {
            $('input[name=genre]').val($(this).attr("data-genre"));
            $('.check').detach();
            let name = $(this).children().children('span').text();
            
            let imgSrc = $(this).children().children('img').attr('src');
            $('.selected_genre').html('<h2>장르</h2><img src="' + imgSrc + '"><span>' + name + '</span>');

            let check = '<div class="check">';
            check += '<i class="fa-regular fa-circle-check"></i>';
            check += '</div>';
            $(this).append(check);
            console.log($('input[name=genre]').val());
        });
        
        $(document).on('click', '.artist_thum', function() {
            $('input[name=artist]').val($(this).attr("data-artist"));
            let imgSrc = $(this).children('img').attr('src');
            let name = $(this).children('span').text();
            
            $('.selected_artist').html('<h2>아티스트</h2><img src="' + imgSrc + '"><span>' + name + '</span>');
            $('input[name=artist_name]').val(name);

    		console.log(imgSrc);
            console.log($('input[name=artist]').val());
        });
        
        $(document).on('click', '.track_thum', function() {
            $('input[name=searchTrack]').val($(this).attr("data-track"));
            let imgSrc = $(this).children('img').attr('src');
            let name = $(this).children('span:eq(0)').text();

            $('.selected_track').html('<h2>트랙</h2><img src="' + imgSrc + '"><span>' + name + '</span>');
            $('input[name=track_name]').val(name);
            console.log($('input[name=searchTrack]').val());
        });
        
        $(document).on('click', '.recomBtn', function() {
        	if ($('input[name=genre]').val() == "") {
                alert("장르를 선택해주세요.");
                return;
            }
            if ($('input[name=artist]').val() == "") {
                alert("아티스트를 선택해주세요.");
                return;
            }
            if ($('input[name=searchTrack]').val() == "") {
                alert("트랙을 선택해주세요.");
                return;
            }

            $('.recom_header').submit();
        });
        
        
        
        $(document).ready(function() {
        	
        	let genresStr = '${ genres }';
        	let genres = JSON.parse(genresStr);
        	console.log(genres);
        	let tmp = '<ul>';
        	
        	genres.genres.forEach(function(genre) {
        		
        		tmp += '<li class="genre" data-genre="' + genre + '">';
        		tmp += '<div class="genre_image">';
        		tmp += '<img src="${pageContext.request.contextPath}/images/genres/' + genre + '.jpg" alt="">';
        		tmp += '<span>' + genre + '</span>';
        		tmp += '</div>';
        		tmp += '</li>';
        	});
        	tmp += '</ul>';
        	$('.choose_genre_body').html(tmp);
        	
        	$(document).on('click', '.searchBtn', function() {
        		let keyword = $('input[name=artist_search]').val();
        		let type = $(this).attr('data-type');
        		
        		$.ajax({
        			type: 'post',
        			url: '/groovy/recommend/search',
        			data: {
        				keyword: keyword,
        				type: type
        			},
        			success: function(result) {
        				let obj = JSON.parse(result);
        				console.log(obj);
        				$('.search_artist_result').html(toArtist(obj));
        			}
        		});
        	});
        	
        	$(document).on('click', '.search_track_btn', function() {
        		let keyword = $('input[name=track_search]').val();
        		let type = $(this).attr('data-type');
        		
        		$.ajax({
        			type: 'post',
        			url: '/groovy/recommend/search',
        			data: {
        				keyword: keyword,
        				type: type
        			},
        			success: function(result) {
        				console.log(result);
        				let obj = JSON.parse(result);
        				console.log(obj);
        				$('.search_track_result').html(toTrack(obj));
        			}
        		});
        	});
        	
        });
        
        let toArtist = function(item) {
        	let tmp = '';
        	
        	item.artists.items.forEach(function(artist) {
        		tmp += '<div class="artist_thum" data-artist="' + artist.id + '">';
        		if (artist.images.length == 0) {
        			tmp += '<div class="no_thum"><i class="fa-solid fa-user"></i></div>';
        		} else {
	        		tmp += '<img src=' + artist.images[0].url + '>';
        		}
        		tmp += '<span>' + artist.name + '</span>';
        		tmp += '</div>';
        	});
        	return tmp;
        }
        
        let toTrack = function(item) {
        	let tmp = '';
        	
        	item.tracks.items.forEach(function(track) {
        		tmp += '<div class="track_thum" data-track="' + track.id +'">';
        		if (track.album.images.length == 0) {
        			tmp += '<div class="no_thum"><i class="fa-solid fa-user"></i></div>';
        		} else {
	        		tmp += '<img src=' + track.album.images[0].url + '>';
        		}
        		tmp += '<span>' + track.name + '</span>';
        		tmp += '<span>' + track.artists[0].name + '</span>';
        		tmp += '</div>';
        	});
        	return tmp;
        }
        
        $('.choose_artist').on('click', '.nextBtn', function() {
        	if ($('input[name=artist]').val() == '') {
        		alert("아티스트를 선택해주세요.");
        		return;
        	}
        	$('.choose_artist').css("display", "none");
        	$('.choose_genre').css('display', 'block');
        });
        $('.choose_genre').on('click', '.prevBtn', function() {
        	$('.choose_artist').css("display", "block");
        	$('.choose_genre').css('display', 'none');
        });
        $('.choose_genre').on('click', '.nextBtn', function() {
        	if ($('input[name=genre]').val() == '') {
        		alert("장르를 선택해주세요.");
        		return;
        	}
        	$('.choose_genre').css('display', 'none');
        	$('.choose_track').css('display', 'block');
        });
        $('.choose_track').on('click', '.prevBtn', function() {
        	$('.choose_genre').css('display', 'block');
        	$('.choose_track').css('display', 'none');
        });
    </script>
</body>

</html>