<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set var="title" value="settings" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<table id="main-container">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<tr>
			<td class="content">
		<!-- 	<script type="text/javascript">
			function checkForm(form)
			  {
				if(form.fullName.value == "") {
				      alert("Error: Your name cannot be blank!");
				      form.fullName.focus();
				      return false;
				    }	
				if(form.oldPass.value != sessionStorage['user'].getPassword()) {
				      alert("Error: Your old password isn't correct!");
				      form.oldPass.focus();
				      return false;
				    }	
				if(form.newPass.value != form.repeatPass.value) {
				      alert("Error: Your new passwords must be the same!");
				      form.newPass.focus();
				      return false;
				    }	
				return true;
				
			  }
			
			</script> -->
				<form id="settings_form" action="../controller" method="post" onsubmit="return checkForm(this);">

					<input type="hidden" name="command" value="viewSettings" />
					<div>
						<p>
							<fmt:message key="settings.language" />
						</p>
						<select name="lang">
							<option value="en">English</option>
							<option value="uk">Українська</option>
							<option value="fi">Finnish</option>
						</select>
					</div>
					<hr>
					<%-- <div>
						<p>
							<fmt:message key="settings.yourname" />
						</p>
						<input name="fullName" value="${user.fullName}">
					</div>
					<hr>
					<div>
						:::<input type="password" pattern="{,20}" name="oldPass" placeholder="старый пароль" />
						<input type="password" pattern="{,20}" name="newPass" placeholder="новый пароль" />
						<input type="password" pattern="{,20}" name="repeatPass" placeholder="повторите пароль" />
					</div> --%>
					<input type="submit" value=<fmt:message key="settings.submit" />><br />
					
				</form>
			</td>
		</tr>
	</table>
</body>
</html>