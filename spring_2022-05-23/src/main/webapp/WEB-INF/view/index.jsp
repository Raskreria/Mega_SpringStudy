<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>첫 화면</title>

</head>
<body>

	<p>첫 화면입니다!</p>
	<p>환영합니다! </p>
	
	<c:if test = "${empty authInfo }">
		<!-- 로그인을 하지 않았다면 -->
		<p> <a href ="/spring_2022-05-23/register/step1">[회원가입하기]</a> </p>
		<p> <a href ="/spring_2022-05-23/login">[로그인]</a> </p>
	</c:if>
	
	<c:if test = "${not empty authInfo }">
		<!-- 로그인을 했다면 -->
		<p> <a href ="/spring_2022-05-23/edit/changePassword">[비밀번호변경하기]</a> </p>
		<p> <a href ="/spring_2022-05-23/logout">[로그아웃]</a> </p>
	</c:if>
	
	
</body>
</html>