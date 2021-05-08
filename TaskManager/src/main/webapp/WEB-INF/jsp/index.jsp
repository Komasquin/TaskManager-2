<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task Manager</title>
</head>
<body>
	<h2>Task Manager</h2>
	<c:if test="${pageContext.request.userPrincipal.name == null}">
	    <p>Welcome, please login or register.</p>
	    <button onclick="document.location='/login'">Login</button>
	    <button onclick="document.location='/register'">Register</button>
	</c:if>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
	    <p>Hi ${pageContext.request.userPrincipal.name}! Welcome to the task manager!</p>
		<button onclick="document.location='/create_task'">Create Task</button>
	    <button onclick="document.location='/display_tasks'">View Tasks</button>
	</c:if>
</body>
</html>