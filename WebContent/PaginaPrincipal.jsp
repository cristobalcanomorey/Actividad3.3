<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="aplicacion.vista.NavBar"%>
<%@page import="aplicacion.modelo.pojo.Usuario" %>
<%@page import="aplicacion.vista.Navegacion"%>
<%!Usuario usuario = null; %>
<%!String resul; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IMC</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%
	usuario = (Usuario) request.getAttribute("usuario");
	resul = (String) request.getAttribute("imc");
	%>
	<%
	out.print(new Navegacion(usuario).toString());
	%>
	<h1>Calcular IMC</h1>
	<form method="POST" action="Principal">
		<p>Peso</p>
		<input name="peso" step="0.01" type="number">
		<p>Altura</p>
		<input name="altura" step="0.01" type="number">
		<input type="submit" value="Calcular">
	</form>
	<div>
	<%
	if(resul != null){
		out.print(resul);
	}
	%>
	</div>
</body>
</html>