<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="Part3" method="post">
		<input name="inputName" /> 
		<br /> 
		<input type="submit" value="Send">
		<br /> 
		<c:forEach var="name" items="${sessionScope.names}">
		${name} <br>
		</c:forEach>
		<a href="Part3?remove=true">remove</a>
	</form>
</body>
</html>