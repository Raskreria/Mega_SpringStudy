<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문조사</title>
</head>
<body>

	<h2>설문조사</h2>
	
	
	<form action="/spring_2022-05-23/survey" method="POST">
	
		<c:forEach var="question" items="${questions }" varStatus="status">
			<p>
			
				<h3>${status.count }. ${question.title} </h3>
				
				<c:if test="${not empty question.options }">
					<c:forEach var="option" items="${question.options }">
						<label> <input type="radio" name="responses[${status.index }]" value="${option }"> ${option } </label>
					</c:forEach>
				</c:if>
				<c:if test="${empty question.options }">
					<input type="text" name="responses[${status.index }]"> 
				</c:if>
			</p>
		
		</c:forEach>
	
		<p>
			<h3>1. 당신의 역할은?</h3>
			<label> <input type="radio" name="responses[0]" value="서버"> 서버 개발자 </label>
			<label> <input type="radio" name="responses[0]" value="프론트"> 프론트 개발자 </label>
			<label> <input type="radio" name="responses[0]" value="풀스택"> 풀스택 개발자 </label>			
		</p>
		
		<p>
			<h3>2. 가장 많이 사용하는 개발도구는?</h3>
			<label> <input type="radio" name="responses[1]" value="Eclipse"> 이클립스</label>
			<label> <input type="radio" name="responses[1]" value="Intellij"> 인텔리제이 </label>
			<label> <input type="radio" name="responses[1]" value="VSCode"> VS Code </label>			
		</p>
		
		<p>
			<h3>3. 하고 싶은 말</h3>
			<input type="text" name="responses[2]">
		</p>	
		<p>
			<h3>응답자의 지역</h3>
			<input type="text" name="res.location">
		</p>	
		<p>
			<h3>응답자의 나이</h3>
			<input type="text" name="res.age">
		</p>	
		<input type="submit" value="전송">
	</form>
</body>
</html>