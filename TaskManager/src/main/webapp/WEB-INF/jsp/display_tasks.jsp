<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display Tasks</title>
<style>
table, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<h2>All Tasks:</h2>
	<c:if test="${not empty notice}">
		<div style="color: red; font-weight: bold;">${notice}</div>
	</c:if>
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
			<th>Action</th>
		</tr>
		<c:forEach items="${tasks}" var="task" varStatus="count">
			<tr id="${count.index}">
				<td>${task.id}</td>
				<td>${task.name}</td>
				<td>${task.startDate}</td>
				<td>${task.endDate}</td>
				<td>${task.description}</td>
				<td>${task.email}</td>
				<td>${task.severity}</td>
				<td>${task.user.name}</td>
				<td>
					<button onclick="document.location='/update_task?id=${task.id}'">Update</button>
					<button onclick="document.location='/delete_task?id=${task.id}'">Delete</button>
				</td>
			</tr>
		</c:forEach>
	</table><br>
	<button onclick="document.location='/create_task'">Create Task</button>
	<button onclick="document.location='/logout'">Log Out</button>
</body>
</html>
