<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Task</title>
<style>
table, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<h2>Delete Task:</h2>
	<table>
		<tr>
			<th>Task ID</th>
			<th>Task Name</th>
			<th>Start Date</th>
			<th>End Date</th>
			<th>Description</th>
			<th>Email</th>
			<th>Severity</th>
			<th>User</th>
		</tr>
		<tr>
			<td>${task.id}</td>
			<td>${task.name}</td>
			<td>${task.startDate}</td>
			<td>${task.endDate}</td>
			<td>${task.description}</td>
			<td>${task.email}</td>
			<td>${task.severity}</td>
			<td>${task.user.name}</td>
		</tr>
	</table><br>
	<form action="/delete_task" method="post">
		Are you sure you want to delete this task?
		<input type="submit" name="submit" value="Delete Task"/>
		<button onclick="document.location='/display_tasks';return false;">Cancel</button>
	</form>
</body>
</html>
