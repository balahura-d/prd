<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Part1</title>
</head>
<body>
	<table border="2">
		<tr>
			<td></td>
			<%
				for (int j = 1; j <= 9; j++) {
			%>
			<td><%=j%></td>
			<%
				}
			%>
		</tr>
		<%
			for (int j = 1; j <= 9; j++) {
		%>
		<tr>

			<td><%=j%></td>
			<%
				for (int k = 1; k <= 9; k++) {
			%>
			<td><%=k%></td>
			<%
				}
			%>
		</tr>
		<%
			}
		%>


	</table>
</body>
</html>