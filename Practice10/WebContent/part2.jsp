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
	<table border=2>
		<tr>
			<td></td>
			<c:forEach var="j" begin="1" end="9">
				<td>${j }</td>
			</c:forEach>
		</tr>
		<c:forEach var="j" begin="1" end="9">
			<tr>
				<td>${j }</td>
				<c:forEach var="k" begin="1" end="9">
					<td>${k * j}</td>
				</c:forEach>
			</tr>
		</c:forEach>


	</table>
</body>
</html>