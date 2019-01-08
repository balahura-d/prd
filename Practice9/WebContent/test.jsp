<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<body>

	<form action="Voting" method="get">
		<select name="sport">
			<c:forEach var="sport" items="${sports}">
				<option value="${sport}">${sport}
			</c:forEach>
		</select> <input type="submit">
	</form>


</body>
</html>