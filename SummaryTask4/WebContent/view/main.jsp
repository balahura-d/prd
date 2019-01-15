<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set var="title" value="main" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<jsp:useBean id="now" class="java.util.Date" />

	<table id="main-container">
		<tr>
			<td class="content center">
				<h4>
					<fmt:message key="main.greeteing" />
					${user.fullName}!
				</h4>
				<p>
					<fmt:message key="main.today" />
					<fmt:formatDate value="${now}" type="date" dateStyle="full" />
				</p>
				<h5>
					<fmt:message key="main.sentiment" />
				</h5> <c:if test="${userRole.name == 'admin'}">
					<form id="stat" action="../controller" method="post">
						<input type="hidden" name="command" value="stat" /> <input
							type="date" name="from" > <input
							type="date" name="to"> <input type="submit">

					</form>
					<c:if test="${not empty ff}">
						<table align="center">
							<caption>From: <fmt:formatDate type = "date" 
         value = "${from}" />, to: <fmt:formatDate type = "date" 
         value = "${to}"/></caption>
							<tr class="content center">
								<td>opened flights</td>
								<td>in-progress flights</td>
								<td>closed flights</td>
								<td>canceled flights</td>
							</tr>


							<tr>
								<c:forEach var="bean" items="${ff}">
									<td>${bean}</td>

								</c:forEach>
							</tr>
						</table>
					</c:if>
				</c:if>
			</td>
		</tr>

	</table>
</body>
</html>