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
			var node = document.getElementById("id_delete");
			node.value = id;
			node = document.getElementById("form_delete");
			node.submit();
		}
		
		function modify_student(id, familyName, name){
			var node = document.getElementById("id_modify");
			node.value = id;
			node = document.getElementById("family_name_modify");
			node.value = familyName;
			node = document.getElementById("name_modify");
			node.value = name;
			node = document.getElementById("form_modify");
			node.submit();
		}
	</script>
</head>
<body>
	<h1 id="grad1">Exercise 6</h1>
	<p><a href="new_student.jsp">Add student</a></p>
	<h2>List of students</h2>
	<div id="container">
		<table class="student-table">
			<thead>
				<tr>
					<td>ID</td>
					<td>Family Name</td>
					<td>Name</td>
					<td></td>
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
					<td><a href="javascript:modify_student(<%= student.getId() %>, '<%= student.getFamilyName() %>', '<%= student.getName() %>')">Edit</a></td>
					<td><a href="javascript:delete_student(<%= student.getId() %>)">Delete</a></td>					
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
	<form id="form_delete" action="delete_student" method="post" hidden="hidden">
		<input id="id_delete" name="id_delete" type="text" value="valore"></input>
		<input id="button_delete" type="submit" value="invia"></input>
	</form>
	<form id="form_modify" action="get_student" method="post" hidden="hidden">
		<input id="id_modify" name="id_modify" type="text" value="valore"></input>
		<input id="family_name_modify" name="family_name_modify" type="text" value="valore"></input>
		<input id="name_modify" name="name_modify" type="text" value="valore"></input>
		<input id="button_modify" type="submit" value="invia"></input>
	</form>
	<p><a href="/Exercise6/index.html">Return home</a></p>
</body>
</html>