<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
	<h2>Please register to proceed.</h2>
	<div id="errors" style="color: red; font-weight: bold;"></div>
	<script>
		var queryString = window.location.search;
		var urlParams = new URLSearchParams(queryString);
		if (urlParams.has('error')) {
			var errors = urlParams.get('error');
			errors = atob(errors);
			var errorElement = document.getElementById('errors');
			errorElement.innerHTML = errors;
		}
	</script>
	<form name='register' action="/register" method='POST'>
		<table>
			<tr>
				<td>Name:</td>
				<td><input type='text' name='name' value=''></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type='text' name='email' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password'/></td>
			</tr>
			<tr>
				<td>Confirm Password:</td>
				<td><input type='password' name='confirm_password'/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input name="submit" type="submit" value="Register"/>
					<button onclick="document.location='/';return false;">Cancel</button>
				</td>
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</body>
</html>