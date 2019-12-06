<%@page import="java.util.ArrayList"%>
<%@page import="it.unipr.informatica.exercise6.model.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<title>Exercise 6 - search students</title>
	<script>
		
		function sendRequest(familyName, name){
			var request = new XMLHttpRequest();
			request.onreadystatechange = function(){
				if (request.readyState == 4 && request.status == 200){ //finito
					var text = request.responseText;
				text = "var response = " + text;
				eval(text);
				fillTable(response);
				}
			}
			request.open("post","search_students",true);        //true = asincrono, false = sincrono (default)
			request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			request.send("familyName=" + encodeURI(familyName) + "&name=" + encodeURI(name));	
		}
		
		function loaded(){
			sendRequest("","");
		}
		
		function fillTable(response){
			var table = document.getElementById("table_student");
			var html = "";
			html += "<thead>";
			html += "<tr>";
			html += "<td>ID</td><td>FamilyName</td><td>Name</td>";
			html += "</tr>";
			html += "</thead>";
			for (i = 0; i < response.length; i++){
				var student = response.students[i];
				html += "<tbody>";
				html += "<tr>";
				html += "<td>" + student.id + "<//td>";
				html += "<td>" + student.familyName + "<//td>";
				html += "<td>" + student.name + "<//td>";
				html += "</tr>";
				html += "<tbody>";
			}
			table.innerHTML = html;
		}
		
		function fieldChanged(){
			var node = document.getElementById("family_name");
			var family_name = node.value;
			node = document.getElementById("name");
			var name = node.value;
			sendRequest(family_name,name);
		}
		
		
		
	</script>
</head>
<body onload="loaded()">
	<h1>Search students</h1>
	<form id="form" action="" method="post">
		<table>
			<tr>
				<td>Family name</td>
				<td><input type="text" name="family_name" id="family_name" value="" oninput="fieldChanged()"></input></td>
				<td>Name</td>
				<td><input type="text" name="name" id="name" value="" oninput="fieldChanged()"></input></td>
			</tr>
		</table>
	</form>
	<br>
	<table id="table_student" class="table table-hover">
		<thead>
			<tr>
				<td>ID</td>
				<td>Family Name</td>
				<td>Name</td>
				<td></td>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<p><a href="index.html">Return home</a></p>
</body>
</html>