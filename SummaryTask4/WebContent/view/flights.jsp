<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set var="title" value="flights" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<script type="text/javascript" src="../script/sort.js"></script>
	<!-- 
<script type="text/javascript">window.onload = function () {makeAllSortable();};</script>
 -->

	<table id="main-container">
		<%-- HEADER --%>
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<%-- HEADER --%>
		<tr>
			<td class="content"><c:if test="${userRole.name ne 'driver'}">
					<a href="#" id="go"><fmt:message key="flights.createFlight" /></a>
				</c:if> <%-- CONTENT --%> <c:choose>
					<c:when test="${fn:length(flightFacadeList) == 0}">
						<fmt:message key="flights.noFlight" />
					</c:when>

					<c:otherwise>
						<table id="list_of_fligts">
							<thead>
								<tr>
									<th>№№</th>
									<th><fmt:message key="name" /></th>
									<th><fmt:message key="date" /></th>
									<th><fmt:message key="status" /></th>
									<th><fmt:message key="driver" /></th>
									<th><fmt:message key="auto" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="bean" items="${flightFacadeList}">

									<tr>
										<td>${bean.id}</td>
										<td>${bean.name}</td>
										<td><fmt:formatDate value="${bean.date}" pattern = "yyyy-MM-dd" /></td>
										<td>${bean.statusId}:<fmt:message key="${bean.statusName}" /></td>

										<c:choose>
											<c:when test="${bean.statusName == 'opened'}">
												<!-- for opened flights -->
												<c:if test="${userRole.name == 'driver'}">
													<!-- drivers can accept flights -->
													<form id="regRequest" action="../controller" method="post">
														<input type="hidden" name="command" value="regRequest" />
														<input type="hidden" name="flightId" value="${bean.id}" />
														<td><input type="submit"
															value="<fmt:message key="apply" />" /></td>
														<td><input type="number" name="seats"
															placeholder="<fmt:message key="typeSeats" />"></td>
													</form>
												</c:if>
												<c:if
													test="${(userRole.name eq 'admin') or (userRole.name eq 'dispatcher') }">
													<!-- admins and dispatcher can delete opened flights -->
													<td></td>
													<td>
														<form id="actFlight" action="../controller" method="post">
															<input type="hidden" name="command" value="actFlight" />
															<input type="hidden" name="beanId" value="${bean.id}" />
															<input type="submit" name="delFlight"
																value="<fmt:message key="flights.delete" />" />
														</form>
													</td>
												</c:if>
											</c:when>
											<c:when test="${bean.statusName eq 'in_progress'}">
												<td>${bean.driverName}</td>
												<td>${bean.autoName}</td>
												<c:if
													test="${(userRole.name == 'driver') and (user.id == bean.driverId)}">
													<td><c:set var="flightId" scope="page"
															value="${bean.id}" />
														<button id="go">
															<fmt:message key="flights.done" />
														</button></td>
												</c:if>
												<c:if test="${userRole.name ne 'driver'}">
													<td>
														<form id="actFlight" action="../controller" method="post">
															<input type="hidden" name="command" value="actFlight" />
															<input type="hidden" name="beanId" value="${bean.id}" />
															<input type="submit" name="cancelFlight"
																value="<fmt:message key="flights.close" />" />
														</form>
													</td>
												</c:if>
											</c:when>
											<c:otherwise>
												<td>${bean.driverName}</td>
												<td></td>
											</c:otherwise>
										</c:choose>
										<c:if test="${userRole.name ne 'driver'}">
											<td>
												<form id="actFlight" action="../controller" method="post">
													<input type="hidden" name="command" value="actFlight" /> <input
														type="hidden" name="beanId" value="${bean.id}" /><input
														type="submit" name="copyFlight" value="<fmt:message key="copy" />" />
												</form>
											</td>
										</c:if>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:otherwise>
				</c:choose> <%-- CONTENT --%></td>
		</tr>
	</table>
	<div id="modal_form">
		<span id="modal_close">X</span>
		<c:if test="${userRole.name ne 'driver'}">
			<form id="actFlight" action="../controller" method="post">
				<input type="hidden" name="command" value="actFlight" /> <input
					name="name" placeholder="имя" required="required" /><br /> <input
					type="submit" name="regFlight"
					value="<fmt:message key="flights.createFlight" />" />
			</form>
		</c:if>
		<c:if test="${userRole.name eq 'driver'}">
			<form action="../controller" method="post">
				<p>${pageScope.flightId}</p>
				<input type="hidden" name="command" value="submitFlight" /> <input
					type="hidden" name="flightId" value="${pageScope.flightId}" /> <input
					name="condition" required="required" type="number"
					title="2 - was in use
3 - minor defects
4 - damaged
5 - to write off"
					min="2" max="5" /><br /> <input type="submit"
					value="<fmt:message key="flights.done" />" />
			</form>
		</c:if>
	</div>
	<div id="overlay"></div>
</body>
</html>