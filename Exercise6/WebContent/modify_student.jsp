<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Exercise 6 - modify</title>
<script>
</script>
</head>
<body>
	<h1>Exercise 6</h1>
	<form id="form" action="modify_student" method="post">
		<p>Id: <input id="id" name="id" type="text" value="<%= session.getAttribute("id") %>" readonly></input></p>
		<p>Family Name: <input id="family_name" name="family_name" type="text" value="<%= session.getAttribute("family_name") %>"></input></p>
		<p>Name: <input id="name" name="name" type="text" value="<%= session.getAttribute("name") %>"></input></p>
		<input  value="submit" type="submit"></input>
	</form>
	<p><a href="index.html">Return home</a></p>
</body>
</body>
</html>