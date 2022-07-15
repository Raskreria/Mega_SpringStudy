<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입(2)</title>
</head>
<body>
	<form:form action="/spring_2022-05-23/register/step3" modelAttribute="formData">
		<div>
			<label> 
			<spring:message code="email" />
			<form:input path="email"/>
			<form:errors path="email"/>
			</label>
		</div>
		
		<div>
			<label>
			<spring:message code="name" />
			<form:input path="name"/>
			<form:errors path="name"/>
			</label>
		</div>
		
		<div>
			<label>
			<spring:message code="password" />
			<form:password path="password"/>
			<form:errors path="password"/>
			</label>
		</div>
		
		<div>
			<label> 
			<spring:message code="password.confirm" />
			<form:password path="confirmPassword"/>
			<form:errors path="confirmPassword"/>
			</label>
		</div>
		
		<input type="submit" value="<spring:message code="register.btn" />">
	</form:form>


</body>
</html>