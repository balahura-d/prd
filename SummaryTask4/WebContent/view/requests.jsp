<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set var="title" value="requests" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<table id="main-container">
		<%-- HEADER --%>
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<%-- HEADER --%>
		<tr>
			<td class="content">
				<%-- CONTENT --%> <c:choose>
					<c:when test="${fn:length(requestList) == 0}">
						<fmt:message key="requests.noRequest" />
					</c:when>

					<c:otherwise>
						<table id="list_request_table" border="1">
							<thead>
								<tr>
									<td>№№</td>
									<td><fmt:message key="driver" /></td>
									<td><fmt:message key="flight" /></td>
									<td><fmt:message key="seats" /></td>
									<td><fmt:message key="status" /></td>
									<td><fmt:message key="processed" /></td>
									<td><fmt:message key="actions" /></td>
								</tr>
							</thead>
							<c:forEach var="bean" items="${requestList}">
								<tr>
									<td>${bean.id}</td>
									<td title="${bean.driverId}">${bean.driverName}</td>
									<td title="${bean.flightId}">${bean.flightName}</td>
									<td>${bean.seats}</td>
									<td><fmt:message key="${bean.statusName}" /></td>
									<td>${bean.processedByDisp}</td>
									<c:if test="${bean.statusName == 'opened'}">
										<c:if
											test="${(userRole.name eq 'admin') or (userRole.name eq 'dispatcher') }">
											<td>
												<form id="closeRequest" action="../controller" method="post">
													<input type="hidden" name="command" value="closeRequest" />
													<input type="hidden" name="requestId" value="${bean.id}" />
													<input type="submit" name="close"
														value="<fmt:message key="requests.close" />" />
												</form>&nbsp;
												<form id="applyRequest" action="../controller" method="post">
													<input type="hidden" name="command" value="applyRequest" />
													<input type="hidden" name="requestId" value="${bean.id}" />
													<select required name="autoId">
														<option selected disabled><fmt:message key="selectCar" /></option>
														<c:forEach var="aut" items="${bean.availableAuto}">
															<option value="${aut.id}">${aut.name},
																${aut.seats} seats</option>
														</c:forEach>
													</select> <input type="submit" name="apply"
														value="<fmt:message key="requests.apply" />" />&nbsp;
												</form>
											</td>
										</c:if>
										<c:if
											test="${(userRole.name == 'driver') and (user.id == bean.driverId)}">
											<td><form id="cancelRequest" action="../controller"
													method="post">
													<input type="hidden" name="command" value="cancelRequest" />
													<input type="hidden" name="requestId" value="${bean.id}" />
													<input type="submit" name="cancel"
														value="<fmt:message key="requests.cancel" />" />
												</form></td>
										</c:if>
									</c:if>
								</tr>
							</c:forEach>
						</table>
					</c:otherwise>
				</c:choose> <%-- CONTENT --%>
			</td>
		</tr>
	</table>
</body>
</html>