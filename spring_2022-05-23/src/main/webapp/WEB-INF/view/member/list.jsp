<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="memberList.title" /></title>
</head>
<body>
	<form:form modelAttribute="cmd">
		<label>from : <form:input path="from" />
		</label>
		<form:errors path="from" />
	
		~
	
		<label>to : <form:input path="to" /></label>
		<form:errors path="to" />
		
		<input type="submit" value="조회">
	</form:form>
	
	<c:if test="${! empty members }">
		<table>
			<thead>
				<tr>
					<th>아이디</th>
					<th>이메일</th>
					<th>이름</th>
					<th>가입일</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach var="member" items="${members}">
					<tr>
						<td>${member.id}</td>
						<td>${member.email}</td>
						<td>${member.name}</td>
						<td>${member.registerDateTime}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
		
</body>
</html>