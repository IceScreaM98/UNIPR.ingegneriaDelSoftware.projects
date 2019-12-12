<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="style.css" />
	<meta charset="UTF-8">
	<title>Exercise 6</title>
</head>
<body>
	<h1>Exercise 6</h1>
	<p>Counter value is:
		<%
		int counter = (int) session.getAttribute("counter");
		out.println(counter);
		%>
	</p>
	<p><a href="counter2">Click here</a></p>
	<p><a href="index.html">Return home</a></p>
</body>
</html>