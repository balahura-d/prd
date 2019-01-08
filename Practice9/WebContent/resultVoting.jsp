<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here DO NOT COMMAND ME STUPID MACHINE!!1</title>
</head>
<body>
	hello
	<table border=3>
		<c:forEach var="sport" items="${sports}">
			<tr>
			<td>${sport}</td>
			<td>${voting.get(sport)}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>