<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Groovy | 뉴스</title>
</head>
<body>
	<form action="<c:url value='news/insert' />" method="post" enctype="multipart/form-data">
		<input type="file" name="file" />
	</form>
</body>
</html>