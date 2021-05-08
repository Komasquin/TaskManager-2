<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Task</title>
</head>
<body>
	<h2>Create Task:</h2>
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
	<form action="/create_task" method="post">
		<label for="tname">Task Name:</label><br>
		<input type="text" id="tname" name="tname"><br>
		<label for="sdate">Start Date:</label><br>
		<input type="date" id="sdate" name="sdate"><br>
		<label for="edate">End Date:</label><br>
		<input type="date" id="edate" name="edate"><br>
		<label for="desc">Description:</label><br>
		<textarea id="desc" name="desc" rows=10 cols=30></textarea><br>
		<label for="sev">Severity:</label><br>
		<select id="sev" name="sev">
			<option value="High">High</option>
			<option value="Medium">Medium</option>
			<option value="Low" selected>Low</option>
		</select><br><br>
		<input type="submit" name="submit" value="Create Task"/>
		<button onclick="document.location='/display_tasks';return false;">Cancel</button>
	</form>
</body>
</html>
