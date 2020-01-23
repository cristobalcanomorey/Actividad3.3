<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="aplicacion.vista.NavBar"%>
<%@page import="aplicacion.modelo.pojo.Usuario" %>
<%@page import="aplicacion.vista.Navegacion"%>
<%!Usuario usuario = null; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Historial</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%
	usuario = (Usuario) request.getAttribute("usuario");
	%>
	<h1>Historial</h1>
	<table>
		<thead>
			<tr>
				<th>Estatura</th>
				<th>Peso</th>
				<th>Fecha</th>
				<th>IMC</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1.9</td>
				<td>101.0</td>
				<td>2018-12-06 01:00:00</td>
				<td>27,98</td>
			</tr>
		</tbody>
	</table>
</body>
</html>