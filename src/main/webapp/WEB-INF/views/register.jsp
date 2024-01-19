<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Groove | 회원가입</title>
    <link rel="stylesheet" href="css/register.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/x-icon">
    <script src="https://kit.fontawesome.com/8f808bece4.js"
	crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
    <div id="wrap">
        <!-- s : header -->
        <%@ include file="header.jsp" %>
        <!-- e : header -->
        <div id="main">
            <div id="container">
                <form id="contents" name="form" action="./register" method="post">
                    <div class="logo">
                        <h2>회원가입</h2>
                    </div>
                    <div class="input_area">
                        <ul>
                            <li>
                                <label for="id"><span>*</span>아이디</label>
                                <input type="text" name="id" id="id" autocomplete="off" required autofocus value="${ dto.id }" placeholder="4자 이상의 영문 대소문자와 숫자">
                                <span id="nullId" class="alert"></span>
                            </li>
                            <li>
                                <label for="password"><span>*</span>비밀번호</label>
                                <input type="password" name="password" id="password" required value="${ dto.pw }" placeholder="4자 이상의 영문 대소문자, 숫자, 특수문자">
                                <span id="nullPw" class="alert"></span>
                            </li>
                            <li>
                                <label for="passwordCheck"></label>
                                <input type="password" name="passwordCheck" id="passwordCheck" placeholder="비밀번호 확인" required value="${ dto.pw }">
                                <span id="nullPwCheck" class="alert"></span>
                            </li>
                            <li>
                                <label for="name"><span>*</span>이름</label>
                                <input type="text" name="name" id="name" autocomplete="off" required value="${ dto.name }">
                                <span id="nullName" class="alert"></span>
                            </li>
                            <li>
                                <label for="nickname"><span>*</span>별명</label>
                                <input type="text" name="nickname" id="nickname" autocomplete="off" required value="${ dto.name }">
                                <span id="nullNickname" class="alert"></span>
                            </li>
                            <li>
                                <label for="tel"><span>*</span>휴대폰</label>
                                <input type="tel" name="tel" id="tel" placeholder="숫자만 입력" autocomplete="off" required value="${ dto.phone }">
                                <span id="nullTel" class="alert"></span>
                            </li>
                            <li>
                                <label for="mail1"><span>*</span>이메일</label>
                                <input type="text" name="mail1" id="mail1" autocomplete="off" required value="${ mail1 }"> @
                                <div id="mail2_area" style="display: inline-block;">
                                    <select name="mail2" id="mail2">
                                        <option value="naver.com" selected>naver.com</option>
                                        <option value="gmail.com">gmail.com</option>
                                        <option value="daum.net">daum.net</option>
                                        <option value="self">직접입력</option>
                                    </select>
                                </div>
                                <span id="nullMail" class="alert"></span>
                            </li>
                            <li>
                                <label for="birth"><span>*</span>생년월일</label>
                                <input type="text" name="birth" id="birth" autocomplete="off" required value="${ mail1 }" placeholder="생년월일 8자리">
                                <span id="nullBirth" class="alert"></span>
                            </li>
                        </ul>
                    </div>
                    <div class="checkbox_area">
                        <div class="area1">
                            <input type="checkbox" name="allCheck" id="allCheck">
                            <label for="allCheck">아래 내용에 모두 동의합니다</label>
                        </div>
                        <div class="area2">
                            <div class="check">
                                <input type="checkbox" name="check" id="check1" class="isCheck" value="1" required>
                                <label for="check1">(필수) 이용약관에 동의합니다</label>
                            </div>
                            <div class="check">
                                <input type="checkbox" name="check" id="check2" class="isCheck" value="2" required>
                                <label for="check2">(필수) 개인정보 수집 및 이용에 대한 안내에 동의합니다</label>
                            </div>
                        </div>
                    </div>
                    <div class="button_area">
                        <button type="button" id="submit_btn">회원가입</button>
                    </div>
                </form>
            </div>
        </div>
        <!-- s : footer -->
        <%@ include file="footer.jsp" %>
        <!-- e : footer -->
    </div>

    <script>
    
        let btn = document.getElementById('submit_btn');

        let allCheck = document.getElementById('allCheck');
        let checkbox = document.querySelectorAll('.isCheck');

        allCheck.addEventListener('change', function() {
            let isCheck;
            if (allCheck.checked) {
                isCheck = true;
            } else {
                isCheck = false;
            }
            for(let i=0; i<checkbox.length; i++) {
                checkbox[i].checked = isCheck;
            }
        });
        for (let i=0; i<checkbox.length; i++) {
            checkbox[i].addEventListener('change', function() {
                if (checkbox[0].checked == true && checkbox[1].checked == true && checkbox[2].checked && true) {
                    allCheck.checked = true;
                } else {
                    allCheck.checked = false;
                }
            })
        }

        btn.onclick = function() {
            let check = true;
            let id = document.getElementById('id').value;
            let nullId = document.getElementById('nullId');
            let password = document.getElementById('password').value;
            let nullPw = document.getElementById('nullPw');
            let passwordCheck = document.getElementById('passwordCheck').value;
            let nullPwCheck = document.getElementById('nullPwCheck');
            let name = document.getElementById('name').value;
            let nullName = document.getElementById('nullName');
            let nickname = document.getElementById('nickname').value;
            let nullNickname = document.getElementById('nullNickname');
            let tel = document.getElementById('tel').value;
            let nullTel = document.getElementById('nullTel');
            let mail1 = document.getElementById('mail1').value;
            let mail2 = document.getElementById('mail2').value;
            let nullMail = document.getElementById('nullMail');
            let birth = document.getElementById('birth').value;
            let nullBirth = document.getElementById('nullBirth');
            
            if (id == "") {
                nullId.innerHTML = '아이디를 입력해주세요.';
                check = false;
            } else if (!(/^[a-zA-Z0-9]{4,}$/.test(id))) {
                nullId.innerHTML = '4자 이상의 영문 대소문자와 숫자로만 입력'
                check = false;
            } else {
                nullId.innerHTML = "";
            }

            if (password == "") {
                nullPw.innerHTML = '비밀번호를 입력해주세요.';
                check = false;
            } else if (!/^[a-zA-Z0-9!@#$%^&*()_+|<>?{}[\]~-]{4,}$/.test(password)) {
                nullPw.innerHTML = '4자 이상의 영문 대소문자, 숫자, 특수문자로만 입력';
                check = false;
            } else {
                nullPw.innerHTML = "";
            }

            if (passwordCheck != password) {
                nullPwCheck.innerHTML = '비밀번호가 다릅니다.';
                check = false;
            } else {
                nullPwCheck.innerHTML = "";
            }

            if (name == "") {
                nullName.innerHTML = '이름을 입력해주세요.';
                check = false;
            } else if (!/^[a-zA-Z가-힣]{2,}$/.test(name)) {
                nullName.innerHTML = '정확한 이름을 입력해주세요.';
                check = false;
            } else {
                nullName.innerHTML = "";
            }

            if (nickname == "") {
                nullNickname.innerHTML = '별명을 입력해주세요.';
                check = false;
            } else {
                nullNickname.innerHTML = "";
            }
            if (tel == "") {
                nullTel.innerHTML = '전화번호를 입력해주세요.';
                check = false;
            } else if (!/^\d{11}$/.test(tel)) {
                nullTel.innerHTML = '정확한 전화번호를 입력해주세요.';
                check = false;
            } else {
                nullTel.innerHTML = "";
            }
            if (mail1 == "") {
                nullMail.innerHTML = '이메일을 앞자리를 입력해주세요.';
                check = false;
            } else if (mail2 == "") {
                nullMail.innerHTML = '이메일을 뒷자리를 입력해주세요.';
                check = false;
            }
             else {
                nullMail.innerHTML = "";
            }
            if (birth == "") {
                nullBirth.innerHTML = '생년월일을 입력해주세요.';
                check = false;
            } else if (!/^\d{8}$/.test(birth)) {
                nullBirth.innerHTML = '정확한 생년월일 8자를 입력해주세요.';
                check = false;
            } else {
                nullBirth.innerHTML = '';
            }

            if (!check) {
                return;
            }
            
            if(!document.getElementById('check1').checked) {
                alert('이용약관에 동의해주세요.');
                return;
            }
            if(!document.getElementById('check2').checked) {
                alert('개인정보 수집 및 이용에 동의해주세요.');
                return;
            }

            if (check) {
            	
    	    	let email = mail1 + "@" + mail2;
    	    	
    	    	$.ajax({
    	    		url: "/groovy/register",
    	    		type: "post",
    	    		headers: {
        				"content-type": "application/json"
        			},
    	    		data: JSON.stringify({
    	    			id: id,
    	    			password: password,
    	    			name: name,
    	    			nickname: nickname,
    	    			tel: tel,
    	    			email: email,
    	    			birth: birth
    	    		}),
    	    		success: function(result) {
    	    			if (result == "REGIST_CHECK") {
    	    				nullId.innerHTML = "중복된 아이디입니다.";
    	    			} else if (result == "REGIST_OK") {
    	    				location.href = '<c:url value="/login" />';
    	    			} else {
    	    				alert("잠시 후 다시 시도해주세요.");
    	    			}
    	    		},
    	    		error: function(request, status, error) {
    					alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
    				}
    	    	});
            }
        }

        let mail = document.getElementById('mail2');
        
        mail.addEventListener('change', function() {
            if (mail.value == 'self') {
                mail.remove();
                let text = "<input type='text' name='mail2' id='mail2' autocomplete='off'>";
                document.getElementById('mail2_area').innerHTML = text;
            }
        });
    </script>
</body>
</html>