<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

	#deleteBtn{
		display:none;
	}
	.btn btn-primary{
		width:20px;
		height:20px;
	}
	#fileName{
		line-height:20px;
	}
	#uploadFile{
    	transform: translateY(-5px);
	}
</style>
</head>
	<body>
		<div class="filebox bs3-primary">
			<label for="uploadFile">파일선택</label>
			<input type="file" name="uploadFile" id="uploadFile" class="upload-hidden" multiple />
			<c:choose>
				<c:when test="${ not empty board }">
					<input id="fileName" class="upload-name" value="${ board.img_src }" disabled="disabled">
				</c:when>
				<c:when test="${ not empty news }">
					<input id="fileName" class="upload-name" value="${ news.img_src }" disabled="disabled">
				</c:when>
				<c:otherwise>
					<input id="fileName" class="upload-name" value="" disabled="disabled">
				</c:otherwise>
			</c:choose>
        </div>
		
		
		
		<input class="btn btn-primary" id="deleteBtn" type="button" value="삭제" /><br>
		
		<c:choose>
			<c:when test="${ not empty board }">
				<input type="hidden" name="fileName" value="${ board.img_src }">
			</c:when>
			<c:when test="${ not empty news }">
				<input type="hidden" name="fileName" value="${ news.img_src }">
			</c:when>
			<c:otherwise>
				<input type="hidden" name="fileName" value="">
			</c:otherwise>
		</c:choose>
		
		<input type='hidden' name="del" id="del" value=0>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script>
			$(document).ready(function(){
				
				var fileTarget = $('.filebox .upload-hidden');

			    fileTarget.on('change', function(){
			        if(window.FileReader){
			            var filename = $(this)[0].files[0].name;
			        } else {
			            var filename = $(this).val().split('/').pop().split('\\').pop();
			        }

			        $(this).siblings('.upload-name').val(filename);
			    });
			    
			    $('#uploadFile').on('change', function() {
			        $('#fileName').text($(this).val());
			    });
	
				// 정규표현식 : 예).com 끝나는 문장 등의 조건이 복잡한 문장을 컴퓨터에게 이해시키기 위한 구문
				let regex = new RegExp("(.*)\.(exe|sh|zip|alz)$");
									// 파일이름 .  exe|sh|zip|alz 인 경우를 체크함
				let maxSize =5242880; // 5Mb
				
				function checkExtension(fileName, fileSize){
					// 파일크기 초과시 종료시킴
					if(fileSize >= maxSize){
						alert("파일 사이즈 초과");
						return false;// return이 있어서 아래쪽 구문은 실행 안됨
					}
					// regex에 표현해둔 정규식과 일치하는지 여부를 체크, 일치하면 true, 아니면 false
					if(regex.test(fileName)){
						alert("해당 확장자를 가진 파일은 업로드할 수 없습니다.");
						return false;
					}
					return true;
				}
				
				$('#writeBtn').on("click", function(e){
					alert("이벤트 발생");
					// form전송 객체, 주로 이미지와 같은 멀티미디어 파일을 페이지 전환없이 
					// 폼데이터를 비동기로 제출하고 싶을 때  사용
					let formData = new FormData(); 
					console.log(formData);
					
					let inputFile = $("input[name='uploadFile']"); 
					console.log(inputFile)
					
					let files = inputFile[0].files;// 실제 file 데이터
					console.log(files);
					if(files.length==0){
						alert("파일이 없습니다.")
						return;
					}
					// 파일 데이터를 폼에 집어넣기
					for(let i = 0; i < files.length; i++){
						console.log(i)
						if(!checkExtension(files[i].name, files[i].size)){
							return false;// 조건에 맞지않은 파일 포함시 onclick 이벤트 함수자체를 종료시켜버림
						}
	
						// 파일 데이터를 가져와서 key, value로 저장
						formData.append("uploadFile", files[i]);
						
						//console.log로는 단순한 객체가 아니라 전송을 위한 특수객체이므로 확인이 어려움
						//다음과 같이 for문으로 확인해야 함
						console.log("--------------파일 적재 후 formData 태그 -------------");
						for (let key of formData.keys()) {
							  console.log(key, ":",formData.get(key));
						}
					}
					
				});
				// uploadBtn onclick	
				$('#modifyBtn').click(function(){
					alert('수정버튼 클릭')
					console.log($('#del').val())
	
					
					let formData = new FormData(); 
					console.log(formData);
					
					let inputFile = $("input[name='uploadFile']"); 
					console.log(inputFile)
					
					let files = inputFile[0].files;// 실제 file 데이터
					console.log(files);
					let fileName = $("#fileName").val()
					if(files.length==0&&fileName.length==0){
						$('#del').val(3);
						alert("파일이 없습니다.")
						modi();
						return;
					}
					else if(files.length==0&&fileName.lengh!=0){
						$('#del').val(1);
						modi();
						return;
					}
					// 파일 데이터를 폼에 집어넣기
					for(let i = 0; i < files.length; i++){
						console.log(i)
						if(!checkExtension(files[i].name, files[i].size)){
							return false;// 조건에 맞지않은 파일 포함시 onclick 이벤트 함수자체를 종료시켜버림
						}
						console.log("파일 길이")
						console.log(files[i].name.length)
						formData.append("uploadFile", files[i]);
						
					}	
					modi();
				});
				function modi(){
					 // 2. 수정 상태이면 수정된 내용을 서버로 전송
					var form = document.getElementById('form');
					console.log(form)
					form.action="<c:url value='/board/modify'/>${searchCondition.queryString}"; 
					form.method="post";
					form.submit();
				}

				$('#deleteBtn').click(function(){
					// form전송 객체, 주로 이미지와 같은 멀티미디어 파일을 페이지 전환없이 
					// 폼데이터를 비동기로 제출하고 싶을 때  사용
					let formData = new FormData(); 
					console.log(formData);
					
					let inputFile = $("input[name='uploadFile']"); 
					console.log(inputFile)
					let fileName = $("#fileName").text()
					let files = inputFile[0].files;// 실제 file 데이터
					console.log(files);
					if(files.length==0 && fileName==''){
						alert("파일이 없습니다.")
						return;
					}
					// 파일 데이터를 폼에 집어넣기
					for(let i = 0; i < files.length; i++){
						console.log(i)
						if(!checkExtension(files[i].name, files[i].size)){
							return false;// 조건에 맞지않은 파일 포함시 onclick 이벤트 함수자체를 종료시켜버림
						}
						console.log("파일 길이")
						console.log(files[i].name.length)

						// 파일 데이터를 가져와서 key, value로 저장
						formData.append("deleteFile", files[i]);
						
						//console.log로는 단순한 객체가 아니라 전송을 위한 특수객체이므로 확인이 어려움
						//다음과 같이 for문으로 확인해야 함
						console.log("--------------파일 적재 후 formData 태그 -------------");
						for (let key of formData.keys()) {
							  console.log(key, ":",formData.get(key));
						}
					}
					
					$.ajax({
						url: '/bd/delete/', 
						// 폼 데이터로 인식하기 위해
						processData : false,
						contentType: false,
						data : formData,
						type : 'POST',
						success : function(result){
							console.log(result);
							alert("파일이 삭제되었습니다.");
							$('#uploadFile').val('');
							$('#fileName').text('');
							$('#del').val(3);
						}
					}); // ajax
					
				});
				
			});	// document ready END    
		</script>
	</body>
</html>