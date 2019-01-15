<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'uk' }" />
<fmt:setBundle basename="ResourceBundle.resource" />

<html>
<head>
<title><fmt:message key="login" /></title>
<link rel="stylesheet" href="style/st4.css"/>
</head>

<body>
	<table id="main-container">
		<tr>
			<td class="content center">
				<form id="login_form" action="controller" method="post">
					<input type="hidden" name="command" value="login" />
					<fieldset>
						<legend><fmt:message key="login" />   &amp;<br>   <fmt:message key="password" /> </legend>
						<input name="login" required="required" placeholder="<fmt:message key="login" />"/><br /> 
						<input type="password" name="password" required="required" placeholder="<fmt:message key="password" />" /><br />
					</fieldset>
					<br /> <input type="submit" value=<fmt:message key="login.submit" />>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>