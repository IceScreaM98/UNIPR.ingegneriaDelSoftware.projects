<%@page import="java.util.ArrayList"%>
<%@page import="it.unipr.informatica.exercise6.model.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exercise 6</title>
</head>
<body>
	<h1>Exercise 6</h1>
	<h2>List of students</h2>
	<table>
		<thead>
			<tr>
				<td>ID</td>
				<td>Family Name</td>
				<td>Name</td>
			</tr>
		</thead>
		<tbody>
			<%
			@SuppressWarnings("unchecked")
			List<Student> allStudents = (List<Student>) session.getAttribute("allStudents");
			if (allStudents == null)
				allStudents = new ArrayList<Student>();
			for (Student student : allStudents){
			%>
			<tr>
				<td><% out.print(student.getId()); %></td>
				<td><%= student.getFamilyName() %></td>
				<td><%= student.getName() %></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<p><a href="index.html">Return home</a></p>
</body>
</html>