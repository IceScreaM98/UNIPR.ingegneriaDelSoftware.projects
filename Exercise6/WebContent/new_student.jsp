<%@page import="java.util.ArrayList"%>
<%@page import="it.unipr.informatica.exercise6.model.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="style.css" />
	<meta charset="UTF-8">
	<title>Exercise 6 - new student</title>
	<script>
	</script>
</head>
<body>
	<h1>Exercise 6 - new student</h1>

	<form id="form" action="add_student" method="post">
		<p>Family Name: <input id="family_name" name="family_name" type="text" value=""></input></p>
		<p>Name: <input id="name" name="name" type="text" value=""></input></p>
		<input id="button" type="submit" value="invia"></input>
	</form>
	<p><a href="index.html">Return home</a></p>
</body>
</html>