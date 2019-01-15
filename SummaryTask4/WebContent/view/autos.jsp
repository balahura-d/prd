<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set var="title" value="autos" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<table id="main-container">
		<%-- HEADER --%>
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<%-- HEADER --%>
		<tr>
			<td class="content">
				<%-- CONTENT --%> <c:choose>
					<c:when test="${fn:length(autoList) == 0}">
						<fmt:message key="autos.noMotors" />
					</c:when>

					<c:otherwise>
						<table id="list_auto_table" border="1">
							<thead>
								<tr>
									<td>№№</td>
									<td><fmt:message key="name" /></td>
									<td><fmt:message key="seats" /></td>
									<td><fmt:message key="condition" /></td>
									<c:if test="${userRole.name eq 'admin'}">
										<td><fmt:message key="actions" /></td>
									</c:if>
								</tr>
							</thead>
							<c:if test="${userRole.name eq 'admin'}">
								<tr>
									<form id="actAut" action="../controller" method="post">
										<td><input type="hidden" name="command" value="actAut" />
										</td>
										<td><input name="name" required="required" /></td>
										<td><input type="number" min="1" required="required" name="seats" /></td>
										<td><input type="number"
											title="1 - new
2 - was in use
3 - minor defects
4 - damaged"
											name="condition" required="required" min="1" max="4" /></td>
										<td><input type="submit" name="regAuto"
											value="<fmt:message key="autos.register" />" /></td>
									</form>
								</tr>
							</c:if>
							<c:forEach var="bean" items="${autoList}">
								<tr>
									<td><c:if test="${userRole.name eq 'admin'}">
											<c:set var="autoId" scope="page" value="${bean.id}" />
											<c:set var="autoName" scope="page" value="${bean.name}" />
											<c:set var="autoSeats" scope="page" value="${bean.seats}" />
											<a href="#" id="go">${bean.id}</a>
										</c:if> <c:if test="${userRole.name ne 'admin'}">${bean.id}</c:if></td>
									<td>${bean.name}</td>
									<td>${bean.seats}</td>
									<td><fmt:message key="${bean.condition}" /></td>
									<c:if test="${(userRole.name eq 'admin') and (bean.condition ne 'is_used')}">
										<td>
											<form id="actAut" action="../controller" method="post">
												<input type="hidden" name="command" value="actAut" /> <input
													type="hidden" name="autoId" value="${bean.id}" /> <input
													type="submit" value="<fmt:message key="sell" />" name="sellAuto" />
												<c:if
													test="${(bean.conditionId gt 2) and (bean.conditionId lt 5)}">
													<input type="submit" value="<fmt:message key="repare" />" name="repareAuto" />
												</c:if>
											</form>
										</td>
									</c:if>
								</tr>
							</c:forEach>
						</table>
					</c:otherwise>
				</c:choose> <%-- CONTENT --%>
			</td>
		</tr>
	</table>
	<div id="modal_form">

		<span id="modal_close">X</span>
		<form id="actAut" action="../controller" method="post">
			<input type="hidden" name="command" value="actAut" /> <input
				type="hidden" name="autoId" value="${pageScope.autoId}" /> <input
				name="name" value="${autoName}" required="required" placeholder="<fmt:message key="name" />" /><br /> <input type="number" name="seats"
				value="${autoSeats}" required="required" placeholder="<fmt:message key="seats" />"/><br /> <input type="submit" name="editAuto"
				value="<fmt:message key="autos.save" />" />
		</form>
	</div>
	<div id="overlay"></div>
</body>
</html>