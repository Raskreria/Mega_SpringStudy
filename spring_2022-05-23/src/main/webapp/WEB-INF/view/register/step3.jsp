<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입(3)</title>
</head>
<body>
	<!-- 이 뷰를 반환하는 컨트롤러의 메서드에서 RegisterRequest 타입의 매개변수를 커맨드 객체로 사용하고 있음.
	개발자가 커맨드 객체를 뷰로 전달해주지 않아도 스프링이 알아서 커맨드 객체를 뷰로 전달해줌.
	이 때 뷰로 전달해주는 커맨드 객체릐 이름은 기본적으로 커맨드 객체의 데이터 타입을 사용해서 이름을 지어 전달함.-->
	<p> <strong>${formData.name }님</strong></p>
	<p>회원가입을 완료했습니다.</p>
	<p> <a href="/spring_2022-05-23/main">[첫 화면으로 이동]</a>  </p>
	
</body>
</html>