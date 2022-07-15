<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입(1)</title>
</head>
<body>
	<h2>약관</h2>
	<p>약관 내용</p>
	
	<form action="/spring_2022-05-23/register/step2" method="POST">
	
		<label>
			<input type="checkbox" name="agree" value ="true">약관동의
		</label>
		
		<input type="submit" value="다음단계">
	</form>

</body>
</html>