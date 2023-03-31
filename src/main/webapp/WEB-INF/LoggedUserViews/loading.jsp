<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="refresh"
	content="12; url=http://localhost:8080/dashboard">
<title>Loading</title>
<link rel="stylesheet" type="text/css" href="/css/loading.css" />
</head>
<body>

	<h1>Creating the API...</h1>
	<div id="loading"></div>

	<div>
		<h1>Here is a programming joke while you wait.</h1>

		<h2 style="">
			<c:out value="${question }"></c:out>
		</h2>
		<h2>
			<c:out value="${punchline }"></c:out>
		</h2>
	</div>


</body>
</html>