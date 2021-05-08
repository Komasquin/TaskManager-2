<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<h2>Please login to proceed.</h2>
	<!--
	<p>For testing purposes, the default login is 'user' and
		'password'.</p>
	-->
	<c:if test="${not empty notice}">
		<div style="color: red; font-weight: bold;">${notice}</div>
	</c:if>
	<form name='login' action="/login" method='POST'>
		<table>
			<tr>
				<td>Email:</td>
				<td><input type='text' name='email' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password'/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input name="submit" type="submit" value="Login"/>
					<button onclick="document.location='/';return false;">Cancel</button>
				</td>
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</body>
</html>