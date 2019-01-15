<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
<c:if test="${empty userToShowRole}">
	<c:set var="title" value="Select the role" />
</c:if>
<c:if test="${not empty userToShowRole}">
	<c:set var="title" value="users" />
</c:if>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<table id="main-container">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<tr>
			<td class="content"><c:if test="${userRole.name eq 'admin'}">
					<a href="#" id="go"><fmt:message key="users.new" /></a>
				</c:if> <%-- CONTENT --%> <c:choose>
					<c:when test="${empty userToShowRole}">
						<p>
							<fmt:message key="selectRoleToShow" />
						</p>
						<a href="../controller?command=listUsers&role=driver"><fmt:message
								key="drivers" /></a> &nbsp;
						<a href="../controller?command=listUsers&role=dispatcher"><fmt:message
								key="dispatchers" /></a> &nbsp;
					</c:when>

					<c:when test="${fn:length(userList) == 0}">
						<p>
							<fmt:message key="noUser" />
						</p>
					</c:when>

					<c:otherwise>
						<table id="user_table" border="1">
							<thead>
								<tr>
									<td>№</td>
									<td><fmt:message key="users.name" /></td>
									<td><fmt:message key="login" /></td>
									<c:if test="${userRole.name eq 'admin'}">
										<td><fmt:message key="actions" /></td>
									</c:if>
								</tr>
							</thead>
							<c:forEach var="bean" items="${userList}">
								<tr>
									<td>${bean.id}</td>
									<td>${bean.fullName}</td>
									<td>${bean.login}</td>
									<c:if test="${userRole.name eq 'admin'}">
										<td>
											<form id="actUser" action="../controller" method="post">
												<input type="hidden" name="command" value="actUser" /> <input
													type="hidden" name="userId" value="${bean.id}" /> <input
													type="submit" name="delUser" value="<fmt:message key="dismiss" />" />
											</form>
										</td>
									</c:if>
								</tr>
							</c:forEach>
						</table>
					</c:otherwise>
				</c:choose> <%-- CONTENT --%></td>
		</tr>
	</table>

	<div id="modal_form">

		<span id="modal_close">X</span>
		<form id="actUser" action="../controller" method="post">
			<input type="hidden" name="command" value="actUser" /> <input
				name="name" pattern="*{,40}" required="required" placeholder="<fmt:message key="users.name" />" /><br />
			<input name="login" pattern="*{,20}" required="required" placeholder="<fmt:message key="login" />" /><br />
			<input type="submit" name="regUser"
				value="<fmt:message key="users.new" />" />
		</form>
	</div>
	<div id="overlay"></div>
</body>
</html>