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
	<title>Exercise 6</title>
	<script>
		function delete_student(id){
			var ok = confirm("Sei sicuro?");
			if (!ok) return;
			var node = document.getElementById("id");
			node.value = id;
			node = document.getElementById("form");
			node.submit();
		}
	</script>
</head>
<body>
	<h1>Exercise 6</h1>
	<p><a href="new_student.jsp">Aggiungi un nuovo studente</a></p>
	<h2>List of students</h2>
	<div id="container">
		<table class="student-table">
			<thead>
				<tr>
					<td>ID</td>
					<td>Family Name</td>
					<td>Name</td>
					<td></td>
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
					<td><a href="javascript:delete_student(<%= student.getId() %>)">Cancella</a></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
	<form id="form" action="delete_student" method="post" hidden>
		<input id="id" name="id" type="text" value="valore"></input>
		<input id="button" type="submit" value="invia"></input>
	</form>
	<p><a href="/Exercise6/index.html">Return home</a></p>
</body>
</html>