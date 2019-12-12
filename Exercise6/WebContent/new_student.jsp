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
		function add_student(){
			var node = document.getElementById("family_name");
			var s = node.value;
			if (s.length == 0 || s.length > 50){
				alert("Invalid family name");
				return;
			}
			node = document.getElementById("name");
			s = node.value;
			if (s.length == 0 || s.length > 50){
				alert("Invalid name");
				return;
			}
			node = document.getElementById("form");
			node.submit();
		}
	</script>
</head>
<body>
	<h1>Exercise 6</h1>

	<form id="form" action="add_student" method="post">
		<p>Family Name: <input id="family_name" name="family_name" type="text" value=""></input></p>
		<p>Name: <input id="name" name="name" type="text" value=""></input></p>
		<a href="javascript:add_student()">Add student</a>
	</form>
	<p><a href="index.html">Return home</a></p>
</body>
</html>